# Siniestro Fácil — Modelo lógico preliminar

## Objetivo
Transformar el modelo conceptual en estructuras lógicas normalizadas, manteniendo separados los valores declarados de sus valores normalizados y preservando la trazabilidad requerida por fraude. fileciteturn19file3

## Tablas lógicas candidatas
| Tabla lógica | Propósito |
|---|---|
| ASEGURADO | Identidad del asegurado |
| REPORTANTE | Identidad/rol de quien reporta |
| POLIZA | Póliza de seguro |
| VEHICULO | Datos del vehículo |
| POLIZA_VEHICULO | Relación póliza-vehículo si se confirma N:M o historial |
| COBERTURA | Cobertura aplicable |
| SINIESTRO | Expediente principal |
| SINIESTRO_PARTICIPANTE | Participantes y su rol |
| EVIDENCIA | Metadata y vínculo al original/derivados |
| EVIDENCIA_VERSION | Versiones derivadas sin alterar el original |
| ASISTENCIA | Solicitud y coordinación |
| PROVEEDOR_ASISTENCIA | Proveedor de grúa/asistencia |
| INSPECCION | Programación y resultado de inspección |
| TALLER | Proveedor de reparación |
| PRESUPUESTO | Presupuesto presentado |
| PRESUPUESTO_DETALLE | Diagnóstico, repuestos y conceptos económicos |
| AUTORIZACION | Decisión sobre presupuesto/cambio |
| ALERTA_ANTIFRAUDE | Señal de riesgo |
| ALERTA_SEÑAL | Datos/señales que sustentan la alerta |
| REGLA_MODELO_VERSION | Versión reproducible de regla/modelo |
| REVISION_ANTIFRAUDE | Decisión y justificación humana |
| PAGO | Pago/indemnización |
| SINIESTRO_ESTADO_HISTORIAL | Historial de estados |
| AUDITORIA | Trazabilidad de cambios/consultas sensibles |
| SINIESTRO_RELACION | Relación entre expedientes |

## Reglas lógicas
1. El expediente `SINIESTRO` es independiente de otros expedientes relacionados.
2. `EVIDENCIA` conserva referencia al contenido original y `EVIDENCIA_VERSION` representa transformaciones derivadas.
3. Los datos declarados y normalizados deben coexistir cuando exista normalización.
4. `ALERTA_ANTIFRAUDE` debe conservar regla/modelo y versión utilizados.
5. `REVISION_ANTIFRAUDE` conserva decisión y justificación humana.
6. `SINIESTRO_ESTADO_HISTORIAL` no reemplaza el estado actual; permite reconstruir la línea de tiempo.
7. `AUDITORIA` registra cambios y accesos sensibles.
8. Las relaciones entre siniestros se modelan como vínculos, no como fusión automática.

## Claves
Se propone utilizar una clave técnica por entidad y claves naturales donde existan identificadores de negocio, pero el mecanismo exacto de generación de claves queda como **PROPUESTA TÉCNICA** pendiente de la arquitectura/persistencia.

## Normalización
El modelo busca como mínimo una estructura equivalente a 3FN para datos transaccionales, sin impedir estructuras derivadas para consulta o analítica posteriormente.

## Pendientes antes de cerrar el modelo
- Confirmar identificador único de póliza.
- Confirmar identificador de vehículo y placa como dato de negocio.
- Confirmar si una persona puede tener múltiples roles.
- Confirmar catálogo de estados y subestados.
- Confirmar catálogos de tipos de evidencia.
- Confirmar moneda y estructura económica de presupuesto/pago.
- Confirmar estrategia de almacenamiento de objetos binarios.
- Confirmar retención y versionado de evidencia.
- Confirmar modelo de autorización y roles técnicos.
