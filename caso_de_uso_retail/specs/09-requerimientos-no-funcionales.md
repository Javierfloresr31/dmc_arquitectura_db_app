# 09. RNF — Requerimientos no funcionales

| ID | Requerimiento | Estado |
|---|---|---|
| RNF-01 | Disponibilidad/reserva operativa en segundos como objetivo de negocio. | Evidenciado; métrica exacta pendiente |
| RNF-02 | Soportar el pico declarado de 12.000 pedidos/hora. | Declarado; dimensionamiento pendiente |
| RNF-03 | Soportar miles de consultas por segundo en campañas. | Declarado; cifra exacta pendiente |
| RNF-04 | La reserva debe tener mayor consistencia que la consulta de disponibilidad. | Evidenciado |
| RNF-05 | Las reservas deben ser idempotentes. | Evidenciado |
| RNF-06 | Debe existir control de concurrencia. | Evidenciado |
| RNF-07 | Debe existir trazabilidad completa de cambios de inventario. | Evidenciado |
| RNF-08 | Debe existir comportamiento degradado controlado. | Evidenciado |
| RNF-09 | Deben protegerse los datos personales según mínimo necesario. | Evidenciado |
| RNF-10 | Debe soportarse integración gradual con sistemas existentes. | Evidenciado |
| RNF-11 | Debe poder reconstruirse la secuencia de eventos. | Evidenciado |
| RNF-12 | Deben controlarse duplicados y eventos fuera de orden. | Evidenciado |

## Parámetros que no se deben inventar

Quedan pendientes: p95/p99, SLO de disponibilidad, RTO/RPO, retención de eventos/auditoría, timeouts, tamaño de mensajes, límites de concurrencia, capacidad de almacenamiento, estrategia de recuperación, requisitos de cifrado, autenticación y autorización técnica.
