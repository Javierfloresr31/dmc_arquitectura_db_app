# 02. Contexto

## Situación actual

NovaRetail opera 78 tiendas físicas, un canal e-commerce y dos centros de distribución. Tiene cerca de 65.000 SKU y durante campañas puede procesar más de 12.000 pedidos por hora. El e-commerce utiliza una réplica de disponibilidad que se actualiza cada varios minutos, mientras el stock cambia más rápido durante campañas. fileciteturn14file1 fileciteturn14file5

El problema observado es la diferencia entre el stock mostrado y la disponibilidad real, causada por fuentes distintas, reservas no siempre sincronizadas y ajustes pendientes. Esto deriva en cancelaciones, sobreventa, traslados urgentes y pérdida de confianza.

## Problema central

No existe una única interpretación operativa de **disponible para vender**. El inventario comprende stock físico, disponible, reservado, comprometido, bloqueado, dañado, en tránsito y pendiente de recepción; además existen productos serializados y productos manejados por lote. fileciteturn14file0

## Necesidad

La organización necesita una solución que permita consultar disponibilidad de forma confiable, reservar temporalmente, coordinar pago/pedido, generar promesas, asignar ubicaciones, preparar pedidos, gestionar excepciones y continuar operando de forma controlada ante retrasos de integración.

## Restricciones relevantes

- No reemplazar todos los sistemas actuales en una sola etapa.
- La reserva y el descuento de stock deben ser transacciones controladas.
- La IA puede recomendar, pero no inventar existencias ni actuar como autoridad transaccional.
- La consulta puede tolerar eventualidad; la confirmación de reserva requiere mayor consistencia.
- En campañas la velocidad es crítica.

## Incertidumbres estructurales

Las entrevistas no definen todavía la fórmula exacta de disponibilidad, duración de reservas, autoridad de cada sistema, partición de pedidos multi-SKU, compensaciones entre pago/reserva/pedido, reglas de modo degradado ni los parámetros de rendimiento.
