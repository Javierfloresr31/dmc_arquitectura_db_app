# 10. Matriz de trazabilidad

| Fuente | Necesidad | Artefactos |
|---|---|---|
| CEO 2-3 | Transparencia, rapidez, vista única, asignación | US-003, US-005, RF-005, RF-011 |
| CEO 5 | Alcance piloto | 01, RN-001..003 |
| CEO 6 | Guía y reportante autorizado | US-001/002 |
| CEO 7 | Privacidad, cobertura, pagos, evidencia, revisión | RNF-001..010, RF-018, 24 |
| CEO 8 | IA revisable | US-013/014, RF-021, 22, 26 |
| CEO 9 | Dependencias y resiliencia | RF-012/020, 08, 22, 25 |
| Operaciones 1-2 | Datos mínimos y validación | US-001/004, RF-001..003, 21 |
| Operaciones 3 | Evidencia | US-007, RF-007, 21, 26 |
| Operaciones 4 | Estados | RF-005/006, 23 |
| Operaciones 5-6 | Asignación, duplicados, reasignación | US-005/006, RF-011, 25 |
| Operaciones 7 | Taller/presupuesto | US-011/012, 21, 22 |
| Operaciones 8-9 | SLA/proveedores | RF-012, RNF-015, Q-008, 22 |
| Operaciones 10 | Auditoría | US-016, RF-016, 24, 25, 26 |
| Fraude 1-4 | Señales, evidencia, alertas | US-007/013/014, RF-008/009, 21, 23 |
| Fraude 5-7 | Política configurable, roles, auditoría | RN-005, RF-019, 24, 22 |
| Fraude 8-10 | Relaciones, calidad, reproducibilidad | US-015/017, RNF-012, Q-012, 23, 26 |

## Trazabilidad técnica adicional

| Artefacto | Propósito |
|---|---|
| 20-arquitectura-backend | capas, dominio, aplicación, persistencia e integraciones |
| 21-contratos-api | operaciones REST y comportamiento HTTP |
| 22-contratos-integraciones | dependencias externas y resiliencia |
| 23-maquina-estados-siniestro | transiciones de negocio |
| 24-seguridad-rbac | roles, permisos y datos sensibles |
| 25-idempotencia | duplicados, reintentos y correlación |
| 26-estrategia-pruebas-backend | pruebas unitarias, integración, API, E2E, seguridad y resiliencia |
| 19-plan-desarrollo-backend | secuencia de sprints y Definition of Done |

## Criterio SDD

Cada historia de usuario que entre a un sprint debe poder trazarse hasta su criterio de aceptación, contrato API o caso de uso, componente backend, persistencia/integración y prueba automatizada correspondiente.