# 02. Historias de usuario

## HU-01 — Iniciar solicitud
**Como** cliente existente con ingresos recurrentes, **quiero** iniciar una solicitud desde una oferta o simulación, **para** solicitar un crédito personal sin repetir información que el banco ya posee.

**Criterios:** crear o recuperar una solicitud identificable; conservarla al cambiar de canal; presentar datos disponibles para confirmación; evitar duplicados por reintentos.

## HU-02 — Confirmar o actualizar información
**Como** cliente, **quiero** confirmar o actualizar mis datos, **para** que la evaluación use información vigente.

**Criterios:** mostrar datos disponibles; permitir actualización de información autorizada; validar con negocio qué datos pueden modificarse.

## HU-03 — Autorizar consultas
**Como** cliente, **quiero** autorizar las consultas necesarias, **para** que el banco pueda evaluar mi solicitud.

**Criterios:** solicitar autorización antes de consultas que la requieran; asociar autorización a la solicitud. Texto legal, vigencia y alcance quedan pendientes.

## HU-04 — Gestionar documentos
**Como** cliente, **quiero** adjuntar un documento cuando sea requerido, **para** completar mi solicitud.

**Criterios:** indicar documento faltante; derivar documentos ilegibles según reglas; si existe extracción asistida, conservar documento de origen y confianza; no completar información ausente.

## HU-05 — Ejecutar evaluación crediticia
**Como** analista de riesgos, **quiero** ejecutar políticas de elegibilidad sobre datos trazables, **para** obtener una decisión controlada y auditable.

**Criterios:** considerar identidad, edad, residencia, situación laboral, ingresos, obligaciones, comportamiento, exposición, producto, monto, plazo y canal; resultado aprobado, rechazado, observado o revisión manual; conservar datos usados, fuentes, fecha/hora, versión de reglas, score, excepciones e intervención humana.

## HU-06 — Gestionar revisión manual
**Como** analista, **quiero** revisar casos observados o excepcionales, **para** resolver situaciones que no pueden aprobarse automáticamente.

**Criterios:** derivar inconsistencias, alertas, exposición cercana al límite, documentos ilegibles, información incompleta o excepciones; permitir recomendación; exigir aprobación de supervisor según nivel de riesgo; registrar justificación, documentos y usuario autorizador; no usar aprobaciones por correo fuera del sistema.

## HU-07 — Consultar estado omnicanal
**Como** cliente, **quiero** consultar el estado desde el canal que elija, **para** saber qué ocurre y cuál es el siguiente paso.

**Criterios:** identificador único; recuperación autenticada; estados comprensibles y acciones pendientes; cambiar de canal no reinicia la solicitud.

## HU-08 — Notificar cambios relevantes
**Como** cliente, **quiero** recibir notificaciones de cambios relevantes, **para** no tener que llamar para conocer el avance.

**Criterios:** contemplar inicio, recordatorio, cambio de estado, aprobación con vigencia, contrato y desembolso; permitir app, correo o SMS; no enviar datos sensibles.

## HU-09 — Aceptar condiciones y completar desembolso
**Como** cliente aprobado, **quiero** revisar y aceptar el contrato y completar el desembolso, **para** recibir el crédito.

**Criterios:** aprobación precede aceptación; revisión de condiciones; transición a listo para desembolso y desembolsado; reglas exactas de firma, contrato y desembolso pendientes.

## HU-10 — Asistir sin modificar riesgo
**Como** asesor/contact center, **quiero** consultar el estado, **para** ayudar al cliente sin alterar la decisión de riesgos.

**Criterios:** acceso autorizado; solo información necesaria; registro de incidencias; no modificar decisión de riesgos.

## HU-11 — Extracción documental asistida
**Como** analista, **quiero** recibir campos extraídos y posibles inconsistencias, **para** reducir trabajo manual sin delegar la decisión crediticia.

**Criterios:** documento de origen; nivel de confianza; revisión humana bajo umbral acordado; no completar campos no encontrados.

## HU-12 — Auditar decisión histórica
**Como** responsable de Riesgos, **quiero** reconstruir una decisión histórica, **para** explicar cómo se obtuvo y mantener su integridad.

**Criterios:** conservar información usada, fuente, fecha/hora y versión de reglas; cambios posteriores no reescriben la decisión histórica.
