# 06. Procesos

## P1. Consultar disponibilidad

1. Canal solicita SKU, cantidad, ubicación/modalidad.
2. Stock Único obtiene la vista operativa.
3. Evalúa disponibilidad según reglas aún pendientes.
4. Informa ubicación candidata y frescura del dato cuando corresponda.

## P2. Reservar inventario

1. Checkout inicia pago/confirmación según regla que debe definirse.
2. Se solicita reserva.
3. Se aplica control de concurrencia.
4. Se garantiza idempotencia.
5. Se registra expiración y estado.
6. Se confirma, libera, vence o traslada según reglas aprobadas.

## P3. Coordinar pago y pedido

1. Se correlaciona intento de pago.
2. Se correlaciona reserva.
3. Se registra/crea pedido.
4. Se procesa el resultado del pago.
5. Se ejecutan compensaciones cuando corresponda.

La semántica exacta ante fallos distribuidos permanece abierta.

## P4. Asignar ubicación y promesa

La selección puede considerar disponibilidad, distancia, capacidad, horario, costo, prioridad de tienda, fecha prometida y restricciones del producto. Las ponderaciones no están definidas. fileciteturn14file4

## P5. Preparar pedido

1. Se genera tarea.
2. Tienda acepta.
3. Preparador recoge.
4. Valida SKU y cantidad.
5. Embala.
6. Marca listo para despacho o retiro.

## P6. Gestionar faltante

1. Preparador registra excepción.
2. Se busca otra ubicación.
3. Se recalcula promesa.
4. Se ofrecen alternativas de negocio cuando estén autorizadas.

## P7. Retiro en tienda

1. Cliente selecciona tienda.
2. Se establece promesa.
3. Tienda prepara.
4. Se genera código de recojo cuando está listo.
5. Se valida retirante.
6. Se registra entrega.
7. Si no recoge, se aplica la regla de liberación pendiente.

## P8. Registrar inventario y auditoría

Los movimientos pueden provenir de POS, pedidos web, recepciones, transferencias, devoluciones, anulaciones, conteos, ajustes, daños, robos y cambios de estado. Algunos llegan en tiempo real y otros por lote. fileciteturn14file2

Cada cambio debe conservar evento de origen, documento, usuario/sistema, momento, cantidad anterior, cantidad nueva y razón.

## P9. Operación degradada

Cuando una integración se retrasa, la solución debe distinguir frescura del dato y aplicar un comportamiento controlado sin mostrar disponibilidad engañosa. Las operaciones permitidas en degradación son una decisión pendiente.

## P10. Analítica e IA

Se contemplan indicadores y recomendaciones para anticipar demanda, redistribuir inventario y detectar anomalías. La IA no es autoridad transaccional.
