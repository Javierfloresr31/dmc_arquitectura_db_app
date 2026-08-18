# 13. Plan de desarrollo de Stock Único

## Propósito

Este documento será el **artefacto vivo de seguimiento** del proyecto. Se actualizará en cada sesión con decisiones, avances, brechas, entregables y próximos pasos.

## Regla de trabajo

Cada sesión debe dejar explícitamente:

- qué se decidió;
- qué evidencia lo sustenta;
- qué artefacto se actualizó;
- qué pregunta quedó resuelta;
- qué pregunta permanece abierta;
- qué validación se ejecutó;
- cuál es el siguiente paso.

## Fases

| Fase | Entregable | Estado inicial |
|---|---|---|
| 0 | Baseline de 12 bloques + trazabilidad | En curso |
| 1 | Cierre de preguntas de negocio | Pendiente |
| 2 | Modelo conceptual de dominio | Pendiente |
| 3 | Modelo lógico de datos | Pendiente |
| 4 | Modelo físico de datos | Pendiente |
| 5 | Especificación técnica SDD | Pendiente |
| 6 | Prototipo Figma validado | Inicial creado |
| 7 | Data sintética para desarrollo/pruebas | Pendiente |
| 8 | Contratos e interfaces de integración | Pendiente |
| 9 | Implementación incremental | Pendiente |
| 10 | Pruebas funcionales, concurrencia, idempotencia y degradación | Pendiente |
| 11 | Validación con métricas y escenarios de campaña | Pendiente |
| 12 | Preparación de evolución del piloto | Pendiente |

## Fase 0 — Baseline documental

- Mantener los 12 bloques alineados con las entrevistas.
- Mantener trazabilidad Historia → RF/RNF → Regla → Criterio → Pregunta.
- Identificar cualquier dato no respaldado.

**Salida:** especificación base aprobable.

## Fase 1 — Cierre de brechas

Resolver prioritariamente las preguntas que afectan disponibilidad, reserva, pago/pedido, multi-SKU, asignación, eventos y rendimiento.

**Salida:** decisiones de negocio versionadas.

## Fase 2 — Modelo conceptual

Construir el modelo conceptual únicamente a partir de conceptos confirmados. Candidatos iniciales: Producto/SKU, Ubicación, Inventario, Movimiento, Reserva, Carrito, Pedido, Pago, Promesa, Tarea de preparación, Transferencia y eventos asociados.

No se crearán entidades por conveniencia técnica sin trazabilidad a una necesidad.

**Salida:** modelo conceptual validado por dominio.

## Fase 3 — Modelo lógico

Transformar el modelo conceptual confirmado en estructuras lógicas, relaciones, cardinalidades, estados y reglas de integridad.

**Salida:** modelo lógico revisado y trazable.

## Fase 4 — Modelo físico

Derivar tablas, claves, índices, restricciones, particionado y estructuras de persistencia únicamente después de cerrar los requisitos de volumen, concurrencia, auditoría y eventos.

**Salida:** modelo físico listo para implementación.

## Fase 5 — SDD técnico

Definir arquitectura de solución, componentes, contratos, secuencias, errores, idempotencia, concurrencia, observabilidad, seguridad y estrategia de integración.

No fijar tecnología si no existe una decisión de arquitectura respaldada.

## Fase 6 — Figma

Evolucionar el prototipo existente conforme se cierren reglas de negocio. Validar especialmente:

- disponibilidad;
- reserva;
- promesa;
- preparación;
- excepción/reasignación;
- retiro.

## Fase 7 — Data sintética

Crear dataset sintético reproducible para desarrollo y pruebas. Debe cubrir como mínimo:

- SKU y productos;
- ubicaciones;
- saldos de inventario;
- reservas;
- movimientos;
- pedidos multi-SKU;
- pagos;
- promesas;
- tareas de preparación;
- transferencias;
- eventos duplicados y fuera de orden;
- faltantes y daños;
- escenarios de degradación.

Los valores generados serán explícitamente sintéticos y no representarán datos reales de NovaRetail.

## Fase 8 — Integraciones

Definir contratos de entrada/salida para POS, inventario, pagos, pedidos y logística cuando se conozcan los sistemas reales.

## Fase 9 — Implementación incremental

Orden recomendado:

1. inventario/movimientos;
2. disponibilidad;
3. reserva;
4. pedido/pago;
5. asignación/promesa;
6. preparación;
7. retiro;
8. excepciones;
9. indicadores;
10. IA recomendadora.

Este orden es una propuesta de trabajo y deberá validarse después de cerrar las preguntas.

## Fase 10 — Pruebas

Escenarios mínimos:

- concurrencia sobre el mismo SKU/ubicación;
- reintentos de checkout;
- reserva duplicada;
- expiración;
- pago aprobado con reserva fallida;
- reserva confirmada con pago fallido;
- faltante y reasignación;
- evento duplicado;
- evento fuera de orden;
- fuente retrasada;
- pico de campaña.

## Fase 11 — Validación

Comparar los resultados contra criterios de aceptación y métricas acordadas. No establecer metas numéricas hasta que negocio las defina.

## Fase 12 — Evolución

Documentar qué se aprendió del piloto y qué habilita la expansión a otras categorías y ciudades.

## Registro de sesiones

| Sesión | Fecha | Decisiones | Artefactos actualizados | Preguntas cerradas | Próximo paso |
|---|---|---|---|---|---|
| S01 | 2026-08-18 | Estructura documental de 12 bloques; separar modelos y data sintética como fases | 01-13 + Figma | Estructura del trabajo | Cerrar preguntas prioritarias |

## Artefactos esperados

- Especificación de 12 bloques.
- Matriz de trazabilidad.
- Modelo conceptual.
- Modelo lógico.
- Modelo físico.
- SDD técnico.
- Prototipo Figma.
- Data sintética reproducible.
- Contratos de integración.
- Plan y evidencias de pruebas.
