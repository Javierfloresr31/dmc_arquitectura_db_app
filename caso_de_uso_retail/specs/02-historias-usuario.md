# 2. Historias de usuario y criterios de aceptación

> Los criterios se limitan a comportamientos sustentados por las entrevistas. Donde la regla exacta no está definida, el criterio exige que la regla sea configurable/validada antes del desarrollo, sin inventar su valor.

## HU-01 — Consultar disponibilidad confiable

**Como** cliente o canal de venta, **quiero** consultar disponibilidad por SKU y ubicación/modalidad, **para** recibir una promesa antes de pagar basada en la información operativa disponible.

### Criterios de aceptación

- **CA-01.1:** La consulta distingue la disponibilidad de la mera existencia física.
- **CA-01.2:** La respuesta identifica la ubicación candidata cuando exista una candidata.
- **CA-01.3:** La respuesta informa si la información está actualizada o si se está operando con una vista degradada.
- **CA-01.4:** La fórmula exacta de disponibilidad debe estar definida por negocio antes de cerrar la implementación.

**Trazabilidad:** CEO 2, 3, 6, 9, 10; Supply Chain 1, 3, 7, 9; E-commerce 1, 3, 9.

## HU-02 — Crear reserva temporal

**Como** canal de venta, **quiero** crear una reserva temporal al iniciar el pago/confirmar el pedido, **para** evitar que la misma unidad sea vendida simultáneamente.

### Criterios de aceptación

- **CA-02.1:** La reserva registra origen, cantidad, ubicación, fecha de creación, expiración y estado.
- **CA-02.2:** Un reintento del mismo checkout no crea una segunda reserva.
- **CA-02.3:** La operación controla concurrencia.
- **CA-02.4:** La reserva puede evolucionar al menos por los estados confirmada, liberada y vencida; el traslado requiere reglas adicionales.
- **CA-02.5:** La duración exacta de la reserva debe ser definida por negocio.

**Trazabilidad:** Supply Chain 4, 5; E-commerce 2, 8; CEO 7.

## HU-03 — Correlacionar pago, pedido y reserva

**Como** operación e-commerce, **quiero** correlacionar intento de pago, pago, pedido y reserva, **para** evitar estados sin trazabilidad.

### Criterios de aceptación

- **CA-03.1:** Cada intento de checkout puede correlacionarse con la operación de reserva y el pedido.
- **CA-03.2:** Un pago aprobado no puede quedar sin una situación trazable del pedido.
- **CA-03.3:** Una reserva confirmada debe poder rastrearse hasta el pedido o su liberación/compensación.
- **CA-03.4:** Las reglas exactas de compensación ante fallas distribuidas quedan como decisión abierta.

**Trazabilidad:** E-commerce 8; CEO 7; reto de arquitectura.

## HU-04 — Asignar ubicación y promesa

**Como** operación omnicanal, **quiero** asignar una ubicación candidata y una fecha prometida, **para** cumplir la modalidad solicitada al cliente.

### Criterios de aceptación

- **CA-04.1:** La selección considera los factores mencionados por Supply Chain: disponibilidad, distancia, capacidad de preparación, horario, costo, prioridad de tienda, fecha prometida y restricciones del producto.
- **CA-04.2:** La solución soporta despacho a domicilio y retiro en tienda.
- **CA-04.3:** Si una tienda no encuentra una unidad reservada, se registra una excepción y se permite buscar otra ubicación.
- **CA-04.4:** Una reasignación puede producir un recálculo de promesa.
- **CA-04.5:** Las ponderaciones y prioridades entre factores deben ser definidas por negocio.

**Trazabilidad:** Supply Chain 7; E-commerce 3, 5, 6; CEO 6.

## HU-05 — Ejecutar preparación de pedido

**Como** preparador de tienda, **quiero** recibir una cola priorizada de tareas, **para** recoger y preparar pedidos dentro del tiempo objetivo.

### Criterios de aceptación

- **CA-05.1:** La tarea muestra SKU, cantidad, ubicación cuando exista, tiempo objetivo y estado.
- **CA-05.2:** El preparador puede validar SKU y cantidad.
- **CA-05.3:** El preparador puede registrar faltantes o daños.
- **CA-05.4:** El acceso a datos personales del cliente se limita a lo necesario para preparar el pedido.
- **CA-05.5:** La lógica exacta de priorización debe ser validada con operaciones.

**Trazabilidad:** E-commerce 4, 7.

## HU-06 — Gestionar faltantes y excepciones

**Como** operador de tienda, **quiero** registrar un faltante y buscar otra ubicación, **para** evitar una cancelación automática cuando exista una alternativa.

### Criterios de aceptación

- **CA-06.1:** El faltante queda asociado al pedido, SKU, ubicación y evento que lo originó.
- **CA-06.2:** El sistema permite iniciar búsqueda de otra ubicación.
- **CA-06.3:** La nueva ubicación puede provocar recálculo de promesa.
- **CA-06.4:** El flujo contempla como opciones de negocio nueva fecha, cambio de tienda, sustituto o devolución.
- **CA-06.5:** No se inventa una alternativa si negocio no la ha definido como válida.

**Trazabilidad:** E-commerce 5; Supply Chain 6, 7.

## HU-07 — Gestionar retiro en tienda

**Como** cliente, **quiero** elegir una tienda con disponibilidad y recibir un código cuando el pedido esté listo, **para** retirarlo en la tienda elegida.

### Criterios de aceptación

- **CA-07.1:** La tienda elegida se basa en una disponibilidad válida para retiro.
- **CA-07.2:** El código de recojo se genera cuando el pedido está listo.
- **CA-07.3:** Se valida quién retira y se registra la entrega.
- **CA-07.4:** Si el cliente no recoge dentro del plazo, la reserva puede liberarse según una regla de negocio pendiente de definir.

**Trazabilidad:** E-commerce 6.

## HU-08 — Registrar movimientos y auditoría

**Como** responsable de inventario, **quiero** auditar cada cambio de cantidad, **para** investigar diferencias y mantener trazabilidad.

### Criterios de aceptación

- **CA-08.1:** Cada cambio registra evento de origen, documento relacionado, usuario o sistema, momento, cantidad anterior, cantidad nueva y razón.
- **CA-08.2:** La aplicación conserva la secuencia de eventos para detectar duplicados o eventos fuera de orden.
- **CA-08.3:** Conteos y ajustes requieren autorización según una política de permisos que aún debe definirse.

**Trazabilidad:** Supply Chain 6, 10.

## HU-09 — Operar de forma degradada

**Como** canal de venta, **quiero** continuar operando de forma controlada cuando una integración se retrasa, **para** evitar interrupciones de ventas sin mostrar disponibilidad engañosa.

### Criterios de aceptación

- **CA-09.1:** Se identifica cuándo un dato no está actualizado.
- **CA-09.2:** La aplicación aplica un comportamiento degradado explícito.
- **CA-09.3:** La confirmación de reserva mantiene mayor consistencia que la consulta de catálogo.
- **CA-09.4:** Los límites y reglas exactas del modo degradado deben ser definidos por arquitectura y negocio.

**Trazabilidad:** CEO 9, 10; E-commerce 1, 9; reto de arquitectura.

## HU-10 — Usar IA sin controlar inventario directamente

**Como** responsable de negocio, **quiero** utilizar IA para predicción, redistribución o anomalías, **para** mejorar decisiones operativas sin comprometer la integridad del stock.

### Criterios de aceptación

- **CA-10.1:** Las recomendaciones de IA se distinguen de las transacciones operativas.
- **CA-10.2:** Una predicción no crea ni modifica existencias por sí sola.
- **CA-10.3:** La reserva y el descuento de stock se ejecutan mediante operaciones controladas.

**Trazabilidad:** CEO 8.