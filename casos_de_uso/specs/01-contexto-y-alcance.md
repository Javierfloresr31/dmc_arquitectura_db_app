# 01. Contexto y alcance

## 1. Contexto
Banco Andino Digital, descrito como contexto ficticio de las entrevistas, busca modernizar la originación de créditos personales. La primera versión se enfoca en clientes existentes con ingresos recurrentes.

Problemas expresados: proceso fragmentado entre canales y áreas; duplicidad de información; abandono por desconocimiento del estado; intervención manual; riesgo de solicitudes duplicadas; dependencia de sistemas legados.

## 2. Objetivo de producto
Construir una capacidad de originación que permita recibir solicitudes, validar identidad, reutilizar datos vigentes, recopilar documentos cuando correspondan, consultar información interna/externa, ejecutar políticas de elegibilidad, gestionar revisión manual, mostrar estado y completar aceptación/desembolso.

## 3. Alcance MVP derivado de entrevistas
- Créditos personales.
- Clientes existentes con ingresos recurrentes.
- Inicio por app, web, agencia o contact center.
- Identificador único de solicitud y continuidad entre canales.
- Confirmación/actualización de información disponible.
- Autorización de consultas.
- Carga y revisión de documentos cuando correspondan.
- Evaluación con reglas controladas y auditables.
- Resultados: aprobado, rechazado, observado o revisión manual.
- Gestión de excepciones con segregación de funciones.
- Seguimiento visible al cliente.
- Notificaciones de eventos relevantes sin exponer datos sensibles.
- Aceptación de contrato y desembolso.
- Trazabilidad de la decisión.
- IA limitada a extracción documental, detección de inconsistencias, orientación y resumen para analistas; no decide el crédito.

## 4. Fuera de alcance explícito o futuro
- Clientes nuevos y trabajadores independientes: mencionados como una etapa posterior.
- Otros productos de crédito: no priorizados para la primera versión.
- Cambios simultáneos de todos los sistemas legados: explícitamente no deseados.
- Decisión crediticia autónoma por IA: no permitida por la entrevista.

## 5. Actores
| Actor | Responsabilidad derivada |
|---|---|
| Cliente | Iniciar/continuar solicitud, confirmar datos, entregar documentos, revisar condiciones, aceptar contrato. |
| Asesor | Asistir al cliente y consultar el mismo estado con acceso restringido. |
| Analista de riesgos | Revisar casos manuales y recomendar excepciones. |
| Supervisor | Autorizar excepciones según nivel de riesgo. |
| Motor de reglas | Ejecutar políticas de elegibilidad. |
| Sistemas/fuentes externas | Proveer información consultada; fuentes exactas pendientes. |
| Contact center | Consultar estado y registrar incidencias; no modificar decisión de riesgos. |

## 6. Objetos de negocio identificados
Cliente, solicitud, oferta, simulación, documento, evaluación, decisión, excepción, contrato, desembolso y notificación.

## 7. Estados visibles al cliente
Borrador; información pendiente; en evaluación; requiere documento; requiere validación; aprobado; no aprobado; pendiente de aceptación; listo para desembolso; desembolsado; cancelado.

Los estados internos pueden ser más detallados y deben traducirse a mensajes simples para el cliente.

## 8. Indicadores mencionados
Conversión de ofertas a desembolsos, tiempo medio desde solicitud hasta decisión, abandono por etapa, mora temprana y solicitudes que requieren intervención manual. Las entrevistas no establecen metas numéricas.

## 9. Volumen informado
8,000 simulaciones diarias y 1,500 solicitudes en condiciones normales; campañas pueden multiplicar por cinco el tráfico durante las primeras horas. Debe tratarse como dato de entrevista, no como SLA contractual.
