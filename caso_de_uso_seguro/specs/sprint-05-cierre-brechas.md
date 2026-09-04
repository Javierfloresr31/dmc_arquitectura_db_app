# Sprint 5 — Cierre de brechas funcionales

## 1. Objetivo

Cerrar las brechas de especificación identificadas en la matriz de trazabilidad del Sprint 5 antes de generar código que convierta inferencias en comportamiento de negocio.

La fuente base mantiene el alcance de Sprint 5 en autorización, seguimiento de reparación, pago/indemnización, controles de duplicidad, reconciliación y cierre.

## 2. Brechas que requieren definición funcional

| ID | Brecha | Clasificación | Tratamiento |
|---|---|---|---|
| B5-01 | Pago/indemnización no tiene historia de usuario específica | Historia de usuario | Crear US específica |
| B5-02 | Pago/indemnización no tiene criterios de aceptación específicos | Criterios de aceptación | Crear CA |
| B5-03 | Duplicidad de pago tiene requisito, pero no criterio operativo | Regla de negocio | Definir regla y resultado |
| B5-04 | Seguimiento de reparación no tiene US/RF/CA ni entidad propia | Requerimiento funcional | Definir necesidad y persistencia |
| B5-05 | Reconciliación aparece en el plan, pero no tiene US/RF/CA/API/modelo suficiente | Requerimiento funcional | Definir alcance o reclasificar |
| B5-06 | Cierre tiene transiciones conceptuales, pero condiciones exactas incompletas | Criterios de aceptación | Definir condiciones |
| B5-07 | Payloads y permisos definitivos de autorización/pago pendientes | Contrato API | Mantener pendiente; no inventar |
| B5-08 | Vigencia de presupuesto de 7 días | Regla/modelo | Implementada; validar mediante pruebas |

## 3. Decisiones que NO se deben inventar

No se fijan en este documento:

- montos máximos o mínimos de autorización;
- niveles jerárquicos de aprobación;
- umbrales económicos;
- criterio exacto de duplicidad de pagos;
- estados internos de reparación no definidos;
- fuente o sistema externo de reconciliación;
- ventanas temporales de reconciliación;
- reglas adicionales de reapertura;
- permisos definitivos por endpoint;
- payloads definitivos no especificados;
- vigencia distinta de la documentada en el SDD;
- nuevas tablas o columnas únicamente por inferencia.

## 4. Propuesta de cierre funcional simulado

Las siguientes respuestas son una **simulación de negocio para cerrar brechas**, no evidencia de una entrevista real. Deben tratarse como decisiones de cierre del SDD del proyecto y quedar diferenciadas de requisitos originalmente confirmados.

### B5-01/B5-02 — Pago e indemnización

**Respuesta simulada de negocio:**

> Como responsable de operaciones, quiero registrar un pago o indemnización asociado a un siniestro autorizado, para completar la resolución económica del expediente y mantener trazabilidad del resultado.

**Criterios de aceptación simulados:**

- Dado un siniestro con autorización registrada, cuando se registra un pago/indemnización, entonces queda asociado al siniestro y a la autorización correspondiente.
- Dado un pago/indemnización registrado, entonces debe quedar trazabilidad suficiente para reconstruir la operación.
- Dado un siniestro sin autorización aplicable, entonces el pago no debe registrarse como operación válida.
- Dado que el pago se procesa nuevamente con la misma `Idempotency-Key`, entonces la operación no debe generar un segundo resultado económico.

### B5-03 — Duplicidad de pago

**Respuesta simulada de negocio:**

> Antes de registrar un pago debe verificarse que no exista una operación económica equivalente ya registrada para el mismo siniestro y autorización. Si existe una coincidencia, la operación debe tratarse como conflicto y no generar un segundo pago.

**Decisión técnica derivada:**

El criterio anterior es funcional y requiere formalizar posteriormente qué atributos constituyen exactamente una operación económica equivalente. No se agrega todavía una restricción `UNIQUE` porque el modelo físico actual no define suficientes claves de negocio.

### B5-04 — Seguimiento de reparación

**Respuesta simulada de negocio:**

> Necesitamos conocer si el vehículo está en reparación y cuándo queda listo para entrega, pero durante el piloto no necesitamos gestionar una orden de reparación independiente; el expediente y sus estados visibles son suficientes para representar estas etapas.

**Decisión:**

Para el alcance simulado del piloto no se crea una entidad independiente de reparación. Se utiliza la máquina de estados existente: `AUTORIZADO -> EN_REPARACION -> LISTO_PARA_ENTREGA`.

Si posteriormente se requiere controlar fechas, avances, responsables, órdenes de taller o hitos de reparación, deberá abrirse una nueva brecha funcional y modificar formalmente el modelo.

### B5-05 — Reconciliación

**Respuesta simulada de negocio:**

> En el piloto la reconciliación consiste en verificar que cada pago registrado en el expediente tenga una autorización asociada y que no existan pagos duplicados. No se requiere todavía una conciliación contra un sistema financiero externo.

**Decisión:**

La reconciliación del piloto queda como validación interna del expediente. No se agrega una nueva entidad ni una integración externa en Sprint 5. Las conciliaciones externas se mantienen para Sprint 6 si se confirma su necesidad.

**Evidencia técnica de cierre:**

La implementación del pago valida que exista una autorización correspondiente al mismo siniestro antes de registrar la operación económica. Asimismo, verifica que no exista una operación económica equivalente y reutiliza la infraestructura de idempotencia existente.

La validación automatizada del backend finaliza correctamente con **26 pruebas ejecutadas, 0 fallos y 0 errores (`BUILD SUCCESS`)**.

**Estado:** Cerrado para el piloto.

### B5-06 — Cierre

**Respuesta simulada de negocio:**

> Un expediente puede cerrarse cuando se haya completado la modalidad de resolución correspondiente: entrega posterior a reparación o indemnización. Debe existir el resultado económico registrado y la transición debe quedar en el historial.

**Decisión:**

Se mantienen únicamente las transiciones ya definidas:

- `INDEMNIZADO -> CERRADO`;
- `LISTO_PARA_ENTREGA -> CERRADO`.

No se agrega una regla de reapertura ni nuevas transiciones.

**Evidencia técnica de cierre:**

La implementación permite cerrar el expediente únicamente desde `LISTO_PARA_ENTREGA` o `INDEMNIZADO` y exige que exista un resultado económico registrado para el siniestro. La transición a `CERRADO` reutiliza la persistencia existente del siniestro, historial de estados y auditoría.

La validación automatizada del backend finaliza correctamente con **26 pruebas ejecutadas, 0 fallos y 0 errores (`BUILD SUCCESS`)**.

**Estado:** Cerrado para el piloto.

### B5-07 — Autorización y API

**Respuesta simulada de negocio:**

> La autorización debe registrar la decisión y al responsable. Para la observación de presupuesto se define un payload mínimo con responsable y observación.

**Decisión:**

Se mantiene `autorizacion.aprobador`.

Para S05.2 se define y valida el endpoint:

`POST /api/v1/presupuestos/{id}/observaciones`

Payload mínimo:

```json
{
  "responsable": "operador01",
  "observacion": "Corregir mano de obra del presupuesto"
}
```

La decisión se persiste en `siniestro_facil.presupuesto_observacion` y produce la transición:

`PRESUPUESTO_RECIBIDO -> OBSERVADO`

La operación soporta `Idempotency-Key`.

La integración de auditoría enriquecida registra evento, actor y `correlation_id` cuando están disponibles.

**Evidencia de validación:**

- Observación: `presupuesto_id = 6`, `siniestro_id = 54`.
- Resultado: `HTTP 200`, observación `id = 3`, estado `OBSERVADO`.
- Auditoría: `PRESUPUESTO_OBSERVADO`, actor `operador01`, `correlation_id = corr-s05-12-obs-54`.
- Autorización: `siniestro_id = 37`, `autorizacion_id = 31`, evento `AUTORIZACION_REGISTRADA`, actor `aprobador01`, `correlation_id = corr-s05-12-auth-37`.
- Pago: `siniestro_id = 37`, `pago_id = 21`, evento `PAGO_REGISTRADO`, `correlation_id = corr-s05-12-pago-37`.
- Pruebas automatizadas: `32` ejecutadas, `0` fallos y `0` errores.

Los permisos y claims Firebase definitivos permanecen pendientes del contrato de seguridad/autorización y no bloquean el cierre funcional de S05.2.


### B5-08 — Vigencia

**Decisión:**

La vigencia del presupuesto se establece en siete días calendario. La implementación existente calcula `LocalDate.now().plusDays(7)`, valida la fecha recibida cuando corresponde y registra la vigencia calculada.

## 5. Reclasificación propuesta

| Elemento | Antes | Después del cierre simulado |
|---|---|---|
| Pago/indemnización | Brecha de US | US nueva: gestionar pago/indemnización |
| Pago/indemnización | Brecha de CA | CA nuevos para autorización, registro e idempotencia |
| Duplicidad | Brecha RN | RN nueva de prevención de operación económica equivalente |
| Reparación | Brecha US/RF/CA/modelo | Capacidad representada por estados del expediente para piloto |
| Reconciliación | Brecha US/RF/CA/API/modelo | Regla operativa interna del piloto; sin integración externa |
| Cierre | Brecha CA | CA asociado a las dos transiciones existentes |
| Payload/permisos API | Pendiente | Se mantiene pendiente técnico; no bloquea dominio |
| Vigencia 7 días | Regla implementada | Validar mediante pruebas y conservar la implementación existente |

## 6. Resultado

**Estado:** BRECHAS FUNCIONALES CERRADAS SIMULADAMENTE PARA CONTINUAR EL DISEÑO DEL SPRINT 5.

La simulación permite continuar con el diseño de autorización, pago, idempotencia, reconciliación interna y cierre sin agregar entidades por inferencia.

Antes de implementar código debe actualizarse la trazabilidad principal para incorporar los identificadores definitivos de las nuevas historias, RF, RN y CA.

## 7. Pendientes que permanecen OPEN

- payload definitivo de autorización;
- payload definitivo de pago;
- permisos definitivos por endpoint;
- claims Firebase, fuera del MVP funcional actual;
- criterio técnico exacto para identificar una operación económica equivalente si se requiere una garantía más fuerte que idempotencia;
- vigencia contractual del presupuesto;
- integración financiera externa, si posteriormente se incorpora;
- reglas de reapertura del expediente.
