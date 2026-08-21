# Plan de Desarrollo Backend — Siniestro Fácil

## 1. Objetivo

Establecer el plan incremental de desarrollo del backend mediante SDD, manteniendo trazabilidad entre las especificaciones funcionales, reglas de negocio, modelo de datos, APIs, pruebas y entregables de cada sprint.

## 2. Baseline

El desarrollo parte de las especificaciones existentes en `caso_de_uso_seguro/specs`, el cierre de brechas, el modelo lógico y el modelo físico PostgreSQL.

## 3. Sprint 0 — Preparación técnica

**Objetivo:** dejar listo el contrato técnico para iniciar desarrollo funcional.

Entregables:
- arquitectura backend;
- contratos API;
- máquina de estados del siniestro;
- seguridad y RBAC;
- contratos de integraciones;
- estrategia de pruebas;
- matriz de trazabilidad SDD;
- validación de PostgreSQL y datos sintéticos.

## 4. Sprint 1 — Registro y consulta del siniestro

Alcance inicial:
- creación de siniestro;
- consulta de siniestro;
- identificación de póliza y vehículo según especificación;
- gestión inicial del estado;
- auditoría de operaciones.

## 5. Sprint 2 — Cobertura, asistencia y evidencia

Alcance:
- validación de cobertura;
- coordinación de asistencia;
- registro de proveedores;
- carga y consulta de evidencia;
- versionamiento de evidencia.

## 6. Sprint 3 — Evaluación, inspección y presupuesto

Alcance:
- evaluación del caso;
- inspección;
- talleres;
- presupuesto;
- detalle de presupuesto;
- observaciones y ampliaciones.

## 7. Sprint 4 — Antifraude

Alcance:
- señales antifraude;
- alertas;
- versión de regla/modelo;
- revisión humana;
- justificación y auditoría.

## 8. Sprint 5 — Autorización, reparación y pago

Alcance:
- autorización;
- seguimiento de reparación;
- pago/indemnización;
- cierre del siniestro.

## 9. Sprint 6 — Integraciones y resiliencia

Alcance:
- integraciones externas;
- timeout;
- retry;
- idempotencia;
- manejo de indisponibilidad;
- observabilidad.

## 10. Sprint 7 — End-to-End y preparación de piloto

Alcance:
- pruebas E2E;
- pruebas de seguridad;
- pruebas de integración;
- validación de criterios de aceptación;
- corrección de defectos;
- preparación del piloto.

## 11. Definition of Done

Una historia se considera terminada cuando:
1. existe trazabilidad a especificación y criterio de aceptación;
2. código implementado;
3. pruebas automatizadas relevantes ejecutadas;
4. persistencia validada contra el modelo físico;
5. manejo de errores implementado;
6. seguridad aplicable validada;
7. documentación técnica actualizada;
8. criterios de aceptación satisfechos.

## 12. Control de avance

Este documento será actualizado en cada sesión con:
- sprint actual;
- historias completadas;
- historias pendientes;
- decisiones tomadas;
- riesgos;
- preguntas abiertas;
- defectos;
- próximos pasos.
