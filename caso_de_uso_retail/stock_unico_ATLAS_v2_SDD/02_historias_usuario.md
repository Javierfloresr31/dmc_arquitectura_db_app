# Stock Único — NovaRetail

**Fuente:** entrevistas del caso `NovaRetail: Stock Único`  
**Método:** PROMPT ATLAS v2.0  
**Regla:** contenido sustentado exclusivamente en las entrevistas; los vacíos se mantienen como pendientes.

## HU-001 — Consultar disponibilidad y promesa

**Como** cliente, **quiero** conocer disponibilidad, modalidad y fecha prometida antes del pago, **para** tener una promesa confiable.

**Valor:** reducir incertidumbre/cancelaciones.  
**Fuente:** CEO P3/P6; E-commerce P3/P9.  
**Estado:** CONFIRMADO.

## HU-002 — Reservar durante checkout

**Como** cliente que inicia el pago, **quiero** reservar temporalmente las unidades, **para** evitar que sean asignadas por otro canal.

**Fuente:** Supply Chain P4; E-commerce P2.  
**Estado:** CONFIRMADO.

## HU-003 — Evitar reservas duplicadas

**Como** sistema de checkout, **quiero** operaciones idempotentes y control de concurrencia, **para** que los reintentos no creen reservas adicionales.

**Fuente:** Supply Chain P5; E-commerce P8.  
**Estado:** CONFIRMADO.

## HU-004 — Correlacionar pago, pedido y reserva

**Como** sistema de comercio electrónico, **quiero** correlacionar intento, pago, pedido y reserva, **para** mantener trazabilidad y evitar un pago aprobado sin pedido.

**Fuente:** E-commerce P8.  
**Estado:** CONFIRMADO.

## HU-005 — Asignar ubicación

**Como** operación de fulfillment, **quiero** asignar una ubicación considerando los criterios indicados por Supply Chain, **para** cumplir la modalidad y promesa.

**Fuente:** Supply Chain P7; E-commerce P3.  
**Estado:** CONFIRMADO; algoritmo pendiente.

## HU-006 — Preparar pedido

**Como** preparador de tienda, **quiero** recibir y ejecutar tareas de preparación, **para** recoger, validar, embalar y marcar el pedido como listo.

**Fuente:** E-commerce P4/P7.  
**Estado:** CONFIRMADO.

## HU-007 — Gestionar faltante

**Como** operador de tienda, **quiero** registrar un faltante, buscar otra ubicación y recalcular la promesa, **para** ofrecer alternativas en vez de cancelar directamente.

**Fuente:** E-commerce P5.  
**Estado:** CONFIRMADO.

## HU-008 — Gestionar retiro en tienda

**Como** cliente, **quiero** seleccionar una tienda, recibir una promesa y un código de recojo, **para** retirar mi pedido.

**Fuente:** E-commerce P6.  
**Estado:** CONFIRMADO.

## HU-009 — Liberar reservas vencidas/no retiradas

**Como** operación de inventario, **quiero** liberar reservas cuando correspondan, **para** evitar bloquear inventario indefinidamente.

**Fuente:** Supply Chain P4; CEO P7; E-commerce P6.  
**Estado:** CONFIRMADO; plazos pendientes.

## HU-010 — Registrar conteos y ajustes auditables

**Como** responsable de inventarios, **quiero** registrar diferencias y ajustes autorizados con motivo, usuario y evidencia, **para** conservar la causa.

**Fuente:** Supply Chain P6.  
**Estado:** CONFIRMADO.

## HU-011 — Procesar movimientos de inventario

**Como** Stock Único, **quiero** recibir movimientos de las fuentes existentes, **para** consolidar el inventario.

**Fuente:** Supply Chain P2.  
**Estado:** CONFIRMADO; contratos pendientes.

## HU-012 — Gestionar transferencias y tránsito

**Como** responsable de inventario, **quiero** controlar solicitudes, despachos, recepciones y diferencias de transferencias, **para** no considerar tránsito como disponible bajo el modelo actual.

**Fuente:** Supply Chain P8.  
**Estado:** CONFIRMADO.

## HU-013 — Operar de forma degradada

**Como** cliente, **quiero** que el sitio opere de manera controlada si una integración se retrasa, **para** no recibir información engañosa.

**Fuente:** CEO P10; Supply Chain P9; E-commerce P9.  
**Estado:** CONFIRMADO; comportamiento pendiente.

## HU-014 — Limitar datos personales en preparación

**Como** preparador, **quiero** visualizar solo los datos personales necesarios, **para** preparar el pedido sin exposición innecesaria.

**Fuente:** E-commerce P7.  
**Estado:** CONFIRMADO.

## HU-015 — Medir resultados operativos

**Como** responsable del negocio, **quiero** medir los indicadores mencionados en las entrevistas, **para** evaluar Stock Único.

**Fuente:** CEO P4; E-commerce P10.  
**Estado:** CONFIRMADO; fórmulas pendientes.

## HU-016 — Usar IA para recomendaciones de inventario

**Como** responsable de inventarios, **quiero** recibir apoyo de IA para demanda, redistribución y anomalías, **para** apoyar decisiones operativas.

**Fuente:** CEO P8.  
**Estado:** CONFIRMADO como capacidad potencial; alcance de primera etapa pendiente.
