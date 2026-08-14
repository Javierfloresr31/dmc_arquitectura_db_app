# Siniestro Fácil — Autovalidación y cobertura

## Matriz de cobertura de los 12 bloques

| Elemento | Existe | Tiene fuente | Trazabilidad | Estado |
|---|---|---|---|---|
| Identidad | Sí | Sí | Sí | OK |
| Contexto | Sí | Sí | Sí | OK |
| Objetivos | Sí | Sí | Sí | OK |
| Alcance | Sí | Sí | Sí | OK |
| Actores | Sí | Sí | Sí | OK |
| Procesos | Sí | Sí | Sí | OK |
| Historias | Sí | Sí | Sí | OK / vivo |
| RF | Sí | Sí | Sí | OK / vivo |
| RNF | Sí | Sí | Sí | OK / vivo |
| Reglas | Sí | Sí | Sí | OK / vivo |
| Criterios | Sí | Sí | Sí | OK / vivo |
| Preguntas | Sí | Sí | Sí | OK / vivo |
| Casos de uso | Parcial | Sí | Parcial | Pendiente |
| Modelo conceptual | Sí | Sí | Sí | Borrador |
| Modelo lógico | Sí | Sí | Sí | Borrador |
| Modelo físico | Sí | Sí | Sí | Pre-diseño |
| Data sintética | Sí | Sí | Sí | Plan |
| Arquitectura | Parcial | Sí | Parcial | Pendiente |
| Integraciones | Parcial | Sí | Parcial | Pendiente |
| Figma | Sí | Sí | Sí | Prototipo inicial |

## Reglas de autovalidación

- Ningún elemento no confirmado se presenta como decisión aprobada.
- Todo requisito debe poder remontarse a una necesidad/fuente.
- Toda regla de negocio debe tener origen en entrevista o decisión aprobada.
- Toda historia debe expresar una necesidad de actor.
- Todo criterio debe poder verificarse.
- El modelo conceptual debe corresponder al vocabulario del negocio.
- El modelo lógico no debe introducir entidades sin justificación.
- El modelo físico no se considera aprobado hasta confirmar tecnología y políticas.
- La data sintética debe respetar las relaciones del modelo lógico.
- Las alertas de fraude deben ser reproducibles y revisables.

## Próxima validación obligatoria

Antes de iniciar DDL e implementación, revisar las preguntas abiertas de `05_preguntas_abiertas.md` que afectan identidad, cardinalidad, estados, cobertura, deducible, evidencia, pagos, relación entre casos, retención y roles.
