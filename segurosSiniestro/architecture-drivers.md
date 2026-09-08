# Siniestro Fácil — Architecture Drivers

## Propósito

Matriz de Architecture Drivers extraídos del SDD de **Siniestro Fácil**. Los drivers se expresan independientemente de una tecnología concreta y sirven como puente entre los requisitos RF/RNF y las futuras decisiones arquitectónicas.

> **Regla de alcance:** este documento no prescribe tecnologías, productos, frameworks, proveedores cloud ni patrones de implementación concretos. Las decisiones técnicas quedan como decisiones arquitectónicas posteriores.

## Matriz RF/RNF → Driver → Decisión candidata → Riesgo → Evidencia

| ID | RF / RNF relacionado | Architecture Driver | Fuente | Decisión candidata | Riesgo si se ignora | Evidencia esperada |
|---|---|---|---|---|---|---|
| AD-01 | RF-01..RF-05 / RNF | Expediente único del siniestro como núcleo del proceso, integrando póliza, vehículo, participantes, evidencia y evolución del caso. | Entrevistas / modelo de dominio | Arquitectura centrada en un expediente de siniestro trazable. | Información fragmentada, duplicidad de datos y pérdida de contexto operativo. | Un caso permite reconstruir su recorrido completo y sus relaciones. |
| AD-02 | RF-06, RF-07 / RNF | Preservación de evidencia original e inmutabilidad. | Operaciones + Fraude | Separar conceptualmente original y versiones derivadas. | Manipulación o pérdida de evidencia; imposibilidad de sustentar una decisión. | Original preservado, versiones derivadas identificables y trazabilidad entre ambas. |
| AD-03 | RF-18, RF-20, RF-21 / RNF-02 | Trazabilidad y auditoría del expediente. | Entrevistas / modelo conceptual | Registrar la línea de tiempo y las decisiones relevantes del caso. | No poder explicar quién hizo qué, cuándo ni por qué. | Línea de tiempo completa del siniestro y registro de decisiones. |
| AD-04 | RF-19..RF-23 / RNF | Reproducibilidad antifraude. | Entrevista Fraude | Versionar reglas/modelos y conservar datos de entrada y evidencia de revisión. | Una alerta histórica no podría reproducirse ni explicarse después de cambiar reglas/modelos. | Para una alerta histórica se identifica versión, entradas, señales y decisión humana. |
| AD-05 | RF-19..RF-23 | Separación entre alerta y decisión de fraude. | Fraude / CEO | Una alerta constituye señal de riesgo, no sentencia; las decisiones sensibles deben ser revisables. | Falsos positivos con impacto sobre clientes y decisiones no justificables. | Alerta diferenciada de decisión humana, con resultado y justificación. |
| AD-06 | RF-19..RF-23 / RNF seguridad | Acceso restringido a información sensible. | Entrevista Fraude | Controlar el acceso según rol y necesidad de conocer. | Exposición de PII y evidencia sensible. | Matriz de acceso verificable y registro de consultas/descargas sensibles. |
| AD-07 | RF-24 / RNF | Relación entre siniestros sin fusión automática de expedientes. | Fraude + modelo lógico | Permitir relaciones entre casos conservando expedientes independientes. | Fusionar casos incorrectamente y contaminar el análisis de fraude. | Dos expedientes relacionados pueden consultarse como relacionados sin convertirse en uno solo. |
| AD-08 | RF-10, RF-15, RF-16 / RNF | Tolerancia a dependencias externas lentas o indisponibles. | CEO | El proceso debe gestionar estados de proveedor, reintentos y escalamiento sin quedar bloqueado indefinidamente. | Un proveedor externo podría detener la gestión completa del siniestro. | Solicitud con estado, resultado, error/rechazo y mecanismo de reintento/reasignación. |
| AD-09 | RF-04, RF-05 | Validación de póliza, vehículo y cobertura como paso explícito del flujo. | CEO / Operaciones | Separar la validación de identidad/póliza/vehículo de la determinación de cobertura. | Casos procesados sin cobertura válida o con decisiones incorrectas. | Eventos diferenciados de validación de póliza/vehículo y cobertura. |
| AD-10 | RF-10 / RF-15 / RF-16 | Coordinación de procesos internos y proveedores externos. | CEO + eventos de dominio | Diferenciar la solicitud de asistencia de la respuesta del proveedor. | El sistema podría asumir que una solicitud equivale a una atención exitosa. | Existencia separada de solicitud de asistencia y respuesta de proveedor registrada. |
| AD-11 | RF-13, RF-14 | Control del ciclo presupuesto → aprobación → ampliación. | Modelo lógico / Operaciones | Mantener presupuesto, autorización y ampliación como conceptos diferenciados. | Reparaciones ejecutadas con presupuestos vencidos o ampliaciones no autorizadas. | Trazabilidad entre presupuesto recibido, observado, aprobado y ampliación autorizada. |
| AD-12 | RF-30 / RNF | Control de pagos y prevención de duplicidades. | CEO / Fraude | El pago debe estar vinculado al expediente y sujeto a las condiciones de autorización correspondientes. | Pagos duplicados o pagos incorrectos. | No existe pago válido sin autorización/trazabilidad correspondiente; duplicidades detectables. |
| AD-13 | RNF seguridad / RF relacionados con participantes | Protección de información personal. | CEO + Fraude | La información expuesta debe depender del contexto y rol del usuario. | Incumplimiento de privacidad y exposición innecesaria de información. | Un operador no puede consultar información que corresponde exclusivamente a roles autorizados. |
| AD-14 | RNF de calidad de datos | Conservar valor declarado y valor normalizado sin sustitución silenciosa. | Fraude | El dato original y su normalización deben permanecer distinguibles. | Pérdida de evidencia del dato aportado y problemas de investigación/reconciliación. | Se puede recuperar tanto el valor declarado como su representación normalizada. |
| AD-15 | RNF / RF de operación | Separación entre automatización y revisión humana. | CEO + Fraude | Automatizar clasificación/recomendaciones sin convertirlas automáticamente en decisiones irreversibles. | Automatización opaca, falsos positivos y decisiones sin posibilidad de revisión. | Una recomendación puede ser inspeccionada, cuestionada y resuelta por un actor autorizado. |
| AD-16 | RF-01..RF-32 / RNF | Gestión explícita del estado y evolución del siniestro. | Eventos + modelo de dominio | Los cambios relevantes deben representar transiciones observables del caso. | Estados inconsistentes, pérdida de historial y dificultad para reconstruir el proceso. | Cada transición relevante genera evento/historial trazable. |
| AD-17 | RF-06, RF-07 / RNF | Separación entre evidencia original y representación optimizada. | Entrevistas + modelo lógico | Una transformación no debe sustituir la evidencia fuente. | Una optimización podría destruir información necesaria para investigación o auditoría. | Original y derivado son recuperables y están relacionados. |
| AD-18 | RF-24 / RNF | Modelo capaz de representar múltiples participantes y casos relacionados. | CEO + Fraude | El dominio debe soportar relaciones sin imponer una única persona/caso por expediente. | Pérdida de relaciones relevantes para fraude y análisis del accidente. | Un siniestro puede tener múltiples participantes y relacionarse con otros sin fusionarse. |
| AD-19 | RNF interoperabilidad / RF-15, RF-16 | Aislamiento de variabilidad de proveedores externos. | CEO | Las diferencias de disponibilidad/respuesta de proveedores no deben contaminar el modelo del expediente. | Cambiar o fallar un proveedor obliga a alterar el flujo completo del siniestro. | El expediente conserva el estado de la solicitud independientemente del proveedor concreto. |
| AD-20 | RNF escalabilidad / disponibilidad, según SDD | Capacidad de soportar el flujo completo sin convertir dependencias externas en un punto único de bloqueo. | CEO; expectativa de piloto y dependencias | La arquitectura debe preservar continuidad operacional ante fallos parciales. | Caída de un proveedor o dependencia interrumpe todo el proceso. | Pruebas de fallo parcial muestran que el expediente continúa gestionable. |

## Drivers arquitectónicamente dominantes

Los drivers con mayor capacidad de condicionar la estructura futura de la solución son:

1. **AD-02 — Evidencia inmutable y preservación del original**
2. **AD-03 — Trazabilidad y auditoría**
3. **AD-04 — Reproducibilidad antifraude**
4. **AD-05 — Separación alerta vs. decisión humana**
5. **AD-06 — Acceso restringido a información sensible**
6. **AD-07 — Relación de expedientes sin fusión**
7. **AD-08 — Tolerancia a dependencias externas**
8. **AD-12 — Control de pagos**
9. **AD-15 — Automatización con revisión humana**
10. **AD-16 — Estados y trazabilidad del ciclo de vida**

## Trazabilidad conceptual

```text
RF / RNF
   ↓
Architecture Driver
   ↓
Decisión arquitectónica candidata
   ↓
Evidencia verificable
```

Un Architecture Driver no constituye por sí mismo una tecnología. La tecnología y los patrones de implementación deberán justificarse posteriormente contra estos drivers y sus evidencias.

## Autovalidación

- Los drivers se derivan de RF/RNF, entrevistas, eventos y modelo de dominio disponibles para Siniestro Fácil.
- No se convierten tecnologías concretas en Architecture Drivers.
- No se agregan requisitos funcionales nuevos.
- Las decisiones candidatas permanecen expresadas a nivel arquitectónico, sin prescribir productos o frameworks.
- Cada driver incluye fuente, riesgo y evidencia esperada para facilitar revisión SDD.
