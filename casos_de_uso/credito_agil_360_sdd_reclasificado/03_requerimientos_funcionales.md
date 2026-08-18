# Crédito Ágil 360 — Requerimientos Funcionales

## Regla de elaboración

Solo se consideran requerimientos funcionales explícitamente sustentados por las entrevistas. Los parámetros no definidos se señalan como pendientes.

| ID | Requerimiento funcional | Evidencia |
|---|---|---|
| RF-001 | Permitir iniciar solicitudes de crédito personal desde app, web, agencia y contact center. | Canales P1 |
| RF-002 | Asignar un identificador único a la solicitud para permitir continuidad omnicanal. | Canales P1/P4 |
| RF-003 | Permitir recuperar una solicitud después de autenticarse y continuar el proceso. | Canales P4 |
| RF-004 | Conservar la información registrada al cambiar de canal. | CEO P6, Canales P4 |
| RF-005 | Mostrar y permitir confirmar o actualizar datos disponibles y potencialmente antiguos. | Canales P3 |
| RF-006 | Solicitar autorización para las consultas necesarias. | Canales P2 |
| RF-007 | Recopilar información faltante y documentos cuando correspondan. | Canales P2 |
| RF-008 | Validar identidad como parte del proceso de originación. | Contexto, Riesgos P1 |
| RF-009 | Consultar información interna relevante para evaluación, incluyendo ingresos, comportamiento, endeudamiento, alertas, score, movimientos y productos internos cuando corresponda. | Riesgos P1/P2 |
| RF-010 | Consultar fuentes externas cuando la evaluación lo requiera. | Riesgos P2 |
| RF-011 | Ejecutar políticas de elegibilidad controladas y auditables. | CEO P7/P10, Riesgos P3 |
| RF-012 | Producir resultados de evaluación como aprobado, rechazado, observado o revisión manual. | Riesgos P4 |
| RF-013 | Registrar en la aprobación monto máximo, plazo permitido, tasa, condiciones y fecha de vigencia cuando esos atributos formen parte del resultado. | Riesgos P4 |
| RF-014 | Registrar razones internas de rechazo. | Riesgos P4 |
| RF-015 | Derivar a revisión manual los casos identificados por Riesgos. | Riesgos P6 |
| RF-016 | Permitir recomendar y aprobar excepciones con justificación, documentos considerados y usuario autorizador, según nivel de riesgo. | Riesgos P7 |
| RF-017 | Mantener trazabilidad de datos, fuentes, hora, versión de reglas, score, excepciones e intervenciones de una decisión. | Riesgos P5 |
| RF-018 | Preservar la decisión histórica aunque cambien posteriormente los datos. | Riesgos P5 |
| RF-019 | Distinguir una solicitud nueva de un reintento del mismo proceso. | Riesgos P8 |
| RF-020 | Permitir reprocesar consultas fallidas sin generar doble evaluación o doble desembolso. | Riesgos P10 |
| RF-021 | Mostrar al cliente los estados definidos por Canales y las acciones pendientes. | Canales P5 |
| RF-022 | Traducir estados internos detallados a mensajes simples para el cliente. | Canales P5 |
| RF-023 | Emitir notificaciones de inicio, información pendiente, cambio relevante de estado, aprobación con vigencia, contrato disponible y desembolso. | Canales P6 |
| RF-024 | Permitir notificación mediante app, correo o SMS según canales permitidos/elegidos. | Canales P6 |
| RF-025 | Evitar datos sensibles en mensajes de notificación. | Canales P6 |
| RF-026 | Permitir revisar condiciones, aceptar contrato y continuar al desembolso. | Canales P2 |
| RF-027 | Informar la confirmación del desembolso. | Canales P2 |
| RF-028 | Permitir consultar el estado desde el asesor autorizado y contact center. | Canales P4/P9 |
| RF-029 | Permitir registrar una incidencia desde contact center sin modificar la decisión de Riesgos. | Canales P9 |
| RF-030 | Registrar eventos de ingreso, abandono, error, documento rechazado, reintento, tiempo de respuesta y conversión. | Canales P10 |
| RF-031 | Separar, en el análisis, los datos de navegación de la información financiera sensible en la medida indicada por Canales. | Canales P10 |
| RF-032 | Si se utiliza IA documental, extraer campos sin inventar valores y conservar documento de origen y confianza. | CEO P8, Riesgos P9 |
| RF-033 | Derivar a revisión humana los campos/casos cuyo nivel de confianza esté por debajo del umbral acordado. | Riesgos P9 |
| RF-034 | Permitir orientar al cliente y resumir casos para analistas si se implementan capacidades de IA. | CEO P8 |
| RF-035 | Permitir una arquitectura que soporte crecimiento por productos y cambio de reglas sin reconstruir toda la aplicación. | CEO P10 |

## Requerimientos no convertidos en RF por falta de definición

No se agregan como funcionales detalles sobre: autenticación concreta, formatos documentales, fuentes externas concretas, SLA, retención, reglas de deduplicación, contratos de integración o mecanismo de firma/aceptación. Las entrevistas los identifican como temas necesarios, pero no especifican la solución.

## Alcance MVP

El MVP se orienta a créditos personales para clientes existentes con ingresos recurrentes y debe funcionar de extremo a extremo hasta el desembolso.

**Origen:** CEO P5 y P9.
