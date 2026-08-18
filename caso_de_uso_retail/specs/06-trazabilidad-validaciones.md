# 6. Trazabilidad y validaciones

## 6.1 Matriz de trazabilidad

| Elemento | Evidencia principal | Resultado |
|---|---|---|
| Disponibilidad confiable | CEO 2-3,6,9; SC 3; EC 1,3,9 | Cubierto por HU-01 / RF-01,02,23 |
| Reserva temporal | SC 4-5; EC 2; CEO 7 | Cubierto por HU-02 / RF-03-06 |
| Pago-pedido-reserva | EC 8 | Cubierto por HU-03 / RF-07 |
| Asignación/promesa | CEO 6; SC 7; EC 3,5 | Cubierto por HU-04 / RF-08,09,14,15 |
| Preparación | EC 4,7 | Cubierto por HU-05 / RF-11-13 |
| Excepciones | EC 5; SC 6,7 | Cubierto por HU-06 / RF-14-16 |
| Retiro | EC 6 | Cubierto por HU-07 / RF-17,18 |
| Auditoría | SC 6,10 | Cubierto por HU-08 / RF-19,20 |
| Degradación | CEO 9,10; EC 9 | Cubierto por HU-09 / RF-23 |
| IA | CEO 8 | Cubierto por HU-10 / RF-26 |

## 6.2 Validación de cobertura

- Cada actor identificado en las entrevistas aparece asociado a una capacidad o se marca como pendiente de permisos detallados.
- Los objetos de negocio mencionados se reflejan en el contexto y en los requerimientos.
- Los eventos explícitamente mencionados se incorporan como entradas de inventario/pedido/auditoría.
- Las modalidades de despacho a domicilio y retiro en tienda están contempladas.
- La preparación desde tienda está contemplada.
- Concurrencia e idempotencia están reflejadas como requisitos y criterios de aceptación.
- Auditoría de cambios de inventario está reflejada.
- Operación degradada está reflejada.
- IA está limitada a recomendación según la evidencia disponible.

## 6.3 Validación de no invención

No se han fijado en esta especificación:

- tiempos de reserva;
- fórmula numérica de disponibilidad;
- pesos de asignación;
- algoritmo de promesa;
- SLO/SLA numéricos;
- RTO/RPO;
- tecnología de persistencia, mensajería o caché;
- proveedores o nombres de sistemas actuales;
- permisos concretos por rol;
- tiendas concretas del piloto;
- SKU concretos del piloto;
- reglas de compensación entre pago, pedido y reserva.

Todos ellos se mantienen como preguntas abiertas.

## 6.4 Validación de consistencia interna

### Consistencia de stock

La documentación diferencia stock físico, stock utilizable/disponibilidad y reservas. Esto es consistente con la declaración de negocio de que una unidad físicamente presente no necesariamente es apta para venta digital.

### Consistencia de reserva

La documentación exige expiración, idempotencia y control de concurrencia. No se define una duración ni un mecanismo técnico concreto.

### Consistencia eventual vs fuerte

La documentación separa consulta y reserva: la primera puede tolerar eventualidad; la segunda requiere mayor consistencia. La tecnología que materializará esta diferencia no se define.

### Integración gradual

La documentación evita una sustitución total y mantiene integración con sistemas actuales, en línea con la restricción de CEO.

### IA

La documentación no convierte recomendaciones predictivas en transacciones, en línea con la restricción de CEO.

## 6.5 Criterio de entrada a diseño técnico

No debe considerarse cerrada la especificación para implementación hasta resolver al menos las preguntas 1-24 y 33-46 de `05-preguntas-abiertas.md`, porque afectan directamente disponibilidad, reserva, pedido, asignación, eventos y escalabilidad.

Las preguntas 47-60 pueden resolverse por fases, pero deben estar asignadas a responsables antes de producción.