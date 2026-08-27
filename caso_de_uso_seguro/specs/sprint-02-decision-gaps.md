# Sprint 2 — Resolución de gaps S2-01 y S2-02

## GAP-S2-01 — Cobertura
No se agregan campos de negocio no sustentados por las Specifications. El MVP implementará la capacidad de validación mediante un puerto de aplicación y un adaptador sintético/mock, dejando la verificación real contra el sistema maestro como dependencia de integración externa. No se implementará cálculo de deducible sin datos definidos en el modelo aprobado.

## GAP-S2-02 — Asistencia
Se mantiene `asistencia` vinculada a siniestro y proveedor. La lógica de reintento/escalamiento/reasignación se prepara mediante un puerto de aplicación, sin inventar un catálogo de estados del proveedor. La integración real queda desacoplada hasta que se cierre el contrato externo.

## GAP-S2-03 — Evidencia
PostgreSQL conserva metadata, hash y trazabilidad; Cloud Storage conserva el binario original y versiones derivadas. No se almacenarán binarios en PostgreSQL.

## Decisión
Estos gaps dejan de ser bloqueadores de diseño para el alcance implementable del MVP. Las dependencias externas permanecen explícitas y no se presentan como funcionalidad integrada.
