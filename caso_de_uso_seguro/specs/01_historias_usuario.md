# Historias de usuario — Siniestro Fácil

## Épica E01 — Reporte
### US-001 Reportar siniestro
**Como** asegurado, **quiero** reportar un accidente desde mi teléfono, **para** iniciar la atención sin llamadas ni formularios repetidos.

### US-002 Reporte por persona autorizada
**Como** reportante autorizado, **quiero** registrar un caso cuando el titular no puede hacerlo, **para** permitir la atención del siniestro.

### US-003 Captura mínima inicial
**Como** reportante, **quiero** iniciar el caso con información mínima, **para** poder pedir ayuda aun cuando no pueda completar toda la evidencia.

## Épica E02 — Cobertura y asistencia
### US-004 Validar póliza, vehículo y cobertura
**Como** operador, **quiero** validar identidad, póliza, vehículo, cobertura y deducible, **para** determinar la ruta operativa.

### US-005 Coordinar asistencia
**Como** asegurado, **quiero** recibir y conocer la coordinación de asistencia, **para** saber qué ocurrirá después del reporte.

### US-006 Gestionar fallos de proveedor
**Como** operador, **quiero** registrar reintentos, rechazo y ausencia de respuesta de proveedores, **para** reintentar, escalar o reasignar sin bloquear el caso.

## Épica E03 — Evidencias
### US-007 Adjuntar evidencias
**Como** reportante, **quiero** adjuntar fotografías, documentos y declaraciones, **para** sustentar el siniestro.

### US-008 Preservar evidencia original
**Como** investigador de fraude, **quiero** disponer del original, hash, metadatos, fuente, transformaciones y versiones derivadas, **para** investigar y reproducir el historial de evidencia.

## Épica E04 — Evaluación y taller
### US-009 Asignar caso
**Como** operación, **quiero** asignar el siniestro según ciudad, daño, severidad, cobertura, disponibilidad y señales de riesgo, **para** dirigir casos simples a flujo digital y complejos a especialistas.

### US-010 Gestionar presupuesto
**Como** taller, **quiero** presentar presupuesto, diagnóstico y ampliaciones, **para** obtener observaciones o autorización de reparación.

### US-011 Autorizar reparación
**Como** operador/supervisor autorizado, **quiero** aprobar u observar un presupuesto y registrar quién decidió, **para** mantener control y trazabilidad.

## Épica E05 — Fraude y riesgo
### US-012 Generar alerta de riesgo
**Como** sistema antifraude, **quiero** generar alertas con tipo, severidad, explicación, origen, fecha y versión de regla/modelo, **para** priorizar investigación.

### US-013 Revisar alerta
**Como** investigador, **quiero** confirmar, descartar o solicitar información adicional y justificar la decisión, **para** evitar que una inconsistencia sea tratada automáticamente como fraude.

### US-014 Relacionar casos
**Como** investigador, **quiero** relacionar pólizas, personas, vehículos, teléfonos, cuentas y talleres entre casos, **para** detectar patrones sin fusionar expedientes incorrectamente.

## Épica E06 — Seguimiento y auditoría
### US-015 Consultar estado
**Como** asegurado, **quiero** conocer el estado y siguiente paso de mi siniestro, **para** reducir llamadas y tener visibilidad.

### US-016 Auditar expediente
**Como** supervisor/operador autorizado, **quiero** consultar una línea de tiempo completa, **para** reconstruir quién hizo qué, cuándo y con qué información.

### US-017 Controlar pagos duplicados
**Como** operación, **quiero** validar que no se autoricen pagos duplicados, **para** controlar pérdidas.

## Preguntas asociadas
Los criterios de identidad fuerte, deduplicación, umbrales antifraude, SLA, conservación y APIs permanecen abiertos y no se fijan en estas historias.
