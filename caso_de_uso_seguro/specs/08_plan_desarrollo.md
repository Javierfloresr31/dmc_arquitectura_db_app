# Plan de Desarrollo Evolutivo — Siniestro Fácil

## 1. Propósito

Este documento es el plan maestro de trabajo de Siniestro Fácil bajo un enfoque SDD. Se actualizará al cierre de cada sesión y funcionará como bitácora de avance, decisiones, pendientes y artefactos generados.

## 2. Estado inicial

**Estado:** Especificación funcional inicial.

**Base de verdad:** entrevistas de CEO, Operaciones de Siniestros y Prevención de Fraude, más la especificación SDD existente. El alcance confirmado comprende siniestros vehiculares de daños materiales sin lesiones graves, clientes directos con pólizas vigentes, reporte desde teléfono, validación de cobertura/deducible, evidencias, asistencia, evaluación, presupuestos, autorización, alertas antifraude con revisión humana, trazabilidad y comunicación al asegurado.

## 3. Fases

| Fase | Entregable principal | Estado |
|---|---|---|
| F01 | Identidad, contexto, objetivos y alcance | Completada |
| F02 | Actores, procesos e historias de usuario | Completada |
| F03 | Requerimientos funcionales y no funcionales | Completada |
| F04 | Reglas de negocio, validaciones y criterios de aceptación | Completada |
| F05 | Preguntas abiertas y cierre de brechas de negocio | Pendiente |
| F06 | Modelo conceptual de dominio/datos | Pendiente |
| F07 | Modelo lógico de datos | Pendiente |
| F08 | Modelo físico de datos | Pendiente |
| F09 | Contratos de integración y diseño de APIs | Pendiente |
| F10 | Arquitectura lógica y componentes | Pendiente |
| F11 | Prototipo Figma iterativo y validación UX | En progreso |
| F12 | Dataset sintético y estrategia de datos de prueba | Pendiente |
| F13 | Diseño técnico para SDD | Pendiente |
| F14 | Desarrollo incremental | Pendiente |
| F15 | Pruebas, seguridad, observabilidad y rendimiento | Pendiente |
| F16 | Piloto controlado y preparación de expansión | Pendiente |

## 4. Objetivos de cada fase

### F01–F04. Especificación base

Consolidar los 12 bloques de la estructura de especificación: identidad, contexto, objetivos, alcance, actores, procesos, historias, RF, RNF, reglas, criterios y preguntas.

### F05. Cierre de brechas

Resolver únicamente mediante respuestas del negocio, decisiones explícitas o restricciones aprobadas los puntos que actualmente no están definidos. No convertir supuestos en reglas del sistema.

### F06. Modelo conceptual

Identificar entidades/agregados y relaciones de negocio, sin comprometer todavía tipos de datos ni detalles de implementación.

Entidades candidatas provenientes de las entrevistas: Póliza, Vehículo, Siniestro, Participante, Cobertura, Evidencia, Asistencia, Inspección, Presupuesto, Autorización, Alerta y Pago. Podrán agregarse entidades solo cuando una necesidad confirmada lo justifique.

Entregables: diagrama conceptual, glosario de entidades, cardinalidades y decisiones abiertas.

### F07. Modelo lógico

Transformar el modelo conceptual en estructuras relacionales lógicas, incluyendo identificadores, claves, relaciones, normalización y reglas de integridad.

Entregables: modelo lógico, diccionario de datos lógico, matriz PK/FK y trazabilidad desde requisitos.

### F08. Modelo físico

Traducir el modelo lógico al motor de persistencia que se determine para la solución. La tecnología concreta queda pendiente hasta confirmar restricciones de plataforma.

Entregables: DDL, índices, constraints, estrategias de auditoría, versionado de esquema y estrategia de datos maestros.

### F09. Integraciones y APIs

Definir contratos para pólizas, talleres, grúas, ajustadores, mapas, mensajería y pagos. Cada integración debe contemplar éxito, rechazo, timeout, ausencia de respuesta, reintento, idempotencia y trazabilidad.

### F10. Arquitectura

Definir componentes, responsabilidades, flujos síncronos/asíncronos, seguridad, almacenamiento de evidencias, auditoría y mecanismos para que la IA sea explicable y revisable.

### F11. Figma / UX

Evolucionar el prototipo con cada decisión validada. Prioridad: reporte guiado, evidencias, estado, asistencia y vista operativa. Las pantallas deben reflejar únicamente reglas confirmadas.

### F12. Data sintética

Crear datos sintéticos reproducibles para desarrollo, pruebas funcionales, integración y demostración, sin utilizar datos personales reales.

Dataset mínimo previsto, sujeto al modelo definitivo:
- asegurados/reportantes;
- pólizas;
- vehículos;
- coberturas;
- siniestros;
- participantes y terceros;
- evidencias y metadatos;
- asistencias/proveedores;
- inspecciones;
- talleres;
- presupuestos y versiones;
- autorizaciones;
- alertas antifraude;
- pagos;
- eventos de auditoría;
- comunicaciones.

El volumen y la distribución de los datos deberán definirse después del modelo y de los casos de prueba. No se fija artificialmente una cantidad todavía.

### F13. Diseño técnico SDD

Convertir las especificaciones en unidades implementables: componentes, casos de uso detallados, contratos, comandos/eventos, validaciones, persistencia, seguridad y pruebas.

### F14. Desarrollo incremental

Implementar por verticales de negocio, manteniendo cada incremento trazable a HU/RF/reglas/criterios.

Orden preliminar:
1. Registro y creación del siniestro.
2. Validación de póliza/cobertura.
3. Evidencias.
4. Asistencia.
5. Asignación/evaluación.
6. Taller/presupuesto/autorización.
7. Estado y comunicaciones.
8. Antifraude y revisión humana.
9. Pago y cierre.

El orden podrá cambiar según respuestas de negocio y dependencias reales.

### F15. Calidad

Definir y ejecutar pruebas unitarias, integración, contrato, funcionales, seguridad, auditoría, rendimiento y resiliencia de terceros. Incluir escenarios de duplicidad, evidencia manipulada/reutilizada, proveedores sin respuesta y decisiones antifraude revisables.

### F16. Piloto

Preparar el flujo completo para una ciudad y grupo controlado de talleres, tal como fue planteado en la entrevista del CEO. La ciudad, talleres, métricas de aceptación y duración del piloto quedan pendientes de definición.

## 5. Gestión por sesión

Cada sesión debe actualizar este documento con:

- decisiones confirmadas;
- preguntas cerradas;
- nuevas preguntas;
- artefactos creados/modificados;
- requisitos afectados;
- riesgos y dependencias;
- siguiente objetivo de trabajo.

## 6. Definition of Ready para desarrollo

No iniciar una unidad de desarrollo cuando exista una ambigüedad crítica en reglas, estados, datos o integraciones. Debe existir como mínimo trazabilidad a historia, requisito, criterio de aceptación y modelo de datos afectado.

## 7. Definition of Done para una unidad

La unidad se considera terminada cuando dispone de implementación, pruebas correspondientes, validaciones, documentación mínima, trazabilidad SDD y actualización de los artefactos de datos/prototipo afectados.

## 8. Riesgos técnicos/funcionales a vigilar

- decisiones automatizadas sin revisión humana;
- pérdida del original de una evidencia;
- duplicidad de siniestros o pagos;
- integraciones externas lentas o indisponibles;
- falta de reproducibilidad de alertas antifraude;
- normalización que sobrescriba el dato declarado;
- exposición indebida de datos sensibles;
- desalineación entre estados internos y estado mostrado al cliente.

## 9. Próxima sesión propuesta

**Objetivo:** cerrar las preguntas de mayor impacto y construir el **modelo conceptual** de Siniestro Fácil, usando los 12 bloques como fuente única y dejando cada decisión trazada.
