# Tarea 3 — Ambiente de pruebas en GCP

## Arquitectura de prueba propuesta
- Frontend: contenedor Nginx desplegado en Cloud Run.
- Backend: servicio existente en GCP, cuya URL y endpoints deben confirmarse.
- Registro de contenedor: Artifact Registry.
- Región: usar la misma región del backend si está disponible, para reducir latencia.
- Exposición: servicio de frontend público solo si la política del proyecto lo permite.

No se ha creado infraestructura en GCP desde este documento; los siguientes comandos son una guía de ejecución. Se necesita una cuenta con permisos sobre el proyecto.

## 1. Definir variables
Reemplazar los valores de ejemplo:
```bash
export PROJECT_ID="TU_PROJECT_ID"
export REGION="us-central1"
export REPOSITORY="siniestro-facil"
export SERVICE="siniestro-facil-frontend"
export API_BASE_URL="https://URL-REAL-DEL-BACKEND"
gcloud config set project "$PROJECT_ID"
gcloud config set run/region "$REGION"
gcloud auth login
gcloud auth configure-docker "$REGION-docker.pkg.dev"
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
Si ya existe, no repetir este paso.

## 4. Construir y publicar imagen
Desde la raíz del repositorio:
```bash
gcloud builds submit ./frontend-siniestro-facil \
  --tag "$REGION-docker.pkg.dev/$PROJECT_ID/$REPOSITORY/$SERVICE:0.1.0" \
  --substitutions=_VITE_API_BASE_URL="$API_BASE_URL"
```

**Nota:** el comando anterior necesita un `cloudbuild.yaml` que consuma la sustitución. Para mantener el proceso simple, alternativa recomendada desde el directorio frontend:
```bash
cd frontend-siniestro-facil
gcloud builds submit --tag "$REGION-docker.pkg.dev/$PROJECT_ID/$REPOSITORY/$SERVICE:0.1.0" \
  --project "$PROJECT_ID" \
  --region "$REGION" \
  --gcs-log-dir "gs://$PROJECT_ID-cloudbuild-logs"
``
Esta alternativa usa los valores definidos en Dockerfile y no inyecta `API_BASE_URL`; para activar integración, crear una compilación con el argumento de build correspondiente o usar Cloud Build config.

## 5. Desplegar en Cloud Run
```bash
gcloud run deploy "$SERVICE" \
  --image "$REGION-docker.pkg.dev/$PROJECT_ID/$REPOSITORY/$SERVICE:0.1.0" \
  --region "$REGION" \
  --platform managed \
  --port 8080 \
  --allow-unauthenticated
``

Usar `--allow-unauthenticated` solo si el frontend debe ser público y el responsable de GCP lo autoriza. En un entorno corporativo, evaluar acceso restringido.

## 6. Verificar despliegue
```bash
gcloud run services describe "$SERVICE" --region "$REGION" --format="value(status.url)"
``
Abrir la URL devuelta y comprobar carga de la interfaz. Revisar logs:
```bash
gcloud run services logs read "$SERVICE" --region "$REGION" --limit=50
``

## 7. Condiciones previas para integración real
- Confirmar URL del backend.
- Confirmar rutas GET/POST y esquema JSON.
- Configurar CORS del backend para el dominio de Cloud Run.
- Definir autenticación/autorización.
- No incluir secretos en variables `VITE_*`.
- Si backend requiere identidad de servicio o acceso privado, definir conectividad y permisos adecuados.

## Limitación
No puedo declarar que el ambiente fue creado ni que el despliegue funcionó: requiere ejecutar estos comandos con acceso al proyecto GCP correcto. Los identificadores reales del proyecto, la región y la URL del backend no están confirmados aquí.
