# Sprint 2 — Cobertura, asistencia y evidencia

## 1. Objetivo
Completar la recepción inicial del expediente mediante validación de cobertura, coordinación de asistencia y gestión trazable de evidencias.

## 2. Baseline
Sprint 2 parte exclusivamente de la rama de Sprint 1 ya cerrada y sincronizada.

## 3. Historias y criterios trazables

| Área | Fuente | Criterio | Estado |
|---|---|---|---|
| Cobertura | US-004 / CA-004 | Verificar identidad, póliza, vehículo, cobertura y deducible antes de continuar | BLOQUEADO POR MODELO |
| Evidencia | US-007 / CA-006 | Vincular evidencia, conservar original, hash, metadatos y versiones derivadas | IMPLEMENTABLE |
| Evidencia faltante | US-008 / CA-002 | Permitir registro inicial sin evidencia y completarla posteriormente | IMPLEMENTABLE |
| Asistencia | US-009 / CA-009 | Registrar intento y permitir reintento, escalamiento o reasignación ante ausencia de respuesta | PARCIAL / MODELO INSUFICIENTE |

## 4. Restricciones

No se inventan atributos, catálogos, estados, contratos de terceros ni reglas de negocio que no estén definidos en las Specifications.

Firebase Authentication continúa fuera del MVP.

Las integraciones reales con terceros permanecen fuera de este sprint mientras Q-002/Q-003/Q-004 sigan abiertas.

## 5. Gaps que deben resolverse antes del cierre funcional

### GAP-S2-01 — Cobertura
La tabla `siniestro_facil.cobertura` actualmente solo contiene la PK técnica `id`. El modelo físico no contiene relación explícita con póliza, vehículo o siniestro ni atributos para cobertura/deducible. Por tanto, no es posible implementar CA-004 de forma trazable sin ampliar primero el modelo aprobado.

### GAP-S2-02 — Asistencia
La tabla `siniestro_facil.asistencia` contiene `siniestro_id` y `proveedor_asistencia_id`, pero no contiene estado del intento, aceptación, rechazo, ausencia de respuesta, timestamps de intento ni información de reintento/escalamiento/reasignación. Por tanto, CA-009 no puede quedar completamente implementado sin una decisión de modelo.

### GAP-S2-03 — Evidencia binaria
La especificación GCP establece Cloud Storage para originales y retención de 10 años desde el cierre. El modelo físico conserva metadata, pero no define explícitamente el identificador/ruta del objeto. La integración debe usar una referencia al objeto sin almacenar el binario en PostgreSQL.

## 6. Secuencia de ejecución

1. Resolver/aprobar los gaps de modelo S2-01 y S2-02.
2. Crear migración Flyway únicamente con atributos aprobados.
3. Implementar puertos de aplicación para cobertura, asistencia y evidencia.
4. Implementar persistencia JDBC.
5. Implementar API REST conforme al contrato.
6. Implementar referencia a Cloud Storage para evidencia original y derivados, sin almacenar binarios en PostgreSQL.
7. Implementar auditoría y trazabilidad.
8. Agregar unit tests y pruebas API/integración.
9. Agregar validación PostgreSQL y validación de Cloud Storage.
10. Ejecutar `mvn clean test`.
11. Ejecutar validación estática.
12. Ejecutar validación contra Cloud SQL.
13. Registrar resultados y defectos.
14. Commit y push.
15. Abrir PR y verificar Definition of Done.

## 7. Criterio de cierre

Sprint 2 solo se marca como CERRADO cuando todos los criterios implementables estén probados y los gaps de cobertura/asistencia hayan sido resueltos mediante una decisión explícita y trazable.

Si alguno permanece abierto, el sprint se marca BLOQUEADO y no se inicia Sprint 3.
