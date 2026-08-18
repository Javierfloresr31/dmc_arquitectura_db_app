# Stock Único — Especificación SDD

## Fuente

`03_retail_stock_unico.md`, entrevistas de NovaRetail.

El material establece que las entrevistas son el punto de partida del descubrimiento y no una solución ya diseñada. fileciteturn0file0L11-L13

## Archivos

1. `01_historias_usuario.md` — historias de usuario y trazabilidad.
2. `02_criterios_aceptacion.md` — criterios verificables asociados a historias.
3. `03_requerimientos_funcionales.md` — requerimientos funcionales trazables.
4. `04_requerimientos_no_funcionales.md` — restricciones y atributos de calidad expresados por las entrevistas.
5. `05_casos_de_uso.md` — casos de uso y diagrama de contexto.
6. `06_modelos_datos_mermaid.md` — modelo conceptual, lógico y físico preliminar, con Mermaid ER.
7. `07_discrepancias.md` — vacíos, decisiones pendientes y puntos que no deben inventarse.

## Regla aplicada

Solo se utilizaron hechos, necesidades, restricciones, actores, objetos y eventos expresados en las entrevistas. Cuando falta una definición necesaria para implementar, se marca como discrepancia/vacío.

## Evidencia central

Las entrevistas identifican actores, objetos de negocio, eventos, restricciones e incertidumbres de forma explícita. fileciteturn0file0L160-L166

El reto exige resolver explícitamente consistencia, reservas, serialización, transacciones distribuidas, compensación, modo degradado y separación entre datos operativos y analítica. fileciteturn0file0L168-L178
