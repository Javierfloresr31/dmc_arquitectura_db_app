# Sprint 01 — Registro y consulta del siniestro

## Estado
PLANIFICADO — no iniciar Sprint 02 hasta cerrar este sprint.

## Objetivo
Implementar el primer flujo ejecutable de Siniestro Fácil: autenticación, autorización inicial, registro y consulta de siniestro, participantes, estado inicial, transición controlada, auditoría e idempotencia.

## Trazabilidad

US/RF/CA del baseline funcional:
- US-001 Registro de siniestro.
- US-003 Consulta/seguimiento.
- RF-001 Registro.
- RF-002 Consulta.
- RF-005 Participantes.
- RF-006 Consulta de participante cuando aplique.
- CA-001, CA-002, CA-003, CA-005 y CA-011 según matriz funcional vigente.

## APIs

- POST `/api/v1/siniestros`
- GET `/api/v1/siniestros/{id}`
- GET `/api/v1/siniestros`
- POST `/api/v1/siniestros/{id}/transiciones`
- POST `/api/v1/siniestros/{id}/participantes`
- GET `/api/v1/siniestros/{id}/participantes`

## Componentes

- Spring Boot backend.
- Arquitectura hexagonal.
- Firebase ID Token validation.
- RBAC inicial.
- DTOs y validadores.
- Casos de uso.
- Adaptador REST.
- Adaptador PostgreSQL.
- Manejo estándar de errores.
- Correlation ID.
- Idempotency Key.

## Persistencia

- `poliza`
- `vehiculo`
- `poliza_vehiculo`
- `siniestro`
- `siniestro_participante`
- `siniestro_estado_historial`
- `auditoria`

No modificar el modelo físico para resolver necesidades de implementación sin una actualización formal de las Specifications.

## Validaciones

1. Compilación.
2. Unit tests.
3. API tests.
4. Integration tests contra PostgreSQL.
5. Firebase authentication/authorization.
6. PK/FK/NOT NULL aplicables.
7. Transacción y rollback.
8. Idempotencia.
9. Transición válida/inválida.
10. Auditoría.
11. Criterios de aceptación.

## Artefactos obligatorios al cierre

- código backend;
- pruebas automatizadas;
- configuración GCP necesaria;
- scripts SQL/migraciones si existen;
- scripts de validación;
- resultados de pruebas;
- actualización de trazabilidad;
- `sprint-01-validacion.md`;
- actualización de este archivo con estado final;
- commit SHA de GitHub.

## Regla

No avanzar a Sprint 02 hasta que el Sprint 01 esté marcado `CERRADO` y sus artefactos estén versionados en GitHub.
