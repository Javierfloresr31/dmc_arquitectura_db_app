# Siniestro Fácil

Documentación inicial derivada exclusivamente de las entrevistas proporcionadas para el caso de uso **Seguros Horizonte: Siniestro Fácil**.

## 1. Objetivo de la solución

Construir una aplicación para registrar siniestros vehiculares, validar cobertura, recopilar evidencias, coordinar asistencia, gestionar evaluación y mantener informado al asegurado, con controles antifraude y trazabilidad completa.

## 2. Alcance inicial confirmado por entrevistas

El alcance inicial se limita a:
- Siniestros vehiculares de daños materiales sin lesiones graves.
- Clientes directos con pólizas vigentes.
- Flujo digital para siniestros simples.
- Derivación a rutas especializadas cuando existan heridos, fallecidos, procesos legales o daños masivos.

## 3. Actores identificados

- Asegurado.
- Reportante autorizado.
- Operador de siniestros.
- Ajustador.
- Investigador de fraude.
- Taller.
- Proveedor de grúa / asistencia.
- Supervisor.

## 4. Objetos de negocio identificados

- Póliza.
- Vehículo.
- Siniestro.
- Participante.
- Cobertura.
- Evidencia.
- Asistencia.
- Inspección.
- Presupuesto.
- Autorización.
- Alerta.
- Pago.

## 5. Historias de usuario

### HU-01: Reportar siniestro desde el móvil
**Como** asegurado o reportante autorizado, **quiero** registrar un siniestro desde mi teléfono **para** iniciar la atención sin llamadas repetidas ni transcripción manual.

### HU-02: Registrar datos mínimos del evento
**Como** operador de siniestros, **quiero** capturar datos mínimos del evento cuando el cliente reporta un accidente **para** crear el caso incluso si aún faltan evidencias.

### HU-03: Validar cobertura y deducible
**Como** operador de siniestros, **quiero** validar cobertura y deducible al crear el caso **para** confirmar si el siniestro puede seguir por el flujo principal.

### HU-04: Adjuntar evidencias
**Como** asegurado, **quiero** adjuntar fotografías, documentos y declaraciones **para** aportar la información requerida para evaluación.

### HU-05: Coordinar asistencia
**Como** operador de siniestros, **quiero** coordinar grúa o asistencia cuando corresponda **para** apoyar al cliente en el momento del evento.

### HU-06: Ver estado del caso
**Como** asegurado, **quiero** ver el estado de mi siniestro **para** conocer el siguiente paso y reducir llamadas de seguimiento.

### HU-07: Priorizar casos complejos
**Como** sistema de siniestros, **quiero** clasificar los casos según severidad y riesgo **para** derivar los complejos a revisión especializada.

### HU-08: Detectar señales de fraude
**Como** investigador de fraude, **quiero** revisar alertas con explicación y evidencia asociada **para** confirmar o descartar inconsistencias.

### HU-09: Mantener trazabilidad completa
**Como** supervisor, **quiero** consultar una línea de tiempo del caso **para** auditar acciones, aprobaciones, comunicaciones y decisiones.

### HU-10: Gestionar presupuestos y aprobaciones
**Como** taller u operador, **quiero** registrar presupuesto, observaciones y aprobaciones **para** controlar la reparación y su vigencia.

## 6. Requerimientos funcionales

### RF-01 Registro del siniestro
El sistema debe permitir crear un siniestro vehicular con número de póliza o documento del asegurado, placa, fecha, ubicación aproximada, tipo de evento y medio de contacto.

### RF-02 Identificación del reportante
El sistema debe permitir identificar si el reporte lo realiza el asegurado o una persona autorizada.

### RF-03 Validación de cobertura
El sistema debe consultar la póliza para validar cobertura y deducible.

### RF-04 Captura de evidencias
El sistema debe permitir adjuntar fotografías, documentos de identidad, licencia, tarjeta de propiedad, declaración del conductor y otros soportes.

### RF-05 Trazabilidad de evidencias
El sistema debe registrar la fuente, fecha de recepción, momento de captura y metadatos disponibles de cada evidencia.

### RF-06 Coordinación de asistencia
El sistema debe permitir registrar y gestionar solicitudes de grúa o asistencia.

### RF-07 Estados del siniestro
El sistema debe manejar, como mínimo, los estados: reportado, validando cobertura, asistencia coordinada, evidencia pendiente, en evaluación, inspección programada, presupuesto recibido, autorizado, observado, rechazado, en reparación, listo para entrega, indemnizado y cerrado.

### RF-08 Asignación inteligente
El sistema debe asignar el siniestro según ciudad, tipo de daño, severidad, cobertura, disponibilidad de proveedores y señales de riesgo.

### RF-09 Derivación a ajustador
El sistema debe derivar los casos complejos a revisión especializada o ajustador.

### RF-10 Gestión de presupuesto
El sistema debe permitir que el taller presente presupuesto, diagnóstico, observaciones y actualizaciones durante la reparación.

### RF-11 Alertas antifraude
El sistema debe generar alertas con tipo, severidad, explicación, datos de origen, fecha, regla o modelo utilizado y estado de revisión.

### RF-12 Revisión humana
El sistema debe permitir que una alerta sea confirmada, descartada o enviada a mayor análisis por un investigador.

### RF-13 Línea de tiempo del caso
El sistema debe mantener una línea de tiempo con cambios, evidencias, asignaciones, autorizaciones, comunicaciones y pagos.

### RF-14 Evitar duplicados
El sistema debe detectar y controlar posibles casos duplicados generados por múltiples canales de reporte.

### RF-15 Conservación de originales
El sistema debe conservar el contenido original de cada evidencia y sus versiones derivadas.

## 7. Requerimientos no funcionales

### RNF-01 Trazabilidad
Toda acción relevante debe quedar registrada con usuario, fecha, cambio ejecutado y contexto.

### RNF-02 Privacidad
El sistema debe restringir el acceso a datos personales y evidencia sensible según rol y necesidad.

### RNF-03 Disponibilidad ante terceros
La arquitectura debe tolerar integraciones lentas o indisponibles con talleres, grúas, mapas, mensajería y pagos.

### RNF-04 Explicabilidad
Las recomendaciones o alertas automatizadas deben poder explicarse con datos de origen y versión de regla o modelo.

### RNF-05 Revisión humana
Las decisiones sensibles no deben depender únicamente de automatización.

### RNF-06 Conservación de evidencia
Los originales de evidencia no deben perderse al generar versiones optimizadas para consumo de la aplicación.

### RNF-07 Escalabilidad
La solución debe soportar el volumen de operación descrito en el contexto: aproximadamente 420,000 pólizas activas y 18,000 reportes mensuales.

## 8. Criterios de aceptación

### CA-01
Dado un asegurado con póliza vigente, cuando registra un siniestro, entonces el sistema crea el caso con los datos mínimos requeridos.

### CA-02
Dado un caso recién creado, cuando se consulta la póliza, entonces el sistema muestra el estado de cobertura y deducible.

### CA-03
Dado un siniestro con evidencias adjuntas, cuando se revisa el caso, entonces el sistema conserva los archivos originales y sus metadatos.

### CA-04
Dado un caso con señales de riesgo, cuando el sistema lo evalúa, entonces genera una alerta con explicación y estado de revisión.

### CA-05
Dado un caso con un proveedor no disponible, cuando se intenta coordinar asistencia, entonces el sistema registra el intento y permite reintento o reasignación.

### CA-06
Dado un caso en trámite, cuando el asegurado consulta la aplicación, entonces puede ver su estado actual y el siguiente paso.

### CA-07
Dado un presupuesto presentado por taller, cuando se registra aprobación u observación, entonces queda trazado quién realizó la acción y cuándo.

## 9. Diseño preliminar en Figma

No se inventa una pantalla cerrada sin validación adicional. Con base en las entrevistas, el prototipo debe contemplar al menos estas vistas:
- Inicio / acceso al reporte.
- Registro del siniestro.
- Carga de evidencias.
- Estado del caso.
- Línea de tiempo del siniestro.
- Coordinación de asistencia.
- Revisión antifraude.
- Gestión de presupuesto y autorización.

## 10. Preguntas abiertas / validaciones pendientes

- ¿Cuál será la política exacta de deduplicación de casos?
- ¿Qué umbrales disparan alertas antifraude críticas?
- ¿Qué evidencias son obligatorias al inicio y cuáles pueden quedar pendientes?
- ¿Qué campos exactos requiere la integración con pólizas?
- ¿Cómo se definirá la conservación de imágenes optimizadas versus originales?
- ¿Qué SLA aplican por región y por tipo de proveedor?
- ¿Qué datos verá el asegurado frente a los que verá un investigador de fraude?
- ¿Qué formato de aprobación requiere el taller para cambios de presupuesto?
- ¿Qué canales de notificación se habilitarán en el piloto?
- ¿Qué ciudad se elegirá para el piloto funcional?

## 11. Observaciones de alcance

La solución debe equilibrar rapidez, control antifraude, evidencia suficiente y trazabilidad. Las decisiones automatizadas deben ser revisables y nunca tratadas como verdad absoluta.
