# Crédito Ágil 360 — Historias de Usuario

## Fuente y criterio

Fuente única: entrevistas del archivo `01_banca_credito_agil_360.md`. Las historias se derivan únicamente de necesidades explícitas de CEO, Riesgos de Crédito y Canales Digitales. No se incorporan funcionalidades no mencionadas.

> Estado: **borrador de descubrimiento**, no especificación aprobada.

## Historias de usuario

| ID | Actor | Historia | Prioridad / alcance | Origen |
|---|---|---|---|---|
| HU-001 | Cliente | Como cliente, quiero iniciar una solicitud de crédito personal para poder continuar con el proceso de originación. | MVP | CEO P1, Canales P1 |
| HU-002 | Cliente | Como cliente, quiero iniciar la solicitud en un canal y continuarla en otro sin perder la información registrada. | MVP | CEO P6, Canales P4 |
| HU-003 | Cliente | Como cliente, quiero confirmar o actualizar mis datos cuando sean antiguos para que la evaluación utilice información vigente. | MVP | Canales P3 |
| HU-004 | Cliente | Como cliente, quiero autorizar las consultas necesarias para que el banco pueda evaluar mi solicitud. | MVP | Canales P2 |
| HU-005 | Cliente | Como cliente, quiero completar la información faltante y adjuntar los documentos que correspondan para continuar mi trámite. | MVP | Canales P2, Riesgos P6 |
| HU-006 | Cliente | Como cliente, quiero conocer el estado de mi solicitud y las acciones pendientes para saber qué debo hacer y evitar llamar al banco. | MVP | CEO P3/P6, Canales P2/P5 |
| HU-007 | Cliente | Como cliente, quiero recibir notificaciones de los cambios relevantes de mi trámite mediante los canales permitidos que elija. | MVP | Canales P6 |
| HU-008 | Cliente | Como cliente, quiero revisar las condiciones de una aprobación y aceptar el contrato para continuar hacia el desembolso. | MVP | Canales P2 |
| HU-009 | Cliente | Como cliente, quiero recibir la confirmación del desembolso para conocer que el crédito fue desembolsado. | MVP | Canales P2/P6 |
| HU-010 | Cliente | Como cliente, quiero que un error de integración no borre la información que ya registré y que se me indique si debo continuar procesando o reintentar una acción. | MVP | Canales P7 |
| HU-011 | Asesor autorizado | Como asesor autorizado, quiero consultar el mismo estado de la solicitud para ayudar al cliente sin acceder a información innecesaria. | MVP | Canales P4 |
| HU-012 | Contact center | Como usuario de contact center, quiero consultar el estado de la solicitud y registrar una incidencia para atender al cliente. | MVP | Canales P9 |
| HU-013 | Analista de Riesgos | Como analista, quiero consultar la información utilizada en una evaluación para revisar los casos que requieren intervención manual. | MVP | Riesgos P1/P6 |
| HU-014 | Analista de Riesgos | Como analista, quiero recomendar una excepción y registrar su justificación y documentos considerados para que pueda ser aprobada según el nivel de riesgo. | MVP, sujeto a validación | Riesgos P7 |
| HU-015 | Supervisor | Como supervisor, quiero aprobar las excepciones que correspondan según el nivel de riesgo para mantener la segregación de funciones. | MVP, sujeto a validación | Riesgos P7 |
| HU-016 | Riesgos | Como área de Riesgos, quiero aplicar políticas de elegibilidad controladas y auditables para que las decisiones crediticias respeten las políticas vigentes. | MVP | CEO P7/P10, Riesgos P3/P4/P5 |
| HU-017 | Riesgos | Como área de Riesgos, quiero reconstruir una decisión histórica con los datos, fuentes, hora, versión de reglas, score, excepciones e intervenciones utilizados en ese momento. | MVP | Riesgos P5 |
| HU-018 | Riesgos | Como área de Riesgos, quiero distinguir una solicitud nueva de un reintento para evitar duplicidad de solicitudes y evaluaciones. | MVP | Riesgos P8, Canales P7 |
| HU-019 | Riesgos | Como área de Riesgos, quiero reprocesar consultas fallidas sin evaluar o desembolsar dos veces la misma solicitud. | MVP | Riesgos P10 |
| HU-020 | Riesgos | Como área de Riesgos, quiero que los campos extraídos de documentos con IA conserven el documento de origen y un nivel de confianza, y que los casos bajo el umbral sean revisados por una persona. | MVP, si se usa IA | CEO P8, Riesgos P9 |
| HU-021 | Negocio/Canales | Como área de Canales, quiero registrar eventos por etapa para analizar abandono, errores, reintentos, tiempos de respuesta y conversión. | MVP | CEO P4, Canales P10 |
| HU-022 | Negocio | Como negocio, quiero atender primero créditos personales para clientes existentes con ingresos recurrentes para concentrar la primera versión en el alcance prioritario. | MVP | CEO P5/P9 |

## Historias explícitamente futuras

Estas capacidades aparecen como evolución posterior, no como alcance del MVP:

- Atención de clientes nuevos.
- Atención de trabajadores independientes.
- Evolución hacia otros productos de crédito.

**Origen:** CEO, pregunta 5.

## Validaciones pendientes

Las historias HU-014, HU-015 y HU-020 dependen de políticas, umbrales o controles que no están definidos en las entrevistas. Se mantienen como historias derivadas, pero su detalle debe cerrarse antes de considerarlas especificación aprobada.

## Trazabilidad

Cada historia tiene referencia a la entrevista y pregunta de origen. No se agregaron historias para funciones no mencionadas por los entrevistados.
