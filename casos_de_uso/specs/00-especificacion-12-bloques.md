# Crédito Ágil 360 — Especificación en 12 bloques

> Estado: Draft de descubrimiento. Esta estructura separa necesidad, solución y evidencia. Los puntos no respaldados por entrevistas quedan como preguntas o decisiones pendientes.

## 01. Identidad
- Solución: **Crédito Ágil 360**.
- Caso de uso: originación de créditos personales.
- MVP: clientes existentes con ingresos recurrentes.
- Canales: app, web, agencia y contact center.
- Las entrevistas son descubrimiento inicial, no especificación aprobada.

## 02. Contexto
El proceso actual está fragmentado entre canales, Riesgos, Operaciones, Cumplimiento y desembolso. Los canales generan identificadores diferentes y el cambio de canal puede obligar al cliente a empezar nuevamente. La evaluación combina datos internos, fuentes externas, reglas distribuidas y actividades manuales.

La solución debe reducir duplicidad, espera y errores sin comprometer la continuidad del core, la protección de datos ni las políticas de riesgo. La solicitud debe tener un identificador único y poder continuar entre canales.

## 03. Objetivos
1. Aumentar conversión de ofertas a desembolsos sin deteriorar la calidad de cartera.
2. Reducir tiempo de solicitud a decisión.
3. Reducir abandono por etapa.
4. Reducir intervención manual cuando el caso pueda resolverse automáticamente.
5. Dar al cliente estado, datos faltantes y siguiente paso claros.
6. Permitir cambiar reglas sin reconstruir toda la aplicación.
7. Mantener decisiones explicables, auditables y reconstruibles.
8. Crear una base que pueda crecer por productos.

Indicadores citados: conversión oferta→desembolso, tiempo medio solicitud→decisión, abandono por etapa, mora temprana y solicitudes con intervención manual.

## 04. Alcance
### Primera versión
- Crédito personal para clientes existentes con ingresos recurrentes.
- Selección de oferta/simulación.
- Creación y recuperación de solicitud mediante identificador único.
- Continuidad omnicanal.
- Confirmación/actualización de datos existentes.
- Autorización de consultas.
- Documentos y revisión cuando corresponda.
- Consultas internas y externas necesarias para evaluación.
- Políticas de elegibilidad versionadas y auditables.
- Resultados aprobado, rechazado/no aprobado, observado y revisión manual.
- Condiciones del aprobado: monto máximo, plazo, tasa, condiciones y vigencia cuando correspondan.
- Excepciones con segregación analista/supervisor.
- Auditoría y trazabilidad.
- Estado y notificaciones.
- Idempotencia y reproceso de consultas fallidas.
- IA asistida para extracción, inconsistencias, orientación y resumen.
- Aceptación de contrato y transición al desembolso.

### Fuera de alcance inicial
- Resolver todos los productos.
- Incorporar desde el inicio clientes nuevos y trabajadores independientes.
- Cambiar simultáneamente todos los sistemas legados.
- Inventar o fijar reglas crediticias no proporcionadas por Riesgos.
- Fijar proveedores externos todavía no identificados.

## 05. Actores
| Actor | Participación |
|---|---|
| Cliente | Inicia/continúa solicitud, confirma datos, entrega documentos y acepta condiciones/contrato. |
| Asesor | Apoya al cliente en canales autorizados. |
| Analista de Riesgos | Revisa casos manuales y recomienda excepciones. |
| Supervisor | Autoriza excepciones según nivel de riesgo. |
| Motor de reglas | Ejecuta políticas de elegibilidad versionadas. |
| Sistemas internos | Proporcionan información de cliente, ingresos, productos, movimientos, comportamiento y exposición. |
| Fuentes externas | Proporcionan información necesaria para evaluación; proveedores pendientes. |
| IA asistida | Extrae información, señala inconsistencias y resume; no decide crédito por sí sola. |
| Contact center | Consulta estado y registra incidencias; no modifica decisiones de Riesgos. |

## 06. Procesos
1. Selección de oferta o simulación.
2. Crear/recuperar solicitud.
3. Identificación/autenticación.
4. Confirmar datos disponibles.
5. Autorizar consultas.
6. Completar información faltante.
7. Cargar/revisar documentos.
8. Consultar fuentes internas/externas.
9. Ejecutar evaluación.
10. Emitir decisión o derivar a revisión manual.
11. Gestionar excepción si corresponde.
12. Comunicar resultado y condiciones.
13. Aceptar contrato.
14. Preparar y ejecutar desembolso.
15. Registrar eventos, auditoría y métricas.

## 07. Historias
- **HU-01:** Como cliente, quiero continuar mi solicitud desde otro canal sin volver a ingresar información.
- **HU-02:** Como cliente, quiero revisar datos precargados y actualizar solo lo necesario.
- **HU-03:** Como cliente, quiero conocer mi estado y acciones pendientes.
- **HU-04:** Como cliente, quiero saber qué documento falta o qué debo corregir.
- **HU-05:** Como Riesgos, quiero ejecutar políticas versionadas para obtener decisiones consistentes y auditables.
- **HU-06:** Como analista, quiero recibir casos derivados con sus evidencias.
- **HU-07:** Como analista/supervisor, quiero gestionar excepciones con autorización y justificación.
- **HU-08:** Como Riesgos, quiero reconstruir decisiones históricas.
- **HU-09:** Como sistema, quiero distinguir reintentos de nuevas solicitudes.
- **HU-10:** Como analista, quiero asistencia de IA con origen y confianza de cada extracción.
- **HU-11:** Como contact center, quiero consultar estado y registrar incidencias sin modificar Riesgos.
- **HU-12:** Como cliente aprobado, quiero revisar condiciones, aceptar contrato y continuar a desembolso.

## 08. RF
RF-01 solicitud mediante identificador único; RF-02 continuidad omnicanal; RF-03 datos internos y actualización; RF-04 autorización de consultas; RF-05 documentos; RF-06 fuentes internas/externas; RF-07 políticas versionadas; RF-08 resultados; RF-09 condiciones del aprobado; RF-10 revisión manual; RF-11 excepciones; RF-12 trazabilidad; RF-13 decisiones históricas inmutables; RF-14 estados comprensibles; RF-15 notificaciones; RF-16 idempotencia/reproceso; RF-17 contact center; RF-18 asistencia IA; RF-19 documento origen y confianza; RF-20 contrato/desembolso.

## 09. RNF
RNF-01 trazabilidad; RNF-02 segregación de funciones; RNF-03 protección de datos; RNF-04 explicabilidad; RNF-05 idempotencia; RNF-06 disponibilidad/capacidad en campañas; RNF-07 tolerancia a fallas; RNF-08 reproceso; RNF-09 accesibilidad y lenguaje claro; RNF-10 minimización de datos en notificaciones; RNF-11 separación de navegación y datos sensibles; RNF-12 independencia de cambios simultáneos en legados.

Los volúmenes de 8,000 simulaciones/día, 1,500 solicitudes/día y factor cinco en campañas son evidencia de entrevistas, no SLA aprobados.

## 10. Reglas
### Confirmadas
- La decisión debe seguir políticas controladas y auditables.
- Las reglas pueden cambiar por campaña, apetito de riesgo, segmento, comportamiento y regulación.
- Las reglas pueden tener vigencia.
- Un aprobado puede contener monto máximo, plazo, tasa, condiciones y vigencia.
- Las razones internas de rechazo deben conservarse; no todas se muestran literalmente al cliente.
- Inconsistencias, alertas de identidad, exposición cercana al límite, documentos ilegibles, información incompleta y ciertas combinaciones de monto/perfil pueden derivar a revisión.
- Las excepciones requieren recomendación y, según nivel de riesgo, autorización del supervisor.
- La excepción conserva justificación, documentos considerados y usuario autorizador.
- IA no debe completar datos que no encuentra.
- Cada extracción IA conserva documento origen y nivel de confianza.
- Casos bajo el umbral de confianza acordado requieren revisión humana.
- Contact center no modifica decisiones de Riesgos.

### Pendientes
Umbrales exactos, score, deduplicación, vigencia de datos, niveles de excepción, umbral IA, razones comunicables, límites de actualización y reglas formales de desembolso.

## 11. Criterios de aceptación
- **CA-01 Continuidad:** al autenticarse en otro canal se recupera la misma solicitud.
- **CA-02 Datos:** los datos precargados pueden confirmarse/actualizarse conforme a reglas y queda trazabilidad.
- **CA-03 Estado:** cada estado muestra explicación comprensible y acciones pendientes.
- **CA-04 Evaluación:** se registra política/versión, fuentes, fecha/hora, resultado y evidencia.
- **CA-05 Manual:** un caso derivado conserva sus evidencias y trazabilidad.
- **CA-06 Excepción:** se exige autorización del supervisor cuando el nivel de riesgo lo determine.
- **CA-07 IA:** cada extracción conserva origen y confianza; bajo umbral se revisa manualmente.
- **CA-08 Idempotencia:** un reintento no genera evaluación/desembolso duplicado.
- **CA-09 Fallas:** un timeout no pierde información ya registrada y permite reintento de la acción necesaria.
- **CA-10 Desembolso:** un aprobado con contrato aceptado transiciona al desembolso cuando cumple condiciones definidas.

## 12. Preguntas
1. ¿Cuál es el producto y variante exacta del MVP?
2. ¿Qué define cliente existente e ingreso recurrente?
3. ¿Cuál es la fuente maestra de cada dato?
4. ¿Qué fuentes externas/proveedores se consultarán?
5. ¿Cómo se autentica el cliente en cada canal?
6. ¿Qué datos puede modificar el cliente?
7. ¿Cuál es la regla formal de deduplicación?
8. ¿Cómo se identifica un reintento?
9. ¿Cuál es el SLA objetivo por etapa?
10. ¿Qué reglas producen cada resultado?
11. ¿Cuál es el score y su propietario?
12. ¿Cómo se versionan/publican políticas?
13. ¿Qué niveles de excepción existen?
14. ¿Qué razones de rechazo se comunican?
15. ¿Cuál es la vigencia de cada dato?
16. ¿Qué documentos son obligatorios por producto/perfil?
17. ¿Qué controles de calidad documental aplican?
18. ¿Cuál es el umbral de confianza de IA?
19. ¿Qué modelos/proveedores IA están permitidos?
20. ¿Qué datos pueden salir hacia IA externa?
21. ¿Cuál es la retención de documentos/evidencias?
22. ¿Qué eventos son auditables obligatoriamente?
23. ¿Qué RTO/RPO se requiere?
24. ¿Qué operación será asíncrona?
25. ¿Cuál es el sistema maestro de contrato?
26. ¿Cuál es el sistema maestro de desembolso?
27. ¿Qué canales de notificación se permiten?
28. ¿Qué datos no pueden enviarse en notificaciones?
29. ¿Qué permisos exactos tendrá cada rol?
30. ¿Qué información podrá ver contact center?
31. ¿Qué eventos se enviarán a analítica?
32. ¿Cómo se manejará eliminación/anonimización?
33. ¿Qué ambientes/datos de prueba existirán?
34. ¿Qué definición de listo/aceptado tendrá cada incremento?
