# 11. Criterios de aceptación

## Disponibilidad

- La consulta diferencia existencia física y disponibilidad operativa.
- La respuesta identifica la ubicación candidata cuando exista.
- La respuesta permite conocer la frescura del dato.
- La fórmula exacta es una decisión previa al desarrollo.

## Reserva

- Registra origen, cantidad, ubicación, creación, expiración y estado.
- Es idempotente ante reintentos.
- Controla concurrencia.
- Permite estados confirmada, liberada y vencida.
- El traslado requiere reglas aprobadas.

## Pago/pedido

- Existe correlación entre intento, pago, reserva y pedido.
- Un pago aprobado mantiene trazabilidad del pedido.
- Una reserva confirmada puede rastrearse hasta pedido, liberación o compensación.

## Asignación/promesa

- La selección considera los factores declarados por Supply Chain.
- Soporta despacho y retiro.
- Un faltante permite iniciar reasignación.
- Una reasignación puede recalcular la promesa.

## Preparación

- La tarea muestra SKU, cantidad, ubicación cuando exista, tiempo objetivo y estado.
- Permite validar SKU/cantidad.
- Permite registrar faltante/daño.
- Limita datos personales al mínimo necesario.

## Retiro

- Se selecciona tienda con disponibilidad válida.
- El código de recojo se genera cuando el pedido está listo.
- Se valida al retirante y se registra la entrega.
- El vencimiento del plazo de recojo debe aplicar una regla de negocio definida.

## Inventario/auditoría

- Cada cambio conserva origen, documento, actor, momento, valor anterior, valor nuevo y razón.
- Se pueden detectar duplicados y eventos fuera de orden.

## Degradación

- Se identifica la frescura del dato.
- Se aplica un comportamiento degradado definido.
- La reserva conserva mayores garantías que la consulta.

## IA

- Una recomendación queda diferenciada de una transacción.
- Una predicción no crea ni modifica stock.

## Estado de los criterios

Los criterios anteriores son base para SDD. Los criterios que dependen de valores no definidos deben convertirse en criterios verificables después del cierre de las preguntas correspondientes.
