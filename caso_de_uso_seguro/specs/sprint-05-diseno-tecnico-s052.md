# Diseño técnico S05.2 — Observación de presupuesto y control de transición

## 1. Objetivo

Definir el diseño técnico para S05.2, correspondiente a la gestión de la decisión sobre un presupuesto recibido, considerando:

- aprobación del presupuesto;
- observación del presupuesto cuando requiere corrección;
- trazabilidad de la decisión;
- transición del estado del siniestro;
- preservación de la idempotencia existente;
- identificación de la brecha técnica del mecanismo genérico de transición.

Este documento precede a cualquier modificación de código.

---

## 2. Alcance

### Incluido

- Revisar y preservar la autorización existente.
- Definir técnicamente la observación de un presupuesto.
- Alinear la transición `PRESUPUESTO_RECIBIDO → OBSERVADO` con el SDD.
- Mantener `PRESUPUESTO_RECIBIDO → AUTORIZADO`.
- Mantener la idempotencia existente de la autorización.
- Identificar la protección necesaria para las transiciones de estado.
- Definir pruebas unitarias y E2E para el alcance aprobado.

### No incluido

- Implementación de reparación.
- Implementación de pago/indemnización.
- Reconciliación financiera.
- Implementación completa de la máquina de estados para estados posteriores.
- Definición de permisos definitivos.
- Definición de claims Firebase.
- Integraciones financieras externas.
- Reglas no definidas formalmente por el SDD.

---

## 3. Evidencia de implementación existente

### 3.1 Autorización

`AutorizacionService` actualmente:

1. calcula el hash de la solicitud;
2. consulta primero la idempotencia;
3. devuelve la autorización existente cuando corresponde;
4. para una solicitud nueva exige `PRESUPUESTO_RECIBIDO`;
5. registra la autorización;
6. cuando la autorización es nueva, transiciona a `AUTORIZADO`.

La transición utilizada actualmente es:

`PRESUPUESTO_RECIBIDO → AUTORIZADO`

Esta funcionalidad fue validada mediante E2E en S05.1.

### 3.2 Presupuesto

`PresupuestoService` actualmente:

1. exige `INSPECCION_PROGRAMADA`;
2. calcula la vigencia a siete días calendario;
3. registra presupuesto, diagnóstico, observaciones, repuestos alternativos y ampliaciones;
4. transiciona a `PRESUPUESTO_RECIBIDO`.

### 3.3 Inspección

`InspeccionService` actualmente:

1. exige `EN_EVALUACION`;
2. registra la inspección;
3. transiciona a `INSPECCION_PROGRAMADA`.

---

## 4. Brecha funcional S05.2

El SDD contempla que operaciones pueda:

- aprobar un presupuesto;
- observar un presupuesto cuando requiere corrección;
- dejar registrada la decisión y su responsable.

La máquina de estados contempla:

`PRESUPUESTO_RECIBIDO → OBSERVADO`

Actualmente `OBSERVADO` no existe como constante en el modelo Java ni existe una operación específica para registrar esta decisión.

### Decisión

La observación debe tratarse como una decisión operacional distinta de la aprobación.

La implementación no deberá inventar campos, payloads, permisos ni estructuras persistentes que todavía no estén definidos por el contrato funcional/API.

Antes de implementar se debe cerrar la representación definitiva de:

- decisión de observar;
- responsable;
- justificación/motivo;
- fecha;
- trazabilidad de la transición.

---

## 5. Brecha técnica de transición

Actualmente `SiniestroRepository.transition(id, estado)` recibe únicamente:

- identificador del siniestro;
- estado destino.

La implementación JDBC verifica que el siniestro exista y posteriormente actualiza el estado, registra historial y auditoría.

No valida que la transición sea válida respecto del estado actual.

Esto representa una brecha respecto de la máquina de estados definida en el SDD, que establece que las transiciones inválidas deben ser rechazadas.

### Decisión

La protección de la máquina de estados se tratará como una brecha técnica separada de la implementación funcional de observación.

No se implementará en este punto una máquina completa que incluya estados funcionales todavía no desarrollados en Sprint 5.

---

## 6. Transiciones actualmente soportadas por los casos de uso implementados

| Estado origen | Estado destino | Caso de uso |
|---|---|---|
| `EN_EVALUACION` | `INSPECCION_PROGRAMADA` | Inspección |
| `INSPECCION_PROGRAMADA` | `PRESUPUESTO_RECIBIDO` | Presupuesto |
| `PRESUPUESTO_RECIBIDO` | `AUTORIZADO` | Autorización |

Estas transiciones ya forman parte del código existente y no deben romperse.

---

## 7. Transición funcional prevista para S05.2

La máquina de estados define:

`PRESUPUESTO_RECIBIDO → OBSERVADO`

Condición funcional:

`requiere corrección`

La observación no equivale a:

- fraude;
- rechazo definitivo;
- eliminación del presupuesto;
- cierre del siniestro.

El SDD no define en este alcance una transición posterior desde `OBSERVADO`. Por tanto, no se inventará una transición de retorno.

---

## 8. Reutilización

Se deberá reutilizar, en la medida compatible con el contrato definitivo:

- `Siniestro`;
- `SiniestroRepository`;
- `SiniestroService`;
- `JdbcSiniestroRepository`;
- `AutorizacionService`;
- `AutorizacionPort`;
- mecanismo existente de idempotencia;
- historial de estados;
- auditoría existente.

No se crearán componentes duplicados.

---

## 9. Idempotencia

La autorización existente utiliza `Idempotency-Key` y hash de solicitud.

Esta capacidad debe preservarse.

Una repetición de una misma solicitud válida debe devolver el resultado existente y no generar una segunda consecuencia económica ni una segunda transición.

Cualquier extensión de S05.2 deberá conservar este comportamiento.

---

## 10. Auditoría e historial

La transición de estado actualmente registra:

- siniestro;
- estado;
- fecha del evento;
- registro de auditoría asociado.

El SDD establece requisitos adicionales de trazabilidad para las transiciones, incluyendo actor, fecha, evento y correlación.

Antes de implementar la observación se deberá verificar que la representación existente sea suficiente para el contrato definitivo.

No se agregarán columnas ni estructuras únicamente por inferencia técnica.

---

## 11. Pruebas requeridas

### Autorización

Debe mantenerse:

- presupuesto en `PRESUPUESTO_RECIBIDO`;
- autorización registrada;
- transición a `AUTORIZADO`;
- idempotencia;
- ausencia de duplicación por repetición de la solicitud.

### Observación

Cuando el contrato definitivo sea cerrado, deberán comprobarse como mínimo:

- presupuesto en `PRESUPUESTO_RECIBIDO`;
- decisión de observación;
- responsable;
- justificación;
- transición a `OBSERVADO`;
- historial;
- auditoría;
- rechazo de solicitudes incompatibles con el estado actual.

### Transiciones inválidas

La cobertura deberá incluir al menos un caso en que una transición no permitida sea rechazada.

---

## 12. Validación E2E

La validación se realizará contra el entorno utilizado para Sprint 5:

- aplicación Spring Boot;
- PostgreSQL;
- Cloud SQL mediante Cloud SQL Auth Proxy;
- Flyway;
- datos sintéticos de prueba.

La evidencia deberá incluir:

- request;
- response;
- estado antes;
- estado después;
- historial;
- auditoría;
- persistencia asociada;
- comportamiento de idempotencia cuando aplique.

---

## 13. Criterios de no invención

Durante la implementación no se deberán introducir sin respaldo funcional:

- nuevos estados no definidos;
- nuevas transiciones no definidas;
- nuevos campos de negocio;
- motivos estandarizados;
- permisos;
- claims Firebase;
- contratos de terceros;
- integraciones financieras;
- reglas económicas adicionales.

Cuando un elemento sea necesario pero no esté definido, deberá registrarse como pendiente de contrato o brecha.

---

## 14. Estado del diseño

**S05.2 — EN DISEÑO**

La autorización se encuentra implementada y validada como parte de S05.1.

La observación del presupuesto y la protección de la máquina de estados requieren cierre técnico antes de modificar la implementación.

---

## 15. Próximo paso

Antes de implementar:

1. revisar el contrato API de autorización;
2. determinar la representación definitiva de aprobación/observación;
3. revisar persistencia disponible;
4. definir pruebas unitarias;
5. implementar únicamente el alcance aprobado;
6. ejecutar `mvn clean test`;
7. ejecutar E2E contra Cloud SQL;
8. actualizar matriz de trazabilidad y cierre de brechas;
9. cerrar S05.2 con evidencia y commit.
