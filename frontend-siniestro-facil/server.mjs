import { createServer } from 'node:http';
import { readFile, stat } from 'node:fs/promises';
import path from 'node:path';
import { fileURLToPath } from 'node:url';
import { GoogleAuth } from 'google-auth-library';

const __dirname = path.dirname(fileURLToPath(import.meta.url));
const DIST_DIR = path.join(__dirname, 'dist');
const PORT = Number(process.env.PORT || 8080);
const BACKEND_URL = (process.env.BACKEND_URL || '').replace(/\\/+$/, '');
const auth = new GoogleAuth();
const mimeTypes = {
  '.html': 'text/html; charset=utf-8',
  '.js': 'text/javascript; charset=utf-8',
  '.css': 'text/css; charset=utf-8',
  '.svg': 'image/svg+xml',
  '.png': 'image/png',
  '.jpg': 'image/jpeg',
  '.jpeg': 'image/jpeg',
  '.ico': 'image/x-icon',
  '.woff': 'font/woff',
  '.woff2': 'font/woff2',
  '.json': 'application/json; charset=utf-8'
};

function send(res, status, body, contentType = 'application/json; charset=utf-8') {
  res.writeHead(status, { 'content-type': contentType, 'cache-control': 'no-store' });
  res.end(body);
}

async function readRequestBody(req, maxBytes = 1024 * 1024) {
  const chunks = [];
  let size = 0;
  for await (const chunk of req) {
    size += chunk.length;
    if (size > maxBytes) throw new Error('REQUEST_TOO_LARGE');
    chunks.push(chunk);
  }
  return Buffer.concat(chunks);
}

function isAllowedApiRequest(method, pathname) {
  if (pathname === '/api/v1/siniestros') return method === 'GET' || method === 'POST';
  if (/^\\/api\\/v1\\/siniestros\\/\\d+$/.test(pathname)) return method === 'GET';
  if (/^\\/api\\/v1\\/siniestros\\/\\d+\\/transiciones$/.test(pathname)) return method === 'POST';
  return false;
}

async function proxyToBackend(req, res, requestUrl) {
  if (!BACKEND_URL) return send(res, 503, JSON.stringify({ error: 'BACKEND_URL_NOT_CONFIGURED' }));
  if (!isAllowedApiRequest(req.method, requestUrl.pathname)) {
    return send(res, 404, JSON.stringify({ error: 'ENDPOINT_NOT_ALLOWED' }));
  }

  let body;
  try {
    if (!['GET', 'HEAD'].includes(req.method)) body = await readRequestBody(req);
  } catch (error) {
    return send(res, error.message === 'REQUEST_TOO_LARGE' ? 413 : 400, JSON.stringify({ error: 'INVALID_REQUEST_BODY' }));
  }

  try {
    const idTokenClient = await auth.getIdTokenClient(BACKEND_URL);
    const authHeaders = await idTokenClient.getRequestHeaders(BACKEND_URL);
    const authorization = new Headers(authHeaders).get('authorization');
    const headers = new Headers();
    if (authorization) headers.set('authorization', authorization);
    for (const name of ['content-type', 'accept', 'x-correlation-id', 'idempotency-key']) {
      const value = req.headers[name];
      if (value) headers.set(name, Array.isArray(value) ? value.join(',') : value);
    }

    const target = new URL(requestUrl.pathname + requestUrl.search, BACKEND_URL);
    const upstream = await fetch(target, {
      method: req.method,
      headers,
      body: body && body.length ? body : undefined,
      redirect: 'manual',
      signal: AbortSignal.timeout(30000)
    });
    const responseBody = Buffer.from(await upstream.arrayBuffer());
    const responseHeaders = {
      'cache-control': 'no-store',
      'content-type': upstream.headers.get('content-type') || 'application/json; charset=utf-8'
    };
    const location = upstream.headers.get('location');
    if (location) responseHeaders.location = location;
    res.writeHead(upstream.status, responseHeaders);
    res.end(responseBody);
  } catch (error) {
    console.error('Backend proxy failed:', error.message);
    send(res, 502, JSON.stringify({ error: 'BACKEND_UNAVAILABLE' }));
  }
}

async function serveStatic(req, res, pathname) {
  const safePath = decodeURIComponent(pathname).replace(/^\\/+/, '');
  let filePath = path.resolve(DIST_DIR, safePath || 'index.html');
  if (!filePath.startsWith(DIST_DIR + path.sep) && filePath !== path.join(DIST_DIR, 'index.html')) {
    return send(res, 400, JSON.stringify({ error: 'INVALID_PATH' }));
  }

  try {
    const info = await stat(filePath);
    if (info.isDirectory()) filePath = path.join(filePath, 'index.html');
    const data = await readFile(filePath);
    const contentType = mimeTypes[path.extname(filePath).toLowerCase()] || 'application/octet-stream';
    res.writeHead(200, { 'content-type': contentType, 'cache-control': filePath.endsWith('index.html') ? 'no-cache' : 'public, max-age=3600' });
    res.end(data);
  } catch {
    if (path.extname(pathname)) return send(res, 404, JSON.stringify({ error: 'NOT_FOUND' }));
    try {
      const html = await readFile(path.join(DIST_DIR, 'index.html'));
      res.writeHead(200, { 'content-type': 'text/html; charset=utf-8', 'cache-control': 'no-cache' });
      res.end(html);
    } catch {
      send(res, 500, JSON.stringify({ error: 'FRONTEND_BUILD_NOT_FOUND' }));
    }
  }
}

createServer(async (req, res) => {
  const requestUrl = new URL(req.url || '/', 'http://localhost');
  if (requestUrl.pathname === '/healthz') return send(res, 200, JSON.stringify({ status: 'ok' }));
  if (requestUrl.pathname === '/api' || requestUrl.pathname.startsWith('/api/')) {
    return proxyToBackend(req, res, requestUrl);
  }
  if (!['GET', 'HEAD'].includes(req.method)) return send(res, 405, JSON.stringify({ error: 'METHOD_NOT_ALLOWED' }));
  return serveStatic(req, res, requestUrl.pathname);
}).listen(PORT, '0.0.0.0', () => {
  console.log(`Siniestro Fácil frontend+BFF listening on port ${PORT}`);
});
