# Matriz de trazabilidad — Sprint 04

## Objetivo

Relacionar el alcance de antifraude y revisión humana con las reglas, requerimientos, implementación y evidencias verificadas durante el Sprint 04.

| Elemento | Fuente SDD | Implementación | Evidencia |
|---|---|---|---|
| Gestión de alertas antifraude | RF-19 | `AntifraudeService`, `AntifraudeController`, `JdbcAntifraudeRepository` | Pruebas + E2E |
| Revisión humana | RF-20 / RF-29 | `RevisionAntifraudeService`, `RevisionAntifraudeController` | POST de revisión + persistencia |
| Versionado de regla/modelo | RF-22 / RF-23 | `ReglaModeloVersionService`, `JdbcReglaModeloVersionRepository` | E2E con `REGLA_SYN-V5` |
| Reproducibilidad | RN-009 / RF-23 | `datos_origen`, `modelo_o_regla`, versión | Registro de alerta |
| Decisión humana | RN-004 / RN-006 | Resultado + justificación | Revisión 27 |
| Auditoría | Seguridad RBAC / modelo lógico | `AuditoriaPort`, `JdbcAuditoriaRepository` | Auditorías de alerta y revisión |
| Señales antifraude | Modelo de datos | `SenalResponse` + persistencia | Alerta E2E |

## Reglas de negocio cubiertas

- Una inconsistencia o alerta no implica automáticamente fraude.
- Las decisiones sensibles requieren revisión humana.
- La alerta conserva información suficiente para reproducir por qué fue generada.
- La versión de regla/modelo forma parte de la trazabilidad.

## Evidencia E2E

Caso validado: alerta `27` para siniestro `27`, severidad `BAJA`, inicialmente `PENDIENTE_REVISION`, asociada a `REGLA_SYN-V5` y posteriormente revisada como `DESCARTADA` con justificación.

## Validación

- `mvn clean test`: 16/16 pruebas satisfactorias.
- Validación estática: 14/14 comprobaciones satisfactorias.
- Auditoría: creación de alerta y revisión registradas.

## Estado

**Sprint 04 — trazabilidad documentada y técnicamente validada.**
