# Plan de Desarrollo Backend — Siniestro Fácil

## 1. Objetivo

Establecer el plan incremental de desarrollo del backend mediante SDD, manteniendo trazabilidad entre especificaciones, reglas de negocio, modelo de datos, APIs, integraciones, pruebas y entregables de cada sprint.

## 2. Regla de ejecución por sprint

El desarrollo será estrictamente incremental:

1. Se selecciona un sprint.
2. Se revisan sus historias, RF, CA y dependencias.
3. Se diseña y genera únicamente el alcance del sprint.
4. Se generan pruebas y validaciones.
5. Se ejecutan las validaciones disponibles en GCP/Cloud SQL.
6. Se documentan resultados, decisiones, defectos y preguntas abiertas.
7. Código, pruebas, scripts, configuraciones y documentación se persisten en GitHub.
8. Se verifica la Definition of Done.
9. El sprint se cierra y se detiene el desarrollo funcional hasta recibir autorización para continuar.

No se considerará terminado un sprint si el código existe únicamente en Cloud Shell o en la conversación.

## 3. Baseline aprobado

El desarrollo parte de:
- entrevistas y especificación funcional;
- historias de usuario, RF, RNF y criterios de aceptación;
- cierre y reclasificación de brechas;
- modelo conceptual y lógico;
- modelo físico PostgreSQL;
- validación de constraints;
- data sintética validada visualmente en Cloud SQL;
- arquitectura GCP con API Gateway, Cloud Run y Cloud SQL;
- Cloud Storage para documentos/evidencias;
- decisiones GCP/FinOps registradas en `27-decisiones-gcp-finops.md`.

### 3.1 Alcance de autenticación del MVP

Por decisión explícita del proyecto, **el MVP omite temporalmente la autenticación con Firebase** para concentrar el desarrollo en la funcionalidad de negocio.

Esto no elimina la seguridad como requisito de arquitectura futura. Firebase Authentication, RBAC y claims permanecen documentados como capacidad posterior y no forman parte de los criterios de cierre funcional del MVP.

Durante el MVP:
- los endpoints funcionales se podrán ejecutar sin Firebase ID Token;
- no se implementarán Custom Claims;
- no se bloquearán los casos de uso por autenticación Firebase;
- la lógica de negocio no debe quedar acoplada a Firebase;
- se mantendrán `correlationId`, auditoría funcional e idempotencia cuando correspondan;
- el diseño debe permitir incorporar Firebase posteriormente sin reescribir los casos de uso.

## 4. Sprint 0 — Contrato técnico y preparación

**Objetivo:** convertir la especificación funcional en una base ejecutable de backend.

Entregables:
- arquitectura backend GCP;
- contratos API;
- contratos de integraciones;
- máquina de estados;
- seguridad/RBAC como diseño futuro;
- idempotencia;
- estrategia de pruebas;
- FinOps y retención;
- estructura inicial del backend;
- pipeline base de build/test.

**Salida:** el primer flujo funcional puede implementarse sin inventar decisiones críticas.

## 5. Sprint 1 — Registro y consulta del siniestro

**Objetivo:** entregar el primer flujo funcional ejecutable del MVP.

Alcance:
- registro de siniestro;
- consulta de siniestro;
- identificación de póliza y vehículo según especificación;
- participantes/reportante;
- estado inicial `REPORTADO`;
- transición controlada;
- auditoría;
- idempotencia de creación.

**Fuera del Sprint 1 MVP:** Firebase Authentication, Firebase ID Token, Custom Claims y validación de RBAC en runtime.

APIs objetivo:
- `POST /api/v1/siniestros`;
- `GET /api/v1/siniestros/{id}`;
- `GET /api/v1/siniestros`;
- `POST /api/v1/siniestros/{id}/transiciones`;
- `POST /api/v1/siniestros/{id}/participantes`;
- `GET /api/v1/siniestros/{id}/participantes`.

Persistencia objetivo:
- `poliza`;
- `vehiculo`;
- `poliza_vehiculo`;
- `siniestro`;
- `siniestro_participante`;
- `siniestro_estado_historial`;
- `auditoria`.

Validaciones mínimas:
- validación de request;
- PK/FK/NOT NULL aplicables;
- transacción/rollback;
- idempotencia;
- transición válida/inválida;
- auditoría;
- unit/API/integration tests.

## 6. Sprint 2 — Cobertura, asistencia y evidencia

**Objetivo:** completar la recepción inicial del caso.

Alcance:
- validación de cobertura;
- coordinación de asistencia;
- proveedores;
- recepción y consulta de evidencia;
- almacenamiento de originales en Cloud Storage;
- preservación y retención;
- versiones derivadas;
- trazabilidad.

Persistencia:
- `cobertura`;
- `asistencia`;
- `proveedor_asistencia`;
- `evidencia`;
- `evidencia_version`.

## 7. Sprint 3 — Evaluación, inspección y presupuesto

**Objetivo:** implementar la evaluación del daño y gestión de talleres.

Alcance:
- evaluación;
- inspección;
- talleres;
- presupuesto y detalle;
- observaciones;
- repuestos alternativos;
- ampliaciones.

Persistencia:
- `inspeccion`;
- `taller`;
- `presupuesto`;
- `presupuesto_detalle`.

## 8. Sprint 4 — Antifraude y revisión humana

**Objetivo:** implementar señales, alertas y revisión humana trazable.

Alcance:
- reglas/modelos versionados;
- señales;
- alertas;
- explicación;
- revisión humana;
- justificación;
- relaciones entre siniestros;
- auditoría sensible.

Persistencia:
- `regla_modelo_version`;
- `alerta_antifraude`;
- `alerta_senal`;
- `revision_antifraude`;
- `siniestro_relacion`.

## 9. Sprint 5 — Autorización, reparación y pago

**Objetivo:** completar la resolución económica del expediente.

Alcance:
- autorización;
- seguimiento de reparación;
- pago/indemnización;
- controles de duplicidad;
- reconciliación;
- cierre.

Persistencia:
- `autorizacion`;
- `pago`;
- historial/auditoría.

## 10. Sprint 6 — Integraciones, resiliencia y observabilidad

**Objetivo:** endurecer el backend para operación integrada.

Alcance:
- sistema de pólizas;
- asistencia/grúa;
- talleres;
- mapas;
- mensajería;
- medios de pago;
- timeout;
- retry con backoff/jitter;
- circuit breaker/fallback cuando corresponda;
- correlación;
- observabilidad;
- métricas de consumo y costo.

## 11. Sprint 7 — End-to-End, seguridad y piloto

**Objetivo:** validar el flujo completo y preparar el piloto.

Alcance:
- E2E;
- contratos;
- seguridad;
- resiliencia;
- criterios de aceptación;
- rendimiento;
- revisión de costos GCP;
- corrección de defectos;
- documentación operativa;
- preparación del piloto.

En este sprint se incorporará la autenticación Firebase si se confirma su entrada en el alcance de salida del MVP/piloto.

## 12. Definition of Done por sprint

Un sprint solo se considera terminado cuando:

- [ ] historias incluidas están implementadas;
- [ ] cada CA tiene prueba asociada;
- [ ] código compila;
- [ ] pruebas automatizadas relevantes pasan;
- [ ] validaciones de integración pasan cuando aplican;
- [ ] persistencia coincide con el modelo físico aprobado;
- [ ] controles de seguridad incluidos en el alcance están probados;
- [ ] idempotencia está probada cuando corresponde;
- [ ] scripts/migraciones necesarios están versionados;
- [ ] documentación SDD está actualizada;
- [ ] resultados de validación están registrados;
- [ ] código y artefactos están persistidos en GitHub;
- [ ] errores conocidos están registrados;
- [ ] preguntas abiertas están identificadas;
- [ ] se registra el commit de entrega.

## 13. Estructura de artefactos por sprint

```text
caso_de_uso_seguro/
├── specs/
│   ├── 19-plan-desarrollo-backend.md
│   ├── sprint-01-plan.md
│   ├── sprint-01-validacion.md
│   └── trazabilidad...
├── backend/
│   └── código generado por sprint
└── validation/
    └── scripts y resultados
```

## 14. Registro obligatorio al cerrar cada sprint

Se actualizará:

- sprint ejecutado;
- historias iniciadas/completadas;
- código generado;
- pruebas ejecutadas;
- validaciones GCP/Cloud SQL;
- resultado de cada CA;
- decisiones tomadas;
- preguntas abiertas;
- defectos/riesgos;
- impacto/costo relevante;
- commit(s) de GitHub;
- estado `CERRADO` o `BLOQUEADO`.

## 15. Regla de continuidad

Después de cerrar un sprint se detiene el desarrollo funcional. El siguiente sprint comienza únicamente con la instrucción explícita de continuar.

## 16. Preguntas abiertas

Se mantienen como OPEN hasta que una fuente las resuelva:
- política exacta de deduplicación;
- umbrales/política antifraude;
- contratos concretos de terceros;
- SLA por región/tipo de siniestro;
- permisos finales por rol;
- fecha de incorporación de Firebase Authentication al MVP/piloto;
- responsable operativo de claims Firebase;
- campos sensibles definitivos;
- operaciones que requieren step-up;
- proveedores concretos de mapas/mensajería/pagos;
- lifecycle exacto de Cloud Storage basado en medición del piloto.
