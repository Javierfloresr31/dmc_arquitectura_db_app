# Integración frontend + backend en GCP

## Arquitectura de esta etapa
- Frontend React + Vite servido por el contenedor Node.js.
- BFF en el mismo servicio Cloud Run, bajo el mismo origen del frontend.
- El navegador invoca `/api/v1/siniestros`; el BFF reenvía solo rutas permitidas al backend privado.
- El BFF obtiene un ID token mediante su identidad de ejecución de Cloud Run.
- Backend existente: `siniestro-facil-backend`, región `us-central1`.
- Firebase Authentication queda fuera de esta etapa.

## Requisitos de seguridad
1. Mantener privado `siniestro-facil-backend`.
2. Crear una cuenta de servicio dedicada para el BFF y concederle únicamente `roles/run.invoker` sobre el backend.
3. Configurar `BACKEND_URL` como variable de ejecución del BFF, no como variable `VITE_*`.
4. Proteger el frontend+BFF con Identity-Aware Proxy (IAP) y autorizar solo al equipo de pruebas.
5. No conceder invocación anónima al BFF mientras la API no tenga autenticación de aplicación.

## Construcción y despliegue
El archivo `frontend-siniestro-facil/cloudbuild.yaml` construye la imagen integrada. Para el entorno real, compilar con `VITE_DEMO_MODE=false` y una base API vacía o `/`, para que el navegador llame al mismo origen. En Cloud Run, configurar `BACKEND_URL` con la URL del servicio backend.

Antes de habilitar el acceso al equipo, verificar que la identidad del BFF puede invocar el backend, que IAP exige inicio de sesión y que la API no es accesible de forma anónima. Si IAP no está disponible, detener el despliegue hasta definir una alternativa de acceso.

## Estado
La implementación de código está en la rama `feat/siniestro-facil-bff` y en el PR asociado. El despliegue en GCP y la conectividad real siguen pendientes de ejecución y verificación. No se debe considerar completado hasta validar build, despliegue, acceso protegido y operaciones GET/POST reales.