# 2. Historias de usuario

## Épica E1 — Reporte y orientación
**US-001 — Registrar siniestro**  
Como asegurado o reportante autorizado, quiero registrar un accidente desde el teléfono para iniciar la atención sin repetir formularios.
**Criterios:** capturar identificación del reportante, póliza/documento, placa, fecha, ubicación aproximada, tipo de evento y contacto; permitir continuar aunque inicialmente falte toda la evidencia; identificar casos fuera de alcance.

**US-002 — Recibir guía paso a paso**  
Como reportante, quiero instrucciones claras sobre qué hacer, cómo protegerme y qué evidencia recopilar para completar el reporte de forma segura.

**US-003 — Consultar avance**  
Como asegurado, quiero conocer el siguiente paso y el estado visible del siniestro para reducir llamadas de seguimiento.

## Épica E2 — Validación y asignación
**US-004 — Validar póliza, vehículo y cobertura**  
Como operador, quiero confirmar identidad, póliza, vehículo, cobertura y deducible antes de continuar con la gestión.

**US-005 — Asignar siniestro**  
Como operación, quiero asignar casos según ciudad, daño, severidad, cobertura, disponibilidad y señales de riesgo para dirigir cada caso a la ruta adecuada.

**US-006 — Reasignar sin perder trazabilidad**  
Como supervisor/operador autorizado, quiero reasignar un caso conservando historial y razón.

## Épica E3 — Evidencia
**US-007 — Gestionar evidencias**  
Como reportante/operador, quiero adjuntar evidencias vinculadas al siniestro y conservar original, metadatos disponibles, hash y versiones derivadas.

**US-008 — Detectar faltantes**  
Como operador, quiero identificar documentos/evidencias faltantes para solicitar información adicional.

## Épica E4 — Asistencia y evaluación
**US-009 — Coordinar asistencia**  
Como operador, quiero solicitar grúa/asistencia y conocer aceptación, rechazo o ausencia de respuesta del proveedor.

**US-010 — Gestionar inspección**  
Como operación, quiero asignar y controlar una inspección programada cuando corresponda.

## Épica E5 — Taller y reparación
**US-011 — Recibir presupuesto**  
Como taller, quiero presentar presupuesto, diagnóstico y solicitudes de aprobación/ampliación.

**US-012 — Gestionar autorización**  
Como operación, quiero aprobar u observar presupuestos y cambios dejando quién y cuándo realizó la acción.

## Épica E6 — Fraude y revisión humana
**US-013 — Generar alerta explicable**  
Como sistema, quiero registrar tipo, severidad, explicación, datos origen, fecha y versión de regla/modelo de una alerta.

**US-014 — Revisar alerta**  
Como investigador, quiero confirmar, descartar o solicitar información adicional y registrar la justificación.

**US-015 — Consultar relaciones**  
Como investigador, quiero relacionar casos, personas, teléfonos, cuentas bancarias, talleres o vehículos sin fusionar incorrectamente expedientes.

## Épica E7 — Auditoría
**US-016 — Consultar línea de tiempo**  
Como operación/supervisión, quiero una línea de tiempo completa de cambios, evidencias, cobertura, proveedores, presupuestos, autorizaciones, comunicaciones y pagos.

**US-017 — Reproducir decisión antifraude**  
Como investigador, quiero saber meses después por qué se generó una alerta, incluso si la regla/modelo cambió.

## Épica E8 — Operación resiliente
**US-018 — Manejar proveedores indisponibles**  
Como operación, quiero reintentar, escalar o reasignar cuando un proveedor no responda, sin bloquear al cliente.
