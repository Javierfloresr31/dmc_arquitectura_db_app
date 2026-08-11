# Plan de desarrollo — Crédito Ágil 360

> Este plan es el artefacto vivo de ejecución. Se actualiza al cierre de cada sesión con decisiones, evidencias, pendientes, entregables y cambios de alcance.

## 1. Principios de ejecución

1. **Specification Driven Development (SDD):** no iniciar una pieza de desarrollo sin especificación suficiente y trazabilidad hacia una necesidad.
2. **No inventar:** toda regla, dato, integración, SLA o tecnología no confirmada queda como pregunta, supuesto explícito o decisión pendiente.
3. **Vertical slices:** priorizar incrementos extremo a extremo sobre componentes aislados.
4. **Seguridad y auditoría desde el diseño:** la decisión crediticia debe ser reconstruible.
5. **Datos como producto:** separar datos maestros, transaccionales, evidencias y eventos.
6. **IA acotada:** asistencia documental/analítica, nunca una decisión crediticia autónoma mientras no exista una definición aprobada que lo permita.
7. **Compatibilidad evolutiva:** no requerir cambios simultáneos de todos los sistemas legados.

## 2. Roadmap por sesiones

| Sesión | Objetivo | Entregables | Estado |
|---|---|---|---|
| S01 | Descubrimiento y baseline | 12 bloques, HU, RF, RNF, criterios, preguntas | Completado |
| S02 | Validación de negocio | Respuestas a preguntas, alcance MVP validado, glosario | Pendiente |
| S03 | Modelo conceptual | Entidades, relaciones, límites de dominio, eventos principales | Pendiente |
| S04 | Procesos y estados | BPMN/flujo de originación, máquina de estados, excepciones | Pendiente |
| S05 | Arquitectura lógica | Contexto, componentes, APIs, eventos, integraciones | Pendiente |
| S06 | Modelo lógico de datos | Entidades/atributos, PK/FK, cardinalidades, catálogos | Pendiente |
| S07 | Modelo físico de datos | DDL, índices, restricciones, auditoría, particionamiento si aplica | Pendiente |
| S08 | Prototipo UX/UI | Flujos cliente, analista, supervisor y contact center en Figma | En progreso |
| S09 | Data sintética | Dataset reproducible para cliente, solicitudes, evaluación, documentos, decisiones y escenarios | Pendiente |
| S10 | Especificación técnica SDD | Contratos API/eventos, componentes, reglas, errores, seguridad | Pendiente |
| S11 | Desarrollo vertical 1 | Solicitud + continuidad + estado | Pendiente |
| S12 | Desarrollo vertical 2 | Evaluación + motor/políticas + trazabilidad | Pendiente |
| S13 | Desarrollo vertical 3 | Revisión manual + excepciones + contact center | Pendiente |
| S14 | Desarrollo vertical 4 | Contrato + desembolso + notificaciones | Pendiente |
| S15 | IA asistida | Extracción documental + confianza + revisión humana + resumen | Pendiente |
| S16 | Integración y resiliencia | Reintentos, idempotencia, fallas, reproceso, observabilidad | Pendiente |
| S17 | Pruebas | Unitarias, integración, contrato, E2E, seguridad, datos sintéticos | Pendiente |
| S18 | Validación de negocio | UAT contra criterios de aceptación y reglas aprobadas | Pendiente |
| S19 | Preparación operativa | Runbook, monitoreo, soporte, auditoría, despliegue | Pendiente |
| S20 | Release MVP | Go/no-go, versión, pendientes posteriores | Pendiente |

El número de sesiones es una planificación inicial y puede cambiar según las respuestas de negocio y la complejidad descubierta.

## 3. Modelo conceptual

### Objetivo
Representar el dominio sin decidir todavía tablas, tipos de datos ni tecnología de persistencia.

### Entidades identificadas por evidencia
- Cliente
- Oferta
- Simulación
- Solicitud
- Documento
- Evaluación
- Decisión
- Excepción
- Contrato
- Desembolso
- Notificación
- Fuente de información
- Política/regla
- Evento de auditoría

### Relaciones a validar
- Cliente puede tener ofertas y solicitudes.
- Una solicitud puede originarse desde una oferta/simulación y continuar entre canales.
- Una solicitud reúne datos, autorizaciones y documentos.
- Una solicitud puede tener una o más evaluaciones según reproceso/versiones.
- Una evaluación produce una decisión.
- Una decisión puede requerir excepción.
- Un aprobado puede conducir a contrato y desembolso.
- Eventos de auditoría registran la reconstrucción de la operación.

**No se fijan cardinalidades definitivas hasta validar el negocio.**

## 4. Modelo lógico

Se construirá después de validar el modelo conceptual. Incluirá:
- entidades y atributos;
- claves primarias y foráneas;
- cardinalidades;
- catálogos y dominios;
- datos maestros vs. transaccionales;
- snapshots de decisión;
- versionado de políticas;
- documentos y metadatos de extracción;
- auditoría/eventos;
- relaciones con fuentes externas.

### Regla de diseño
La decisión histórica debe conservar el contexto utilizado en el momento de decidir. Los cambios posteriores de datos no deben reescribir esa decisión.

## 5. Modelo físico

Se definirá únicamente después de aprobar el modelo lógico y conocer las restricciones de plataforma.

Entregables:
- DDL;
- PK/FK/UNIQUE/CHECK/NOT NULL;
- índices justificados;
- estrategia de secuencias/identificadores;
- estrategia de auditoría;
- estrategia de documentos/evidencias;
- estrategia de retención;
- estrategia de particionamiento si los volúmenes lo justifican;
- scripts de creación y rollback;
- scripts de data sintética;
- diccionario de datos.

No se asume Oracle, PostgreSQL, SQL Server u otra tecnología como decisión final hasta validar la plataforma objetivo.

## 6. Data sintética

### Objetivo
Crear datos seguros y reproducibles para desarrollo, pruebas funcionales, integración, carga y demostraciones sin utilizar información financiera o personal real.

### Dominios mínimos
- clientes;
- ofertas;
- simulaciones;
- solicitudes;
- datos laborales/ingresos requeridos por la especificación;
- obligaciones/exposición;
- evaluaciones;
- resultados/decisiones;
- documentos y estados;
- excepciones;
- contratos;
- desembolsos;
- notificaciones;
- eventos/auditoría;
- respuestas de fuentes internas/externas simuladas.

### Escenarios obligatorios
1. Solicitud aprobada automáticamente.
2. Solicitud rechazada.
3. Solicitud observada por información pendiente.
4. Solicitud derivada a revisión manual.
5. Excepción recomendada y aprobada por supervisor.
6. Excepción rechazada.
7. Documento ilegible.
8. Inconsistencia entre ingreso declarado y fuente.
9. Consulta externa con timeout y posterior reproceso.
10. Reintento idempotente de solicitud.
11. Cambio de canal durante la solicitud.
12. Extracción IA con alta confianza.
13. Extracción IA bajo umbral y revisión humana.
14. Decisión histórica consultada después de modificar datos del cliente.

### Reglas de generación
- Datos ficticios sin PII real.
- Valores consistentes entre tablas/objetos relacionados.
- Seeds reproducibles.
- Identificadores claramente sintéticos.
- Dataset pequeño para desarrollo y dataset ampliado para pruebas de carga.
- No introducir reglas de riesgo que no hayan sido aprobadas; para escenarios de prueba se usarán etiquetas de escenario explícitas.

## 7. Diseño de arquitectura

La arquitectura deberá resolver, como mínimo:
- canales desacoplados del núcleo de originación;
- gestión de solicitud y estado;
- orquestación de consultas;
- evaluación/políticas versionadas;
- revisión manual y excepciones;
- auditoría;
- documentos;
- notificaciones;
- contrato/desembolso;
- observabilidad;
- seguridad;
- capacidades IA aisladas y gobernadas.

Las decisiones de tecnología, patrón de integración y despliegue se registrarán como ADR cuando sean aprobadas.

## 8. Calidad y pruebas

Cada HU tendrá criterios de aceptación ejecutables. Cada RF tendrá pruebas funcionales. Cada RNF tendrá una estrategia de verificación. La data sintética cubrirá caminos felices, errores, límites y reprocesos.

### Gates
- **Gate G1:** requisitos validados.
- **Gate G2:** modelo conceptual aprobado.
- **Gate G3:** arquitectura lógica aprobada.
- **Gate G4:** modelo lógico aprobado.
- **Gate G5:** modelo físico aprobado.
- **Gate G6:** data sintética validada.
- **Gate G7:** especificación SDD lista para desarrollo.
- **Gate G8:** vertical slice aceptado.
- **Gate G9:** UAT aprobado.
- **Gate G10:** release MVP autorizado.

## 9. Control de cambios por sesión

Al finalizar cada sesión actualizar:
- fecha y número de sesión;
- decisiones tomadas;
- preguntas respondidas;
- preguntas nuevas;
- requisitos agregados/modificados/eliminados;
- historias afectadas;
- modelos afectados;
- prototipo Figma afectado;
- data sintética afectada;
- riesgos/bloqueos;
- entregables completados;
- siguiente objetivo.

## 10. Registro inicial — Sesión S01

**Completado:** consolidación de las entrevistas en 12 bloques; historias de usuario; RF; RNF; criterios de aceptación; preguntas abiertas; trazabilidad; prototipo preliminar Figma.

**Nueva planificación:** incorporar explícitamente modelo conceptual, modelo lógico, modelo físico, dataset sintético, estrategia de pruebas y gates de aprobación.

**Bloqueos:** las preguntas de negocio pendientes impiden fijar reglas crediticias, fuentes, cardinalidades definitivas, estructura física y algunos contratos de integración.

**Siguiente sesión:** validar preguntas de negocio y comenzar el modelo conceptual.
