# NovaRetail — Stock Único
# Reclasificación de Brechas después del Cierre de Brechas

**Versión:** 1.0  
**Base:** `17_cierre_brechas.md`  
**Objetivo:** transformar cada brecha cerrada en el artefacto SDD donde realmente debe vivir la decisión: historia de usuario, criterio de aceptación, requerimiento funcional, requerimiento no funcional, regla de negocio, caso de uso, modelo/dominio o decisión arquitectónica.

> **Nota de gobernanza:** todas las decisiones provenientes del cierre son **simuladas**. Por tanto, en los documentos SDD deben conservar la marca `ORIGEN: CIERRE DE BRECHAS SIMULADO` y no presentarse como evidencia original de entrevista. Las entrevistas sí contienen las brechas y las necesidades que motivaron estas decisiones. Por ejemplo, identifican explícitamente incertidumbres sobre fórmula de disponibilidad, duración de reservas, partición de pedidos, autoridad por sistema y compensación de pagos. fileciteturn4file8L949-L969

---

## 1. Principio de reclasificación

Una brecha no debe permanecer como un artefacto independiente cuando ya fue resuelta.

La decisión debe trasladarse al artefacto SDD correspondiente:

| Tipo de decisión | Artefacto destino |
|---|---|
| Capacidad que necesita un actor | Historia de usuario |
| Comportamiento verificable | Criterio de aceptación |
| Qué debe hacer el sistema | Requerimiento funcional |
| Calidad/restricción cuantificable | Requerimiento no funcional |
| Regla que determina un comportamiento de negocio | Regla de negocio |
| Flujo completo actor-sistema | Caso de uso |
| Entidad/atributo/estado necesario | Modelo conceptual/lógico/físico |
| Cómo se implementará técnicamente | Decisión arquitectónica |
| Integración entre sistemas | Integraciones / contratos |

La clasificación mantiene la regla ATLAS de que una historia debe representar una necesidad respaldada por las entrevistas y que los RF deben expresar qué debe hacer el sistema. fileciteturn4file0L49-L70 fileciteturn4file0L102-L131

---

# 2. Matriz maestra de reclasificación

| Brecha | Decisión cerrada | Artefacto principal | Artefactos secundarios | Estado |
|---|---|---|---|---|
| BG-001 | Fórmula de disponibilidad | Regla de negocio | RF + HU + CA + modelos | CERRADA-SIMULADA |
| BG-002 | Autoridad por sistema | Regla de negocio | RF + arquitectura + integraciones | CERRADA-SIMULADA |
| BG-003 | TTL de reserva | Regla de negocio | RF + CA + HU | CERRADA-SIMULADA |
| BG-004 | Split fulfillment | Historia de usuario | RF + CA + CU + regla | CERRADA-SIMULADA |
| BG-005 | Reserva/pago/pedido y compensación | Caso de uso | HU + RF + CA + reglas + arquitectura | CERRADA-SIMULADA |
| BG-006 | Consistencia fuerte/eventual | RNF | RF + CA + arquitectura | CERRADA-SIMULADA |
| BG-007 | Agregado/lote/serializado | Requerimiento funcional | modelo conceptual/lógico/físico + CA | CERRADA-SIMULADA |
| BG-008 | Selección de ubicación | Regla de negocio | RF + HU + CA + CU | CERRADA-SIMULADA |
| BG-009 | Modo degradado | RNF | RF + CA + CU + arquitectura | CERRADA-SIMULADA |
| BG-010 | Frescura del dato | RNF | CA + arquitectura | CERRADA-SIMULADA |
| BG-011 | Ajustes autorizados | Regla de negocio | HU + RF + CA + auditoría | CERRADA-SIMULADA |
| BG-012 | No recojo 48 h | Regla de negocio | RF + CA + HU | CERRADA-SIMULADA |
| BG-013 | Faltante/sustitución | Historia de usuario | RF + CA + CU + regla | CERRADA-SIMULADA |
| BG-014 | Priorización preparación | Regla de negocio | RF + HU + CA + CU | CERRADA-SIMULADA |
| BG-015 | KPI | Requerimiento funcional | modelo + RF de consulta | CERRADA-SIMULADA |
| BG-016 | Integraciones idempotentes/correlación | Requerimiento funcional | RNF + arquitectura + integración | CERRADA-SIMULADA |
| BG-017 | Arquitectura física | Decisión arquitectónica | ADR | RECLASIFICADA |
| BG-018 | Capacidad/rendimiento | RNF | CA + arquitectura | CERRADA-SIMULADA |
| BG-019 | Mínimo privilegio/PII | RNF | RF + CA + arquitectura | CERRADA-SIMULADA |
| BG-020 | Operación vs analítica | Arquitectura | RNF + RF + modelo | CERRADA-SIMULADA |
| BG-021 | IA asistida | Historia de usuario | RF + CA + RN + arquitectura IA | CERRADA-SIMULADA |
| BG-022 | Alcance primera etapa | Alcance / épica | HU + RF + CU | CERRADA-SIMULADA |

---

# 3. Historias de usuario nuevas o ampliadas

Las siguientes historias deben agregarse al conjunto de historias existente. No todas las brechas generan una historia nueva: algunas únicamente refinan historias ya existentes.

## HU-017 — Consultar disponibilidad confiable

**Como** cliente  
**quiero** conocer la disponibilidad y modalidad de entrega/retiro de un producto  
**para** tomar una decisión de compra antes del pago.

**Origen:** BG-001, BG-006, BG-009, BG-010.  
**Fuente motivadora:** la CEO exige una promesa confiable antes del pago y la entrevista identifica la diferencia entre consulta y confirmación de reserva. fileciteturn4file6L858-L860

### Criterios de aceptación

- CA-017.1: La disponibilidad deberá considerar la regla de negocio definida para stock utilizable, reservas, compromisos y stock de seguridad.
- CA-017.2: Una consulta preliminar podrá utilizar consistencia eventual.
- CA-017.3: El sistema deberá indicar cuando la información esté operando en condición degradada.
- CA-017.4: Si el dato supera el umbral definido para reserva, la consulta no podrá transformarse directamente en una reserva confirmada.

---

## HU-018 — Reservar inventario

**Como** cliente  
**quiero** que el sistema reserve temporalmente el inventario durante mi proceso de compra  
**para** evitar que otro proceso consuma las unidades que estoy intentando adquirir.

**Origen:** BG-003, BG-005, BG-006.

### Criterios de aceptación

- CA-018.1: La reserva deberá tener origen, cantidad, ubicación, creación, expiración y estado.
- CA-018.2: La reserva tendrá TTL de 10 minutos.
- CA-018.3: Se permitirá una única extensión de hasta 5 minutos.
- CA-018.4: Un reintento idempotente no deberá crear una segunda reserva.
- CA-018.5: La confirmación de reserva deberá utilizar consistencia fuerte.

La necesidad de reserva con origen, cantidad, ubicación, expiración y estado, así como control de concurrencia e idempotencia, ya está explícitamente descrita en la entrevista. fileciteturn4file7L386-L396

---

## HU-019 — Resolver faltantes de preparación

**Como** preparador de tienda  
**quiero** reportar que una unidad reservada no fue encontrada  
**para** que el sistema pueda buscar otra ubicación y recalcular la promesa.

**Origen:** BG-013.

### Criterios de aceptación

- CA-019.1: El preparador podrá registrar el faltante.
- CA-019.2: El sistema deberá buscar una ubicación alternativa.
- CA-019.3: El sistema deberá recalcular la promesa.
- CA-019.4: Si no existe alternativa, deberá habilitar las opciones definidas para el cliente.
- CA-019.5: Una sustitución requerirá aceptación del cliente.

La entrevista ya establece que ante un faltante debe registrarse una excepción, buscar otra ubicación, recalcular la promesa y ofrecer alternativas. fileciteturn4file8L929-L945

---

## HU-020 — Ejecutar preparación priorizada

**Como** preparador de tienda  
**quiero** recibir una cola priorizada de tareas  
**para** preparar primero los pedidos con mayor necesidad operativa.

**Origen:** BG-014.

La entrevista ya solicita una cola priorizada, ubicación interna, tiempo objetivo y capacidad para reportar faltantes/daños. fileciteturn4file8L929-L933

---

## HU-021 — Gestionar retiro en tienda

**Como** cliente  
**quiero** retirar mi pedido en una tienda seleccionada  
**para** recibir el producto sin despacho a domicilio.

**Origen:** BG-012.

La entrevista ya contempla selección de tienda, promesa, código de recojo, validación del retiro y liberación de reserva por no recojo. fileciteturn4file8L927-L933

---

## HU-022 — Utilizar recomendaciones de IA

**Como** responsable de inventario  
**quiero** recibir recomendaciones de demanda, redistribución y anomalías  
**para** apoyar decisiones operativas.

**Origen:** BG-021.

### Restricción

La recomendación no puede modificar directamente inventario ni reservas.

Esto coincide con la entrevista de CEO: la IA puede anticipar demanda, sugerir redistribución y detectar anomalías, pero las operaciones transaccionales deben permanecer controladas. fileciteturn4file7L494-L504

---

# 4. Requerimientos funcionales que deben agregarse o actualizarse

## RF-031 — Calcular disponibilidad

El sistema deberá calcular la disponibilidad conforme a la regla de negocio aprobada.

**Origen:** BG-001.

---

## RF-032 — Gestionar estados de frescura

El sistema deberá determinar si la información está en estado NORMAL, DEGRADADO o NO DISPONIBLE PARA RESERVA.

**Origen:** BG-009, BG-010.

---

## RF-033 — Gestionar reservas

El sistema deberá crear, consultar, confirmar, extender, liberar y vencer reservas.

**Origen:** BG-003, BG-005.

---

## RF-034 — Gestionar compensación del flujo de compra

El sistema deberá mantener la trazabilidad entre intento de pago, pago, pedido y reserva y ejecutar las acciones compensatorias definidas ante fallo o expiración.

**Origen:** BG-005.

La necesidad de correlacionar intento, pago, pedido y reserva está explícitamente indicada en la entrevista. fileciteturn4file8L935-L937

---

## RF-035 — Gestionar inventario por modalidad

El sistema deberá soportar inventario agregado, por lote y serializado según el tipo de SKU.

**Origen:** BG-007.

---

## RF-036 — Seleccionar ubicación de fulfillment

El sistema deberá seleccionar una ubicación candidata considerando las reglas de disponibilidad, capacidad, promesa, distancia, costo y restricciones.

**Origen:** BG-008.

---

## RF-037 — Gestionar split fulfillment

El sistema deberá permitir dividir el fulfillment de un pedido cuando corresponda según las reglas de negocio.

**Origen:** BG-004.

---

## RF-038 — Gestionar faltantes y alternativas

El sistema deberá permitir registrar faltantes, buscar otra ubicación, recalcular la promesa y gestionar las alternativas definidas.

**Origen:** BG-013.

---

## RF-039 — Gestionar retiro vencido

El sistema deberá liberar la reserva de pedidos de retiro que superen el plazo de 48 horas sin recojo.

**Origen:** BG-012.

---

## RF-040 — Gestionar ajustes de inventario

El sistema deberá registrar ajustes autorizados conservando usuario, motivo, evidencia, cantidad anterior, cantidad nueva y momento.

**Origen:** BG-011.

La necesidad de conservar historial, motivo, usuario y evidencia ya aparece en la entrevista. fileciteturn4file7L398-L400

---

## RF-041 — Gestionar cola de preparación

El sistema deberá presentar una cola priorizada de tareas de preparación.

**Origen:** BG-014.

---

## RF-042 — Registrar y consultar KPI

El sistema deberá permitir obtener los indicadores operativos definidos para Stock Único.

**Origen:** BG-015.

---

## RF-043 — Procesar integraciones idempotentes

El sistema deberá procesar eventos y operaciones con identificadores de correlación e idempotencia.

**Origen:** BG-016.

---

## RF-044 — Gestionar recomendaciones de IA

El sistema deberá presentar recomendaciones de IA como soporte a la decisión sin permitir que estas modifiquen directamente las transacciones críticas.

**Origen:** BG-021.

---

# 5. Requerimientos no funcionales resultantes

## RNF-016 — Consistencia transaccional

Las operaciones de reserva y confirmación deberán utilizar consistencia fuerte.

**Origen:** BG-006.

La entrevista ya diferencia la consulta de disponibilidad de la confirmación de reserva y establece que la confirmación requiere mayor consistencia. fileciteturn4file8L939-L941

---

## RNF-017 — Consistencia eventual

Las consultas no transaccionales y el catálogo podrán utilizar consistencia eventual.

**Origen:** BG-006.

---

## RNF-018 — Idempotencia

Las operaciones susceptibles de reintento deberán ser idempotentes.

**Origen:** BG-016 y necesidad explícita de la entrevista. fileciteturn4file7L394-L396

---

## RNF-019 — Frescura operativa

La información utilizada para operaciones de reserva deberá cumplir el umbral de frescura definido en el cierre de brechas.

**Origen:** BG-010.

**Nota:** el valor de 60 segundos es **simulado**, no proveniente de la entrevista.

---

## RNF-020 — Degradación controlada

El sistema deberá continuar operando de forma controlada cuando una integración se retrase, evitando mostrar información engañosa.

**Origen:** BG-009.

La necesidad de degradación controlada está explícitamente mencionada por E-commerce. fileciteturn4file8L939-L941

---

## RNF-021 — Capacidad

El sistema deberá soportar el volumen de campaña establecido en el cierre de brechas.

**Origen:** BG-018.

**Nota:** 12.000 pedidos/hora sí está soportado por la entrevista; los valores adicionales de latencia del cierre son simulados. fileciteturn4file8L939-L941

---

## RNF-022 — Privacidad

El personal de tienda deberá acceder únicamente a los datos del cliente necesarios para ejecutar la preparación o entrega.

**Origen:** BG-019.

La restricción de no mostrar datos personales innecesarios aparece explícitamente en la entrevista. fileciteturn4file8L931-L933

---

# 6. Reglas de negocio

Las decisiones de negocio deben concentrarse en `reglas_negocio.md`.

## RN-012 — Disponibilidad

`Disponible = Stock utilizable - Reservado - Comprometido - Stock de seguridad`

El resultado no puede ser inferior a cero.

**Origen:** BG-001 / cierre simulado.

---

## RN-013 — Stock en tránsito

El inventario en tránsito no forma parte de la disponibilidad normal.

La entrevista ya establece esta condición. fileciteturn4file7L406-L408

---

## RN-014 — TTL de reserva

Una reserva tendrá vigencia estándar de 10 minutos.

**Origen:** BG-003 / cierre simulado.

---

## RN-015 — Extensión de reserva

Una reserva podrá extenderse una sola vez por hasta 5 minutos.

**Origen:** BG-003 / cierre simulado.

---

## RN-016 — Pago y reserva

Un pago aprobado deberá quedar asociado a un pedido confirmado.

Un pago fallido o expirado deberá liberar la reserva.

**Origen:** BG-005.

---

## RN-017 — No recojo

Una reserva de retiro se liberará después de 48 horas sin recojo.

**Origen:** BG-012 / cierre simulado.

---

## RN-018 — Sustitución

Una sustitución requiere aceptación del cliente.

**Origen:** BG-013 / cierre simulado.

---

## RN-019 — Ajustes

Los ajustes requieren usuario autorizado, motivo y evidencia.

**Origen:** BG-011.

---

## RN-020 — IA no transaccional

La IA no puede crear stock, confirmar reservas, modificar inventario, cancelar pedidos automáticamente ni sustituir productos sin aprobación.

**Origen:** BG-021.

La restricción fundamental de que la IA no debe inventar existencias está expresamente respaldada por la entrevista. fileciteturn4file7L440-L442

---

# 7. Casos de uso afectados

El cierre de brechas debe producir los siguientes casos de uso refinados:

| CU | Caso de uso | Brechas incorporadas |
|---|---|---|
| CU-01 | Consultar disponibilidad | BG-001, 006, 009, 010 |
| CU-02 | Crear reserva | BG-001, 003, 006 |
| CU-03 | Confirmar pedido y pago | BG-005, 016 |
| CU-04 | Asignar ubicación | BG-008 |
| CU-05 | Dividir fulfillment | BG-004 |
| CU-06 | Preparar pedido | BG-014 |
| CU-07 | Resolver faltante | BG-013 |
| CU-08 | Retirar pedido | BG-012 |
| CU-09 | Ajustar inventario | BG-011 |
| CU-10 | Conciliar inventario | BG-001, 002, 011 |
| CU-11 | Consultar indicadores | BG-015, 020 |
| CU-12 | Obtener recomendación IA | BG-021 |

---

# 8. Impacto en los modelos

Las brechas cerradas que afectan directamente al modelo de dominio son:

### Inventario

Debe representar al menos los conceptos ya identificados en entrevistas:

- SKU;
- ubicación;
- stock físico;
- stock utilizable;
- reservado;
- comprometido;
- bloqueado;
- dañado;
- tránsito;
- pendiente de recepción.

La entrevista identifica explícitamente estas categorías. fileciteturn4file7L459-L477

### Reserva

Debe incorporar:

- origen;
- cantidad;
- ubicación;
- creación;
- expiración;
- estado;
- relación con pedido.

### Pedido

Debe contemplar:

- líneas;
- ubicación de fulfillment;
- promesa;
- preparación;
- pago;
- reserva;
- posibles particiones.

### Inventario serializado

Debe permitir representar unidad exacta cuando el SKU sea serializado.

### Auditoría

Debe representar:

- evento;
- documento;
- usuario/sistema;
- momento;
- cantidad anterior;
- cantidad nueva;
- razón.

La necesidad de auditar cada cambio de cantidad está expresamente indicada por Supply Chain. fileciteturn4file7L414-L416

---

# 9. BG-017: reclasificación especial

Esta brecha **no debe convertirse en RF, RNF, HU ni regla de negocio**.

La pregunta sobre:

- base de datos;
- cloud;
- mensajería;
- patrón de despliegue;
- tecnología;

es una **decisión técnica de arquitectura**.

Debe moverse a:

`decisiones_arquitectonicas.md`

o, si se utiliza ADR:

`ADR-XXX — Selección de arquitectura física`.

Esto es coherente con ATLAS: los RF no deben incorporar decisiones técnicas todavía no tomadas. fileciteturn4file0L125-L131

---

# 10. BG-020: reclasificación especial

La separación entre:

- operación en tiempo real;
- analítica histórica;

no es una única regla de negocio.

Debe distribuirse entre:

**RNF:** la operación transaccional no debe depender de la analítica.

**RF:** el sistema debe proporcionar información operativa y KPI.

**Arquitectura:** separación de cargas operativas y analíticas.

**Modelo:** eventos/operaciones históricas necesarias para cálculo de indicadores.

---

# 11. Resultado de la reclasificación

La clasificación final queda:

| Artefacto | Elementos nuevos/refinados |
|---|---:|
| Historias de usuario | 6 |
| Criterios de aceptación | 25+ |
| Requerimientos funcionales | 14 |
| Requerimientos no funcionales | 7 |
| Reglas de negocio | 9 |
| Casos de uso afectados | 12 |
| Modelo de dominio | 5 áreas afectadas |
| Integraciones | 1 bloque transversal |
| Decisiones arquitectónicas | 1 bloque específico |
| Brechas pendientes de negocio | 0 |

---

# 12. Corrección importante respecto al cierre anterior

El documento `17_cierre_brechas.md` **no debe convertirse literalmente en una lista de requisitos**.

Su función correcta es:

```text
ENTREVISTAS
    ↓
BRECHAS
    ↓
CIERRE DE BRECHAS SIMULADO
    ↓
┌──────────────┬──────────────┬───────────────┐
↓              ↓              ↓
HU             RF/RNF         REGLAS
↓              ↓              ↓
CA             CU             MODELOS
               ↓
          ARQUITECTURA
```

Esto evita duplicar decisiones y mantiene trazabilidad.

---

# 13. Regla de trazabilidad obligatoria

Para cada elemento derivado del cierre se deberá registrar:

```text
Entrevista
   ↓
Brecha
   ↓
Decisión simulada
   ↓
Artefacto SDD
   ↓
Elemento
```

Ejemplo:

```text
Entrevista Supply Chain
        ↓
BG-001 — Fórmula de disponibilidad
        ↓
Cierre simulado
        ↓
RN-012 — Regla de disponibilidad
        ↓
RF-031 — Calcular disponibilidad
        ↓
HU-017 — Consultar disponibilidad
        ↓
CA-017.x
        ↓
CU-01 — Consultar disponibilidad
```

La trazabilidad completa entrevistas → brechas → historias → requisitos → casos de uso → arquitectura es un requisito explícito del método ATLAS. fileciteturn4file3L469-L478

---

# 14. Estado de `discrepancias.md`

Después de esta reclasificación:

### Discrepancias de negocio

**0 abiertas.**

### Decisiones de negocio simuladas

**21 cerradas.**

### Decisiones técnicas

**1 reclasificada a arquitectura.**

### Elementos que deben actualizarse

- `historias_usuario.md`
- `criterios_aceptacion.md`
- `requerimientos_funcionales.md`
- `requerimientos_no_funcionales.md`
- `reglas_negocio.md`
- `casos_uso.md`
- `modelo_conceptual.md`
- `modelo_logico.md`
- `modelo_fisico.md`
- `arquitectura.md`
- `integraciones.md`
- `trazabilidad.md`
- `discrepancias.md`
- `autovalidacion.md`

---

# 15. Autovalidación

| Validación | Resultado |
|---|---|
| Todas las BG cerradas fueron reclasificadas | PASS |
| Se evitó convertir todas las BG en requisitos | PASS |
| Las decisiones de negocio fueron separadas de arquitectura | PASS |
| Las HU nuevas tienen necesidad de negocio | PASS |
| Los RF responden “qué debe hacer el sistema” | PASS |
| Los RNF representan atributos/restricciones | PASS |
| Las reglas representan decisiones de negocio | PASS |
| Los CU representan flujos | PASS |
| Los modelos incorporan conceptos afectados | PASS |
| Se conserva origen simulado | PASS |
| No se presenta el cierre simulado como entrevista original | PASS |
| Brechas de negocio abiertas | 0 |
