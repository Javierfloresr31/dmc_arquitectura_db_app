# Sprint 5 — Matriz de trazabilidad inicial

## 1. Objetivo

Establecer la trazabilidad del Sprint 5 antes de modificar código, base de datos o contratos. La matriz se deriva exclusivamente de las historias de usuario, requerimientos funcionales, criterios de aceptación, reglas de negocio, máquina de estados, contrato API, modelo físico y plan de desarrollo vigentes.

## 2. Alcance SDD del Sprint 5

El plan de desarrollo define como objetivo completar la resolución económica del expediente mediante:

- autorización;
- seguimiento de reparación;
- pago/indemnización;
- controles de duplicidad;
- reconciliación;
- cierre.

La persistencia indicada es `autorizacion`, `pago` e historial/auditoría.

## 3. Matriz de trazabilidad

| ID | Elemento | Fuente SDD | RF | CA | RN | API | Persistencia | Estado actual | Brecha / observación | Acción antes de código |
|---|---|---|---|---|---|---|---|---|---|---|
| S05-01 | Recibir presupuesto | US-011 | RF-014 | CA-010 | — | POST `/api/v1/siniestros/{id}/presupuestos` | `presupuesto`, `presupuesto_detalle` | Implementado en Sprint 3 | Base existente; Sprint 5 consume el presupuesto | Verificar integración con autorización |
| S05-02 | Aprobar u observar presupuesto/cambio | US-012 | RF-015 | CA-010 | RN-006 | POST `/api/v1/siniestros/{id}/autorizaciones` / POST `/api/v1/presupuestos/{id}/observaciones` | `autorizacion`, `presupuesto_observacion` | Implementado y validado | Observación implementada con responsable, observación, fecha, transición e idempotencia | Mantener contrato mínimo y evidencia de pruebas |
| S05-03 | Vigencia del presupuesto | US-012 | RF-015 | CA-010 | — | Relacionado con autorización | `presupuesto.vigencia` | Implementado en Sprint 3 | El servicio existente calcula y registra una vigencia de 7 días calendario | Validar mediante pruebas y conservar la implementación existente |
| S05-04 | Responsable de aprobación | US-012 | RF-015 | CA-010 | RN-006 | POST autorizaciones | `autorizacion.aprobador` | Modelado | Campo existente; identidad/autorización de actor sigue pendiente en MVP | Mantener `aprobador` sin inventar claims/permisos runtime |
| S05-05 | Seguimiento de reparación | — | RF-005 | CA-005 | — | Transiciones de estado | `siniestro`, `siniestro_estado_historial` | Cerrado para piloto | La necesidad de seguimiento se cubre mediante los estados `AUTORIZADO → EN_REPARACION → LISTO_PARA_ENTREGA`; no se requiere una entidad independiente de reparación | No agregar entidad ni atributos de reparación; mantener las transiciones existentes |
| S05-06 | Pago/indemnización | US-019 | RF-018 | CA-013 | RN-005 | POST `/api/v1/siniestros/{id}/pagos` | `pago` | Implementado y probado | El pago/indemnización queda asociado a un siniestro autorizado, con trazabilidad del resultado y controles de idempotencia y duplicidad | Mantener la implementación existente y validar integración posterior |
| S05-07 | Control de pagos duplicados | US-019 | RF-018 | CA-014 | RN-012 | POST `/api/v1/siniestros/{id}/pagos` | `pago` + idempotencia técnica | Implementado y probado | Si ya existe una operación económica equivalente para el mismo siniestro y autorización, un nuevo pago debe tratarse como conflicto y no generar un segundo resultado económico | Mantener el control existente; no agregar restricciones físicas no definidas |
| S05-08 | Idempotencia del pago | — | RF-018 | — | — | POST pagos + `Idempotency-Key` | `pago` + infraestructura de idempotencia | Implementado y probado | El pago reutiliza la infraestructura existente de idempotencia y evita reprocesamientos con la misma clave y payload | Mantener la infraestructura y las pruebas existentes |
| S05-09 | Reconciliación | — | — | — | — | Validación interna | `pago`, `autorizacion` | Cerrado para piloto | La reconciliación consiste en verificar que cada pago tenga autorización aplicable, no exista una segunda operación equivalente y la operación sea reconstruible mediante auditoría | No integrar sistema financiero externo ni crear entidad específica de reconciliación |
| S05-10 | Indemnización | US-019 | RF-005 / RF-018 | CA-013 | — | POST `/api/v1/siniestros/{id}/pagos` | `pago` | Definido | La indemnización está cubierta por la funcionalidad de pago y la transición `AUTORIZADO → INDEMNIZADO` | Implementar como parte del flujo de pago; no crear funcionalidad independiente |
| S05-11 | Cierre de expediente | — | RF-005 / RF-016 | CA-015 | — | POST `/api/v1/siniestros/{id}/transiciones` | `siniestro`, `siniestro_estado_historial`, `auditoria` | Implementado y probado | El cierre procede desde `LISTO_PARA_ENTREGA` o `INDEMNIZADO` cuando se completó la modalidad de resolución correspondiente, existe resultado económico registrado y la transición queda en el historial | Mantener las condiciones y transiciones existentes; no crear una entidad independiente de cierre |
| S05-12 | Historial/auditoría de operaciones | US-016 | RF-016 | CA-011 | RN-006 | Transiciones / autorizaciones / pagos / observaciones | `siniestro_estado_historial`, `auditoria` | Implementado y validado | Las operaciones sensibles conservan trazabilidad; las transiciones registran actor, fecha, evento y correlación cuando la operación dispone de esos datos; el historial funcional es append-only | Mantener la integración y conservar evidencia E2E y pruebas automatizadas |

## 4. Trazabilidad por historia existente

### US-011 — Recibir presupuesto

Fuente: `02-historias-usuario.md`.

- RF: RF-014.
- CA: CA-010.
- Persistencia: `presupuesto`, `presupuesto_detalle`.
- API: presupuesto ya definido.
- Relación con Sprint 5: el presupuesto constituye la entrada para autorización.
- Resultado: **cubierta por Sprint 3; reutilizable en Sprint 5**.

### US-012 — Gestionar autorización

Fuente: `02-historias-usuario.md`.

- RF: RF-015.
- CA: CA-010.
- Persistencia: `autorizacion`.
- API: `POST /api/v1/siniestros/{id}/autorizaciones`.
- Máquina de estados: `PRESUPUESTO_RECIBIDO -> AUTORIZADO` y `PRESUPUESTO_RECIBIDO -> OBSERVADO`.
- Resultado: **alcance funcional definido; representación contractual/técnica de la observación todavía pendiente**.

No se inventan montos, niveles de aprobación, motivos obligatorios adicionales, payloads, estructuras persistentes ni permisos concretos porque permanecen pendientes en la especificación.

## 5. Elementos del Sprint 5 sin historia de usuario equivalente

Los siguientes elementos aparecen en el plan del Sprint 5 o en requerimientos/estados, pero no tienen una historia de usuario específica en `02-historias-usuario.md`:

1. Pago/indemnización.
2. Control funcional de pagos duplicados.
3. Reconciliación.
4. Seguimiento de reparación.
5. Cierre como capacidad funcional explícita.

Esto no significa que el alcance deba eliminarse. Significa que existe una **brecha de trazabilidad funcional** que debe cerrarse antes de generar implementación para esos comportamientos.

## 6. Elementos que sí existen pero requieren precisión

### Autorización

Existe trazabilidad suficiente a nivel de historia/RF/CA/API/modelo, pero faltan detalles de payload y permisos definitivos. El contrato API declara expresamente que los payloads exactos y permisos por endpoint permanecen pendientes.

### Pago

Existe tabla física, RF-018 y endpoint objetivo. Falta historia de usuario y criterios de aceptación específicos. El control de duplicidad e idempotencia no debe implementarse con reglas inventadas.

### Reparación

La máquina de estados contiene `EN_REPARACION` y `LISTO_PARA_ENTREGA`, pero el modelo físico vigente no tiene una entidad específica de reparación/orden de reparación/seguimiento. No se agrega una tabla por inferencia.

### Reconciliación

El plan la declara como alcance, pero las especificaciones funcionales consultadas no contienen una definición operativa suficiente para determinar qué se reconcilia, contra qué fuente, cuándo, estados ni resultado esperado.

### Cierre

La máquina de estados define las transiciones finales, pero mantiene abiertas las condiciones exactas de cierre y reapertura. Por tanto, se puede validar la transición conceptual, pero no inventar reglas adicionales.

## 7. Brechas clasificadas

| Brecha | Clasificación | Severidad para Sprint 5 | Tratamiento |
|---|---|---:|---|
| Pago sin US | Historia de usuario | Alta | Crear US de pago/indemnización |
| Pago sin CA | Criterios de aceptación | Alta | Crear CA de pago |
| Duplicidad sin criterio operativo | Regla de negocio | Alta | Definir regla antes de implementar |
| Reparación sin US/RF/CA | Requerimiento funcional | Alta | Crear US/RF/CA y ajustar modelo si corresponde |
| Reconciliación sin US/RF/CA | Requerimiento funcional | Alta | Definir alcance o reclasificar como pendiente de Sprint 5 |
| Cierre sin CA específico | Criterio de aceptación | Media | Completar CA y condiciones de cierre |
| Payload/permisos de autorización y pago pendientes | Contrato API | Media | No bloquear diseño de dominio; no inventar contrato definitivo |
| Regla de vigencia de 7 días implementada | Regla/modelo | Media | Validar mediante pruebas y conservar la implementación existente |

## 8. Decisiones de ejecución

1. **No iniciar todavía la implementación de pago, reparación o reconciliación.** Primero se debe cerrar la brecha funcional.
2. **Sí puede continuar el análisis técnico de autorización**, porque existe trazabilidad funcional suficiente para delimitar el caso, aunque deben respetarse los pendientes del contrato.
3. **No se agregan tablas, columnas, constraints, estados o reglas** únicamente para completar la matriz.
4. La idempotencia técnica existente se reutilizará; el comportamiento funcional específico del pago deberá probarse cuando se cierre el criterio de duplicidad.
5. La máquina de estados vigente sigue siendo la fuente para las transiciones ya formalizadas.
6. Las preguntas abiertas del SDD permanecen abiertas hasta que una fuente las resuelva.

## 9. Resultado de la revisión

**Estado del Sprint 5 antes de implementación: BLOQUEADO PARCIALMENTE POR BRECHAS DE ESPECIFICACIÓN.**

La autorización puede pasar a diseño detallado. Pago/indemnización, seguimiento de reparación, reconciliación y reglas específicas de cierre requieren cierre funcional antes de generar código que convierta inferencias en comportamiento de negocio.
