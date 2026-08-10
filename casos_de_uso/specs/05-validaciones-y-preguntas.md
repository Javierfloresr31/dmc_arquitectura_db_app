# 05. Validaciones y preguntas por responder

Estas preguntas bloquean o condicionan decisiones de implementación. No deben resolverse por suposición.

## Riesgos / Crédito
1. ¿Cuáles son las políticas exactas de elegibilidad del MVP?
2. ¿Qué reglas son automáticas y cuáles siempre requieren revisión humana?
3. ¿Cuáles son los umbrales para inconsistencias, score, exposición y confianza documental?
4. ¿Qué fuentes internas exactas se consultarán y cuál es el sistema maestro de cada dato?
5. ¿Qué fuentes externas se consultarán y qué hacer ante timeout/no disponibilidad?
6. ¿Cuál es la política exacta de deduplicación entre reintentos y solicitudes concurrentes?
7. ¿Qué niveles de riesgo requieren aprobación de supervisor para excepciones?
8. ¿Qué información de un rechazo puede mostrarse al cliente y con qué redacción?
9. ¿Qué datos deben conservarse para reconstruir una decisión y durante cuánto tiempo?
10. ¿Cómo debe versionarse y activarse una política por campaña, segmento y vigencia?

## Cumplimiento / Legal
11. ¿Qué consentimientos son obligatorios para cada consulta y cuánto duran?
12. ¿Qué textos de autorización, contrato y comunicaciones deben aprobarse?
13. ¿Cuál es la política de protección, clasificación, retención y eliminación de documentos?
14. ¿Qué restricciones regulatorias aplican a explicabilidad, decisiones automatizadas y uso de IA?
15. ¿Qué evidencias de auditoría deben conservarse y por cuánto tiempo?

## Canales / Experiencia
16. ¿Qué datos puede actualizar el cliente y cuáles requieren validación adicional?
17. ¿Cómo se autentica y recupera una solicitud en cada canal?
18. ¿Qué información puede visualizar un asesor/contact center?
19. ¿Qué canales de notificación están permitidos por tipo de evento y cómo se registra la preferencia?
20. ¿Qué ocurre exactamente cuando una integración falla después de que el usuario presiona continuar?

## Operaciones / Desembolso
21. ¿Qué sistemas intervienen en contrato y desembolso?
22. ¿Qué condiciones deben cumplirse antes de pasar a listo para desembolso?
23. ¿Cómo se garantiza idempotencia de desembolso en el sistema destino?
24. ¿Existe reversa/cancelación posterior al desembolso y cuál es su proceso?

## Datos / Arquitectura
25. ¿Cuál es el identificador maestro del cliente y cómo se relaciona con el identificador único de solicitud?
26. ¿Qué datos son maestros, cuáles son snapshot transaccional y cuáles son derivados de la evaluación?
27. ¿Qué SLA técnico debe cumplir cada integración?
28. ¿Cuáles son RTO/RPO y disponibilidad objetivo?
29. ¿Qué volumen máximo de campaña debe soportarse y durante cuánto tiempo?
30. ¿Qué stack tecnológico y restricciones de infraestructura existen?

## IA
31. ¿Qué proveedor/modelo está permitido para extracción documental?
32. ¿Cómo se calcula y calibra el nivel de confianza?
33. ¿Cuál es el umbral que obliga a revisión humana?
34. ¿Qué documentos y campos están dentro del alcance del MVP?
35. ¿Qué datos pueden enviarse al modelo y qué datos deben permanecer dentro del banco?
36. ¿Cómo se monitorearán precisión, deriva, errores y responsables del modelo?

## Validaciones mínimas antes de desarrollo
- Validar alcance MVP con CEO/Product.
- Validar reglas y estados de decisión con Riesgos.
- Validar consentimientos, retención y mensajes con Cumplimiento/Legal.
- Validar identidad, datos maestros y fuentes con Arquitectura/Datos.
- Validar contratos de integración con los dueños de sistemas.
- Validar contrato y desembolso con Operaciones.
- Validar IA con Riesgos, Cumplimiento y responsable técnico/model risk.
- Convertir los valores de volumen y tiempo de entrevista en objetivos técnicos solo después de aprobación.
