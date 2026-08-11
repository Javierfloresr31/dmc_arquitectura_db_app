# Modelo lógico — plan de construcción

> Estado: Pendiente de validación del modelo conceptual y respuestas de negocio.

## Objetivo
Transformar el modelo conceptual aprobado en estructuras de información independientes de una tecnología específica de base de datos.

## Artefactos
- Diagrama entidad-relación lógico.
- Diccionario de datos.
- Entidades y atributos.
- PK/FK y cardinalidades.
- Catálogos y dominios.
- Reglas de integridad.
- Datos actuales vs. snapshots de decisión.
- Versionado de políticas.
- Evidencias documentales y extracción IA.
- Auditoría y eventos.

## Principios
1. No almacenar dos veces un dato sensible sin una razón de negocio/técnica documentada.
2. Separar datos maestros, datos transaccionales, snapshots y eventos.
3. Una decisión histórica debe conservar el contexto necesario para reconstrucción.
4. Las relaciones con sistemas externos deben evitar acoplamiento innecesario.
5. Los atributos que todavía no estén respaldados por entrevistas se marcarán como pendientes.

## Entradas requeridas
- Modelo conceptual aprobado.
- Respuestas de las preguntas de negocio.
- Reglas de Riesgos validadas.
- Fuentes maestras de datos.
- Retención y cumplimiento.
- Plataforma de persistencia seleccionada.

## Criterio de salida
Modelo lógico aprobado por negocio, Riesgos, Arquitectura y Datos, sin preguntas críticas abiertas que afecten la estructura.
