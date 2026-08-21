# Arquitectura Backend — Siniestro Fácil

## 1. Propósito

Definir la arquitectura técnica del backend de Siniestro Fácil sobre Google Cloud, manteniendo el dominio, requisitos y modelo físico aprobados como fuentes de verdad.

## 2. Baseline tecnológico decidido

| Capacidad | Plataforma |
|---|---|
| Identidad | Firebase Authentication |
| API | Google Cloud API Gateway |
| Backend | Google Cloud Run |
| Persistencia | Cloud SQL for PostgreSQL |
| Evidencias/objetos | Cloud Storage |
| Secretos | Secret Manager |
| Imágenes de contenedor | Artifact Registry |
| CI/CD | Cloud Build |
| Logs | Cloud Logging |
| Métricas | Cloud Monitoring |
| Desarrollo/despliegue | Cloud Shell + Google Cloud |

Proyecto GCP de desarrollo: `brave-inn-368220`.

Infraestructura de datos existente:
`dmcappasistidoia` → PostgreSQL 18 → `dmcsiniestrofacil` → esquema `siniestro_facil`.

## 3. Arquitectura lógica

```text
Canales / Frontend
        |
        v
Firebase Authentication
        |
        | Firebase ID Token
        v
Google Cloud API Gateway
        |
        v
Google Cloud Run
  API / Controllers
        |
        v
Application / Use Cases
        |
        v
Domain
  |       |        |
  v       v        v
Siniestro Evidencia Antifraude
        |
        v
Ports / Interfaces
   |          |             |
   v          v             v
Persistence Integrations  Storage
   |          |             |
   v          +-- Pólizas  +-- Evidencias
Cloud SQL    +-- Asistencia
PostgreSQL   +-- Talleres
             +-- Mapas
             +-- Mensajería
             +-- Pagos
```

## 4. Responsabilidades

### Firebase Authentication
Autentica al usuario y proporciona el Firebase ID Token. La identidad obtenida del token no será sustituida por un identificador enviado de confianza desde el cliente.

### API Gateway
Punto de entrada de las APIs públicas y aplicación de la configuración de API/OpenAPI definida para el backend.

### Cloud Run
Ejecuta el backend stateless. Valida la identidad recibida, aplica autorización funcional, ejecuta casos de uso y comunica con persistencia/integraciones mediante puertos.

### Cloud SQL
Persistencia transaccional del modelo físico aprobado en `siniestro_facil`.

### Cloud Storage
Almacenamiento de objetos/evidencias cuando el caso de uso requiera persistencia de archivos. La referencia al objeto y su trazabilidad deben mantenerse en el dominio/persistencia aprobados.

### Secret Manager
Credenciales y secretos de integración. No se deben almacenar secretos en código, repositorio, imagen de contenedor ni configuración versionada.

### Cloud Build / Artifact Registry / Cloud Run
Cadena de construcción y despliegue: GitHub → Cloud Build → imagen en Artifact Registry → Cloud Run.

## 5. Capas de aplicación

### API
Validación sintáctica, serialización, códigos HTTP, correlación y extracción de identidad del contexto autenticado.

### Application
Orquesta casos de uso, transacciones, autorización y publicación de eventos cuando corresponda.

### Domain
Reglas de negocio, invariantes y transiciones de estado. No depende de PostgreSQL ni de proveedores externos.

### Infrastructure
Repositorios PostgreSQL, clientes de integraciones, acceso a Cloud Storage y mecanismos de mensajería definidos posteriormente.

## 6. Agregados iniciales

- Siniestro: expediente, estado, participantes y relaciones.
- Evidencia: original, hash, metadatos y versiones.
- Asistencia: solicitud y proveedor.
- Evaluación: inspección, taller y presupuesto.
- Antifraude: regla/modelo, alerta, señal y revisión.
- Autorización/Pago: decisión y resultado.

La separación es arquitectónica; no implica crear nuevas tablas fuera del modelo físico aprobado.

## 7. Persistencia

PostgreSQL utiliza el esquema `siniestro_facil`. El modelo físico existente es la fuente de verdad para tablas, columnas, PK/FK y restricciones estructurales. No se agregarán claves únicas, catálogos cerrados o triggers sin una decisión sustentada.

## 8. Seguridad

Firebase Authentication resuelve autenticación. La autorización funcional permanece en el backend mediante roles/claims y reglas de acceso. La matriz definitiva de permisos debe quedar cerrada antes del endpoint productivo.

## 9. Observabilidad

Toda operación relevante debe permitir correlacionar request, usuario/actor técnico, siniestro, evento, integración externa, resultado y error mediante identificadores de correlación definidos por la especificación de idempotencia.

## 10. Manejo de errores

Clasificación mínima:
- error de validación;
- recurso no encontrado;
- transición inválida;
- autorización insuficiente;
- conflicto/idempotencia;
- dependencia externa no disponible;
- error persistente;
- error interno.

## 11. Decisiones pendientes

Quedan como decisiones de refinamiento, no como sustitución del baseline:
- proveedor concreto de mapas;
- mecanismo de mensajería/eventos;
- contratos reales de terceros;
- permisos definitivos por rol;
- política definitiva de almacenamiento/retención de objetos.

## 12. Criterio de salida

La arquitectura queda lista para implementación cuando cada HU del sprint tenga identificado su caso de uso, API, servicio de aplicación, componente de dominio, persistencia, integración requerida y controles de seguridad.
