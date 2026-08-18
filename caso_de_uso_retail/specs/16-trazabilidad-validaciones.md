# 16. Trazabilidad y validaciones

## Matriz principal

| Necesidad | Proceso | Historia | RF/RNF | Regla | Criterio |
|---|---|---|---|---|---|
| Disponibilidad confiable | P1 | HU-01 | RF-01/02/22, RNF-03 | RN-01/RN-11 | Disponibilidad |
| Reserva segura | P2 | HU-02 | RF-03/04/05/06, RNF-04/05/06 | RN-02/RN-03 | Reserva |
| Pago/pedido trazable | P3 | HU-03 | RF-07 | Pendiente | Pago/pedido |
| Promesa y asignación | P4 | HU-04 | RF-08/09/14/15 | RN-06/RN-07 | Asignación/promesa |
| Preparación | P5 | HU-05 | RF-11/12/13 | Pendiente | Preparación |
| Excepciones | P6 | HU-06 | RF-14/15 | RN-07/RN-08 | Excepción |
| Retiro | P7 | HU-07 | RF-16/17 | RN-08 | Retiro |
| Auditoría | P8 | HU-08 | RF-18/19/20/21, RNF-07/11/12 | RN-05 | Inventario |
| Degradación | P9 | HU-09 | RF-22/23, RNF-08 | RN-11 | Degradación |
| IA | P10 | HU-10 | RF-25/26 | RN-09 | IA |

## Validación de los 12 bloques

- **Identidad:** define la solución y la fuente de verdad documental.
- **Contexto:** mantiene los hechos y problemas expresados en entrevistas.
- **Objetivos:** conserva resultados y métricas mencionados por negocio.
- **Alcance:** limita el primer paso a tecnología y pequeños electrodomésticos en Lima.
- **Actores:** no inventa roles ni permisos.
- **Procesos:** transforma las entrevistas en flujos sin fijar decisiones no confirmadas.
- **Historias:** cubre los principales comportamientos requeridos.
- **RF:** convierte necesidades en capacidades verificables.
- **RNF:** conserva rendimiento, consistencia, idempotencia, auditoría, degradación y seguridad como requisitos.
- **Reglas:** separa reglas evidenciadas de parámetros pendientes.
- **Criterios:** permite validar cada capacidad.
- **Preguntas:** mantiene las brechas sin convertirlas en supuestos.

## Validación de no invención

No se fijan en la documentación:

- fórmula numérica de disponibilidad;
- duración de reservas;
- algoritmo de asignación/promesa;
- tecnología de base de datos, eventos o caché;
- sistemas/proveedores concretos;
- SLO/SLA numéricos;
- RTO/RPO;
- cardinalidades definitivas;
- atributos definitivos del modelo de datos;
- tiendas/SKU concretos del piloto;
- datos reales de clientes;
- reglas definitivas de compensación.

## Criterio de entrada a diseño detallado

Antes de cerrar el diseño técnico deben resolverse las preguntas que afectan disponibilidad, reserva, pago/pedido, multi-SKU, asignación, eventos y rendimiento. El modelo conceptual puede comenzar con conceptos candidatos, pero sus relaciones y atributos deben quedar sujetos a validación.
