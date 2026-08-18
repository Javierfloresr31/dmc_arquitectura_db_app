# Especificación SDD — Criterios de Aceptación
## Stock Único — NovaRetail

Los criterios se derivan exclusivamente de las entrevistas. Cada criterio está asociado a una historia de usuario.

### CA-HU-01 — Disponibilidad

- **Dado** un SKU y una modalidad de entrega, **cuando** el cliente consulta disponibilidad, **entonces** la respuesta debe considerar la disponibilidad de la ubicación candidata y mostrar una fecha/promesa.
- **Dado** que la información utilizada esté retrasada, **cuando** se responda al cliente, **entonces** el sistema debe evitar mostrar datos engañosos y operar de forma degradada controlada.
- **Dado** un producto físicamente existente pero no apto para venta digital, **cuando** se calcule disponibilidad, **entonces** no debe tratarse automáticamente como disponible para venta digital.

**Trazabilidad:** líneas 77–79, 101–103, 116–126, 148–150.

### CA-HU-02 — Reserva

- **Dado** un intento de checkout, **cuando** corresponda reservar, **entonces** la reserva debe tener origen, cantidad, ubicación, fecha de creación, expiración y estado.
- Una reserva debe poder confirmarse, liberarse, vencer o trasladarse según reglas controladas.
- La reserva no debe producirse simplemente por visitar la página.

**Trazabilidad:** líneas 81–87, 120–122.

**Pendiente:** duración exacta de expiración.

### CA-HU-03 — Concurrencia e idempotencia

- Un reintento del checkout no debe generar una segunda reserva para la misma operación.
- Las operaciones concurrentes deben impedir la doble asignación de las mismas unidades.
- Para productos serializados, la solución deberá contemplar identificación de unidad exacta cuando corresponda.

**Trazabilidad:** líneas 85–87.

### CA-HU-04 — Pago/pedido/reserva

- Un intento de pago debe poder correlacionarse con pago, pedido y reserva.
- Un pago aprobado no debe finalizar sin pedido.
- Una reserva confirmada debe conservar trazabilidad.

**Trazabilidad:** líneas 144–146.

### CA-HU-05 — Asignación

- La selección de ubicación debe considerar, como mínimo, disponibilidad, distancia, capacidad de preparación, horario, costo, prioridad de tienda, fecha prometida y restricciones del producto.
- La solución debe permitir que algunas tiendas no preparen pedidos durante determinadas horas.

**Trazabilidad:** líneas 93–95.

### CA-HU-06 — Preparación

- Una vez confirmado el pedido y asignada la ubicación, debe generarse una tarea de preparación.
- La ubicación debe aceptar la tarea.
- El preparador debe poder recoger, validar SKU/cantidad, embalar y marcar listo.
- La operación debe disponer de una cola priorizada y tiempo objetivo.
- Debe poder reportar faltantes o daños y escanear el SKU.

**Trazabilidad:** líneas 128–130, 140–142.

### CA-HU-07 — Faltante

- Si una ubicación no encuentra una unidad reservada, debe registrarse una excepción.
- Debe buscarse otra ubicación.
- La promesa debe poder recalcularse.
- El cliente debe recibir opciones: nueva fecha, cambio de tienda, sustituto o devolución.

**Trazabilidad:** líneas 132–134.

### CA-HU-08 — Retiro en tienda

- El cliente debe poder elegir una tienda con disponibilidad.
- Debe recibir una promesa.
- Cuando el pedido esté listo, debe generarse un código de recojo.
- Debe validarse quién retira.
- Debe registrarse la entrega.
- La reserva debe poder liberarse si no se recoge dentro del plazo.

**Trazabilidad:** líneas 136–138.

**Pendiente:** plazo exacto de recojo.

### CA-HU-09 — Auditoría

- Cada cambio de cantidad debe conservar evento de origen, documento relacionado, usuario/sistema, momento, cantidad anterior, cantidad nueva y razón.
- Debe conservarse la secuencia de eventos para detectar duplicados o eventos fuera de orden.
- Los ajustes deben conservar motivo, usuario y evidencia.

**Trazabilidad:** líneas 89–91, 105–107.

### CA-HU-10 — Transferencias

- Una transferencia debe registrar origen, destino, unidades solicitadas, despachadas, recibidas y diferencias.
- El stock en tránsito no debe aparecer como disponible hasta la recepción.
- El modelo futuro de promesa sobre stock en camino no debe asumirse como parte del alcance actual.

**Trazabilidad:** líneas 97–99.

### CA-HU-11 — Alto volumen y latencia

- Las ventas y reservas requieren respuesta en segundos.
- En campañas debe soportarse el contexto declarado de miles de consultas por segundo y picos de 12,000 pedidos por hora.
- El checkout no debe demorarse excesivamente para obtener una disponibilidad perfecta.

**Trazabilidad:** líneas 54–56, 101–103, 148–150.

> **Nota:** las entrevistas no definen valores concretos de SLA, TPS, disponibilidad porcentual, RTO o RPO. No se agregan.

### CA-HU-12 — Protección de datos

- El personal de tienda no debe visualizar datos personales del cliente que no sean necesarios para preparar el pedido.

**Trazabilidad:** líneas 140–142.

### CA-HU-13 — Indicadores

Debe existir capacidad de medir:
- cancelación por falta de stock;
- exactitud de inventario;
- pedidos entregados dentro de la promesa;
- ventas omnicanal;
- rotación;
- quiebres;
- costo de preparación;
- reservas vencidas sin convertirse en venta;
- conversión;
- abandono;
- productos sin disponibilidad;
- tiempo de preparación;
- reasignaciones;
- cancelaciones;
- sustituciones;
- origen del problema: inventario, pago, logística o tienda.

**Trazabilidad:** líneas 34–36, 152–154.

### CA-HU-14 — IA

- La IA puede recomendar anticipación de demanda, redistribución y detección de anomalías.
- La IA no puede crear existencias.
- La reserva y el descuento de stock deben ejecutarse mediante transacciones controladas.

**Trazabilidad:** líneas 50–52.
