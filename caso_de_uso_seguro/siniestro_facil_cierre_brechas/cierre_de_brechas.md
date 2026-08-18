# Siniestro Fácil — Cierre de Brechas de Negocio

## 1. Propósito

Este documento cierra las discrepancias y vacíos identificados en la especificación inicial de **Siniestro Fácil**.

### Regla de construcción

Las entrevistas originales son ficticias. Por indicación del negocio, las brechas pendientes se cierran mediante **respuestas de negocio simuladas**, procurando que sean coherentes con los objetivos y restricciones expresados en las entrevistas originales.

Las decisiones de este documento son, por tanto, **decisiones de negocio simuladas para completar la especificación SDD** y no deben confundirse con información contenida literalmente en las entrevistas originales.

---

# 2. Criterios generales de negocio simulados

Antes de cerrar cada brecha se adoptan los siguientes principios:

1. El proceso inicial cubre únicamente siniestros vehiculares de daños materiales sin lesiones graves.
2. Los casos con heridos, fallecidos, procesos legales o daños masivos salen del flujo digital y pasan a una ruta especializada.
3. Una inconsistencia antifraude nunca significa fraude automáticamente.
4. Las decisiones sensibles requieren revisión humana.
5. El expediente original nunca se elimina por una normalización o transformación.
6. Los casos relacionados se vinculan, pero mantienen expedientes independientes.
7. Una falla de un proveedor externo no debe bloquear indefinidamente al asegurado.
8. Las reglas de negocio que afectan decisiones sensibles deben ser versionadas.
9. El piloto se implementará en una sola ciudad antes de una expansión nacional.
10. Los parámetros configurables deben poder modificarse sin alterar el código de negocio cuando la naturaleza de la regla lo permita.

---

# 3. Cierre de discrepancias

## BG-01 — Política de deduplicación de siniestros

**Discrepancia original:** Operaciones reporta creación de casos duplicados, pero no existe una política formal.

### Respuesta de negocio simulada

Se considerará potencialmente duplicado un reporte cuando exista coincidencia suficiente entre:

- póliza;
- vehículo;
- ventana temporal del evento;
- ubicación aproximada;
- tipo de evento;
- participantes relevantes.

El sistema no eliminará automáticamente un caso. Generará una alerta de posible duplicidad y permitirá al operador confirmar si corresponde reutilizar el expediente existente o mantener ambos casos.

### Decisión

**CERRADA.**

### Regla de negocio

**RN-DUP-01:** El sistema debe detectar posibles duplicados, pero nunca eliminar o fusionar automáticamente expedientes.

---

## BG-02 — Reportante autorizado

### Respuesta de negocio simulada

El titular podrá designar personas autorizadas previamente. Cuando una persona no registrada necesite reportar el siniestro, el operador podrá validar la autorización mediante información proporcionada por el titular.

La autorización deberá quedar registrada en el expediente.

### Decisión

**CERRADA.**

### Regla de negocio

**RN-REP-01:** Solo el titular o una persona cuya autorización pueda ser validada podrá completar el reporte en nombre del asegurado.

---

## BG-03 — Validación de cobertura y deducible

### Respuesta de negocio simulada

La validación consultará la póliza vigente y determinará:

- existencia de cobertura aplicable;
- vigencia de la póliza;
- vehículo relacionado;
- deducible aplicable.

Si la información no puede ser confirmada automáticamente, el caso pasará a revisión de Operaciones.

### Decisión

**CERRADA.**

### Regla de negocio

**RN-COV-01:** Un caso no debe rechazarse automáticamente cuando el sistema no pueda determinar la cobertura; debe existir una ruta de revisión.

---

## BG-04 — Transiciones de estados

### Respuesta de negocio simulada

Se establecen las siguientes transiciones principales:

```text
Reportado
  -> Validando cobertura
  -> Asistencia coordinada
  -> Evidencia pendiente
  -> En evaluación
  -> Inspección programada
  -> Presupuesto recibido
  -> Autorizado
  -> En reparación
  -> Listo para entrega
  -> Indemnizado
  -> Cerrado
```

También podrán existir:

```text
En evaluación -> Observado
Validando cobertura -> Rechazado
Presupuesto recibido -> Observado
Cualquier estado operativo permitido -> Revisión especializada
```

Un caso rechazado podrá ser reabierto únicamente mediante una decisión autorizada y registrada.

### Decisión

**CERRADA.**

---

## BG-05 — Asignación de siniestros

### Respuesta de negocio simulada

La asignación se realizará mediante una combinación de:

1. severidad;
2. tipo de daño;
3. cobertura;
4. ciudad;
5. disponibilidad de proveedor;
6. señales de riesgo.

### Clasificación simulada

- **Bajo riesgo + daño simple:** flujo digital.
- **Daño complejo:** ajustador.
- **Señales relevantes de riesgo:** revisión especializada.
- **Alta exposición económica:** revisión especializada.

La regla exacta se administrará mediante parámetros configurables.

### Decisión

**CERRADA.**

---

## BG-06 — SLA

### Respuesta de negocio simulada

Para el piloto se establecen los siguientes objetivos:

| Etapa | Objetivo |
|---|---:|
| Primera respuesta | 15 minutos |
| Solicitud de grúa | 10 minutos |
| Validación de cobertura | 30 minutos |
| Asignación | 30 minutos |
| Inspección | 24 horas |
| Recepción de presupuesto | 24 horas desde inspección |
| Autorización | 8 horas desde presupuesto completo |
| Cierre | Según finalización de reparación/indemnización |

Los SLA podrán variar posteriormente por región y tipo de siniestro.

### Decisión

**CERRADA para el piloto.**

---

## BG-07 — Reintentos y escalamiento de proveedores

### Respuesta de negocio simulada

Ante ausencia de respuesta:

1. primer reintento automático;
2. segundo reintento;
3. escalamiento al operador;
4. reasignación cuando exista proveedor alternativo.

Una solicitud aceptada o rechazada no se considera una falla técnica.

### Decisión

**CERRADA.**

---

## BG-08 — Vigencia de presupuestos

### Respuesta de negocio simulada

El presupuesto tendrá una vigencia de **7 días calendario** desde su recepción.

Si vence sin autorización, el sistema lo marcará como vencido y requerirá actualización del taller.

### Decisión

**CERRADA.**

---

## BG-09 — Aprobación de presupuestos y ampliaciones

### Respuesta de negocio simulada

Las aprobaciones dependerán del monto y del nivel de exposición:

- reparaciones dentro del presupuesto aprobado: autorización operativa;
- ampliaciones que incrementen el monto: nueva aprobación;
- ampliaciones que superen el umbral definido para el operador: aprobación de supervisor.

Ninguna ampliación podrá ejecutarse sin autorización registrada.

### Decisión

**CERRADA conceptualmente.**

**Parámetro pendiente:** monto exacto de cada umbral.

---

## BG-10 — Umbrales antifraude

### Respuesta de negocio simulada

Se utilizarán tres niveles:

| Nivel | Tratamiento |
|---|---|
| Bajo | Continuar proceso y registrar señal |
| Medio | Priorizar revisión |
| Alto | Revisión especializada y posible bloqueo temporal según política |

La clasificación se determinará por combinación de señales, no por una señal individual.

### Decisión

**CERRADA conceptualmente.**

**Parámetros pendientes:** pesos exactos de cada señal.

---

## BG-11 — Bloqueo temporal de pagos

### Respuesta de negocio simulada

Solo una alerta de nivel alto, combinada con exposición económica relevante, podrá detener temporalmente un pago.

El bloqueo debe:

- registrar motivo;
- identificar regla/modelo;
- registrar fecha;
- permitir revisión humana;
- registrar la decisión final.

Una alerta por sí sola no implica rechazo del siniestro.

### Decisión

**CERRADA.**

---

## BG-12 — Retención de imágenes

### Respuesta de negocio simulada

Los originales se conservarán durante **10 años** desde el cierre del siniestro.

Las versiones optimizadas se conservarán mientras sean necesarias para la operación.

El original será la fuente de referencia para investigaciones.

### Decisión

**CERRADA para la especificación inicial.**

---

## BG-13 — Roles y permisos

### Respuesta de negocio simulada

Se establece la siguiente separación:

| Rol | Acceso |
|---|---|
| Asegurado | Propios siniestros |
| Operador | Casos operativos asignados |
| Supervisor | Casos operativos y supervisión |
| Ajustador | Información necesaria para evaluación |
| Investigador de fraude | Información ampliada de casos bajo investigación |
| Taller | Información necesaria para reparación |
| Proveedor de grúa | Información necesaria para asistencia |

Las descargas de evidencia estarán restringidas a los roles autorizados.

### Decisión

**CERRADA.**

---

## BG-14 — Métricas y falsos positivos de IA

### Respuesta de negocio simulada

Durante el piloto se medirán:

- precisión de clasificación;
- tasa de falsos positivos;
- tasa de falsos negativos cuando pueda determinarse;
- porcentaje de recomendaciones aceptadas por humanos;
- porcentaje de alertas descartadas.

Como objetivo inicial, el negocio establece que la tasa de falsos positivos de las alertas antifraude de alta severidad debe ser **menor al 10%**.

### Decisión

**CERRADA para el piloto.**

---

## BG-15 — Versionado de modelos y reglas

### Respuesta de negocio simulada

Toda alerta deberá identificar:

- versión de la regla;
- versión del modelo, cuando aplique;
- fecha de ejecución;
- datos de entrada utilizados;
- resultado;
- decisión humana posterior.

Los cambios de versión no modificarán retroactivamente la evidencia histórica.

### Decisión

**CERRADA.**

---

## BG-16 — Procedimiento de inspección

### Respuesta de negocio simulada

La inspección podrá ser:

- presencial;
- basada en evidencia digital cuando el caso sea elegible.

El ajustador documentará daños observados y resultado de la evaluación.

### Decisión

**CERRADA conceptualmente.**

**Pendiente posterior:** criterios exactos de elegibilidad para inspección digital.

---

## BG-17 — Integraciones con terceros

### Respuesta de negocio simulada

Las integraciones se clasificarán en:

- síncronas cuando se requiera respuesta inmediata;
- asíncronas cuando el proveedor pueda responder posteriormente.

El sistema deberá manejar estados de solicitud:

```text
Pendiente
Aceptada
Rechazada
Sin respuesta
Error técnico
```

No se asumirá que todos los terceros cuentan con API moderna.

### Decisión

**CERRADA conceptualmente.**

---

## BG-18 — Indisponibilidad de proveedores

### Respuesta de negocio simulada

Cuando un proveedor no esté disponible:

1. se registra la indisponibilidad;
2. se ejecutan reintentos;
3. se escala cuando corresponda;
4. se busca proveedor alternativo;
5. se informa al operador;
6. se evita bloquear al cliente indefinidamente.

### Decisión

**CERRADA.**

---

## BG-19 — Piloto

### Respuesta de negocio simulada

El piloto se realizará en **Lima Metropolitana**, con **10 talleres seleccionados**.

La duración inicial será de **12 semanas**.

El piloto incluirá únicamente:

- clientes directos;
- pólizas vigentes;
- daños materiales sin lesiones graves.

### Decisión

**CERRADA.**

---

## BG-20 — Criterios de salida del piloto

### Respuesta de negocio simulada

La expansión se evaluará cuando se cumplan simultáneamente:

- satisfacción del cliente >= 80%;
- reducción del tiempo a primera asistencia;
- reducción del costo operativo;
- ausencia de incidentes críticos de seguridad;
- falsos positivos de alta severidad < 10%;
- trazabilidad completa de los casos piloto.

### Decisión

**CERRADA.**

---

## BG-21 — Comunicación al asegurado

### Respuesta de negocio simulada

El canal principal será la aplicación móvil.

Se enviarán comunicaciones cuando:

- se registre el siniestro;
- se valide cobertura;
- se coordine asistencia;
- se solicite evidencia;
- se programe inspección;
- se reciba presupuesto;
- se autorice/rechace/observe una etapa;
- se produzca un cambio relevante;
- se cierre el caso.

### Decisión

**CERRADA.**

---

## BG-22 — Múltiples reclamos de un accidente

### Respuesta de negocio simulada

Cada póliza/reclamo conservará su propio expediente.

Los casos podrán vincularse mediante un identificador de evento relacionado.

La relación no implica fusionar información ni decisiones de cobertura.

### Decisión

**CERRADA.**

---

## BG-23 — Cardinalidades y claves del modelo de datos

### Respuesta de negocio simulada

Se adopta:

- una póliza puede tener múltiples siniestros;
- un vehículo puede estar involucrado en múltiples siniestros;
- un siniestro puede tener múltiples participantes;
- un siniestro puede tener múltiples evidencias;
- un siniestro puede tener múltiples presupuestos;
- un siniestro puede tener múltiples alertas;
- un siniestro puede tener múltiples autorizaciones;
- un siniestro puede tener múltiples pagos;
- un siniestro puede relacionarse con otros siniestros.

Las claves técnicas serán identificadores internos únicos.

### Decisión

**CERRADA conceptualmente.**

---

## BG-24 — Motor y modelo físico de persistencia

### Respuesta de negocio simulada

Para el diseño inicial se utilizará:

- base de datos relacional para información transaccional;
- almacenamiento de objetos para evidencias originales y derivados.

La selección definitiva del producto tecnológico queda como decisión de arquitectura técnica.

### Decisión

**CERRADA a nivel arquitectónico; producto tecnológico pendiente.**

---

## BG-25 — Retención legal

### Respuesta de negocio simulada

La retención funcional inicial será de 10 años para expedientes y evidencias.

Si una obligación legal o regulatoria exige un período mayor, prevalecerá dicha obligación.

### Decisión

**CERRADA para especificación funcional.**

---

## BG-26 — Rendimiento y disponibilidad

### Respuesta de negocio simulada

Para el piloto:

- disponibilidad objetivo: **99.5% mensual**;
- operaciones interactivas críticas: objetivo de respuesta de hasta **3 segundos** en condiciones normales;
- operaciones de procesamiento de evidencia e integraciones externas podrán ser asíncronas.

### Decisión

**CERRADA para el piloto.**

---

## BG-27 — Arquitectura de integraciones sin APIs modernas

### Respuesta de negocio simulada

La solución deberá utilizar una capa de integración que permita encapsular diferentes mecanismos de comunicación.

Se admitirán integraciones:

- API;
- intercambio de archivos;
- mecanismos síncronos;
- mecanismos asíncronos.

La lógica del proceso de siniestros no deberá depender directamente de la tecnología particular de un proveedor.

### Decisión

**CERRADA conceptualmente.**

---

## BG-28 — Seguridad y cifrado

### Respuesta de negocio simulada

Se establece como mínimo:

- cifrado de datos sensibles en tránsito;
- cifrado de información sensible almacenada;
- control de acceso por rol;
- principio de mínimo privilegio;
- auditoría de accesos sensibles;
- protección de evidencias originales contra modificación no autorizada.

Los algoritmos criptográficos concretos serán definidos en la arquitectura de seguridad.

### Decisión

**CERRADA funcionalmente; detalle criptográfico pendiente de diseño técnico.**

---

# 4. Cierre de las tensiones estratégicas

## 4.1 Rapidez vs. fraude

Se adopta un esquema de **riesgo graduado**.

Los casos de bajo riesgo siguen flujo rápido. Los casos con señales relevantes se priorizan para revisión. Solo determinados casos de alto riesgo y exposición podrán generar bloqueo temporal.

**Decisión:** automatización para acelerar; revisión humana para decisiones sensibles.

---

## 4.2 Experiencia simple vs. evidencia suficiente

La evidencia se solicitará progresivamente.

El sistema permitirá crear el caso con información mínima y posteriormente solicitará evidencia según el escenario.

**Decisión:** no bloquear el reporte inicial por falta de evidencia cuando exista riesgo para el asegurado.

---

## 4.3 Automatización vs. revisión humana

La IA tendrá carácter de **recomendación**.

No podrá producir por sí sola un rechazo definitivo por fraude.

**Decisión:** toda decisión sensible deberá ser revisable y auditable.

---

## 4.4 Expediente único vs. múltiples participantes

Cada reclamo mantiene su expediente.

Los expedientes relacionados se conectan mediante relaciones de negocio.

**Decisión:** relacionar no significa fusionar.

---

## 4.5 Originales vs. versiones optimizadas

El original constituye la evidencia de referencia.

Las versiones comprimidas, transformadas o derivadas se almacenan como versiones adicionales.

**Decisión:** nunca reemplazar silenciosamente el original.

---

## 4.6 Síncrono vs. asíncrono

Las operaciones que requieren respuesta inmediata podrán ser síncronas.

Las operaciones de terceros que puedan demorar deberán operar mediante estados y procesamiento asíncrono.

**Decisión:** el proceso de negocio no puede depender de que un tercero responda inmediatamente.

---

# 5. Nuevas reglas de negocio cerradas

| ID | Regla |
|---|---|
| RN-01 | No eliminar automáticamente casos potencialmente duplicados. |
| RN-02 | El reportante debe estar validado como titular o autorizado. |
| RN-03 | La falta de confirmación automática de cobertura genera revisión, no rechazo automático. |
| RN-04 | Los estados y sus transiciones deben quedar registrados. |
| RN-05 | Los casos complejos o de riesgo pueden derivarse a ajustador/revisión especializada. |
| RN-06 | Un presupuesto tiene vigencia de 7 días calendario. |
| RN-07 | Una ampliación requiere nueva autorización. |
| RN-08 | Una alerta antifraude no equivale a fraude. |
| RN-09 | Solo determinados casos de alto riesgo pueden bloquear temporalmente pagos. |
| RN-10 | Las reglas y modelos antifraude deben versionarse. |
| RN-11 | La evidencia original se conserva durante 10 años. |
| RN-12 | Los valores declarados y normalizados se conservan por separado. |
| RN-13 | Los expedientes relacionados no se fusionan. |
| RN-14 | Los proveedores externos no pueden bloquear indefinidamente el proceso. |
| RN-15 | Las decisiones sensibles requieren revisión humana. |
| RN-16 | Las descargas y consultas sensibles deben auditarse. |
| RN-17 | El piloto inicial será limitado a Lima Metropolitana y 10 talleres. |
| RN-18 | La duración inicial del piloto será de 12 semanas. |
| RN-19 | El canal principal de comunicación será la aplicación móvil. |
| RN-20 | Las operaciones externas potencialmente lentas se gestionarán de forma asíncrona. |

---

# 6. Impacto sobre la especificación SDD

El cierre de brechas permite convertir varios vacíos anteriores en elementos especificables.

## Antes

Existían dudas sobre:

- deduplicación;
- autorización;
- cobertura;
- estados;
- SLA;
- antifraude;
- retención;
- roles;
- IA;
- piloto;
- modelo de datos;
- persistencia;
- integraciones.

## Después

Se cuenta con decisiones de negocio simuladas para construir:

- nuevas historias de usuario;
- reglas de negocio;
- criterios de aceptación;
- requerimientos funcionales;
- requerimientos no funcionales;
- casos de uso;
- modelo lógico;
- modelo físico preliminar;
- arquitectura de integración;
- estrategia de auditoría;
- estrategia antifraude.

---

# 7. Puntos que siguen siendo decisiones técnicas

Aunque las brechas de negocio hayan sido cerradas, todavía no corresponde inventar decisiones técnicas.

Quedan para la fase de arquitectura:

1. producto de base de datos;
2. tecnología de almacenamiento de objetos;
3. tecnología API;
4. mecanismo de mensajería;
5. patrón de integración;
6. proveedor cloud/on-premise;
7. algoritmos criptográficos;
8. proveedor/modelo de IA;
9. mecanismo de autenticación;
10. estrategia de observabilidad;
11. estrategia de backup;
12. infraestructura de despliegue.

Estos elementos ya no constituyen **brechas de negocio**; son decisiones de **arquitectura y diseño técnico**.

---

# 8. Estado final de las discrepancias

| Categoría | Estado |
|---|---|
| Brechas de negocio | Cerradas mediante decisiones simuladas |
| Reglas de negocio | Definidas |
| SLA del piloto | Definidos |
| Piloto | Definido |
| Antifraude | Política funcional definida |
| IA | Uso funcional definido |
| Evidencias | Política funcional definida |
| Roles | Definidos conceptualmente |
| Modelo lógico | Puede completarse |
| Modelo físico | Puede iniciarse como diseño preliminar |
| Arquitectura técnica | Pendiente de diseño |
| Tecnología concreta | Pendiente de arquitectura |

# 9. Nota de trazabilidad

Todas las decisiones anteriores deben considerarse **respuestas simuladas del negocio creadas para cerrar las brechas de las entrevistas ficticias**.

No representan hechos reales de Seguros Horizonte.

El documento original de discrepancias continúa siendo válido como evidencia de cuáles eran los vacíos antes del cierre. Este documento representa la versión posterior a la simulación de las decisiones de negocio.

---

# 10. Resultado

Con este cierre de brechas, **Siniestro Fácil** queda en condiciones de pasar de una especificación basada únicamente en descubrimiento a una especificación SDD con decisiones de negocio suficientemente definidas.

El siguiente paso lógico es **reclasificar las brechas cerradas** y actualizar:

- historias de usuario;
- criterios de aceptación;
- requerimientos funcionales;
- requerimientos no funcionales;
- reglas de negocio;
- casos de uso;
- mapa de procesos;
- modelo conceptual;
- modelo lógico;
- modelo físico preliminar;
- diagramas ER.

Las decisiones técnicas deben documentarse posteriormente en la especificación de arquitectura y no mezclarse con las decisiones de negocio.
