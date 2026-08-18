# Stock Único — NovaRetail

**Fuente:** entrevistas del caso `NovaRetail: Stock Único`  
**Método:** PROMPT ATLAS v2.0  
**Regla:** contenido sustentado exclusivamente en las entrevistas; los vacíos se mantienen como pendientes.

# Autovalidación ATLAS v2.0

## 1. Trazabilidad

| Control | Resultado |
|---|---|
| Cada historia tiene fuente | PASS |
| Cada criterio se relaciona con historia | PASS |
| Cada RF tiene fuente | PASS |
| Cada RNF tiene fuente | PASS |
| Cada caso de uso tiene fuente | PASS |
| Modelos usan objetos mencionados | PASS con validación pendiente de cardinalidades |

## 2. No invención

Se revisó explícitamente la inclusión de:

- requisitos inventados;
- reglas inventadas;
- actores inventados;
- tecnologías inventadas;
- métricas inventadas;
- datos inventados;
- procesos inventados.

**Resultado:** PASS.

Las decisiones técnicas no definidas fueron trasladadas a discrepancias.

## 3. Consistencia

**Resultado:** PASS.

No se detectó contradicción explícita entre las entrevistas. Las incertidumbres se conservaron como pendientes.

## 4. Cobertura

Las brechas identificadas tienen correspondencia con historias y/o requisitos.

**Resultado:** PASS.

## 5. Vacíos

Los puntos necesarios para avanzar a diseño detallado y que no están definidos aparecen en `15_discrepancias.md`.

**Resultado:** PASS.

## 6. Limitaciones

La fuente no permite validar:

- arquitectura tecnológica concreta;
- modelo físico de BD;
- contratos de integración;
- SLA/SLO;
- mecanismo técnico de seguridad;
- algoritmo de asignación;
- fórmula definitiva de disponibilidad;
- compensación distribuida;
- arquitectura concreta de IA.

No se inventaron estos elementos.

## Conclusión

**La especificación es trazable y consistente como especificación inicial de descubrimiento/SDD, pero no debe considerarse diseño técnico definitivo hasta resolver las decisiones críticas de `15_discrepancias.md`.**
