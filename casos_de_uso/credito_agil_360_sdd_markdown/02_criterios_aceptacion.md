# Crédito Ágil 360 — Criterios de Aceptación

## Fuente

Los criterios se derivan exclusivamente de las historias y declaraciones contenidas en `01_banca_credito_agil_360.md`.

> Los criterios que requieren parámetros no proporcionados quedan expresamente pendientes; no se inventan valores.

## Criterios

### HU-001 — Iniciar solicitud

**CA-001.1** Dado que un cliente inicia el proceso desde un canal soportado, cuando comienza una solicitud, entonces debe registrarse el inicio del trámite.

**CA-001.2** La solicitud debe poder originarse desde app, web, agencia o contact center, porque esos canales fueron identificados en las entrevistas.

**Pendiente:** definición del flujo exacto de autenticación por canal y campos obligatorios de creación.

### HU-002 — Continuidad omnicanal

**CA-002.1** La solicitud debe disponer de un identificador único que permita recuperarla después de cambiar de canal.

**CA-002.2** Al recuperar una solicitud, debe conservarse la información previamente registrada.

**CA-002.3** Un asesor autorizado debe poder consultar el mismo estado de la solicitud.

**Pendiente:** mecanismo concreto de autenticación y reglas de autorización por rol.

### HU-003 — Datos vigentes

**CA-003.1** El cliente debe poder confirmar datos previamente disponibles.

**CA-003.2** El cliente debe poder actualizar información cuando corresponda.

**CA-003.3** No debe asumirse que un dato disponible es vigente sin permitir su confirmación/actualización cuando aplique.

**Pendiente:** criterio de vigencia y límites de actualización.

### HU-004 — Autorización de consultas

**CA-004.1** El recorrido debe contemplar la autorización de las consultas necesarias antes de ejecutarlas.

**Pendiente:** fuentes exactas, texto de autorización, evidencia legal y duración de la autorización.

### HU-005 — Información y documentos

**CA-005.1** El cliente debe poder completar información faltante.

**CA-005.2** El cliente debe poder adjuntar documentos cuando correspondan.

**CA-005.3** Un documento ilegible debe poder derivar a revisión manual.

**Pendiente:** tipos de documentos, formatos, tamaño máximo, almacenamiento y política de retención.

### HU-006 — Estado y acciones

**CA-006.1** El cliente debe visualizar, como mínimo, los estados: Borrador, información pendiente, en evaluación, requiere documento, requiere validación, aprobado, no aprobado, pendiente de aceptación, listo para desembolso, desembolsado y cancelado.

**CA-006.2** El cliente debe visualizar las acciones pendientes.

**CA-006.3** Los estados internos pueden ser más detallados, pero deben traducirse a mensajes simples.

### HU-007 — Notificaciones

**CA-007.1** Deben contemplarse notificaciones de inicio, información pendiente, cambio relevante de estado, aprobación con vigencia, contrato disponible y desembolso.

**CA-007.2** Los canales permitidos mencionados son notificación dentro de la app, correo o SMS.

**CA-007.3** El mensaje no debe contener datos sensibles.

**Pendiente:** reglas de preferencia, reintentos, plantillas y condiciones exactas de cada notificación.

### HU-008 — Contrato y aceptación

**CA-008.1** Una solicitud aprobada debe permitir revisar las condiciones de aprobación.

**CA-008.2** El cliente debe poder aceptar el contrato para continuar al desembolso.

**Pendiente:** mecanismo de aceptación, firma y versión contractual.

### HU-009 — Desembolso

**CA-009.1** Una solicitud aceptada debe poder llegar al estado listo para desembolso y posteriormente desembolsado.

**CA-009.2** Debe existir una confirmación del desembolso.

**Pendiente:** integración y contrato técnico con desembolso/core.

### HU-010 / HU-019 — Fallos e idempotencia

**CA-010.1** Un timeout o fallo de integración no debe eliminar la información ya registrada.

**CA-010.2** El cliente debe recibir una indicación sobre continuar procesando o reintentar la acción necesaria.

**CA-010.3** Un reintento no debe producir una segunda evaluación ni un segundo desembolso de la misma solicitud.

**Pendiente:** clave de idempotencia y semántica exacta de reintento.

### HU-013 — Revisión de Riesgos

**CA-013.1** El analista debe poder revisar los casos que requieren intervención manual.

**CA-013.2** La revisión debe disponer de la información utilizada por la evaluación.

**Pendiente:** interfaz y permisos detallados.

### HU-014 / HU-015 — Excepciones

**CA-014.1** Un analista puede recomendar una excepción.

**CA-014.2** Debe registrarse justificación, documentos considerados y usuario que autoriza.

**CA-014.3** Según el nivel de riesgo, la excepción debe requerir aprobación de un supervisor.

**Pendiente:** niveles de riesgo y matriz de aprobación.

### HU-016 / HU-017 — Decisión y auditoría

**CA-016.1** La evaluación debe producir como mínimo: aprobado, rechazado, observado o revisión manual.

**CA-016.2** Una aprobación puede incluir monto máximo, plazo permitido, tasa, condiciones y fecha de vigencia.

**CA-016.3** Un rechazo debe registrar las razones internas, sin asumir que todas se muestran literalmente al cliente.

**CA-017.1** Debe ser posible reconstruir una decisión indicando datos utilizados, fuentes, hora, versión de reglas, score, excepciones e intervención.

**CA-017.2** Un cambio posterior de datos no debe reescribir la decisión histórica.

### HU-018 — Duplicidad

**CA-018.1** El sistema debe distinguir una solicitud nueva de un reintento del mismo proceso.

**Pendiente:** reglas de deduplicación y definición formal de solicitud equivalente.

### HU-020 — IA documental

**CA-020.1** Si se utiliza IA para extraer datos, no debe completar campos que no encuentre en el documento.

**CA-020.2** Cada campo extraído debe conservar referencia al documento de origen y nivel de confianza.

**CA-020.3** Los casos por debajo del umbral acordado deben ser revisados por una persona.

**Pendiente:** umbral, modelo, responsable, monitoreo, límites y criterios de revisión.

### HU-021 — Analítica de recorrido

**CA-021.1** Deben registrarse eventos de ingreso, abandono, error, documento rechazado, reintento, tiempo de respuesta y conversión.

**CA-021.2** La analítica debe evitar mezclar información de navegación con información financiera sensible más allá de lo necesario.

**Pendiente:** catálogo de eventos, retención, plataforma analítica y definición de métricas.

## Vacíos transversales

Quedan pendientes, entre otros: SLA por etapa, fuentes externas exactas, política de retención, reglas de deduplicación, límites de actualización de datos, autenticación/autorización, formatos documentales, contratos de integración y parámetros de IA.
