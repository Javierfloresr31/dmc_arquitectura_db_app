# 03. Requerimientos

## Requerimientos funcionales

| ID | Requerimiento | Fuente |
|---|---|---|
| RF-01 | Crear/recuperar una solicitud mediante identificador único. | Canales |
| RF-02 | Permitir continuidad entre app, web, agencia y contact center. | Canales |
| RF-03 | Mostrar datos internos disponibles y permitir confirmación/actualización según reglas. | Canales |
| RF-04 | Gestionar autorización para consultas. | Canales/Riesgos |
| RF-05 | Gestionar documentos requeridos y estados de revisión. | Canales/Riesgos |
| RF-06 | Consultar fuentes internas y externas necesarias para evaluación. | Riesgos |
| RF-07 | Ejecutar políticas de elegibilidad versionadas y auditables. | Riesgos/CEO |
| RF-08 | Emitir resultados aprobado, rechazado, observado o revisión manual. | Riesgos |
| RF-09 | Gestionar monto máximo, plazo, tasa, condiciones y vigencia cuando correspondan a un aprobado. | Riesgos |
| RF-10 | Derivar casos que requieren revisión manual. | Riesgos |
| RF-11 | Gestionar excepciones con recomendación, autorización y justificación. | Riesgos |
| RF-12 | Registrar trazabilidad de datos, fuentes, fecha/hora, reglas, score, excepciones e intervención. | Riesgos |
| RF-13 | Mantener decisiones históricas sin reescribirlas por cambios posteriores de datos. | Riesgos |
| RF-14 | Mostrar al cliente estados comprensibles y acciones pendientes. | Canales |
| RF-15 | Enviar notificaciones de eventos relevantes mediante canales permitidos. | Canales |
| RF-16 | Evitar duplicidad por reintentos y permitir reprocesar consultas fallidas. | Riesgos/Canales |
| RF-17 | Permitir al contact center consultar estado y registrar incidencia sin modificar decisiones de Riesgos. | Canales |
| RF-18 | Permitir asistencia de IA para extracción documental, inconsistencias, orientación y resumen. | CEO/Riesgos |
| RF-19 | Conservar documento origen y nivel de confianza de cada extracción asistida. | Riesgos |
| RF-20 | Permitir aceptación de contrato y transición hacia desembolso. | Canales |

## Requerimientos no funcionales

| ID | Requerimiento | Evidencia |
|---|---|---|
| RNF-01 | Trazabilidad completa de decisiones y operaciones relevantes. | Riesgos |
| RNF-02 | Segregación de funciones entre analista y supervisor para excepciones. | Riesgos |
| RNF-03 | Protección de datos personales y financieros. | CEO/Riesgos |
| RNF-04 | Decisiones explicables y reconstruibles. | CEO/Riesgos |
| RNF-05 | Idempotencia para evitar evaluación o desembolso duplicado. | Riesgos |
| RNF-06 | Disponibilidad y capacidad para campañas con incremento significativo de tráfico. | Riesgos/Canales |
| RNF-07 | Tolerancia a fallas de integraciones sin perder datos ya registrados. | Canales |
| RNF-08 | Capacidad de reprocesar consultas fallidas. | Riesgos |
| RNF-09 | Accesibilidad: lenguaje claro, formularios cortos, validaciones inmediatas y compatibilidad con lectores de pantalla. | Canales |
| RNF-10 | Minimización de datos expuestos en notificaciones. | Canales |
| RNF-11 | Separación de datos de navegación de información financiera sensible cuando no sea necesaria su mezcla. | Canales |
| RNF-12 | No depender de cambios simultáneos de todos los sistemas legados. | CEO |

## Parámetros que NO deben convertirse en SLA sin validación
- 8,000 simulaciones diarias.
- 1,500 solicitudes diarias.
- Factor cinco de incremento en campañas.
- Objetivo de primera versión operativa en cuatro meses.
- Competidor con desembolsos inferiores a diez minutos.

Estos valores son evidencia de entrevistas, no compromisos técnicos ni metas contractuales.
