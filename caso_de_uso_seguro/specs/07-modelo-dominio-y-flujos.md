# 7. Modelo de dominio y flujo preliminar

## Entidades candidatas
Siniestro, Reportante, Asegurado, Póliza, Vehículo, Participante, Cobertura, Evidencia, Asistencia, Inspección, Presupuesto, Autorización, Alerta de fraude, Regla/Modelo, Revisión de alerta, Proveedor, Taller, Comunicación, Pago, Relación entre casos, Evento de auditoría.

## Flujo de alto nivel
Reporte → identificación/póliza/vehículo → cobertura/deducible → clasificación y asignación → asistencia/evidencia → evaluación/inspección → presupuesto → autorización/observación → reparación → indemnización → cierre.

## Flujo antifraude
Señales → alerta → revisión humana / solicitud de información → confirmación o descarte → decisión operativa/pago según política vigente.

## Flujo de proveedor
Solicitud → aceptada / rechazada / sin respuesta → reintento / escalamiento / reasignación.

## Pregunta de modelado
Q-001: ¿Cuál es la definición formal y cardinalidad de cada relación entre siniestros relacionados, pólizas, vehículos y participantes?
