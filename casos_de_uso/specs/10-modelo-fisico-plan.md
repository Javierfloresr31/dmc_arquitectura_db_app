# Modelo físico — plan de construcción

> Estado: Pendiente de modelo lógico aprobado y decisión de plataforma.

## Objetivo
Convertir el modelo lógico en una implementación persistente lista para desarrollo, pruebas y despliegue.

## Artefactos
- DDL de tablas.
- PK/FK/UNIQUE/CHECK/NOT NULL.
- Índices y justificación.
- Secuencias/estrategia de identificadores.
- Auditoría.
- Estrategia de documentos/evidencias.
- Retención y archivado.
- Particionamiento si los volúmenes lo justifican.
- Scripts de instalación y rollback.
- Migraciones/versionado de esquema.
- Dataset sintético y scripts de carga.
- Diccionario físico.

## Validaciones técnicas
- integridad referencial;
- consultas críticas;
- concurrencia/idempotencia;
- rendimiento con dataset sintético;
- recuperación ante fallos;
- seguridad de acceso;
- minimización de exposición de datos sensibles;
- backup/restore conforme a RTO/RPO aprobado.

## Restricción
No se selecciona una tecnología concreta ni se generan DDL definitivos hasta que se confirme la plataforma objetivo y se apruebe el modelo lógico.
