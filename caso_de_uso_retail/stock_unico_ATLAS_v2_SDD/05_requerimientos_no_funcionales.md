# Stock Único — NovaRetail

**Fuente:** entrevistas del caso `NovaRetail: Stock Único`  
**Método:** PROMPT ATLAS v2.0  
**Regla:** contenido sustentado exclusivamente en las entrevistas; los vacíos se mantienen como pendientes.

| ID | Categoría | Requerimiento | Estado | Fuente |
|---|---|---|---|---|
| RNF-001 | Latencia | Ventas y reservas requieren respuesta en segundos. | CONFIRMADO cualitativo | Supply Chain P9 |
| RNF-002 | Escalabilidad | Debe contemplarse miles de consultas por segundo y 12,000 pedidos/hora en campaña. | CONFIRMADO | E-commerce P9 |
| RNF-003 | Consistencia | La confirmación de reserva requiere mayor consistencia que el catálogo/consulta. | CONFIRMADO cualitativo | E-commerce P9 |
| RNF-004 | Concurrencia | Las reservas deben impedir doble asignación. | CONFIRMADO | Supply Chain P5 |
| RNF-005 | Idempotencia | Los reintentos del checkout no deben duplicar reservas. | CONFIRMADO | Supply Chain P5 |
| RNF-006 | Degradación | El sitio debe degradar de manera controlada y no mostrar datos engañosos. | CONFIRMADO | E-commerce P9 |
| RNF-007 | Integración | La solución debe evolucionar sin reemplazar todos los sistemas actuales en una sola etapa. | CONFIRMADO | CEO P7 |
| RNF-008 | Auditoría | Los cambios de inventario deben conservar información suficiente para investigar causas. | CONFIRMADO | Supply Chain P6/P10 |
| RNF-009 | Trazabilidad | Intento, pago, pedido y reserva deben correlacionarse. | CONFIRMADO | E-commerce P8 |
| RNF-010 | Privacidad | Preparadores no deben acceder a datos personales innecesarios. | CONFIRMADO | E-commerce P7 |
| RNF-011 | Exactitud | No debe prometer unidades inexistentes y debe distinguir stock físico de aptitud para venta digital. | CONFIRMADO | CEO P7; Supply Chain P3 |
| RNF-012 | Integridad de eventos | Deben detectarse eventos duplicados o fuera de orden. | CONFIRMADO | Supply Chain P10 |
| RNF-013 | Rendimiento checkout | No debe demorarse excesivamente el checkout para obtener disponibilidad perfecta. | CONFIRMADO cualitativo | CEO P9 |

### No definidos por la fuente

No se especifican valores para SLA/SLO, disponibilidad porcentual, RTO/RPO, P95/P99, concurrencia máxima, cifrado, autenticación, proveedor cloud, motor de BD, backup, observabilidad concreta, retención ni estrategia de escalamiento.

Estos puntos quedan pendientes; no se convierten en requisitos inventados.
