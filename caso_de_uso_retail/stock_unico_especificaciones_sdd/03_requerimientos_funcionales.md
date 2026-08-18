# Especificación SDD — Requerimientos Funcionales
## Stock Único — NovaRetail

## Convención

- **RF:** requerimiento funcional derivado de las entrevistas.
- **Fuente:** ubicación exacta en el material de entrevistas.
- **Estado:** `Definido` cuando la necesidad está expresamente indicada; `Pendiente` cuando la entrevista plantea la necesidad pero no define la regla necesaria para implementarla.

## RF-001 — Consolidación de disponibilidad
**El sistema debe** consolidar la interpretación operativa de disponibilidad considerando las existencias y estados de inventario indicados por Supply Chain.

**Fuente:** líneas 28, 69–79.  
**Estado:** Definido en objetivo; fórmula exacta pendiente.

## RF-002 — Estados de inventario
**El sistema debe** distinguir, por SKU y ubicación, stock físico, disponible, reservado, comprometido, bloqueado, dañado, en tránsito y pendiente de recepción.

**Fuente:** líneas 69–71.  
**Estado:** Definido.

## RF-003 — Movimientos de inventario
**El sistema debe** procesar movimientos provenientes de ventas en caja, pedidos web, recepciones, transferencias, devoluciones, anulaciones, conteos, ajustes, daños y robos.

**Fuente:** líneas 73–75.  
**Estado:** Definido.

## RF-004 — Disponibilidad según contexto
**El sistema debe** considerar que la disponibilidad puede variar por categoría, tienda, campaña y modalidad de entrega.

**Fuente:** líneas 77–79.  
**Estado:** Definido como necesidad; reglas específicas pendientes.

## RF-005 — Consulta previa al pago
**El sistema debe** permitir consultar productos, cantidades, ubicación candidata, modalidad, costo, fecha prometida y vigencia de la oferta durante checkout.

**Fuente:** líneas 124–126.  
**Estado:** Definido.

## RF-006 — Reserva temporal
**El sistema debe** crear reservas temporales durante el inicio/confirmación del pago, con expiración corta.

**Fuente:** líneas 81–83, 120–122.  
**Estado:** Definido; duración exacta pendiente.

## RF-007 — Ciclo de vida de reserva
**El sistema debe** soportar los estados/operaciones de confirmar, liberar, vencer y trasladar una reserva.

**Fuente:** líneas 81–83.  
**Estado:** Definido; reglas de transición pendientes.

## RF-008 — Concurrencia e idempotencia
**El sistema debe** controlar concurrencia y permitir operaciones idempotentes de reserva.

**Fuente:** líneas 85–87.  
**Estado:** Definido.

## RF-009 — Serialización
**El sistema debe** permitir eventualmente identificar la unidad exacta de productos serializados.

**Fuente:** líneas 69–71, 85–87.  
**Estado:** Necesidad mencionada; alcance de implementación pendiente.

## RF-010 — Conteos y ajustes
**El sistema debe** permitir conteos y ajustes autorizados y conservar motivo, usuario y evidencia.

**Fuente:** líneas 89–91.  
**Estado:** Definido; autorización concreta pendiente.

## RF-011 — Selección de ubicación
**El sistema debe** seleccionar una ubicación considerando disponibilidad, distancia, capacidad, horario, costo, prioridad, fecha prometida y restricciones de producto.

**Fuente:** líneas 93–95.  
**Estado:** Definido; algoritmo/pesos pendientes.

## RF-012 — Transferencias
**El sistema debe** registrar origen, destino, unidades solicitadas, despachadas, recibidas y diferencias.

**Fuente:** líneas 97–99.  
**Estado:** Definido.

## RF-013 — Tratamiento de tránsito
**El sistema no debe** considerar inventario en tránsito como disponible hasta la recepción bajo el modelo actual.

**Fuente:** líneas 97–99.  
**Estado:** Definido.

## RF-014 — Estado de actualización
**El sistema debe** indicar si el dato está actualizado o si opera con una vista degradada.

**Fuente:** líneas 101–103.  
**Estado:** Necesidad definida; umbral de "actualizado" pendiente.

## RF-015 — Confirmación y fulfillment
**El sistema debe** permitir confirmar el pedido, asignar ubicación, generar tarea de preparación y recibir aceptación de tienda o centro de distribución.

**Fuente:** líneas 128–130.  
**Estado:** Definido.

## RF-016 — Preparación
**El sistema debe** permitir recoger productos, validar SKU/cantidad, embalar y marcar el pedido como listo para despacho o recojo.

**Fuente:** líneas 128–130.  
**Estado:** Definido.

## RF-017 — Gestión de excepciones
**El sistema debe** permitir registrar faltantes, buscar otra ubicación y recalcular la promesa.

**Fuente:** líneas 132–134.  
**Estado:** Definido.

## RF-018 — Alternativas ante cambio de promesa
**El sistema debe** permitir ofrecer nueva fecha, cambio de tienda, sustituto o devolución cuando la promesa cambie por un faltante.

**Fuente:** líneas 132–134.  
**Estado:** Definido; reglas de elegibilidad pendientes.

## RF-019 — Retiro en tienda
**El sistema debe** soportar selección de tienda, promesa, código de recojo, validación del retirante, registro de entrega y liberación por no recojo.

**Fuente:** líneas 136–138.  
**Estado:** Definido; plazo de no recojo pendiente.

## RF-020 — Cola de preparación
**El sistema debe** proporcionar una cola priorizada de tareas, ubicación interna cuando exista y tiempo objetivo.

**Fuente:** líneas 140–142.  
**Estado:** Definido; fórmula de prioridad pendiente.

## RF-021 — Escaneo y reporte operativo
**El sistema debe** permitir escanear SKU y reportar faltantes o daños durante preparación.

**Fuente:** líneas 140–142.  
**Estado:** Definido.

## RF-022 — Correlación transaccional
**El sistema debe** correlacionar intento, pago, pedido y reserva.

**Fuente:** líneas 144–146.  
**Estado:** Definido.

## RF-023 — Consistencia pago-pedido
**El sistema debe** impedir que un pago aprobado quede sin pedido y mantener trazabilidad de una reserva confirmada.

**Fuente:** líneas 144–146.  
**Estado:** Necesidad definida; mecanismo de compensación pendiente.

## RF-024 — Modalidades omnicanal
**El sistema debe** soportar despacho a domicilio, retiro en tienda y preparación desde tienda.

**Fuente:** líneas 11, 124–130, 136–138.  
**Estado:** Definido.

## RF-025 — Auditoría de inventario
**El sistema debe** registrar cada cambio de cantidad con evento de origen, documento, usuario/sistema, momento, cantidad anterior, cantidad nueva y razón.

**Fuente:** líneas 105–107.  
**Estado:** Definido.

## RF-026 — Secuencia de eventos
**El sistema debe** conservar la secuencia de eventos para identificar eventos duplicados o fuera de orden.

**Fuente:** líneas 105–107.  
**Estado:** Definido.

## RF-027 — Indicadores
**El sistema debe** soportar la medición de los indicadores mencionados por CEO y E-commerce.

**Fuente:** líneas 34–36, 152–154.  
**Estado:** Definido; fórmulas de KPI pendientes.

## RF-028 — IA como recomendador
**El sistema puede** utilizar IA para anticipar demanda, sugerir redistribución y detectar anomalías.

**Fuente:** líneas 50–52.  
**Estado:** Definido como capacidad potencial, no como requisito obligatorio de primera etapa.

## RF-029 — Control de operaciones de stock
**Las operaciones de reserva y descuento de stock deben** ser transacciones controladas y no depender de una predicción de IA.

**Fuente:** líneas 50–52.  
**Estado:** Definido.

## RF-030 — Operación degradada
**El sistema debe** permitir continuar vendiendo de manera segura si una integración se retrasa, sin mostrar datos engañosos.

**Fuente:** líneas 56–60, 148–150.  
**Estado:** Definido; comportamiento exacto pendiente.

## RF-031 — Integración gradual
**La solución debe** integrarse con los sistemas actuales y evolucionar gradualmente, evitando reemplazarlos todos en una sola etapa.

**Fuente:** líneas 46–48.  
**Estado:** Definido como restricción de solución.

## RF-032 — Protección de datos en tienda
**El sistema debe** limitar los datos personales visibles al personal de tienda a los necesarios para preparar el pedido.

**Fuente:** líneas 140–142.  
**Estado:** Definido; matriz de datos permitidos pendiente.

## Alcance inicial expresamente indicado

La primera etapa debe comenzar con **tecnología y pequeños electrodomésticos en Lima**.

**Fuente:** líneas 38–40.

No se infieren categorías, ciudades ni etapas adicionales.
