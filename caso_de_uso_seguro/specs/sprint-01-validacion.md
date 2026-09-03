# Sprint 01 — Implementación y validación

## Estado
IMPLEMENTADO EN BRANCH — pendiente ejecución en Cloud Shell/GCP y revisión por PR.

## Alcance implementado
- `POST /api/v1/siniestros`
- `GET /api/v1/siniestros/{id}`
- `GET /api/v1/siniestros`
- `POST /api/v1/siniestros/{id}/transiciones`
- creación de participante/reportante cuando se recibe en el request
- estado inicial `REPORTADO`
- historial de estado inicial
- auditoría del expediente
- `X-Correlation-Id`
- recepción de `Idempotency-Key`
- validación de request
- persistencia PostgreSQL mediante Spring JDBC

La fuente funcional indica como datos mínimos para crear el caso: número de póliza o documento, placa, fecha, ubicación aproximada, tipo de evento y medio de contacto; también establece que la evidencia no siempre puede exigirse al inicio. fileciteturn115file16

## MVP
Firebase Authentication no se implementa en este sprint, por decisión expresa del MVP. No hay Firebase SDK, filtro de autenticación ni claims en el código.

## Validación realizada en ambiente de desarrollo

### Validación estática
Resultado: **10 PASS / 0 FAIL**.

Se verificó:
- `pom.xml`;
- Dockerfile;
- driver PostgreSQL;
- endpoints POST/GET;
- referencias a `siniestro_facil.siniestro`;
- historial;
- auditoría;
- header `Idempotency-Key`;
- ausencia de Firebase en código.

### Compilación/pruebas Maven
**NO EJECUTADA en este ambiente.** El ambiente de desarrollo disponible no tiene Maven/Gradle ni acceso de red para descargar dependencias. No se declara `build` exitoso sin esa ejecución.

### PostgreSQL/GCP
Se generó `validation/sprint01_postgresql_validation.sql`. Debe ejecutarse contra `dmcsiniestrofacil` desde Cloud Shell. Utiliza una transacción y `ROLLBACK`, por lo que la prueba no debe dejar datos de prueba persistentes.

## Observación técnica — idempotencia
El endpoint recibe `Idempotency-Key`, pero el modelo físico aprobado no contiene una entidad persistente de idempotencia. No se agregó una tabla nueva para evitar modificar el modelo sin una decisión SDD.

Por tanto:
- contrato/header: implementado;
- persistencia distribuida de la clave: **OPEN**;
- idempotencia productiva sobre múltiples instancias Cloud Run: **NO CERRADA**.

Esta limitación debe resolverse antes de declarar la idempotencia de producción como DONE.

## Trazabilidad
`US-001 / registro de siniestro` → `RF-001/RF-002` → `CA-001/CA-002/CA-003` → REST → Application Service → PostgreSQL.

## Criterios de aceptación
| CA | Estado | Evidencia |
|---|---|---|
| CA-001 | Implementado; requiere prueba GCP | POST + estado REPORTADO |
| CA-002 | Implementado | request no contiene evidencia |
| CA-003 | Implementado; requiere prueba GCP | validación y error |
| CA-005 | Implementado; requiere prueba GCP | GET |
| CA-011 | Parcial | API acepta Idempotency-Key; persistencia pendiente |

## Cierre técnico
No se debe marcar Sprint 01 como cerrado todavía. El código está preparado en branch y debe pasar por Cloud Shell/GCP, compilación, pruebas automatizadas y revisión PR antes de merge a `main`.
