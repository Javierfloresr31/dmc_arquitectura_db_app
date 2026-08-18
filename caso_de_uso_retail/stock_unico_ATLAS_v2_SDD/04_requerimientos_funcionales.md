# Stock Único — NovaRetail

**Fuente:** entrevistas del caso `NovaRetail: Stock Único`  
**Método:** PROMPT ATLAS v2.0  
**Regla:** contenido sustentado exclusivamente en las entrevistas; los vacíos se mantienen como pendientes.

| ID | Requerimiento | Estado | Fuente |
|---|---|---|---|
| RF-001 | Consolidar la interpretación operativa de disponibilidad. | CONFIRMADO | CEO P2 |
| RF-002 | Mantener estados de inventario por SKU/ubicación: físico, disponible, reservado, comprometido, bloqueado, dañado, tránsito y pendiente de recepción. | CONFIRMADO | Supply Chain P1 |
| RF-003 | Procesar ventas, pedidos, recepciones, transferencias, devoluciones, anulaciones, conteos, ajustes, daños, robos y cambios de estado. | CONFIRMADO | Supply Chain P2 |
| RF-004 | Calcular disponibilidad considerando stock utilizable, reservas, compromisos y stock de seguridad, sujeto a variación por contexto. | PENDIENTE | Supply Chain P3 |
| RF-005 | Consultar productos, cantidades, ubicación candidata, modalidad, costo, fecha prometida y vigencia de oferta. | CONFIRMADO | E-commerce P3 |
| RF-006 | Crear reservas temporales durante inicio/confirmación del pago. | CONFIRMADO | E-commerce P2 |
| RF-007 | Gestionar confirmación, liberación, vencimiento y traslado de reservas. | CONFIRMADO | Supply Chain P4 |
| RF-008 | Aplicar idempotencia y control de concurrencia en reservas. | CONFIRMADO | Supply Chain P5 |
| RF-009 | Permitir identificación de unidad exacta para serializados cuando corresponda. | PENDIENTE | Supply Chain P1/P5 |
| RF-010 | Registrar conteos y ajustes autorizados con motivo, usuario y evidencia. | CONFIRMADO | Supply Chain P6 |
| RF-011 | Seleccionar ubicación mediante los criterios indicados. | CONFIRMADO | Supply Chain P7 |
| RF-012 | Registrar transferencias con origen, destino, solicitadas, despachadas, recibidas y diferencias. | CONFIRMADO | Supply Chain P8 |
| RF-013 | Excluir tránsito de la disponibilidad normal hasta recepción bajo el modelo actual. | CONFIRMADO | Supply Chain P8 |
| RF-014 | Indicar si un dato está actualizado o se opera con vista degradada. | CONFIRMADO | Supply Chain P9 |
| RF-015 | Confirmar pedido, asignar ubicación, generar tarea y permitir aceptación de tienda/CD. | CONFIRMADO | E-commerce P4 |
| RF-016 | Ejecutar preparación, validación, embalaje y marcado de listo. | CONFIRMADO | E-commerce P4 |
| RF-017 | Registrar faltantes/daños, buscar otra ubicación y recalcular promesa. | CONFIRMADO | E-commerce P5 |
| RF-018 | Ofrecer nueva fecha, cambio de tienda, sustituto o devolución ante cambio de promesa. | CONFIRMADO | E-commerce P5 |
| RF-019 | Soportar retiro en tienda con promesa, código, validación, entrega y liberación por no recojo. | CONFIRMADO | E-commerce P6 |
| RF-020 | Proporcionar cola priorizada, ubicación interna cuando exista y tiempo objetivo. | CONFIRMADO | E-commerce P7 |
| RF-021 | Permitir escaneo de SKU y reporte de faltantes/daños. | CONFIRMADO | E-commerce P7 |
| RF-022 | Correlacionar intento, pago, pedido y reserva. | CONFIRMADO | E-commerce P8 |
| RF-023 | Mantener trazabilidad entre pago aprobado, pedido y reserva. | CONFIRMADO | E-commerce P8 |
| RF-024 | Soportar despacho a domicilio, retiro en tienda y preparación desde tienda. | CONFIRMADO | Contexto + E-commerce |
| RF-025 | Auditar cada cambio de cantidad con los datos indicados por Supply Chain. | CONFIRMADO | Supply Chain P10 |
| RF-026 | Conservar secuencia de eventos para detectar duplicados/fuera de orden. | CONFIRMADO | Supply Chain P10 |
| RF-027 | Medir indicadores de negocio y operación mencionados. | CONFIRMADO | CEO P4; E-commerce P10 |
| RF-028 | Permitir capacidades de IA para demanda, redistribución y anomalías. | CONFIRMADO/POTENCIAL | CEO P8 |
| RF-029 | Mantener reserva y descuento de stock como transacciones controladas, independientes de predicciones. | CONFIRMADO | CEO P8 |
| RF-030 | Continuar vendiendo de forma segura si una integración se retrasa, evitando datos engañosos. | CONFIRMADO | CEO P10; E-commerce P9 |
| RF-031 | Integrarse con sistemas actuales y evolucionar gradualmente. | CONFIRMADO | CEO P7/P10 |
| RF-032 | Limitar datos personales visibles al preparador a los necesarios. | CONFIRMADO | E-commerce P7 |

> RF-004, RF-009 y varios comportamientos de RF-030 quedan condicionados por decisiones registradas en `15_discrepancias.md`.
