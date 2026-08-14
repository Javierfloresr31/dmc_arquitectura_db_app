# Siniestro Fácil — Plan de desarrollo evolutivo

## Propósito
Este documento es el **plan vivo** de la solución. Se actualiza en cada sesión y es la referencia para decidir qué se analiza, diseña, valida, modela y desarrolla a continuación.

## Principios de ejecución
- Una sola fuente de verdad: los 12 bloques.
- No convertir preguntas abiertas en decisiones sin validación.
- Mantener trazabilidad entrevista → historia → RF/RNF → regla → criterio → diseño → prueba.
- Diseñar antes de implementar cuando una decisión afecta el dominio o persistencia.
- Usar datos sintéticos para probar flujos sin exponer datos reales.
- Validar cada incremento funcional antes de pasar al siguiente.

## Estado inicial
| ID | Fase | Entregable | Estado | Próximo criterio de salida |
|---|---|---|---|---|
| P01 | Descubrimiento | 12 bloques | COMPLETADO | Estructura maestra publicada |
| P02 | Requisitos | Historias, RF, RNF, reglas, criterios | COMPLETADO | Trazabilidad revisada |
| P03 | Preguntas | Preguntas abiertas | ABIERTO | Resolver las preguntas prioritarias |
| P04 | UX | Prototipo Figma preliminar | INICIAL | Validar flujos con requisitos |
| P05 | Dominio | Modelo conceptual | EN SIGUIENTE SESIÓN | Entidades y relaciones validadas |
| P06 | Datos | Modelo lógico | PENDIENTE | Cardinalidades, claves y restricciones validadas |
| P07 | Persistencia | Modelo físico | PENDIENTE | Motor y decisiones físicas confirmadas |
| P08 | Datos sintéticos | Dataset + generador | PENDIENTE | Casos normales, excepcionales y antifraude cubiertos |
| P09 | Arquitectura | Arquitectura de solución | PENDIENTE | Componentes e integraciones definidos |
| P10 | Backend | APIs/servicios | PENDIENTE | Contratos aprobados |
| P11 | Frontend | Experiencia asegurado/operación | PENDIENTE | Flujos implementados |
| P12 | Integraciones | Pólizas, talleres, grúa, ajustadores, mapas, mensajería, pagos | PENDIENTE | Contratos y resiliencia definidos |
| P13 | Fraude/IA | Alertas, explicación, versionado y revisión | PENDIENTE | Política validada |
| P14 | Calidad | Pruebas funcionales/no funcionales | PENDIENTE | Criterios de aceptación cumplidos |
| P15 | Piloto | Ciudad + talleres controlados | PENDIENTE | Experiencia completa validada |

## Secuencia de trabajo por sesiones

### Sesión 1 — Consolidación de especificaciones
**Resultado:** 12 bloques y trazabilidad base.
- Revisar identidad, contexto, objetivos y alcance.
- Revisar actores y procesos.
- Revisar historias, RF, RNF, reglas y criterios.
- Mantener preguntas abiertas.

### Sesión 2 — Modelo conceptual
**Resultado esperado:** modelo conceptual validado.
- Identificar entidades candidatas desde los objetos de negocio entrevistados.
- Definir relaciones y cardinalidades candidatas.
- Separar expediente de siniestro de relaciones con otros reclamos.
- Modelar evidencia original y derivados como concepto de negocio.
- Modelar alertas, revisión humana y trazabilidad.
- Registrar preguntas de dominio pendientes.

### Sesión 3 — Modelo lógico
**Resultado esperado:** modelo lógico normalizado.
- Definir claves primarias y foráneas.
- Resolver cardinalidades N:M mediante entidades asociativas cuando corresponda.
- Definir atributos declarados y atributos normalizados por separado.
- Incorporar versionado necesario para reglas/modelos antifraude.
- Definir restricciones de integridad.
- Revisar auditoría e historial de estados.

### Sesión 4 — Modelo físico
**Resultado esperado:** modelo físico listo para implementación.
- Confirmar motor de base de datos: **PREGUNTA**.
- Definir tipos físicos, índices, particionamiento y estrategia de almacenamiento.
- Diseñar almacenamiento de evidencias y referencia al objeto original.
- Diseñar estrategia de auditoría y retención: **PREGUNTA**.
- Crear DDL una vez aprobado el motor.

### Sesión 5 — Datos sintéticos
**Resultado esperado:** dataset reproducible.
- Definir entidades y volumen por entidad.
- Generar pólizas, vehículos, asegurados/reportantes, siniestros, evidencias, participantes, asistencias, inspecciones, presupuestos, autorizaciones, alertas y pagos según el modelo aprobado.
- Generar casos normales, duplicados, inconsistentes y con señales antifraude.
- Generar relaciones entre múltiples siniestros sin fusionar expedientes.
- Mantener trazabilidad del escenario sintético.
- Crear seed reproducible.

### Sesión 6 — Arquitectura de solución
**Resultado esperado:** arquitectura candidata.
- Frontend móvil/web según definición de producto: **PREGUNTA**.
- Servicios de negocio.
- Persistencia.
- almacenamiento de evidencia.
- Integraciones síncronas/asíncronas.
- auditoría.
- seguridad y autorización por rol.
- componentes IA como recomendaciones revisables.

### Sesión 7 — Contratos e integraciones
**Resultado esperado:** contratos de APIs/eventos.
- Sistema de pólizas.
- Talleres.
- Grúa.
- Ajustadores.
- Mapas.
- Mensajería.
- Medios de pago.
- Definir timeout, retry, escalamiento y reasignación según política aprobada.

### Sesión 8 — Backend
**Resultado esperado:** vertical slice inicial.
- Registro de siniestro.
- Validación de póliza/vehículo/cobertura.
- Evidencias.
- Estados e historial.
- Asignación.
- Auditoría.

### Sesión 9 — Frontend y experiencia
**Resultado esperado:** flujo funcional basado en Figma.
- Reporte guiado.
- Evidencias.
- Seguimiento.
- Operación.
- Revisión antifraude.
- Manejo de excepciones.

### Sesión 10 — Fraude e IA
**Resultado esperado:** flujo explicable y revisable.
- Señales determinísticas.
- Señales de modelo.
- Tipo/severidad/explicación.
- Versionado de regla/modelo.
- Entrada utilizada.
- Revisión humana.
- Medición de falsos positivos.

### Sesión 11 — Pruebas
**Resultado esperado:** suite de pruebas.
- Criterios de aceptación.
- Casos felices.
- Excepciones.
- Duplicidad.
- Evidencia.
- proveedores indisponibles.
- alertas.
- pagos duplicados.
- autorización/reasignación.

### Sesión 12 — Piloto
**Resultado esperado:** preparación para piloto.
- Ciudad piloto: **PREGUNTA**.
- Grupo de talleres: **PREGUNTA**.
- Datos sintéticos y/o datos autorizados.
- Métricas de éxito.
- observabilidad.
- plan de expansión.

## Actualización obligatoria en cada sesión
Agregar al final del documento:

### Registro de sesiones
| Sesión | Fecha | Decisiones | Preguntas resueltas | Nuevas preguntas | Entregables modificados | Próximo paso |
|---|---|---|---|---|---|---|
| 1 | 2026-08-13 | Se adopta estructura de 12 bloques como marco maestro | — | Modelo conceptual, motor físico, estrategia de datos sintéticos y demás preguntas existentes | 00, 12, 13-16 | Validar modelo conceptual |

## Criterio para cambiar de fase
Una fase puede marcarse como COMPLETADA solo cuando sus entregables tienen criterios de aceptación verificables y las decisiones que dependen de preguntas abiertas han sido confirmadas o explícitamente aceptadas como propuestas técnicas.
