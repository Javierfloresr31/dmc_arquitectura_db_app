# Crédito Ágil 360 — Requerimientos No Funcionales

## Criterio

Se incluyen únicamente atributos de calidad, restricciones técnicas u operativas expresamente mencionados. No se inventan valores cuantitativos.

| ID | RNF | Criterio / restricción | Evidencia |
|---|---|---|---|
| RNF-001 | Trazabilidad | Debe ser posible reconstruir una decisión con datos, fuentes, hora, versión de reglas, score, excepciones e intervención. | Riesgos P5 |
| RNF-002 | Inmutabilidad histórica | Un cambio posterior de datos no debe reescribir la decisión histórica. | Riesgos P5 |
| RNF-003 | Segregación de funciones | Las excepciones deben respetar aprobación según nivel de riesgo y no deben aprobarse fuera del sistema. | Riesgos P7/P10 |
| RNF-004 | Protección de datos | Debe protegerse la información financiera y los datos personales. | CEO P7, Riesgos P10 |
| RNF-005 | No exposición sensible | Las notificaciones no deben contener datos sensibles. | Canales P6 |
| RNF-006 | Disponibilidad | La solución debe soportar disponibilidad durante campañas. | Riesgos P10 |
| RNF-007 | Escalabilidad | Debe soportar campañas donde el tráfico de las primeras horas puede multiplicar por cinco el volumen normal. | Canales P8 |
| RNF-008 | Continuidad | No debe comprometerse la continuidad del core bancario. | CEO P7 |
| RNF-009 | Integración desacoplada | No debe depender de que todos los sistemas legados cambien simultáneamente. | CEO P7 |
| RNF-010 | Idempotencia | Debe evitarse evaluar o desembolsar dos veces la misma solicitud. | Riesgos P10 |
| RNF-011 | Reprocesamiento | Debe existir capacidad de reprocesar consultas fallidas. | Riesgos P10 |
| RNF-012 | Explicabilidad | Las decisiones crediticias deben poder explicarse y seguir políticas controladas y auditables. | CEO P7/P8 |
| RNF-013 | IA gobernada | Si se utilizan modelos, deben existir monitoreo, responsables y límites claros. | CEO P8 |
| RNF-014 | Calidad de extracción IA | La IA no debe completar datos que no encuentre; cada campo extraído debe mantener origen y confianza. | Riesgos P9 |
| RNF-015 | Accesibilidad | La experiencia debe ser compatible con lectores de pantalla. | Canales P9 |
| RNF-016 | Usabilidad | Debe utilizar lenguaje claro, formularios cortos y validaciones inmediatas. | Canales P9 |
| RNF-017 | Rendimiento percibido / continuidad de operación | Un fallo o timeout de integración no debe dejar la pantalla congelada ni perder información ya registrada. | Canales P7 |
| RNF-018 | Observabilidad de negocio | Deben registrarse eventos por etapa para analizar abandono, errores, reintentos, tiempos de respuesta y conversión. | Canales P10 |
| RNF-019 | Minimización de datos | El análisis no debe mezclar información de navegación con información financiera más sensible de lo necesario. | Canales P10 |
| RNF-020 | Evolutividad | La arquitectura debe poder crecer por productos y permitir cambios de reglas sin reconstruir toda la aplicación. | CEO P10 |

## Parámetros pendientes de definición

Las entrevistas no proporcionan valores de SLA, RTO/RPO, porcentajes de disponibilidad, latencias objetivo, throughput, retención, cifrado específico, algoritmos, tamaños máximos de documentos, límites de reintentos ni umbrales cuantitativos de IA.

Por SDD, esos valores deben ser definidos antes de cerrar la especificación técnica.
