# Criterios de aceptación — Siniestro Fácil

Formato: Given / When / Then.

## CA-001 Reporte mínimo
**Given** un reportante inicia un accidente vehicular, **when** proporciona póliza/documento, placa, fecha, ubicación aproximada, tipo de evento y contacto, **then** el sistema puede crear el caso y permitir continuar según validaciones de identidad/cobertura.

## CA-002 Evidencia diferida
**Given** el reportante está en una situación de riesgo, **when** no puede adjuntar toda la evidencia, **then** el caso puede continuar con el mínimo requerido y quedar evidencia pendiente.

## CA-003 Alcance inicial
**Given** un caso reportado, **when** se identifica lesión grave, fallecimiento, proceso legal o daño masivo, **then** el caso se enruta a una ruta especializada y no al flujo piloto normal.

## CA-004 Cobertura
**Given** un caso elegible, **when** se valida póliza, vehículo, cobertura y deducible, **then** el resultado queda registrado y es auditable.

## CA-005 Evidencia
**Given** una evidencia recibida, **when** se almacena, **then** queda vinculada al siniestro y se conserva el original junto con hash/metadatos disponibles y versiones derivadas.

## CA-006 Proveedor sin respuesta
**Given** una solicitud de asistencia o servicio externo, **when** el proveedor rechaza o no responde, **then** el sistema registra el intento y permite reintentar, escalar o reasignar según política.

## CA-007 Asignación
**Given** un caso elegible para asignación, **when** se evalúan ciudad, daño, severidad, cobertura, disponibilidad y riesgo, **then** se determina la ruta y se conserva la razón de asignación/reasignación.

## CA-008 Presupuesto
**Given** un taller presenta un presupuesto, **when** se recibe diagnóstico y monto, **then** queda registrado con vigencia y puede ser observado, aprobado o rechazado por un actor autorizado.

## CA-009 Alerta no equivale a fraude
**Given** una alerta antifraude, **when** se genera, **then** contiene explicación y origen y no produce rechazo automático salvo que una política crítica configurada lo determine.

## CA-010 Revisión humana
**Given** una alerta sensible, **when** un investigador la revisa, **then** puede confirmarla, descartarla o pedir información y debe registrar la justificación.

## CA-011 Reproducibilidad
**Given** una alerta histórica, **when** un investigador consulta el expediente meses después, **then** puede identificar regla/modelo, versión, datos de entrada disponibles y decisión humana asociada.

## CA-012 Datos declarados/normalizados
**Given** un dato como nombre o placa, **when** se normaliza, **then** el valor declarado original permanece disponible por separado.

## CA-013 Seguimiento cliente
**Given** un siniestro activo, **when** cambia su estado, **then** el asegurado puede conocer el estado actual y siguiente paso sin exponer subestados internos.

## CA-014 Auditoría
**Given** un usuario autorizado consulta el expediente, **when** revisa la línea de tiempo, **then** puede reconstruir cambios, evidencias, cobertura, proveedores, presupuesto, comunicaciones y pagos.

## CA-015 Acceso sensible
**Given** un usuario sin privilegio suficiente, **when** intenta consultar o descargar evidencia sensible, **then** el acceso es denegado y el intento queda sujeto al mecanismo de auditoría definido.

## CA-016 Pago duplicado
**Given** una solicitud de pago, **when** existe evidencia de un pago ya autorizado/emitido para la misma obligación, **then** el sistema debe impedir o derivar la operación según la política que aún debe definirse.
