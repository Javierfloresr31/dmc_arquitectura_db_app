# 3. Requerimientos funcionales y no funcionales

## 3.1 Requerimientos funcionales

| ID | Requerimiento | Prioridad inicial | Fuente |
|---|---|---|---|
| RF-01 | Consultar disponibilidad por SKU, ubicación y modalidad | Alta | CEO 3,6; SC 3,7; EC 3 |
| RF-02 | Diferenciar stock físico de disponibilidad operativa | Alta | CEO 2; SC 1,3 |
| RF-03 | Crear reservas temporales con origen, cantidad, ubicación, creación, expiración y estado | Alta | SC 4 |
| RF-04 | Controlar concurrencia para evitar doble reserva | Alta | SC 5 |
| RF-05 | Soportar operaciones idempotentes y reintentos | Alta | SC 5; EC 8 |
| RF-06 | Confirmar, liberar, vencer y eventualmente trasladar reservas | Alta | SC 4; EC 2 |
| RF-07 | Correlacionar intento de pago, pago, reserva y pedido | Alta | EC 8 |
| RF-08 | Asignar ubicación candidata para preparación | Alta | SC 7; EC 4 |
| RF-09 | Generar y gestionar una promesa de entrega | Alta | CEO 3,6; EC 3 |
| RF-10 | Soportar despacho a domicilio y retiro en tienda | Alta | Contexto; EC 6 |
| RF-11 | Generar tareas de preparación | Alta | EC 4,7 |
| RF-12 | Priorizar tareas de preparación | Media | EC 7 |
| RF-13 | Registrar validación de SKU/cantidad y faltantes/daños | Alta | EC 4,7 |
| RF-14 | Reasignar un pedido cuando la ubicación no encuentra el producto | Alta | EC 5 |
| RF-15 | Recalcular la promesa ante reasignación | Alta | EC 5 |
| RF-16 | Ofrecer alternativas ante excepción: fecha, tienda, sustituto o devolución | Media | EC 5 |
| RF-17 | Gestionar retiro con código de recojo y registro de entrega | Alta | EC 6 |
| RF-18 | Liberar reserva ante no retiro según regla definida | Media | EC 6 |
| RF-19 | Registrar movimientos de inventario y ajustes autorizados | Alta | SC 2,6 |
| RF-20 | Mantener auditoría de cambios y secuencia de eventos | Alta | SC 10 |
| RF-21 | Procesar eventos de inventario en tiempo casi real y también lotes | Alta | SC 2,9 |
| RF-22 | Detectar o manejar duplicados y eventos fuera de orden | Alta | SC 10 |
| RF-23 | Informar frescura/estado de los datos y soportar modo degradado | Alta | CEO 9,10; EC 9 |
| RF-24 | Proteger datos personales mostrando solo lo necesario al preparador | Alta | EC 7 |
| RF-25 | Exponer indicadores operativos mencionados por negocio | Media | CEO 4; EC 10 |
| RF-26 | Separar recomendaciones de IA de las transacciones de inventario | Alta | CEO 8 |

## 3.2 Reglas de negocio evidenciadas

### RN-01 — Disponibilidad no equivale a stock físico

El stock físico puede no ser apto para venta digital. La disponibilidad debe considerar, como mínimo conceptual, stock utilizable, reservas y compromisos; además puede considerar stock de seguridad y reglas por categoría, tienda, campaña y modalidad. La fórmula exacta no está definida.

### RN-02 — Reserva temporal

La reserva es una asignación temporal a una intención de compra o pedido. Debe tener trazabilidad y expiración.

### RN-03 — No doble reserva

El checkout debe usar control de concurrencia e idempotencia.

### RN-04 — Inventario en tránsito

El inventario en tránsito no debe aparecer como disponible hasta la recepción, salvo que en el futuro se apruebe un modelo de promesa sobre stock en camino.

### RN-05 — Ajustes con evidencia

Los conteos y ajustes deben conservar motivo, usuario y evidencia; no debe corregirse el número sin historial.

### RN-06 — IA no es autoridad transaccional

La IA puede recomendar, pero la reserva y el descuento de stock son operaciones controladas.

## 3.3 Requerimientos no funcionales

| ID | Requerimiento | Evidencia |
|---|---|---|
| RNF-01 | La consulta operativa de disponibilidad debe responder en segundos como objetivo de negocio | SC 9 |
| RNF-02 | La solución debe soportar picos declarados de 12.000 pedidos/hora y miles de consultas/segundo como requisito de dimensionamiento a validar | CEO/EC 9 |
| RNF-03 | La confirmación de reserva requiere mayor consistencia que la consulta de catálogo | EC 9 |
| RNF-04 | Las operaciones de reserva deben ser idempotentes | SC 5 |
| RNF-05 | Debe existir control de concurrencia para evitar doble reserva | SC 5 |
| RNF-06 | Debe existir trazabilidad completa de cambios de inventario | SC 10 |
| RNF-07 | Debe existir comportamiento degradado controlado ante retraso de integraciones | CEO 10; EC 9 |
| RNF-08 | Debe protegerse la información personal del cliente según mínimo necesario para el rol | EC 7 |
| RNF-09 | La integración debe permitir evolución gradual sin reemplazar todos los sistemas actuales | CEO 7 |
| RNF-10 | Debe poderse reconstruir la secuencia de eventos de inventario | SC 10 |

### Valores aún no especificados

Las entrevistas no establecen SLO/SLA numéricos, disponibilidad mensual, RPO/RTO, retención de auditoría, límites de payload, timeouts, capacidad exacta de particionado, cifrado, autenticación/autorización técnica ni observabilidad concreta. Deben ser definidos en una fase de requisitos no funcionales detallados.