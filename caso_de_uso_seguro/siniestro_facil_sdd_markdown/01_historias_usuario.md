# Siniestro Fácil — Historias de Usuario

## 1. Propósito

Especificar las historias de usuario derivadas exclusivamente de las entrevistas de descubrimiento. No se incorporan capacidades que no tengan respaldo explícito en el material fuente.

## 2. Convenciones de trazabilidad

- **CEO-01..10**: respuestas de la entrevista del CEO.
- **OPS-01..10**: respuestas de Operaciones de Siniestros.
- **FRA-01..10**: respuestas de Prevención de Fraude.
- **EV-01..04**: evidencias iniciales extraídas de las entrevistas.

## 3. Historias de usuario

### HU-01 — Reportar un siniestro desde el teléfono
**Actor:** Asegurado.

**Como** asegurado,  
**quiero** reportar un accidente desde mi teléfono,  
**para** registrar el siniestro sin depender de llamadas o formularios repetidos.

**Trazabilidad:** CEO-03, CEO-06, EV-01.

---

### HU-02 — Permitir reporte por una persona autorizada
**Actor:** Reportante autorizado.

**Como** persona autorizada,  
**quiero** poder reportar un siniestro cuando el titular no pueda hacerlo,  
**para** que el evento pueda ser registrado oportunamente.

**Trazabilidad:** CEO-06.

**Vacío asociado:** la entrevista no define cómo se acredita la autorización.

---

### HU-03 — Crear el expediente con información mínima
**Actor:** Operador / sistema.

**Como** operador,  
**quiero** crear un caso con la información mínima disponible,  
**para** no bloquear el reporte cuando el asegurado se encuentre en una situación de riesgo.

**Información mínima mencionada:** número de póliza o documento del asegurado, placa, fecha, ubicación aproximada, tipo de evento y medio de contacto.

**Trazabilidad:** OPS-02.

---

### HU-04 — Validar identidad, póliza, vehículo, cobertura y deducible
**Actor:** Operador.

**Como** operador,  
**quiero** confirmar identidad, póliza y vehículo y verificar cobertura y deducible,  
**para** determinar cómo debe continuar el caso.

**Trazabilidad:** OPS-01.

**Vacío asociado:** no se define el procedimiento exacto de validación ni las reglas de cobertura.

---

### HU-05 — Guiar al asegurado durante el reporte
**Actor:** Asegurado.

**Como** asegurado,  
**quiero** recibir una guía paso a paso sobre qué hacer, cómo protegerme y qué evidencia recopilar,  
**para** completar el reporte de manera comprensible.

**Trazabilidad:** CEO-06.

---

### HU-06 — Adjuntar y vincular evidencias al siniestro
**Actor:** Asegurado / operador.

**Como** usuario autorizado,  
**quiero** adjuntar fotografías, documentos y declaraciones vinculándolos al siniestro,  
**para** mantener la evidencia asociada al expediente.

**Trazabilidad:** OPS-03, FRA-03.

**Evidencias mencionadas:** fotografías del vehículo y daños, entorno, identidad, licencia, tarjeta de propiedad, declaración del conductor, datos de terceros y denuncia cuando aplica.

---

### HU-07 — Preservar evidencia original y sus versiones
**Actor:** Investigador de fraude / sistema.

**Como** investigador de fraude,  
**quiero** conservar el contenido original, hash, metadatos disponibles, fecha de recepción, fuente, transformaciones y versiones derivadas de cada evidencia,  
**para** poder utilizar el material en investigaciones posteriores.

**Trazabilidad:** FRA-03.

---

### HU-08 — Mostrar el avance del siniestro al asegurado
**Actor:** Asegurado.

**Como** asegurado,  
**quiero** conocer el siguiente paso y recibir información sobre el avance del caso,  
**para** tener visibilidad del proceso sin depender de llamadas.

**Trazabilidad:** CEO-03, CEO-06, OPS-04.

---

### HU-09 — Gestionar estados del siniestro
**Actor:** Operador / ajustador.

**Como** operador,  
**quiero** gestionar el caso mediante estados operativos,  
**para** conocer en qué etapa se encuentra y qué actividad corresponde.

**Estados mencionados:** Reportado, validando cobertura, asistencia coordinada, evidencia pendiente, en evaluación, inspección programada, presupuesto recibido, autorizado, observado, rechazado, en reparación, listo para entrega, indemnizado y cerrado.

**Trazabilidad:** OPS-04.

**Nota:** existen subestados internos, pero no fueron especificados.

---

### HU-10 — Coordinar asistencia
**Actor:** Operador.

**Como** operador,  
**quiero** coordinar asistencia cuando corresponda,  
**para** atender oportunamente al asegurado.

**Trazabilidad:** OPS-01, CEO-03.

---

### HU-11 — Asignar siniestros según contexto operativo y riesgo
**Actor:** Supervisor / operador.

**Como** supervisor,  
**quiero** asignar siniestros considerando ciudad, tipo de daño, severidad, cobertura, disponibilidad de proveedores y señales de riesgo,  
**para** dirigir casos simples a una vía digital y casos complejos a revisión especializada.

**Trazabilidad:** OPS-05, CEO-03.

**Vacío asociado:** no se especifican los criterios, ponderaciones ni umbrales de asignación.

---

### HU-12 — Reasignar conservando historial y motivo
**Actor:** Supervisor.

**Como** supervisor,  
**quiero** reasignar un caso conservando el historial y la razón de la reasignación,  
**para** mantener trazabilidad operativa.

**Trazabilidad:** OPS-05.

---

### HU-13 — Gestionar órdenes, presupuestos y ampliaciones de taller
**Actor:** Taller / operador.

**Como** taller,  
**quiero** recibir una orden, presentar presupuesto y diagnóstico, y solicitar aprobación u ampliaciones,  
**para** gestionar la reparación del vehículo.

**Trazabilidad:** OPS-07.

**Vacíos asociados:** no se definen formatos, validaciones, vigencia concreta ni reglas de aprobación.

---

### HU-14 — Registrar aprobaciones y cambios de reparación
**Actor:** Operador / supervisor.

**Como** operador,  
**quiero** registrar quién aprobó un presupuesto o cambio,  
**para** mantener trazabilidad de las decisiones durante la reparación.

**Trazabilidad:** OPS-07, OPS-10.

---

### HU-15 — Gestionar fallas de proveedores
**Actor:** Sistema / operador.

**Como** operador,  
**quiero** distinguir solicitudes aceptadas, rechazadas y sin respuesta y poder reintentar, escalar o reasignar,  
**para** evitar que un proveedor indisponible bloquee el caso.

**Trazabilidad:** OPS-09.

---

### HU-16 — Controlar tiempos por etapa
**Actor:** Supervisor.

**Como** supervisor,  
**quiero** controlar los tiempos de primera respuesta, grúa, cobertura, asignación, inspección, presupuesto, autorización y cierre,  
**para** gestionar los compromisos de atención.

**Trazabilidad:** OPS-08.

**Vacío asociado:** los SLA exactos por tipo de siniestro, ubicación y región no están definidos.

---

### HU-17 — Mantener una línea de tiempo auditable
**Actor:** Operador / supervisor / auditoría.

**Como** responsable de operaciones,  
**quiero** una línea de tiempo completa del caso,  
**para** saber quién registró información, qué cambió, qué evidencias se agregaron, qué cobertura se aplicó, qué proveedor actuó, qué presupuesto fue aprobado, qué comunicación recibió el cliente y qué pago se autorizó.

**Trazabilidad:** OPS-10.

---

### HU-18 — Detectar señales de posible fraude
**Actor:** Investigador de fraude / sistema.

**Como** investigador de fraude,  
**quiero** recibir señales sobre posibles inconsistencias o patrones sospechosos,  
**para** priorizar los casos que requieren revisión.

**Señales mencionadas:** repetición de participantes, vehículos o talleres; pólizas contratadas cerca del evento; ubicaciones incoherentes; fotografías reutilizadas; montos atípicos; versiones contradictorias; patrones de contacto compartidos y antecedentes de reclamos.

**Trazabilidad:** FRA-01, FRA-02.

---

### HU-19 — Gestionar alertas con explicación y revisión humana
**Actor:** Investigador de fraude.

**Como** investigador,  
**quiero** consultar el tipo, severidad, explicación, datos de origen, fecha, modelo o regla utilizada y estado de una alerta, y poder confirmarla, descartarla o solicitar más información,  
**para** tomar una decisión revisable y justificarla.

**Trazabilidad:** FRA-04, FRA-05.

---

### HU-20 — Configurar y versionar políticas antifraude
**Actor:** Prevención de Fraude.

**Como** responsable de fraude,  
**quiero** que la política que determina cuándo una alerta detiene temporalmente un pago o deriva un caso sea configurable y versionada,  
**para** reproducir la decisión posteriormente.

**Trazabilidad:** FRA-05, FRA-10.

**Vacío asociado:** no se definen umbrales ni política concreta.

---

### HU-21 — Preservar reproducibilidad de decisiones antifraude
**Actor:** Investigador de fraude.

**Como** investigador,  
**quiero** conservar versiones, datos de entrada y evidencia de revisión humana,  
**para** saber meses después por qué un caso recibió una alerta aunque la regla o modelo haya cambiado.

**Trazabilidad:** FRA-10.

---

### HU-22 — Relacionar casos y participantes sin fusionar expedientes
**Actor:** Investigador de fraude.

**Como** investigador,  
**quiero** relacionar siniestros que compartan pólizas, personas, teléfonos, cuentas bancarias, talleres u otros elementos,  
**para** detectar patrones sin fusionar incorrectamente los expedientes.

**Trazabilidad:** FRA-08.

---

### HU-23 — Conservar valor declarado y valor normalizado
**Actor:** Investigador de fraude / sistema.

**Como** investigador,  
**quiero** conservar por separado el valor declarado y el valor normalizado de nombres, placas y ubicaciones,  
**para** poder investigar inconsistencias sin alterar silenciosamente la información original.

**Trazabilidad:** FRA-09.

---

### HU-24 — Restringir acceso a información sensible
**Actor:** Investigador / operador.

**Como** responsable de seguridad,  
**quiero** que el acceso a información ampliada y consultas o descargas sensibles dependa del rol y necesidad,  
**para** proteger la información del caso y mantener registro de accesos sensibles.

**Trazabilidad:** CEO-07, FRA-07.

---

### HU-25 — Usar IA como recomendación revisable
**Actor:** Operador / investigador.

**Como** responsable del proceso,  
**quiero** que la IA clasifique fotografías, detecte documentos faltantes, resuma declaraciones, priorice casos, compare daños, detecte posible reutilización de imágenes, extraiga datos y agrupe relaciones sospechosas,  
**para** apoyar la operación sin convertir una recomendación en una decisión absoluta.

**Trazabilidad:** CEO-08, FRA-06.

**Vacío asociado:** no se especifican modelos, métricas, umbrales ni mecanismo técnico de explicabilidad.

---

### HU-26 — Medir el éxito del piloto
**Actor:** Dirección.

**Como** dirección,  
**quiero** medir tiempo hasta primera asistencia, tiempo hasta decisión, casos resueltos sin llamadas adicionales, satisfacción, costo operativo por siniestro y pérdidas evitadas por fraude,  
**para** evaluar el éxito de Siniestro Fácil.

**Trazabilidad:** CEO-04.

---

### HU-27 — Ejecutar piloto controlado
**Actor:** Dirección / Operaciones.

**Como** dirección,  
**quiero** ejecutar un piloto en una ciudad y con un grupo controlado de talleres,  
**para** validar la experiencia completa antes de una expansión nacional.

**Trazabilidad:** CEO-10.

## 4. Historias que no pueden completarse sin decisión adicional

- Acreditación del reportante autorizado.
- Reglas concretas de validación de cobertura y deducible.
- Criterios y umbrales de asignación.
- SLA exactos por región/tipo.
- Política de deduplicación.
- Política de retención de imágenes.
- Umbrales antifraude.
- Política de bloqueo temporal de pagos.
- Parámetros de IA y métricas de falsos positivos.
- Reglas concretas de aprobación de presupuestos y ampliaciones.
