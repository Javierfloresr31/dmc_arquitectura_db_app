# Siniestro Fácil — 12 bloques de especificación

> Documento rector de la especificación SDD. Su objetivo es evitar mezclar necesidad, solución y evidencia. Lo confirmado proviene de las entrevistas; lo no definido se mantiene como pregunta abierta.

## 01. Identidad
**Solución:** Siniestro Fácil.
**Dominio:** gestión de siniestros vehiculares.
**Organización referenciada en entrevistas:** Seguros Horizonte.
**Propósito:** transformar el reporte y gestión del siniestro en un expediente trazable, con automatización operativa y apoyo de analítica/riesgo.

## 02. Contexto
El alcance inicial considera siniestros vehiculares por daños materiales, sin lesiones graves, reportados por clientes directos con pólizas vigentes. El flujo contempla reporte desde teléfono, evidencias, validación de cobertura, asistencia, evaluación, presupuestos, autorización, comunicación y cierre.

## 03. Objetivos
1. Facilitar el reporte inicial.
2. Mantener un expediente único y trazable.
3. Reducir trabajo operativo repetitivo mediante automatización.
4. Preservar evidencia y trazabilidad.
5. Apoyar detección de riesgo/fraude sin sustituir revisión humana.
6. Comunicar al asegurado el estado del caso.

## 04. Alcance
### Incluido
- Identidad/reportante.
- Póliza y vehículo.
- Evento y ubicación aproximada.
- Cobertura y deducible.
- Evidencias.
- Asistencia.
- Asignación/evaluación.
- Presupuestos.
- Autorización de reparación.
- Alertas antifraude y revisión humana.
- Línea de tiempo auditable.
- Comunicación de estado.

### Fuera del alcance inicial
- Heridos/fallecidos.
- Procesos legales.
- Daños masivos.
- Rutas especializadas no descritas en el alcance inicial.

## 05. Actores
- Asegurado.
- Reportante autorizado.
- Operador.
- Ajustador.
- Investigador de fraude.
- Taller.
- Proveedor de grúa.
- Supervisor.

## 06. Procesos
1. Reportar siniestro.
2. Validar identidad, póliza y cobertura.
3. Registrar/recibir evidencias.
4. Coordinar asistencia.
5. Asignar y evaluar.
6. Programar inspección cuando corresponda.
7. Recibir y evaluar presupuesto.
8. Autorizar/observar/rechazar.
9. Gestionar reparación.
10. Indemnizar/cerrar.
11. Comunicar cambios de estado.
12. Registrar alertas y decisiones de riesgo.

Estados identificados: Reportado → Validando cobertura → Asistencia coordinada → Evidencia pendiente → En evaluación → Inspección programada → Presupuesto recibido → Autorizado → Observado/Rechazado → En reparación → Listo para entrega → Indemnizado → Cerrado.

## 07. Historias
Las historias de usuario existentes en `01_historias_usuario.md` son la fuente de detalle. No se crean historias adicionales por ausencia de definición de negocio; cualquier nueva necesidad deberá trazarse aquí antes del desarrollo.

## 08. Requerimientos funcionales
Los RF existentes en `02_requerimientos.md` constituyen la lista base. Todo RF nuevo debe indicar origen y relación con una HU, proceso o regla.

## 09. Requerimientos no funcionales
Los RNF existentes en `02_requerimientos.md` constituyen la base. Los valores cuantitativos no confirmados por negocio deben permanecer como TBD y no convertirse en SLA técnico implícito.

## 10. Reglas
Las reglas confirmadas y validaciones están en `04_reglas_negocio_validaciones.md`. Principios críticos:
- un expediente por caso;
- conservar relaciones entre casos sin fusionarlos incorrectamente;
- evidencia original inmutable y derivados separados;
- trazabilidad completa;
- automatización como recomendación/apoyo en decisiones sensibles;
- datos declarados y normalizados separados.

## 11. Criterios
Los criterios Given/When/Then existentes en `03_criterios_aceptacion.md` son la base de validación funcional. Cada HU que entre a desarrollo debe tener criterios verificables.

## 12. Preguntas
Las preguntas pendientes están en `05_preguntas_abiertas.md`. Una pregunta sin respuesta no debe resolverse mediante una suposición silenciosa. Debe permanecer como TBD, decisión pendiente o restricción conocida.

## Regla de gobierno de la documentación
Toda modificación futura debe actualizar este índice y los documentos especializados afectados. La trazabilidad mínima será:
`Proceso → Historia → RF/RNF → Regla → Criterio → Diseño → Modelo de datos → Implementación → Prueba → Evidencia`.
