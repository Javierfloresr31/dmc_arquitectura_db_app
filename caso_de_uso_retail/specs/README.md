# NovaRetail — Stock Único

## Especificación inicial SDD

Esta carpeta contiene la especificación inicial derivada exclusivamente de las entrevistas del caso de uso **Retail — Stock Único**.

### Documentos

1. `01-contexto-alcance.md` — contexto, objetivos, alcance inicial, actores, conceptos y evidencias.
2. `02-historias-usuario.md` — historias de usuario, criterios de aceptación y trazabilidad a entrevistas.
3. `03-requerimientos.md` — requerimientos funcionales, reglas evidenciadas y requisitos no funcionales.
4. `04-arquitectura-preliminar.md` — arquitectura lógica preliminar y decisiones que deben validarse antes de implementar.
5. `05-preguntas-abiertas.md` — brechas, preguntas de negocio y decisiones pendientes.
6. `06-trazabilidad-validaciones.md` — matriz de trazabilidad y validaciones de consistencia de la especificación.

## Principio de elaboración

- No se incorporan datos de negocio no presentes en las entrevistas.
- Cuando una decisión no está definida, se documenta como pregunta abierta o hipótesis de trabajo explícita.
- Las historias de usuario representan necesidades derivadas de las entrevistas; no constituyen todavía una solución técnica definitiva.
- Las cifras de 78 tiendas, 2 centros de distribución, aproximadamente 65.000 SKU y picos de 12.000 pedidos/hora se tratan como datos declarados en la entrevista y deberán confirmarse en discovery técnico.

## Prototipo Figma

Prototipo preliminar: **NovaRetail - Stock Único - Prototipo**.

Incluye tres vistas conceptuales:

- Dashboard operativo.
- Consulta de disponibilidad y reserva.
- Cola de preparación y gestión de faltantes.

El prototipo es exploratorio y no debe interpretarse como definición cerrada de UI, reglas o arquitectura.