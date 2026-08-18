# Stock Único — NovaRetail

**Fuente:** entrevistas del caso `NovaRetail: Stock Único`  
**Método:** PROMPT ATLAS v2.0  
**Regla:** contenido sustentado exclusivamente en las entrevistas; los vacíos se mantienen como pendientes.

# ⚠️ DECISIONES PENDIENTES QUE BLOQUEAN O CONDICIONAN EL DISEÑO

## D-001 — Fórmula exacta de disponibilidad
La fuente da un principio, pero indica que varía por categoría, tienda, campaña y modalidad. Debe definirse la fórmula y stock de seguridad.

## D-002 — Autoridad por sistema
Debe definirse qué sistema es autoridad para cada operación/dato.

## D-003 — Duración y ciclo de vida de reservas
Se exige expiración corta, pero no se define TTL, renovación ni reglas exactas.

## D-004 — Partición de pedidos
Para carritos con varios productos debe decidirse si salen juntos, desde ubicaciones distintas o con alternativa.

## D-005 — Compensación entre pago, pedido y reserva
Se exige coherencia, pero no se define cómo compensar fallos entre operaciones.

## D-006 — Consistencia fuerte vs eventual
Debe decidirse qué operaciones requieren consistencia fuerte y cuáles pueden ser eventuales.

## D-007 — Inventario agregado vs serializado/lote
Existen serializados y productos por lote; el alcance y momento de identificación exacta están pendientes.

## D-008 — Algoritmo de asignación de ubicación
Se conocen criterios, pero no pesos, prioridades ni desempates.

## D-009 — Modo degradado
Debe definirse qué operaciones se permiten, qué datos se consideran confiables, cuándo se activa y cómo se recupera.

## D-010 — Vigencia de datos
Debe definirse qué significa "actualizado" por fuente/tipo de dato.

## D-011 — Autorización de ajustes
Se requiere ajuste autorizado, pero no se definen roles, aprobadores ni separación de funciones.

## D-012 — Plazo de retiro
Debe definirse cuánto tiempo puede permanecer una reserva antes de liberarse por no recojo.

## D-013 — Reglas de alternativas
Debe definirse cuándo ofrecer nueva fecha, cambio de tienda, sustituto o devolución y qué condiciones debe cumplir cada alternativa.

## D-014 — Priorización de preparación
Existe cola priorizada y tiempo objetivo, pero no la fórmula de prioridad ni desempates.

## D-015 — KPI
Se conocen indicadores, pero no fórmulas, fuentes oficiales, dimensiones ni periodicidad.

## D-016 — Contratos de integración
No se definen interfaces, payloads, protocolos, seguridad, reintentos, orden, duplicados ni versionado.

## D-017 — Arquitectura física
No se define motor de BD, mensajería, cache, cloud/on-premise, API gateway ni tecnología de despliegue.

## D-018 — SLO/SLA y capacidad
Se conocen "segundos", miles de consultas por segundo y 12,000 pedidos/hora, pero no percentiles, límites, margen ni concurrencia máxima.

## D-019 — Seguridad técnica
Solo está definida la restricción de exposición de datos personales al preparador. El mecanismo técnico está pendiente.

## D-020 — Arquitectura analítica
Se requiere diferenciar operación en tiempo real de analítica histórica, pero no se define arquitectura, retención o latencia.

## D-021 — IA
Se mencionan tres capacidades potenciales, pero no alcance de primera etapa, datos, modelo, evaluación ni control operacional.

## D-022 — Expansión de alcance
La primera etapa es tecnología y pequeños electrodomésticos en Lima; criterios y calendario de expansión no están definidos.

## Contradicciones

No se identificó una contradicción explícita entre las tres entrevistas. Sí existen decisiones abiertas que podrían producir interpretaciones diferentes si no se resuelven.

## Criterio de bloqueo

Los puntos D-001, D-002, D-003, D-004, D-005, D-006, D-009 y D-016 condicionan directamente el diseño de la solución y/o la implementación transaccional.
