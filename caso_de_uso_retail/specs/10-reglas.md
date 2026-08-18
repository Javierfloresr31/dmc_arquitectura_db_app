# 10. Reglas de negocio

## RN-01 — Disponibilidad no equivale a stock físico

Una unidad físicamente existente puede no ser apta para venta digital. La disponibilidad debe considerar el estado operativo y reglas de negocio.

## RN-02 — Reserva temporal

Una reserva asigna temporalmente unidades a una intención de compra o pedido y debe registrar origen, cantidad, ubicación, creación, expiración y estado.

## RN-03 — No doble reserva

Los reintentos no deben generar otra reserva y debe existir control de concurrencia.

## RN-04 — Stock en tránsito

No debe considerarse disponible hasta la recepción, salvo aprobación futura de una política explícita sobre stock en camino.

## RN-05 — Ajuste con historial

Conteos y ajustes deben conservar motivo, usuario y evidencia.

## RN-06 — Asignación multidimensional

La ubicación puede depender de disponibilidad, distancia, capacidad, horario, costo, prioridad, fecha prometida y restricciones del producto. Las ponderaciones son pendientes.

## RN-07 — Excepción de preparación

Si la tienda no encuentra la unidad reservada, debe registrar la excepción, buscar otra ubicación y recalcular la promesa.

## RN-08 — Alternativas al cliente

Ante cambio de promesa, las entrevistas mencionan nueva fecha, cambio de tienda, sustituto o devolución. La elegibilidad concreta de cada alternativa debe ser definida.

## RN-09 — IA no modifica stock por sí sola

La IA puede recomendar demanda, redistribución o anomalías, pero no puede inventar existencias ni actuar como autoridad transaccional.

## RN-10 — Integración gradual

No se reemplazan todos los sistemas actuales en una sola etapa.

## RN-11 — Frescura explícita

Cuando la información de inventario esté retrasada, el sistema debe distinguir la vista degradada de una disponibilidad confirmada.

## RN-12 — Pedidos multi-SKU

La estrategia de salida conjunta o dividida no está definida y debe resolverse antes del diseño definitivo.
