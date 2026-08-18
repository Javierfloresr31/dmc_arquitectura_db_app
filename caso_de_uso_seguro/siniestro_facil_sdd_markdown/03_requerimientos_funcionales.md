# Siniestro Fácil — Requerimientos Funcionales

## 1. Regla de trazabilidad

Cada requerimiento tiene origen explícito en entrevistas. No se agregan funcionalidades por buenas prácticas generales.

| ID | Requerimiento | Origen |
|---|---|---|
| RF-01 | Registrar un siniestro vehicular desde el teléfono | CEO-03, CEO-06 |
| RF-02 | Permitir que una persona autorizada reporte por el titular | CEO-06 |
| RF-03 | Crear el caso con información mínima | OPS-02 |
| RF-04 | Registrar identidad, póliza y vehículo | OPS-01 |
| RF-05 | Registrar validación de cobertura y deducible | OPS-01 |
| RF-06 | Recopilar evidencias asociadas al siniestro | OPS-03 |
| RF-07 | Preservar original, hash, metadatos, fuente, transformaciones y derivados | FRA-03 |
| RF-08 | Registrar los estados operativos definidos | OPS-04 |
| RF-09 | Mostrar al asegurado avance y siguiente paso | CEO-03, CEO-06 |
| RF-10 | Coordinar asistencia cuando corresponda | OPS-01 |
| RF-11 | Asignar casos usando ciudad, daño, severidad, cobertura, disponibilidad y riesgo | OPS-05 |
| RF-12 | Reasignar conservando historial y motivo | OPS-05 |
| RF-13 | Gestionar orden, presupuesto, diagnóstico y ampliaciones de taller | OPS-07 |
| RF-14 | Registrar aprobación de presupuesto/cambios y vigencia | OPS-07 |
| RF-15 | Gestionar reintentos, escalamiento y reasignación de proveedores | OPS-09 |
| RF-16 | Registrar resultado de cada intento de integración | OPS-09 |
| RF-17 | Controlar tiempos de etapas | OPS-08 |
| RF-18 | Mantener línea de tiempo completa del caso | OPS-10 |
| RF-19 | Generar/gestionar alertas antifraude | FRA-02, FRA-04 |
| RF-20 | Permitir confirmación, descarte o solicitud de información sobre una alerta | FRA-04 |
| RF-21 | Registrar justificación de la revisión | FRA-04 |
| RF-22 | Configurar y versionar política antifraude | FRA-05, FRA-10 |
| RF-23 | Conservar versiones, datos de entrada y evidencia de revisión humana | FRA-10 |
| RF-24 | Relacionar casos por elementos compartidos sin fusionarlos | FRA-08 |
| RF-25 | Conservar valor declarado y valor normalizado separadamente | FRA-09 |
| RF-26 | Restringir acceso a información sensible por rol/necesidad | CEO-07, FRA-07 |
| RF-27 | Registrar descargas de evidencia y consultas sensibles | FRA-07 |
| RF-28 | Incorporar capacidades de IA para las tareas expresamente mencionadas | CEO-08, FRA-06 |
| RF-29 | Permitir revisión humana de decisiones sensibles | CEO-07, CEO-08, FRA-01 |
| RF-30 | Medir las métricas de éxito definidas por dirección | CEO-04 |
| RF-31 | Soportar piloto limitado a una ciudad y talleres controlados | CEO-10 |
| RF-32 | Integrarse con sistema de pólizas, talleres, grúas, ajustadores, mapas, mensajería y medios de pago | CEO-09 |

## 2. Restricciones de interpretación

- RF-28 no implica una solución concreta de IA; las entrevistas solo identifican capacidades.
- RF-32 no implica APIs REST ni una tecnología específica: el CEO indica que no todos los proveedores tienen APIs modernas.
- No se definen endpoints, esquemas de mensajes, formatos, tecnologías, reglas de negocio detalladas ni contratos de integración.

## 3. Requerimientos funcionales pendientes de definición

1. Regla de deduplicación de casos.
2. Transiciones válidas entre estados.
3. Reglas exactas de cobertura y deducible.
4. Reglas de severidad.
5. Reglas de asignación y reasignación.
6. SLA por región/tipo de siniestro.
7. Vigencia exacta de presupuestos.
8. Reglas de aprobación y ampliaciones.
9. Reintentos y escalamiento de proveedores.
10. Umbrales y política de bloqueo antifraude.
11. Retención de imágenes.
12. Métricas y umbrales de calidad de IA.
13. Mecanismo de acreditación del reportante autorizado.
