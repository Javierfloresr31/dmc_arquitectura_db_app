# Siniestro Fácil — Especificación maestra en 12 bloques

## Principio
**12 bloques. Una sola fuente de verdad.**

La documentación separa necesidad de negocio, comportamiento esperado y evidencia de implementación. La fuente funcional es la entrevista de descubrimiento de Seguros Horizonte: Siniestro Fácil. fileciteturn0file0

> Regla: si un punto no está respaldado por las entrevistas, se marca como `PREGUNTA` o `PROPUESTA TÉCNICA`, nunca como hecho de negocio.

## 01. Identidad
- Organización: Seguros Horizonte.
- Solución: Siniestro Fácil.
- Caso de uso: seguros vehiculares.
- Contexto: transformación del proceso de atención de siniestros.
- Alcance inicial indicado: daños materiales sin lesiones graves, clientes directos y pólizas vigentes, piloto en una ciudad y con grupo controlado de talleres.

## 02. Contexto
El proceso actual es manual y poco transparente: existen múltiples canales de reporte, transcripciones repetidas, evidencias distribuidas y poca visibilidad para el cliente. La solución debe integrar reporte, cobertura, evidencia, asistencia, evaluación y comunicación. fileciteturn19file4

## 03. Objetivos
1. Reducir fricción para reportar y dar seguimiento.
2. Disminuir tiempos operativos en casos simples.
3. Mantener controles contra fraude y pagos incorrectos.
4. Proporcionar una vista única del expediente.
5. Habilitar revisión humana de decisiones sensibles.
6. Medir primera asistencia, decisión, llamadas adicionales, satisfacción, costo operativo y pérdidas evitadas por fraude.

## 04. Alcance
### Incluido en el alcance inicial
- Reporte desde teléfono.
- Identificación de reportante, póliza y vehículo.
- Registro de datos mínimos.
- Validación de cobertura y deducible.
- Evidencias vinculadas al siniestro.
- Coordinación de asistencia.
- Asignación y reasignación.
- Evaluación/inspección.
- Presupuestos y autorizaciones.
- Seguimiento del asegurado.
- Alertas de fraude y revisión humana.
- Auditoría y trazabilidad.

### Fuera de la ruta inicial
Heridos, fallecidos, procesos legales y daños masivos: continúan por rutas especializadas. fileciteturn19file5

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
2. Validar identidad/póliza/vehículo.
3. Registrar evento.
4. Validar cobertura y deducible.
5. Coordinar asistencia.
6. Clasificar/asignar caso.
7. Recopilar y validar evidencia.
8. Inspeccionar/evaluar.
9. Recibir y revisar presupuesto.
10. Autorizar/observar/rechazar según corresponda.
11. Gestionar reparación.
12. Gestionar indemnización/pago.
13. Cerrar expediente.
14. Auditar y gestionar alertas antifraude.

Los estados operativos identificados son: Reportado, validando cobertura, asistencia coordinada, evidencia pendiente, en evaluación, inspección programada, presupuesto recibido, autorizado, observado, rechazado, en reparación, listo para entrega, indemnizado y cerrado. fileciteturn19file6

## 07. Historias
Las historias de usuario detalladas se encuentran en `02-historias-usuario.md`. Cubren, como mínimo, reporte, asistencia, evidencia, seguimiento, operación, asignación, taller, presupuesto, auditoría y fraude.

## 08. RF
Los requerimientos funcionales están en `03-requerimientos-funcionales.md`. Deben mantener trazabilidad con historias y entrevistas.

## 09. RNF
Los requisitos no funcionales están en `04-requerimientos-no-funcionales.md`, especialmente seguridad, trazabilidad, disponibilidad de integraciones, evidencia inmutable y reproducibilidad antifraude.

## 10. Reglas
Las reglas de negocio están en `06-reglas-negocio.md`. Las políticas que dependen de umbrales, SLA o configuración aún no definida permanecen como preguntas.

## 11. Criterios
Los criterios de aceptación están en `05-criterios-aceptacion.md` y se complementan con `11-validaciones.md`.

## 12. Preguntas
Las preguntas abiertas están en `09-riesgos-y-preguntas-abiertas.md`. Ninguna pregunta abierta debe convertirse en una regla de negocio sin confirmación.

## Modelado y datos
La evolución de esta especificación incorpora:
- modelo conceptual;
- modelo lógico;
- modelo físico;
- estrategia y generación de datos sintéticos.

Estos entregables están definidos en `13-modelo-conceptual.md`, `14-modelo-logico.md`, `15-modelo-fisico.md` y `16-datos-sinteticos.md`.

## Plan vivo
El plan de desarrollo se mantiene en `12-plan-desarrollo.md` y se actualiza en cada sesión, registrando estado, decisiones, preguntas resueltas y próximos pasos.
