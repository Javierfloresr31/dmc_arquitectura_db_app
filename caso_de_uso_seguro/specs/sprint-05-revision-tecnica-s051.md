# Sprint 5 — Revisión técnica S05.1 Autorización

## 1. Resultado de la inspección

S05.1 no parte de cero. La rama contiene una implementación existente de autorización.

Componentes identificados:

- `application/service/AutorizacionService.java`
- `application/port/AutorizacionPort.java`
- `infrastructure/persistence/JdbcAutorizacionRepository.java`
- `interfaces/rest/AutorizacionController.java`
- `application/dto/AutorizacionRequest.java`

## 2. Componentes reutilizables

| Componente | Estado | Decisión |
|---|---|---|
| AutorizacionService | Existe | REUTILIZAR, ajustar solo si la brecha lo exige |
| AutorizacionPort | Existe | REUTILIZAR |
| JdbcAutorizacionRepository | Existe | REUTILIZAR |
| AutorizacionController | Existe | REUTILIZAR, contrastar contrato |
| AutorizacionRequest | Existe | REVISAR contra contrato SDD |
| SiniestroRepository | Existe | REUTILIZAR para transición |
| idempotencia_request | Existe | REUTILIZAR |

## 3. Evidencia técnica

`AutorizacionService.registrar(...)`:

- calcula un hash de la solicitud;
- consulta la Idempotency-Key antes de procesar;
- valida que exista el siniestro;
- exige estado `PRESUPUESTO_RECIBIDO`;
- registra la autorización;
- cuando la operación es nueva, transiciona a `AUTORIZADO`.

El puerto expone registro, consulta por Idempotency-Key y listado.

El repositorio persiste en `siniestro_facil.autorizacion`, utiliza la tabla técnica de idempotencia y detecta reutilización de una clave con payload diferente.

## 4. Brecha encontrada

El SDD de Sprint 5 establece que la gestión de autorización contempla dos resultados funcionales:

- aprobación → `PRESUPUESTO_RECIBIDO -> AUTORIZADO`;
- observación → `PRESUPUESTO_RECIBIDO -> OBSERVADO`.

La implementación inspeccionada de `AutorizacionService` solamente modela la aprobación y siempre transiciona una operación nueva a `AUTORIZADO`.

Por tanto, **S05.1 todavía no está completamente cerrado**.

## 5. Restricción de diseño

No se debe agregar una decisión de negocio no definida. Antes de modificar el DTO/endpoint para soportar observación, se debe contrastar la definición exacta del contrato API vigente y determinar si la observación pertenece al mismo caso de uso/endpoint o a una operación distinta.

No se implementará todavía una propiedad `decision`, `motivo` u otro campo hasta que el SDD/contrato lo justifique.

## 6. Idempotencia

La autorización ya tiene soporte técnico para `Idempotency-Key`.

Comportamientos existentes que deben preservarse y probarse:

1. solicitud nueva → crea autorización;
2. misma clave + mismo hash → devuelve la autorización existente;
3. misma clave + hash diferente → conflicto;
4. sin clave → conserva el comportamiento existente definido por el código.

## 7. Transición

El dominio actual define `PRESUPUESTO_RECIBIDO` y `AUTORIZADO`; la interfaz `SiniestroRepository` expone `transition(id, estado)`.

La máquina de estados SDD contiene además `OBSERVADO`, pero el modelo Java inspeccionado no contiene actualmente esa constante.

Esto debe resolverse como parte del cierre de S05.1, no mediante una modificación aislada del controlador.

## 8. Auditoría

La revisión debe confirmar la existencia de auditoría de la operación de autorización y de la transición. Si ya existe infraestructura reusable, debe integrarse sin duplicarla.

## 9. Criterio para implementar la corrección

Antes del cambio de código se debe confirmar:

- forma definitiva de representar aprobación/observación;
- transición correspondiente;
- justificación requerida para observación, si está definida;
- efecto sobre idempotencia;
- auditoría de la decisión;
- respuesta API.

## 10. Estado

**S05.1 — EN REVISIÓN TÉCNICA.**

La infraestructura existente es reutilizable, pero la cobertura funcional completa requiere cerrar la discrepancia entre la implementación actual y la máquina de estados/gestión de autorización definida por el SDD.
