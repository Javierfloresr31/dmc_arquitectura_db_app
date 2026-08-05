# Caso de uso 1 — Banca

## Banco Andino Digital: Crédito Ágil 360

### Contexto ficticio

Banco Andino Digital es una entidad financiera peruana con 1.2 millones de clientes, una red de 45 agencias y canales web y móvil. El banco desea modernizar la originación de créditos personales para clientes existentes y nuevos.

En la actualidad, una solicitud puede iniciarse por la aplicación móvil, la web, una agencia o el contact center. Sin embargo, cada canal registra información de manera distinta y parte de la evaluación se ejecuta manualmente. El proyecto propone una aplicación moderna denominada **Crédito Ágil 360**, capaz de recibir solicitudes, validar identidad, recopilar documentos, consultar fuentes internas, ejecutar reglas de elegibilidad y mostrar el estado del trámite.

Las entrevistas siguientes representan el inicio del descubrimiento. No constituyen todavía una especificación aprobada.

---

## Entrevista 1 — CEO

**Entrevistada:** Mariana Salazar, CEO  
**Objetivo:** comprender la prioridad estratégica, el resultado esperado y las restricciones del negocio.

### 1. ¿Por qué este proyecto es importante ahora?

**CEO:** Estamos creciendo en clientes digitales, pero no estamos convirtiendo ese crecimiento en colocaciones de crédito. Muchos clientes reciben una oferta, comienzan la solicitud y abandonan porque les pedimos información que el banco ya posee o porque no saben qué está ocurriendo. Además, un competidor ofrece desembolsos en menos de diez minutos para ciertos perfiles.

### 2. ¿Cuál es el problema principal desde su perspectiva?

**CEO:** El problema no es solamente tecnológico. Tenemos un proceso fragmentado. El cliente ve una sola marca, pero internamente la solicitud pasa por canales, riesgos, operaciones, cumplimiento y desembolso como si fueran empresas distintas. Cada transferencia agrega espera, duplicidad y posibilidad de error.

### 3. ¿Qué resultado de negocio espera obtener?

**CEO:** Para clientes preaprobados, espero una experiencia prácticamente inmediata. Para los demás, quiero reducir el tiempo total de evaluación y evitar que el cliente tenga que llamar para conocer el estado. La aplicación debe aumentar la conversión sin deteriorar la calidad de la cartera.

### 4. ¿Cómo mediría el éxito?

**CEO:** Observaría cuatro indicadores: conversión de ofertas a desembolsos, tiempo medio desde la solicitud hasta la decisión, abandono por etapa y mora temprana. También quiero medir cuántas solicitudes necesitan intervención manual.

### 5. ¿A qué clientes debe atender primero?

**CEO:** La primera versión debería enfocarse en créditos personales para clientes existentes con ingresos recurrentes. Luego podremos incluir clientes nuevos y trabajadores independientes. No quiero intentar resolver todos los productos desde el comienzo.

### 6. ¿Qué experiencia espera para el cliente?

**CEO:** El cliente debe poder iniciar en un canal y continuar en otro sin perder información. Debe saber qué datos estamos utilizando, qué documento falta y cuál es el siguiente paso. No quiero mensajes ambiguos como “solicitud observada”; deben ser instrucciones claras.

### 7. ¿Qué riesgos no está dispuesta a aceptar?

**CEO:** No aceptaría una solución que apruebe créditos fuera de las políticas, que exponga información financiera o que tome decisiones imposibles de explicar. Tampoco podemos comprometer la continuidad del core bancario ni depender de que todos los sistemas legados cambien al mismo tiempo.

### 8. ¿Qué papel debería tener la inteligencia artificial?

**CEO:** Puede ayudar a leer documentos, detectar inconsistencias, orientar al cliente y resumir un caso para el analista. La decisión crediticia debe seguir políticas controladas y auditables. Si utilizamos modelos, deben tener monitoreo, responsables y límites claros.

### 9. ¿Existe una fecha o compromiso relevante?

**CEO:** Queremos una primera versión operativa en cuatro meses para una campaña de clientes con abono de sueldo. Prefiero un alcance reducido que funcione de extremo a extremo antes que una demostración muy amplia sin capacidad real de desembolso.

### 10. ¿Qué decisión espera habilitar con este taller?

**CEO:** Quiero que el equipo defina una arquitectura que pueda crecer por productos, que no duplique innecesariamente datos sensibles y que nos permita cambiar reglas sin reconstruir toda la aplicación.

---

## Entrevista 2 — Área crítica: Riesgos de Crédito

**Entrevistado:** Diego Paredes, gerente de Riesgos de Crédito  
**Objetivo:** comprender las reglas de decisión, los datos requeridos y los controles de riesgo.

### 1. ¿Cómo se evalúa hoy una solicitud?

**Riesgos:** Primero identificamos al solicitante y verificamos si es cliente. Consultamos información de ingresos, comportamiento interno, endeudamiento, alertas y score. Después aplicamos políticas de elegibilidad. Algunas solicitudes se aprueban automáticamente, otras pasan a revisión y otras se rechazan.

### 2. ¿Qué información es indispensable?

**Riesgos:** Identidad, edad, residencia, situación laboral, ingresos, obligaciones vigentes, comportamiento de pago, exposición total, producto solicitado, monto, plazo y canal de origen. Para clientes existentes usamos movimientos y productos internos. Para clientes nuevos dependemos más de documentos y fuentes externas.

### 3. ¿Las reglas cambian con frecuencia?

**Riesgos:** Sí. Cambian por campaña, apetito de riesgo, segmento, comportamiento de cartera y regulación. Algunas reglas tienen vigencia definida. El problema actual es que parte de ellas está en sistemas, parte en hojas de cálculo y parte en manuales operativos.

### 4. ¿Qué resultados puede producir una evaluación?

**Riesgos:** Como mínimo: aprobado, rechazado, observado o enviado a revisión manual. Un aprobado puede incluir monto máximo, plazo permitido, tasa, condiciones y fecha de vigencia. Un rechazo debe registrar las razones internas, aunque no todas se muestran literalmente al cliente.

### 5. ¿Qué necesita auditar?

**Riesgos:** Necesitamos reconstruir la decisión: qué datos se usaron, de qué fuente, a qué hora, qué versión de reglas estaba vigente, qué score se obtuvo, qué excepciones se aplicaron y quién intervino. Si un dato cambia después, la decisión histórica no debe reescribirse.

### 6. ¿Qué casos requieren revisión manual?

**Riesgos:** Inconsistencias entre ingresos declarados y observados, alertas de identidad, exposición cercana al límite, documentos ilegibles, clientes con información incompleta y solicitudes que una campaña marca como excepcionales. También ciertas combinaciones de monto y perfil.

### 7. ¿Cómo se manejan las excepciones?

**Riesgos:** Un analista puede recomendar una excepción, pero según el nivel de riesgo debe aprobarla un supervisor. Debe quedar una justificación, los documentos considerados y el usuario que autorizó. No queremos aprobaciones por correo fuera del sistema.

### 8. ¿Qué problemas de datos existen hoy?

**Riesgos:** Hay diferencias en nombres, direcciones y ocupaciones entre sistemas. Algunas consultas externas responden tarde o no responden. También recibimos información duplicada cuando el cliente intenta varias veces. Necesitamos distinguir una nueva solicitud de un reintento del mismo proceso.

### 9. ¿Qué espera de la IA?

**Riesgos:** Puede extraer campos de boletas o constancias y señalar contradicciones, pero no debe completar datos que no encuentra. Cada campo extraído debe conservar el documento de origen y un nivel de confianza. Una persona debe revisar los casos por debajo del umbral acordado.

### 10. ¿Cuáles son los principales requerimientos no funcionales?

**Riesgos:** Trazabilidad completa, segregación de funciones, protección de datos personales, disponibilidad durante campañas, capacidad de reprocesar consultas fallidas e idempotencia para no evaluar o desembolsar dos veces la misma solicitud.

---

## Entrevista 3 — Área crítica: Canales Digitales

**Entrevistada:** Valeria Núñez, gerente de Canales Digitales  
**Objetivo:** comprender la experiencia del usuario, el recorrido omnicanal y las integraciones necesarias.

### 1. ¿Cómo inicia hoy un cliente la solicitud?

**Canales:** Puede responder una oferta en la app, llenar un formulario web, llamar al contact center o acercarse a una agencia. Cada canal crea registros con identificadores diferentes. Si el cliente cambia de canal, muchas veces debe empezar otra vez.

### 2. ¿Qué recorrido desea implementar?

**Canales:** El cliente selecciona la oferta o simula el crédito, confirma sus datos, autoriza consultas, completa información faltante, adjunta documentos cuando corresponde, revisa las condiciones, acepta el contrato y recibe el desembolso. Debe ver una línea de tiempo con el estado y las acciones pendientes.

### 3. ¿Qué datos no deberían volver a solicitarse?

**Canales:** Datos de contacto verificados, dirección, información laboral reciente y cuentas de abono cuando ya están disponibles y vigentes. Sin embargo, debemos permitir que el cliente confirme o actualice información, porque usar datos antiguos también genera problemas.

### 4. ¿Cómo debe funcionar la continuidad entre canales?

**Canales:** La solicitud necesita un identificador único. El cliente debe recuperar el proceso después de autenticarse. Un asesor autorizado debería ver el mismo estado y ayudar sin acceder a información innecesaria. El canal puede cambiar, pero la solicitud debe ser la misma.

### 5. ¿Qué estados necesita mostrar al cliente?

**Canales:** Borrador, información pendiente, en evaluación, requiere documento, requiere validación, aprobado, no aprobado, pendiente de aceptación, listo para desembolso, desembolsado y cancelado. Los nombres internos pueden ser más detallados, pero debemos traducirlos a mensajes simples.

### 6. ¿Qué notificaciones se requieren?

**Canales:** Confirmación de inicio, recordatorio de información pendiente, cambio relevante de estado, aprobación con vigencia, contrato disponible y desembolso. El cliente debe elegir canales permitidos, como notificación dentro de la app, correo o SMS. No debemos enviar datos sensibles en el mensaje.

### 7. ¿Qué ocurre cuando una integración falla?

**Canales:** No deberíamos perder lo que el cliente ya registró. Debemos informar que continuaremos procesando o pedir que reintente solo la acción necesaria. Hoy un timeout puede dejar una pantalla congelada y el usuario vuelve a presionar, creando solicitudes duplicadas.

### 8. ¿Qué volumen esperan?

**Canales:** Normalmente recibimos unas 8,000 simulaciones diarias y 1,500 solicitudes. En campañas podemos multiplicar por cinco el tráfico durante las primeras horas. La mayoría consulta desde el móvil.

### 9. ¿Qué accesibilidad y soporte se necesitan?

**Canales:** Lenguaje claro, formularios cortos, validaciones inmediatas y compatibilidad con lectores de pantalla. El contact center debe poder consultar el estado y registrar una incidencia, pero no modificar una decisión de riesgos.

### 10. ¿Qué información necesita el área para mejorar el recorrido?

**Canales:** Eventos por etapa: ingreso, abandono, error, documento rechazado, reintento, tiempo de respuesta y conversión. Necesitamos analizarlos sin mezclar datos de navegación con información financiera más sensible de lo necesario.

---

## Evidencias iniciales extraídas de las entrevistas

| Categoría | Elementos mencionados |
|---|---|
| Actores | Cliente, asesor, analista de riesgos, supervisor, sistemas externos, motor de reglas |
| Objetos de negocio | Cliente, solicitud, oferta, simulación, documento, evaluación, decisión, excepción, contrato, desembolso, notificación |
| Eventos posibles | Solicitud iniciada, datos confirmados, documento cargado, evaluación solicitada, decisión emitida, excepción aprobada, contrato aceptado, crédito desembolsado |
| Restricciones | Trazabilidad, datos sensibles, decisión explicable, segregación de funciones, idempotencia, continuidad omnicanal |
| Incertidumbres | Fuentes externas exactas, política de retención, reglas de deduplicación, límites de actualización de datos, SLA por etapa |

## Reto para el equipo

Convertir estas entrevistas en una primera especificación del producto **Crédito Ágil 360**, separando claramente:

- hechos confirmados;
- supuestos del equipo;
- reglas que deben validar Riesgos o Cumplimiento;
- datos maestros frente a datos transaccionales;
- decisiones síncronas frente a procesos asíncronos;
- funcionalidades del MVP frente a capacidades futuras.