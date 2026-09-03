# Sprint 5 — Diseño técnico S05.1 Autorización

## 1. Objetivo

Cerrar el diseño técnico de la capacidad de autorización sin inventar campos, reglas o permisos que el SDD todavía declara pendientes.

## 2. Fuentes contractuales

- US-012 — Gestionar autorización.
- RF-015 — Registrar aprobación de presupuesto/cambio, vigencia y aprobador.
- CA-010 — Presupuesto: vigencia, diagnóstico, aprobación/observación, cambios y responsable.
- Contrato API: `POST /api/v1/siniestros/{id}/autorizaciones`.
- Máquina de estados: `PRESUPUESTO_RECIBIDO -> AUTORIZADO` y `PRESUPUESTO_RECIBIDO -> OBSERVADO`.

## 3. Estado actual de implementación

Existe una implementación reutilizable formada por `AutorizacionController`, `AutorizacionService`, `AutorizacionPort`, `JdbcAutorizacionRepository` e infraestructura de idempotencia.

La operación actual recibe `aprobador`, registra la autorización y, cuando la operación es nueva, realiza la transición a `AUTORIZADO`.

## 4. Decisiones técnicas

### D-01 — Reutilizar autorización existente

No se crea un nuevo agregado ni una segunda implementación. Se mantiene el flujo existente y solo se modifica donde exista una brecha contractual demostrada.

### D-02 — Idempotencia

Se conserva el comportamiento existente:

1. solicitud nueva: crea autorización;
2. misma `Idempotency-Key` y mismo hash: devuelve la operación existente;
3. misma clave y hash diferente: conflicto;
4. sin clave: conserva el comportamiento actual.

### D-03 — Aprobación

La aprobación mantiene la transición:

`PRESUPUESTO_RECIBIDO -> AUTORIZADO`

La autorización persistida conserva el aprobador actualmente definido por el modelo físico.

### D-04 — Observación

La máquina de estados y US-012 contemplan observación, pero el contrato API no define el payload exacto ni la estructura persistente necesaria para representar la decisión y su eventual justificación.

Por tanto, **no se implementa todavía la observación** mediante campos inventados.

La transición `PRESUPUESTO_RECIBIDO -> OBSERVADO` queda identificada como brecha contractual pendiente.

### D-05 — Endpoint canónico

El contrato SDD define `/api/v1/siniestros/{id}/autorizaciones` en plural. El código actual expone `/autorizacion` en singular.

El endpoint plural se considera el contrato canónico para el cierre de S05.1. La eliminación o compatibilidad temporal del endpoint existente requiere una decisión de compatibilidad que no está especificada en el SDD; por ello no se elimina silenciosamente el endpoint actual en esta etapa.

### D-06 — Identidad del aprobador

El contrato establece que la identidad debe provenir del actor autenticado y no de un identificador enviado por el cliente. Sin embargo, la autenticación Firebase y los claims definitivos están declarados pendientes en el propio contrato API.

Por ello, no se implementa todavía una integración de identidad que no esté definida en el alcance técnico vigente.

### D-07 — Auditoría

La transición del siniestro ya registra historial de estado y auditoría técnica. No se duplica dicha infraestructura. Debe verificarse en la prueba de integración que la autorización y su transición sean reconstruibles mediante la línea de tiempo existente.

## 5. Flujo técnico aprobado

```text
Cliente
  |
  | POST /api/v1/siniestros/{id}/autorizaciones
  | Idempotency-Key
  | X-Correlation-Id
  v
AutorizacionController
  |
  v
AutorizacionService
  |
  +--> validar existencia del siniestro
  |
  +--> validar estado PRESUPUESTO_RECIBIDO
  |
  +--> resolver idempotencia
  |
  +--> AutorizacionPort
  |       |
  |       v
  |   JdbcAutorizacionRepository
  |       |
  |       v
  |   autorizacion + idempotencia_request
  |
  +--> si nueva
          |
          v
      transition(AUTORIZADO)
          |
          +--> siniestro_estado_historial
          +--> auditoria
```

## 6. Criterios técnicos para cerrar S05.1

- La aprobación solo procede desde `PRESUPUESTO_RECIBIDO`.
- Una repetición idempotente no genera una segunda autorización ni una segunda transición.
- Una `Idempotency-Key` reutilizada con payload diferente genera conflicto.
- La transición queda registrada en historial.
- La operación queda reconstruible mediante auditoría/línea de tiempo.
- El endpoint contractual debe quedar alineado con `/autorizaciones` sin eliminar comportamiento existente sin una decisión de compatibilidad.
- No se introducen campos de negocio no definidos por el SDD.

## 7. Brechas que permanecen abiertas

| ID | Brecha | Estado |
|---|---|---|
| B-S051-01 | Payload definitivo de aprobación/observación | Pendiente en SDD |
| B-S051-02 | Representación persistente de observación | Pendiente en SDD |
| B-S051-03 | Justificación de observación | No definida explícitamente |
| B-S051-04 | Permisos definitivos por endpoint | Pendiente en contrato |
| B-S051-05 | Claims Firebase definitivos | Pendiente en contrato |
| B-S051-06 | Política de compatibilidad `/autorizacion` vs `/autorizaciones` | Pendiente |

## 8. Alcance de implementación inmediato

Se autoriza únicamente la corrección/alineación de la interfaz HTTP con el contrato existente y las pruebas de la aprobación ya definida, sin implementar observación mediante un contrato inventado.

La capacidad de pago no se inicia hasta validar el cierre de S05.1.

## 9. Estado

**S05.1 — DISEÑO TÉCNICO CERRADO CON BRECHAS EXPLÍCITAS.**

La aprobación es implementable con la información disponible. La observación permanece como brecha contractual y no debe resolverse por inferencia técnica.
