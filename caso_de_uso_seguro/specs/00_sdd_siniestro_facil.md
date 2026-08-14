# SDD — Siniestro Fácil

## 1. Propósito
Especificación inicial para transformar las entrevistas de descubrimiento de Seguros Horizonte en insumos trazables para desarrollo SDD.

## 2. Alcance confirmado
- Siniestros vehiculares por daños materiales.
- Sin lesiones graves.
- Clientes directos con pólizas vigentes.
- Reporte desde teléfono.
- Registro de identidad, póliza, vehículo, evento, ubicación aproximada y contacto.
- Validación de cobertura y deducible.
- Evidencias vinculadas al siniestro.
- Coordinación de asistencia.
- Asignación y evaluación.
- Gestión de presupuestos y autorización de reparación.
- Alertas antifraude con revisión humana.
- Línea de tiempo auditable.
- Comunicación del estado al asegurado.

## 3. Fuera del alcance inicial
Casos con heridos, fallecidos, procesos legales o daños masivos; deben continuar por rutas especializadas.

## 4. Principios de solución
1. Un expediente único por caso, conservando relaciones entre reclamos/casos sin fusionarlos incorrectamente.
2. Automatización como recomendación y apoyo operativo, no como sustituto de la revisión humana en decisiones sensibles.
3. Evidencia original inmutable; versiones derivadas separadas.
4. Trazabilidad completa de cambios, decisiones, evidencias, comunicaciones y pagos.
5. Integraciones externas tolerantes a lentitud, rechazo y ausencia de respuesta.
6. Datos declarados y normalizados conservados por separado.

## 5. Actores
Asegurado, reportante autorizado, operador, ajustador, investigador de fraude, taller, proveedor de grúa y supervisor.

## 6. Objetos de negocio
Póliza, vehículo, siniestro, participante, cobertura, evidencia, asistencia, inspección, presupuesto, autorización, alerta y pago.

## 7. Estados de negocio identificados
Reportado → Validando cobertura → Asistencia coordinada → Evidencia pendiente → En evaluación → Inspección programada → Presupuesto recibido → Autorizado → Observado/Rechazado → En reparación → Listo para entrega → Indemnizado → Cerrado.

Los subestados internos no necesariamente se exponen al cliente.

## 8. Integraciones candidatas
Sistema de pólizas, red de talleres, proveedores de grúa, ajustadores, mapas, mensajería y medios de pago. La tecnología/protocolo de cada integración no está definida en las entrevistas.

## 9. Criterio SDD
Toda implementación debe poder trazarse a una historia de usuario, requisito o regla; lo no definido se registra como pregunta abierta y no se convierte en comportamiento implícito.
