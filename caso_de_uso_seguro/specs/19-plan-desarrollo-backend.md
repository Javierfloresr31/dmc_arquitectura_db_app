# Plan de Desarrollo Backend — Siniestro Fácil

## 1. Objetivo

Establecer el plan incremental de desarrollo del backend mediante SDD, manteniendo trazabilidad entre especificaciones, reglas de negocio, modelo de datos, APIs, integraciones, seguridad, pruebas y entregables de cada sprint.

## 2. Baseline aprobado

El desarrollo parte de:
- entrevistas y especificación funcional;
- historias de usuario, RF, RNF y criterios de aceptación;
- cierre y reclasificación de brechas;
- modelo conceptual y lógico;
- modelo físico PostgreSQL;
- validación de constraints;
- data sintética validada visualmente en Cloud SQL.

El modelo físico materializa 23 estructuras lógicas y evita inventar restricciones no sustentadas. `ASEGURADO`, `REPORTANTE` y `COBERTURA` permanecen mínimas; `INSPECCION.ajustador_id` no tiene FK mientras no exista una entidad AJUSTADOR en el modelo. 

## 3. Sprint 0 — Contrato técnico

**Objetivo:** convertir la especificación funcional en una base ejecutable de backend.

Entregables:
- `20-arquitectura-backend.md`;
- `21-contratos-api.md`;
- `22-contratos-integraciones.md`;
- `23-maquina-estados-siniestro.md`;
- `24-seguridad-rbac.md`;
- `25-idempotencia.md`;
- `26-estrategia-pruebas-backend.md`;
- actualización de trazabilidad SDD.

**Criterio de salida:** contratos, estados, seguridad, idempotencia y estrategia de pruebas suficientemente definidos para comenzar implementación; las preguntas abiertas que no bloqueen el primer flujo quedan registradas, no inventadas.

## 4. Sprint 1 — Registro y consulta del siniestro

- creación de siniestro;
- consulta de siniestro;
- identificación de póliza y vehículo según especificación;
- participantes/reportante según lo definido;
- estado inicial y transición controlada;
- auditoría.

## 5. Sprint 2 — Cobertura, asistencia y evidencia

- validación de cobertura;
- coordinación de asistencia;
- proveedores;
- recepción y consulta de evidencia;
- preservación del original y versiones derivadas;
- trazabilidad de evidencia.

## 6. Sprint 3 — Evaluación, inspección y presupuesto

- evaluación;
- inspección;
- talleres;
- presupuesto y detalle;
- observaciones;
- repuestos alternativos;
- ampliaciones.

## 7. Sprint 4 — Antifraude y revisión humana

- señales;
- alertas;
- reglas/modelos versionados;
- revisión humana;
- justificación;
- relaciones entre siniestros;
- auditoría de acciones sensibles.

## 8. Sprint 5 — Autorización, reparación y pago

- autorización;
- seguimiento de reparación;
- pago/indemnización;
- controles contra duplicidad;
- cierre.

## 9. Sprint 6 — Integraciones y resiliencia

- sistema de pólizas;
- asistencia/grúa;
- talleres;
- mapas;
- mensajería;
- medios de pago;
- timeout, retry, fallback, correlación e idempotencia;
- observabilidad.

## 10. Sprint 7 — End-to-End y piloto

- pruebas E2E;
- pruebas de contrato;
- seguridad;
- resiliencia;
- validación de CA;
- corrección de defectos;
- preparación del piloto en ciudad y grupo controlado de talleres.

## 11. Definition of Done

Una historia queda terminada cuando:
1. tiene trazabilidad HU → RF → CA → API/servicio → persistencia → prueba;
2. código implementado;
3. pruebas automatizadas relevantes ejecutadas;
4. persistencia validada contra el modelo físico;
5. errores y estados de negocio tratados;
6. seguridad aplicable validada;
7. idempotencia aplicada cuando corresponda;
8. documentación actualizada;
9. criterios de aceptación satisfechos.

## 12. Control por sesión

Actualizar este documento en cada sesión con:
- sprint y objetivo actual;
- historias iniciadas/completadas;
- artefactos SDD modificados;
- decisiones arquitectónicas;
- preguntas abiertas y su impacto;
- defectos y riesgos;
- próximos pasos.

## 13. Preguntas que siguen abiertas

No bloquean por sí mismas el Sprint 0, pero deben resolverse antes de implementar las funcionalidades afectadas:
- política exacta de deduplicación;
- umbrales y política de bloqueo antifraude;
- conservación de imágenes y estrategia de objetos;
- SLA por región/tipo de siniestro;
- contratos concretos con talleres y otros terceros;
- permisos finales por rol;
- claves de negocio no definidas en el modelo físico.
