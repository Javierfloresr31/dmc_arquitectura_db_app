# Siniestro Fácil — Modelo físico preliminar

## Estado
**PRELIMINAR / AGNÓSTICO DE MOTOR.** No se selecciona Oracle, PostgreSQL, SQL Server u otro motor porque las entrevistas no lo especifican.

## Objetivo
Preparar una base para el DDL sin convertir decisiones técnicas pendientes en requisitos de negocio.

## Convenciones propuestas
Estas convenciones son propuestas técnicas y deben validarse antes del DDL definitivo:
- claves técnicas: `UUID` o equivalente;
- fechas/horas: `TIMESTAMP` con consideración de zona horaria;
- importes: `DECIMAL(p,s)`;
- texto: `VARCHAR`/`TEXT` según motor;
- booleanos: tipo booleano o equivalente;
- hashes: representación textual o binaria según estrategia de almacenamiento;
- evidencia binaria: preferentemente almacenamiento de objetos con metadata en BD, sujeto a arquitectura.

## Índices candidatos
- `POLIZA(numero_poliza)`.
- `VEHICULO(placa)`.
- `SINIESTRO(numero_siniestro)`.
- `SINIESTRO(estado, fecha_reporte)`.
- `EVIDENCIA(siniestro_id, fecha_recepcion)`.
- `ALERTA_ANTIFRAUDE(estado_revision, severidad)`.
- `SINIESTRO_ESTADO_HISTORIAL(siniestro_id, fecha_evento)`.
- `AUDITORIA(entidad, entidad_id, fecha_evento)`.

Los índices finales deberán basarse en consultas reales y pruebas de carga.

## Integridad
- FK entre entidades relacionadas.
- restricciones de unicidad donde el negocio confirme identificadores únicos.
- checks para estados y valores permitidos, preferentemente mediante catálogos si son configurables.
- prohibición de sobrescribir el contenido original de evidencia.
- trazabilidad de versión de regla/modelo en cada alerta.

## Evidencias
La entrevista exige conservar contenido original, hash, metadatos, fecha de recepción, fuente, transformaciones y versiones derivadas. fileciteturn19file3

Por tanto, el diseño físico debe separar:
1. metadata de evidencia;
2. referencia al objeto original;
3. hash del original;
4. versiones derivadas;
5. metadata de cada transformación.

## Auditoría
Debe existir capacidad de reconstruir quién registró/cambió información, qué evidencia se añadió, cobertura aplicada, proveedor actuante, presupuesto aprobado, comunicaciones y pago autorizado. fileciteturn19file7

## Decisiones bloqueantes
- `PREGUNTA`: motor de base de datos.
- `PREGUNTA`: almacenamiento de objetos/evidencias.
- `PREGUNTA`: política de retención.
- `PREGUNTA`: cifrado y gestión de llaves.
- `PREGUNTA`: particionamiento esperado.
- `PREGUNTA`: volumen de evidencia por siniestro.
- `PREGUNTA`: necesidades de reporting/analítica.
- `PREGUNTA`: requisitos de recuperación ante desastre.

## Criterio de salida
El modelo físico se considera listo para implementación cuando el motor, estrategia de evidencia, retención, seguridad y volumen objetivo estén definidos y las tablas/índices hayan sido revisados contra los RF y casos de uso.
