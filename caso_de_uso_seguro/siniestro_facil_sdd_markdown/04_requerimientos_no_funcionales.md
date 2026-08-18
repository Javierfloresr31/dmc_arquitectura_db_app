# Siniestro Fácil — Requerimientos No Funcionales

## 1. Regla de trazabilidad

Solo se consideran RNF aquellos atributos de calidad o restricciones técnicas explícitamente derivados de las entrevistas. No se inventan valores de disponibilidad, latencia, RTO/RPO, capacidad, cifrado o tecnologías.

| ID | Requerimiento no funcional | Origen |
|---|---|---|
| RNF-01 | Proteger datos personales del proceso de siniestros | CEO-07 |
| RNF-02 | Mantener trazabilidad completa de cambios y decisiones | CEO-07, OPS-10 |
| RNF-03 | Mantener evidencia inmutable/original para investigación | FRA-03, EV-03 |
| RNF-04 | Preservar original aun cuando existan versiones optimizadas | FRA-03, reto |
| RNF-05 | Aplicar control de acceso por rol y necesidad | FRA-07 |
| RNF-06 | Registrar descargas de evidencia y consultas sensibles | FRA-07 |
| RNF-07 | Garantizar explicabilidad de alertas y decisiones sensibles | CEO-08, FRA-04 |
| RNF-08 | Permitir revisión humana de decisiones sensibles | CEO-07, CEO-08, FRA-01 |
| RNF-09 | Mantener reproducibilidad de decisiones antifraude a través del tiempo | FRA-10 |
| RNF-10 | Versionar reglas/modelos utilizados en decisiones antifraude | FRA-04, FRA-05, FRA-10 |
| RNF-11 | Tolerar indisponibilidad temporal o lentitud de proveedores externos | CEO-09 |
| RNF-12 | Registrar resultados de solicitudes a terceros como aceptada, rechazada o sin respuesta | OPS-09 |
| RNF-13 | Permitir reintento, escalamiento o reasignación sin bloquear al cliente por una falla externa | OPS-09 |
| RNF-14 | Conservar historial durante reasignaciones | OPS-05 |
| RNF-15 | Conservar valores declarados y normalizados sin reemplazo silencioso | FRA-09 |
| RNF-16 | Medir falsos positivos de capacidades antifraude/IA | FRA-06 |
| RNF-17 | Proporcionar experiencia comprensible y guía paso a paso al asegurado | CEO-06 |
| RNF-18 | Soportar integraciones con proveedores que no necesariamente dispongan de APIs modernas | CEO-09 |
| RNF-19 | Permitir operación diferenciada para flujo simple y casos complejos/especializados | CEO-03, OPS-05 |
| RNF-20 | Permitir operación inicial como piloto controlado antes de expansión nacional | CEO-10 |

## 2. Valores no definidos por las entrevistas

No deben establecerse todavía los siguientes valores:

- disponibilidad porcentual;
- tiempos máximos de respuesta de la aplicación;
- throughput;
- volumen técnico de almacenamiento;
- RTO/RPO;
- ventanas de mantenimiento;
- algoritmos criptográficos;
- política de contraseñas;
- infraestructura cloud/on-premise;
- tecnología de base de datos;
- tecnología de mensajería;
- observabilidad específica;
- estándares de logging;
- política de respaldo;
- retención legal exacta.

Estos elementos quedan como **discrepancias/vacíos**, no como requisitos inventados.
