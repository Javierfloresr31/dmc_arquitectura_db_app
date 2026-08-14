# Siniestro Fácil — Plan de desarrollo SDD

## 1. Propósito

Este documento es el plan vivo de trabajo de la solución. Se actualizará al cierre de cada sesión. Cada sesión debe registrar: decisiones tomadas, artefactos modificados, preguntas cerradas, preguntas nuevas, validaciones realizadas y siguiente paso.

## 2. Principios de ejecución

1. Una sola fuente de verdad: las entrevistas y decisiones de negocio aprobadas.
2. No implementar decisiones no confirmadas como si fueran requisitos.
3. Mantener trazabilidad desde fuente hasta artefacto técnico.
4. Separar necesidad de negocio, solución, evidencia y decisión técnica.
5. La IA puede recomendar, clasificar, resumir o detectar señales; no se tratará como autoridad automática en decisiones sensibles.
6. Los modelos de datos se consideran propuestas hasta validación funcional.
7. La data sintética no representa datos reales ni debe utilizar información personal real.

## 3. Roadmap por fases

| Fase | Actividad | Entregable | Estado |
|---|---|---|---|
| F0 | Consolidación de entrevistas y fuente de verdad | 12 bloques + matriz de fuentes | COMPLETADO |
| F1 | Historias de usuario | HU trazables | COMPLETADO / mantener vivo |
| F2 | RF, RNF y criterios | Catálogo verificable | COMPLETADO / mantener vivo |
| F3 | Reglas y validaciones | Reglas versionables + preguntas | COMPLETADO / mantener vivo |
| F4 | Casos de uso y flujos | CU + diagramas | PENDIENTE |
| F5 | Modelo conceptual | Entidades, relaciones y cardinalidades | PENDIENTE |
| F6 | Modelo lógico | Entidades normalizadas, PK/FK, catálogos y relaciones | PENDIENTE |
| F7 | Modelo físico | Tablas, tipos, índices, constraints, auditoría | PENDIENTE — motor BD por confirmar |
| F8 | Data sintética | Dataset reproducible para escenarios | PENDIENTE |
| F9 | Contratos e integraciones | APIs/eventos/adaptadores definidos | PENDIENTE |
| F10 | Arquitectura de solución | Componentes, límites, flujos y decisiones | PENDIENTE |
| F11 | Prototipo Figma | Flujos UX validados | INICIAL — continuar |
| F12 | Implementación | Vertical slices priorizados | PENDIENTE |
| F13 | Pruebas | Unitarias, integración, aceptación y datos | PENDIENTE |
| F14 | Piloto | Ciudad + talleres controlados | PENDIENTE |

## 4. Plan específico de modelado de datos

### 4.1 Modelo conceptual

Objetivo: representar el lenguaje del negocio sin decidir todavía tecnología ni estructura física.

Objetos candidatos derivados explícitamente de las entrevistas: Póliza, Vehículo, Siniestro, Participante, Cobertura, Evidencia, Asistencia, Inspección, Presupuesto, Autorización, Alerta y Pago. fileciteturn19file12L1-L1

También deben evaluarse como conceptos de soporte: Persona/Reportante, Taller, Proveedor de grúa, Ajustador, Evento/Estado y Relación entre casos. Su permanencia como entidades independientes debe validarse con negocio.

Entregable: `10_modelo_conceptual.md`.

### 4.2 Modelo lógico

Objetivo: convertir el modelo conceptual validado en un modelo relacional independiente del motor.

Debe definir:
- entidades/tablas lógicas;
- atributos;
- identificadores;
- claves primarias y foráneas;
- cardinalidades;
- catálogos;
- relaciones N:M mediante entidades asociativas;
- historial de estados;
- trazabilidad de evidencia y transformaciones;
- versionado de reglas/modelos de riesgo cuando aplique.

Entregable: `11_modelo_logico.md`.

### 4.3 Modelo físico

Objetivo: convertir el modelo lógico aprobado en una implementación concreta.

Debe quedar pendiente hasta confirmar:
- motor de base de datos;
- versión;
- estrategia de almacenamiento de evidencia;
- estrategia de particionamiento si aplica;
- políticas de índices;
- retención;
- cifrado;
- mecanismo de auditoría.

Entregable: `12_modelo_fisico.md`.

## 5. Plan de data sintética

La data sintética se construirá después de estabilizar el modelo lógico y antes de las pruebas de implementación.

### Escenarios mínimos

1. Siniestro simple con cobertura válida.
2. Siniestro con evidencia pendiente.
3. Siniestro con asistencia de grúa.
4. Siniestro complejo que requiere ajustador.
5. Siniestro con presupuesto y observaciones.
6. Siniestro con alerta de riesgo que requiere revisión humana.
7. Múltiples siniestros relacionados por una persona/vehículo/taller.
8. Evidencia original + versión derivada.
9. Proveedor sin respuesta, seguido de reintento/escalamiento/reasignación.
10. Intento de pago duplicado para validar control.
11. Caso rechazado por una decisión de cobertura que debe quedar trazable.
12. Caso indemnizado y cerrado.

No se inventarán valores de negocio definitivos: catálogos, límites, montos, SLA y reglas deben usar valores marcados como sintéticos/provisionales hasta que negocio los confirme.

Entregable: `13_data_sintetica.md` y, posteriormente, archivos CSV/SQL/JSON según la tecnología aprobada.

## 6. Plan de sesiones

### Sesión actual — S01
- [x] Adoptar estructura de 12 bloques.
- [x] Consolidar especificación SDD.
- [x] Crear plan vivo.
- [x] Incorporar plan de modelos conceptual, lógico y físico.
- [x] Incorporar plan de data sintética.
- [x] Crear borradores iniciales de modelos.
- [ ] Validar preguntas abiertas de negocio.

### S02 — Modelo conceptual
- Validar entidades candidatas.
- Validar cardinalidades.
- Validar límites del agregado Siniestro/Expediente.
- Validar relaciones entre participantes y casos.
- Actualizar historias/requisitos si aparecen nuevas necesidades.

### S03 — Modelo lógico
- Transformar conceptos validados.
- Definir PK/FK.
- Definir catálogos y relaciones.
- Revisar normalización.
- Trazar cada entidad a una fuente.

### S04 — Modelo físico
- Confirmar motor de BD.
- Definir DDL.
- Definir índices y constraints.
- Definir auditoría y estrategia de evidencia.

### S05 — Data sintética
- Generar datasets por escenario.
- Validar integridad referencial.
- Crear datos de casos normales y excepcionales.
- Preparar datos para pruebas.

### S06+ — Arquitectura e implementación
- Contratos e integraciones.
- Arquitectura de componentes.
- Vertical slice de reporte.
- Vertical slice de evidencias.
- Vertical slice de evaluación/alertas.
- Vertical slice de taller/presupuesto.
- Trazabilidad, observabilidad y pruebas.

## 7. Criterio de avance

Una fase se considera cerrada cuando:

- tiene artefacto documentado;
- tiene fuente/trazabilidad;
- sus preguntas críticas están cerradas o explícitamente aceptadas como pendientes;
- no contiene supuestos presentados como hechos;
- pasa la matriz de cobertura.

## 8. Registro de sesiones

| Sesión | Fecha | Resultado | Artefactos | Decisiones | Pendientes |
|---|---|---|---|---|---|
| S01 | 2026-08-13 | Estructura 12 bloques + plan SDD + modelos iniciales | 08–13 | Modelo como propuesta; motor BD pendiente | Preguntas de negocio |
