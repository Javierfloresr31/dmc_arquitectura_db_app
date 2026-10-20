# Tarea 1 — Código frontend e interfaces

## Estado
Implementación inicial creada en `frontend-siniestro-facil/`.

## Interfaces incluidas
1. Panel con contadores de siniestros.
2. Listado y búsqueda por número, vehículo o estado.
3. Formulario de reporte básico.
4. Mensajes de estado, errores y modo demostración.
5. Diseño adaptable a escritorio y móvil.

## Tecnologías
- React 18
- Vite 6
- Lucide React para iconos
- CSS responsive sin framework adicional
- Nginx para servir la compilación en contenedor

## Alcance no implementado
- Inicio de sesión real.
- Autorización de reportante.
- Carga de imágenes/evidencias.
- Validación de póliza/cobertura.
- Vista de detalle conectada a API.
- Gestión de roles.

Estos puntos requieren contrato backend y decisiones de autenticación confirmadas. No se simulan como funcionalidad productiva.

## Integración provisional
La URL se configura con `VITE_API_BASE_URL`. Se asumen provisionalmente `GET /api/siniestros` y `POST /api/siniestros`; verificar estos endpoints con el backend antes de activar integración real.
