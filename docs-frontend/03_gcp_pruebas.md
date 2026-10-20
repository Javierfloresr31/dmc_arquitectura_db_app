# Tarea 3 — Ambiente de pruebas en GCP

## Arquitectura de prueba propuesta
- Frontend: contenedor Nginx desplegado en Cloud Run.
- Backend: servicio existente en GCP; su URL y endpoints deben confirmarse.
- Registro de contenedor: Artifact Registry.
- Región: preferir la misma región del backend si está disponible.
- Exposición: servicio público solo si la política del proyecto lo permite.

No se ha creado infraestructura en GCP desde este documento. Los siguientes comandos deben ejecutarse con una cuenta autorizada para el proyecto.

## 1. Definir variables
Reemplazar los valores de ejemplo:
```bash
export PROJECT_ID="TU_PROJECT_ID"
export REGION="us-central1"
export REPOSITORY="siniestro-facil"
export SERVICE="siniestro-facil-frontend"
export TAG="0.1.0"
export API_BASE_URL="https://URL-REAL-DEL-BACKEND"
gcloud auth login
gcloud config set project "$PROJECT_ID"
gcloud config set run/region "$REGION"
```

## 2. Habilitar APIs
```bash
gcloud services enable run.googleapis.com artifactregistry.googleapis.com cloudbuild.googleapis.com
```

## 3. Crear repositorio Docker (una sola vez)
```bash
gcloud artifacts repositories create "$REPOSITORY" \
  --repository-format=docker \
  --location="$REGION" \
  --description="Imágenes para pruebas de Siniestro Fácil"
```
Si el repositorio ya existe, omitir este paso.

## 4. Construir y publicar imagen con Cloud Build
Ejecutar desde la raíz del repositorio:
```bash
gcloud builds submit . \
  --config=frontend-siniestro-facil/cloudbuild.yaml \
  --project="$PROJECT_ID" \
  --substitutions="_REGION=$REGION,_REPOSITORY=$REPOSITORY,_SERVICE=$SERVICE,_TAG=$TAG,_VITE_API_BASE_URL=$API_BASE_URL"
``

Para publicar solo la demo, dejar `API_BASE_URL=""`. La URL del backend se incorpora en tiempo de compilación; no incluir credenciales ni secretos en variables `VITE_*`.

## 5. Desplegar en Cloud Run
```bash
gcloud run deploy "$SERVICE" \
  --image "$REGION-docker.pkg.dev/$PROJECT_ID/$REPOSITORY/$SERVICE:$TAG" \
  --region "$REGION" \
  --platform managed \
  --port 8080 \
  --allow-unauthenticated
``

Usar `--allow-unauthenticated` solo si el frontend debe ser público y el responsable de GCP lo autoriza. Para un entorno corporativo, evaluar acceso restringido.

## 6. Verificar el despliegue
```bash
gcloud run services describe "$SERVICE" --region "$REGION" --format="value(status.url)"
gcloud run services logs read "$SERVICE" --region "$REGION" --limit=50
``
Abrir la URL devuelta y verificar la interfaz.

## 7. Condiciones previas para integración real
- Confirmar URL del backend.
- Confirmar rutas GET/POST y esquema JSON.
- Configurar CORS del backend para el dominio de Cloud Run.
- Definir autenticación/autorización.
- Si el backend es privado, definir conectividad e identidad de servicio adecuadas.
- No exponer secretos en el frontend.

## Estado
Los comandos y el archivo Cloud Build están preparados. El despliegue no está ejecutado ni verificado porque no se han proporcionado el ID de proyecto GCP, permisos, región real ni URL del backend.