# Siniestro Fácil — Modelo físico

> Estado: PRE-DISEÑO. No debe ejecutarse todavía. El motor de base de datos no está definido en las entrevistas.

## 1. Objetivo

Preparar el diseño físico que será implementado cuando se confirme la tecnología de persistencia y las políticas de infraestructura.

## 2. Componentes previstos a definir

| Área | Definición | Estado |
|---|---|---|
| Motor BD | Motor y versión | PENDIENTE |
| Tablas | DDL derivado del modelo lógico | PENDIENTE |
| PK/FK | Constraints | PENDIENTE |
| Índices | Acceso por póliza, placa, siniestro, estado y consultas operativas | PENDIENTE de validar con cargas reales |
| Auditoría | Historial y acciones sensibles | REQUERIDO; mecanismo pendiente |
| Evidencias | Originales y derivadas | REQUERIDO; tecnología/almacenamiento pendiente |
| Integridad | Reglas de consistencia | PENDIENTE |
| Retención | Evidencias/datos/auditoría | PENDIENTE de negocio |
| Seguridad | Acceso y protección | ReQUERIDO; mecanismo pendiente |

## 3. Restricciones funcionales que el físico debe soportar

- Evidencia original inmutable y sus versiones derivadas.
- Hash y metadatos de evidencia.
- Trazabilidad de modificaciones.
- Versionado de reglas/modelos de riesgo.
- Relación entre siniestros sin fusionarlos incorrectamente.
- Control de pagos duplicados.
- Historial completo de estados y acciones.
- Conservación de valores declarados y normalizados por separado.

Estas necesidades están sustentadas por las entrevistas de Operaciones y Prevención de Fraude. fileciteturn19file1L1-L1 fileciteturn19file7L1-L1

## 4. DDL

El DDL se generará únicamente después de cerrar:

1. modelo lógico;
2. motor de BD;
3. cardinalidades;
4. catálogos;
5. retención;
6. requisitos de auditoría;
7. estrategia de almacenamiento de evidencia.

## 5. Regla de no invención

No se fija Oracle, PostgreSQL, SQL Server, MongoDB, almacenamiento de objetos, cifrado específico, índices concretos ni particionamiento como decisión arquitectónica hasta contar con evidencia o decisión explícita.
