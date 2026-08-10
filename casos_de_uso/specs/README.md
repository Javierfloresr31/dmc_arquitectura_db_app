# Crédito Ágil 360 — Especificación SDD

## Estado
Draft de descubrimiento. No es una especificación aprobada.

## Fuente principal
`01_banca_credito_agil_360.md`, documento de entrevistas entregado para el caso de uso. Las entrevistas son explícitamente un insumo inicial de descubrimiento.

## Regla de trazabilidad
Esta carpeta separa hechos expresados en entrevistas, decisiones preliminares de arquitectura y preguntas pendientes. No se deben convertir preguntas abiertas en requisitos implementables hasta obtener respuesta del responsable correspondiente.

## Documentos
- `01-contexto-y-alcance.md`: contexto, alcance MVP, actores, objetos y trazabilidad.
- `02-historias-de-usuario.md`: historias de usuario y criterios de aceptación.
- `03-requerimientos.md`: requerimientos funcionales y no funcionales.
- `04-arquitectura-preliminar.md`: diseño lógico preliminar y decisiones SDD sujetas a validación.
- `05-validaciones-y-preguntas.md`: validaciones, incertidumbres y preguntas por responder.
- `06-trazabilidad.md`: matriz de trazabilidad desde entrevistas hacia requisitos e historias.

## Prototipo Figma
Prototipo preliminar: https://www.figma.com/design/dl82zsFUYmbdvcDqz6MUcs

El prototipo representa únicamente los flujos soportados por las entrevistas: inicio/oferta, solicitud, seguimiento de estado y vista preliminar de analista. No representa reglas, datos, textos regulatorios ni condiciones crediticias no definidas en las entrevistas.

## Criterio para pasar a desarrollo SDD
Una historia solo debe considerarse lista para implementación cuando tenga: alcance claro, actor, precondiciones, flujo principal, escenarios alternativos, criterios de aceptación verificables y dependencias/preguntas resueltas cuando afecten el comportamiento.
