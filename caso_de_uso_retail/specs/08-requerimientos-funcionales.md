# 08. RF — Requerimientos funcionales

| ID | Requerimiento |
|---|---|
| RF-01 | Consultar disponibilidad por SKU, ubicación y modalidad. |
| RF-02 | Diferenciar stock físico de disponibilidad operativa. |
| RF-03 | Crear reservas temporales con origen, cantidad, ubicación, creación, expiración y estado. |
| RF-04 | Controlar concurrencia para evitar doble reserva. |
| RF-05 | Garantizar idempotencia en reintentos. |
| RF-06 | Confirmar, liberar, vencer y trasladar reservas según reglas aprobadas. |
| RF-07 | Correlacionar intento de pago, pago, reserva y pedido. |
| RF-08 | Asignar ubicación candidata. |
| RF-09 | Generar y recalcular promesa. |
| RF-10 | Soportar despacho a domicilio y retiro en tienda. |
| RF-11 | Generar tareas de preparación. |
| RF-12 | Priorizar tareas de preparación. |
| RF-13 | Validar SKU/cantidad y registrar faltantes/daños. |
| RF-14 | Reasignar pedidos ante faltantes. |
| RF-15 | Gestionar alternativas ante excepciones. |
| RF-16 | Generar código de recojo y registrar entrega. |
| RF-17 | Gestionar liberación por no retiro según regla definida. |
| RF-18 | Registrar movimientos, conteos y ajustes autorizados. |
| RF-19 | Mantener auditoría y secuencia de eventos. |
| RF-20 | Procesar movimientos en tiempo real y por lote. |
| RF-21 | Detectar duplicados y eventos fuera de orden. |
| RF-22 | Informar frescura del dato. |
| RF-23 | Aplicar modo degradado controlado. |
| RF-24 | Proteger datos personales según necesidad del rol. |
| RF-25 | Exponer indicadores operativos. |
| RF-26 | Separar recomendaciones de IA de transacciones de inventario. |
| RF-27 | Registrar transferencias con origen, destino, solicitadas, despachadas, recibidas y diferencias. |

## Validación

Los RF-01 a RF-26 se corresponden con necesidades explícitas o directamente derivadas de las entrevistas. RF-27 deriva de la descripción explícita del proceso de transferencias. No se han agregado reglas de negocio no mencionadas.
