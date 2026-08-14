# 9. Riesgos y preguntas abiertas

## Riesgos
R-001 Exposición de datos personales.
R-002 Rechazo incorrecto de cobertura.
R-003 Pagos duplicados.
R-004 Manipulación/pérdida de evidencia.
R-005 Falsos positivos antifraude.
R-006 Dependencia de proveedores sin API moderna o temporalmente indisponibles.
R-007 Duplicación de expedientes por múltiples reportantes.
R-008 Confusión entre valores declarados y normalizados.
R-009 Decisiones automatizadas no explicables.

## Preguntas que deben responderse antes de desarrollo
Q-005 ¿Cómo se identifica/autentica al asegurado y al reportante autorizado?
Q-006 ¿Qué reglas exactas determinan duplicidad de siniestros?
Q-007 ¿Qué campos son obligatorios por tipo de evento y en qué momento?
Q-008 ¿Cuáles son los SLA/umbrales por etapa y región?
Q-009 ¿Cuánto tiempo se conservan originales, derivados, metadatos y auditoría?
Q-010 ¿Qué formatos/tamaños de evidencia se aceptan?
Q-011 ¿Qué reglas son críticas y qué umbrales/montos permiten bloquear pago o derivar?
Q-012 ¿Qué modelos de IA se usarán y cómo se versionan/validan?
Q-013 ¿Quién puede cambiar reglas antifraude y bajo qué aprobación?
Q-014 ¿Qué canales y plantillas de comunicación se utilizarán?
Q-015 ¿Cómo se calculan deducibles y cobertura en el sistema de pólizas?
Q-016 ¿Qué estados/subestados son visibles al cliente?
Q-017 ¿Qué ciudades y talleres integran el piloto?
Q-018 ¿Qué política define la reasignación de proveedores?
Q-019 ¿Qué controles impiden pagos duplicados y cuál es la fuente maestra de pagos?
Q-020 ¿Qué datos personales puede ver cada rol?
Q-021 ¿Cuál es el procedimiento de contingencia cuando una integración no está disponible?
