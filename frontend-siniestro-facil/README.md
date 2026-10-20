# Siniestro Fácil — Frontend + BFF

Frontend React + Vite servido por Node.js en Cloud Run. El mismo origen atiende la interfaz y expone un proxy acotado para el backend privado.

## Ejecutar localmente en modo demo
```bash
npm install
cp .env.example .env
npm run dev
```
Con `VITE_DEMO_MODE=true`, se muestran datos ficticios y no se envían peticiones al backend.

## Integración con el backend
El contrato utilizado se basa en `SiniestroController` y `CrearSiniestroRequest`:
- `GET /api/v1/siniestros`
- `POST /api/v1/siniestros`
- `GET /api/v1/siniestros/{id}`
- `POST /api/v1/siniestros/{id}/transiciones?estado=...`

El formulario envía `poliza.numero`, `vehiculo.placa`, `fecha`, `ubicacionAproximada`, `tipoEvento` y `danosAparentes`. El servidor `server.mjs` sirve los archivos estáticos y reenvía solo los endpoints permitidos al backend usando un ID token de Google obtenido con la identidad de servicio de Cloud Run. No se colocan credenciales en el navegador.

## Seguridad para pruebas
Mantener privado el backend. Usar una cuenta de servicio dedicada al frontend+BFF y concederle solo `roles/run.invoker` sobre el backend. Proteger el frontend+BFF con Identity-Aware Proxy (IAP), autorizando únicamente a los usuarios/grupos de prueba. No permitir acceso anónimo al BFF. Firebase queda fuera de esta etapa.

## Build
```bash
npm run build
npm start
```
`npm start` sirve `dist` en el puerto 8080. En Cloud Run, configurar `BACKEND_URL` como variable de entorno de ejecución; no usar una variable `VITE_*` para esa URL.

## Docker
```bash
docker build -t siniestro-facil-frontend .
docker run --rm -p 8080:8080 -e BACKEND_URL=https://URL-DEL-BACKEND siniestro-facil-frontend
```

Consultar `../docs-frontend/03_gcp_pruebas.md` antes del despliegue. El código debe compilarse y la infraestructura verificarse antes de declarar la integración operativa.
