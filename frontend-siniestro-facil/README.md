# Siniestro Fácil — Frontend

Frontend React + Vite en español. Incluye panel de siniestros, búsqueda y formulario de registro.

## Requisitos
- Node.js 20+
- npm
- Docker (opcional, para despliegue)

## Ejecutar localmente
```bash
cd frontend-siniestro-facil
npm install
cp .env.example .env
npm run dev
```
Abrir la URL indicada por Vite (por defecto http://localhost:5173).

## Modo demostración
Si `VITE_API_BASE_URL` está vacío, se muestran datos ficticios y el formulario crea registros únicamente en memoria. No se transmiten datos al backend.

## Integración backend
Configurar `VITE_API_BASE_URL` con la URL base del servicio. El adaptador actual espera provisionalmente:
- `GET /api/siniestros` → array de siniestros o objeto `{ items: [] }`.
- `POST /api/siniestros` → acepta JSON con vehículo, placa, fecha, tipo, descripción y estado.

**Importante:** confirmar rutas, autenticación, nombres de campos, respuestas y política CORS con el backend existente antes de habilitar la integración real. Vite incorpora las variables `VITE_*` durante la compilación; no guardar secretos en ellas.

## Build y validación
```bash
npm run build
```

## Docker
```bash
docker build -t siniestro-facil-frontend .
docker run --rm -p 8080:8080 siniestro-facil-frontend
```
Abrir http://localhost:8080. La URL del backend debe configurarse durante el build mediante `--build-arg VITE_API_BASE_URL=https://URL-DEL-BACKEND`.

## GCP Cloud Run
Ver `../docs-frontend/03_gcp_pruebas.md` para los comandos de despliegue.
