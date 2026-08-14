# Siniestro Fácil — Especificaciones SDD

## 12 bloques. Una sola fuente de verdad.

La estructura de especificación separa **necesidad, contexto, objetivos, alcance, actores, procesos, historias, requisitos, reglas, criterios y preguntas**. La fuente funcional es la entrevista de descubrimiento del caso de uso 2 — Seguros Horizonte: Siniestro Fácil. fileciteturn0file0

**Regla de trazabilidad:** esta especificación no introduce políticas, umbrales, SLA, datos, integraciones ni decisiones de negocio no expresamente soportadas por la fuente. Lo que no está definido se registra como pregunta abierta o como propuesta técnica explícitamente etiquetada.

## Bloques maestros

| # | Bloque | Documento |
|---|---|---|
| 01 | Identidad | `00-especificacion-maestra-12-bloques.md` |
| 02 | Contexto | `00-especificacion-maestra-12-bloques.md` |
| 03 | Objetivos | `00-especificacion-maestra-12-bloques.md` |
| 04 | Alcance | `01-vision-y-alcance.md` |
| 05 | Actores | `00-especificacion-maestra-12-bloques.md` |
| 06 | Procesos | `07-modelo-dominio-y-flujos.md` |
| 07 | Historias | `02-historias-usuario.md` |
| 08 | RF | `03-requerimientos-funcionales.md` |
| 09 | RNF | `04-requerimientos-no-funcionales.md` |
| 10 | Reglas | `06-reglas-negocio.md` |
| 11 | Criterios | `05-criterios-aceptacion.md` |
| 12 | Preguntas | `09-riesgos-y-preguntas-abiertas.md` |

## Especificaciones existentes
- `01-vision-y-alcance.md`
- `02-historias-usuario.md`
- `03-requerimientos-funcionales.md`
- `04-requerimientos-no-funcionales.md`
- `05-criterios-aceptacion.md`
- `06-reglas-negocio.md`
- `07-modelo-dominio-y-flujos.md`
- `08-integraciones-y-eventos.md`
- `09-riesgos-y-preguntas-abiertas.md`
- `10-matriz-trazabilidad.md`
- `11-validaciones.md`

## Diseño y construcción
- `12-plan-desarrollo.md` — plan vivo que se actualizará en cada sesión.
- `13-modelo-conceptual.md` — entidades y relaciones conceptuales candidatas.
- `14-modelo-logico.md` — estructuras lógicas preliminares y normalización.
- `15-modelo-fisico.md` — diseño físico preliminar, agnóstico de motor hasta confirmar la plataforma.
- `16-datos-sinteticos.md` — estrategia, escenarios y criterios para generar datos sintéticos reproducibles.

## Orden de evolución
1. Validar los 12 bloques.
2. Resolver preguntas prioritarias.
3. Cerrar modelo conceptual.
4. Cerrar modelo lógico.
5. Confirmar decisiones de persistencia y cerrar modelo físico.
6. Generar datos sintéticos reproducibles.
7. Consolidar arquitectura e integraciones.
8. Implementar vertical slices.
9. Validar UX y prototipo.
10. Ejecutar pruebas y preparar piloto.

## Fuente
La especificación se deriva del archivo de entrevistas adjunto `02_seguros_siniestro_facil(1).md`. Las entrevistas identifican actores, objetos de negocio, eventos, restricciones e incertidumbres; entre estas últimas figuran deduplicación, umbrales antifraude, conservación de imágenes, SLA regional e integración con talleres. fileciteturn19file1

## Prototipo Figma
**Siniestro Fácil - Prototipo Validaciones:** https://www.figma.com/design/0Rei4fxORWRHcGBuK3IA3n

El prototipo es preliminar y representa únicamente comportamientos soportados por las entrevistas; no constituye una definición visual definitiva.
