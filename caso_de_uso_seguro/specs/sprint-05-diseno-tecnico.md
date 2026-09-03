# Sprint 5 — Diseño técnico previo a implementación

## 1. Objetivo

Definir el diseño técnico mínimo trazable para implementar Sprint 5 sin inventar reglas fuera del cierre funcional aprobado.

## 2. Subunidades

- S05.1 Autorización
- S05.2 Reparación mediante estados del expediente
- S05.3 Pago/indemnización
- S05.4 Idempotencia y control de duplicidad
- S05.5 Reconciliación interna del piloto
- S05.6 Cierre del expediente

## 3. S05.1 — Autorización

### Entrada

- `siniestroId`.
- decisión de autorización/observación.
- aprobador.
- información de presupuesto existente cuando corresponda.

El contrato definitivo de payload permanece pendiente; por tanto, estos elementos representan el concepto funcional y no un contrato JSON definitivo.

### Reglas

1. La autorización se registra contra el siniestro.
2. El responsable de aprobación queda trazado mediante `autorizacion.aprobador`.
3. Una aprobación conduce conceptualmente de `PRESUPUESTO_RECIBIDO` a `AUTORIZADO`.
4. Una observación conduce de `PRESUPUESTO_RECIBIDO` a `OBSERVADO`.
5. No se inventan montos, niveles jerárquicos ni permisos.

### Persistencia

`autorizacion(id, siniestro_id, aprobador)`.

### Auditoría

La operación debe registrar auditoría funcional y, cuando produzca transición, historial de estado.

### Puerto de aplicación candidato

`AutorizacionPort` para desacoplar el caso de uso de JDBC.

### Servicio candidato

`AutorizacionService` como orquestador del caso de uso.

### Adaptador candidato

`JdbcAutorizacionRepository`.

## 4. S05.2 — Reparación

Para el piloto, la decisión de cierre de brechas establece que no se crea una entidad independiente de reparación.

Se utilizan las transiciones ya existentes:

`AUTORIZADO -> EN_REPARACION -> LISTO_PARA_ENTREGA`.

La transición se solicita mediante el endpoint general de transiciones y debe conservar actor, fecha, evento y correlación.

No se implementan fechas de reparación, porcentajes de avance, orden de taller ni hitos adicionales porque no están definidos.

## 5. S05.3 — Pago/indemnización

### Objetivo

Registrar el resultado económico de un siniestro autorizado.

### Flujo

`AUTORIZADO -> INDEMNIZADO` cuando la modalidad corresponde a indemnización/pago.

### Persistencia existente

`pago(id, siniestro_id, autorizacion)`.

La columna `autorizacion` es `text` en el modelo físico vigente; no se cambia a FK sin una decisión formal de evolución del modelo.

### Servicio candidato

`PagoService`.

### Puerto candidato

`PagoPort`.

### Adaptador candidato

`JdbcPagoRepository`.

### Reglas mínimas

1. Debe existir una autorización aplicable.
2. Debe evitarse una segunda operación por repetición con la misma `Idempotency-Key`.
3. Debe rechazarse una operación económicamente equivalente ya registrada según el criterio funcional del cierre de brechas.
4. El resultado debe quedar trazable.

La identificación técnica completa de equivalencia económica queda pendiente si posteriormente se requiere una garantía distinta de la idempotencia.

## 6. S05.4 — Idempotencia y duplicidad

La API utiliza `Idempotency-Key` para operaciones mutables que admitan repetición segura.

Para pago:

1. recibir la clave;
2. calcular/validar la solicitud correspondiente;
3. consultar la infraestructura de idempotencia existente;
4. si la misma operación ya fue procesada, devolver el resultado previamente asociado;
5. si existe conflicto con una operación distinta usando la misma clave, responder conflicto;
6. solo crear el pago una vez.

No se sustituye la idempotencia por una restricción `UNIQUE` inventada sobre columnas del modelo.

## 7. S05.5 — Reconciliación

En el piloto la reconciliación será interna.

Validaciones:

- cada pago debe tener autorización aplicable;
- no debe existir una segunda operación equivalente;
- las operaciones relevantes deben ser reconstruibles mediante auditoría.

No se incorpora integración financiera externa en este sprint.

## 8. S05.6 — Cierre

Transiciones permitidas ya formalizadas:

- `INDEMNIZADO -> CERRADO`.
- `LISTO_PARA_ENTREGA -> CERRADO`.

La transición debe utilizar la máquina de estados existente y registrar historial/auditoría.

No se agrega reapertura ni otras transiciones.

## 9. Matriz técnica

| Subunidad | Servicio | Puerto | Persistencia | Estado | Pruebas principales |
|---|---|---|---|---|---|
| S05.1 | AutorizacionService | AutorizacionPort | autorizacion | Diseñado | aprobar, observar, auditoría, transición |
| S05.2 | Transicion existente | puerto existente | siniestro + historial | Reutilizar | AUTORIZADO→EN_REPARACION, EN_REPARACION→LISTO |
| S05.3 | PagoService | PagoPort | pago | Diseñado | pago autorizado, trazabilidad, indemnización |
| S05.4 | Idempotencia existente | puerto existente | meta.idempotencia_request | Reutilizar | repetición, misma clave, conflicto |
| S05.5 | ReconciliacionService | ReconciliacionPort | pago + autorizacion | Diseñado | autorización presente, duplicidad |
| S05.6 | Transicion existente | puerto existente | siniestro + historial | Reutilizar | INDEMNIZADO→CERRADO, LISTO→CERRADO |

## 10. Orden de implementación

1. Verificar código existente de autorización/transiciones/idempotencia.
2. Implementar autorización.
3. Implementar pruebas de autorización.
4. Implementar pago.
5. Implementar idempotencia específica de pago.
6. Implementar control de duplicidad definido.
7. Implementar reconciliación interna.
8. Integrar transiciones de reparación y cierre.
9. Integrar auditoría.
10. Ejecutar pruebas automatizadas y validación Cloud SQL.

## 11. Fuera de este diseño

No se implementa todavía:

- Firebase Authentication/runtime RBAC;
- integración financiera externa;
- proveedor de pagos real;
- orden de reparación independiente;
- regla contractual de vigencia de 7 días;
- montos o umbrales de aprobación;
- permisos definitivos por endpoint.

Estos elementos permanecen fuera del alcance cerrado de implementación o pendientes según el SDD.

## 12. Criterio de entrada a código

El Sprint 5 puede iniciar implementación cuando la revisión confirme que:

- las brechas funcionales fueron cerradas o explícitamente mantenidas OPEN;
- no se requiere nueva estructura física para el alcance actual;
- autorización, pago, idempotencia y cierre tienen flujo técnico definido;
- las pruebas asociadas están identificadas;
- cualquier cambio de modelo queda sustentado por una necesidad funcional explícita.
