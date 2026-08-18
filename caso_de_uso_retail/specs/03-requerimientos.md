# 03. Objetivos

## Objetivo general

Definir y posteriormente implementar una solución **Stock Único** que reduzca la diferencia entre disponibilidad mostrada y disponibilidad real, permita reservas controladas y mejore la confiabilidad de las promesas omnicanal.

## Objetivos de negocio declarados

1. Reducir cancelaciones por falta de stock.
2. Reducir sobreventa.
3. Mejorar la promesa de entrega.
4. Aprovechar inventario de tiendas para pedidos digitales cuando sea conveniente y rentable.
5. Mejorar la exactitud de inventario.
6. Mejorar el porcentaje de ventas omnicanal.
7. Reducir quiebres y costos de preparación.
8. Controlar reservas que vencen sin convertirse en venta.
9. Mantener continuidad de ventas durante retrasos de integraciones.

## Indicadores de éxito mencionados

- Tasa de cancelación por falta de stock.
- Exactitud de inventario.
- Pedidos entregados dentro de la promesa.
- Porcentaje de ventas omnicanal.
- Rotación.
- Quiebres.
- Costo de preparación por pedido.
- Reservas vencidas sin conversión.
- Conversión y abandono.
- Tiempo de preparación.
- Reasignaciones.
- Sustituciones.

## Criterio de éxito

No se establecen metas numéricas en las entrevistas. Por tanto, los indicadores quedan definidos como métricas de negocio y sus metas deberán ser acordadas antes del cierre de la especificación.

## Objetivo de arquitectura

Separar claramente la lectura de disponibilidad de la transacción de reserva y establecer qué dato es autoridad para cada operación, permitiendo integración gradual y procesamiento de eventos casi en tiempo real. 
