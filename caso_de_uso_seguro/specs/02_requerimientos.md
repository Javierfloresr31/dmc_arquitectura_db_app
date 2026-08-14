# Requerimientos — Siniestro Fácil

## Requerimientos funcionales
| ID | Requisito | Fuente |
|---|---|---|
| RF-001 | Registrar siniestro con póliza/documento, placa, fecha, ubicación aproximada, tipo de evento y contacto. | Operaciones |
| RF-002 | Permitir completar evidencia posteriormente cuando la situación inicial sea de riesgo. | Operaciones/CEO |
| RF-003 | Validar identidad del reportante, póliza y vehículo antes de continuar con la ruta normal. | Operaciones |
| RF-004 | Validar cobertura y deducible. | Operaciones |
| RF-005 | Identificar y enrutar fuera del piloto los casos con heridos, fallecidos, procesos legales o daños masivos. | CEO |
| RF-006 | Vincular cada evidencia al siniestro y conservar momento de captura, fuente y metadatos disponibles. | Operaciones/Fraude |
| RF-007 | Conservar original de evidencia y registrar hash, transformaciones y versiones derivadas. | Fraude |
| RF-008 | Registrar asistencia, solicitudes a proveedores, reintentos, aceptación, rechazo y ausencia de respuesta. | Operaciones |
| RF-009 | Asignar casos considerando ciudad, daño, severidad, cobertura, disponibilidad y señales de riesgo. | Operaciones |
| RF-010 | Registrar presupuesto, diagnóstico, observaciones, alternativas y ampliaciones. | Operaciones |
| RF-011 | Registrar aprobación/rechazo/observación del presupuesto, vigencia y responsable de decisión. | Operaciones |
| RF-012 | Generar alertas antifraude con tipo, severidad, explicación, datos de origen, fecha y versión de regla/modelo. | Fraude |
| RF-013 | Permitir confirmar, descartar o solicitar información adicional para una alerta, registrando justificación. | Fraude |
| RF-014 | Relacionar casos y entidades sin fusionar expedientes incorrectamente. | Fraude |
| RF-015 | Mantener datos declarados y normalizados como valores separados. | Fraude |
| RF-016 | Exponer al asegurado estado actual y siguiente paso, ocultando subestados internos. | Operaciones/CEO |
| RF-017 | Mantener línea de tiempo de cambios, evidencias, cobertura, proveedores, presupuesto, comunicaciones y pagos. | Operaciones |
| RF-018 | Registrar accesos/descargas sensibles y aplicar acceso por rol y necesidad. | Fraude |
| RF-019 | Evitar pagos duplicados. | CEO/Operaciones |
| RF-020 | Tolerar proveedores externos lentos o temporalmente indisponibles. | CEO/Operaciones |

## Requerimientos no funcionales
| ID | Requisito | Fuente |
|---|---|---|
| RNF-001 | Seguridad y privacidad de datos personales. | CEO |
| RNF-002 | Trazabilidad auditable de decisiones y cambios. | CEO/Operaciones |
| RNF-003 | Reproducibilidad de alertas meses después mediante versión de regla/modelo, datos de entrada y revisión humana. | Fraude |
| RNF-004 | Integraciones tolerantes a latencia, rechazo, timeout y ausencia de respuesta. | CEO/Operaciones |
| RNF-005 | Control de acceso basado en rol y necesidad. | Fraude |
| RNF-006 | Evidencia original preservada sin sustitución silenciosa por versiones optimizadas. | Fraude |
| RNF-007 | UX guiada, lenguaje humano y comunicación del siguiente paso. | CEO |
| RNF-008 | Observabilidad suficiente para reconstruir solicitudes a proveedores y estados de procesamiento. | Operaciones |
| RNF-009 | Configurabilidad/versionado de políticas antifraude que puedan bloquear temporalmente pago o derivar revisión. | Fraude |
| RNF-010 | El sistema debe soportar el piloto controlado; capacidad objetivo nacional aún no está definida técnicamente. | CEO |
