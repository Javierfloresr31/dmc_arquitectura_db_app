# Modelo conceptual v0 — Crédito Ágil 360

> Estado: preliminar. Se deriva exclusivamente de los objetos y procesos explícitos en las entrevistas. No representa todavía un modelo lógico ni físico.

## Dominios

### Cliente y oferta
- Cliente
- Oferta
- Simulación

### Originación
- Solicitud
- Canal
- Autorización
- Documento

### Riesgos
- Evaluación
- Política/Regla
- Score
- Decisión
- Excepción

### Formalización y desembolso
- Contrato
- Desembolso

### Integración y gobierno
- Fuente de información
- Notificación
- Evento de auditoría
- Intervención de usuario

## Relaciones conceptuales

```text
Cliente ──< Oferta
Cliente ──< Simulación
Cliente ──< Solicitud >── Canal
Solicitud ──< Autorización
Solicitud ──< Documento
Solicitud ──< Evaluación >── Política/Regla
Evaluación ──> Decisión
Decisión ──< Excepción
Decisión ──> Contrato ──> Desembolso
Solicitud ──< Notificación
Solicitud ──< Evento de auditoría
Evaluación ──< Fuente de información
Documento ──< Extracción IA / evidencia
```

## Estados conceptuales de solicitud

Los estados expuestos por Canales son:
- Borrador
- Información pendiente
- En evaluación
- Requiere documento
- Requiere validación
- Aprobado
- No aprobado
- Pendiente de aceptación
- Listo para desembolso
- Desembolsado
- Cancelado

Los nombres internos pueden ser más detallados, pero el cliente debe recibir mensajes simples.

## Eventos conceptuales

- Solicitud iniciada
- Datos confirmados
- Documento cargado
- Evaluación solicitada
- Decisión emitida
- Excepción aprobada
- Contrato aceptado
- Crédito desembolsado

## Decisiones pendientes

Antes del modelo lógico deben validarse:
1. cardinalidades;
2. ciclo de vida exacto de oferta/simulación/solicitud;
3. relación entre cliente y datos históricos/snapshots;
4. granularidad de evaluación y reproceso;
5. entidad formal de política y versión;
6. estructura de fuentes externas;
7. documento vs. evidencia de extracción;
8. contrato y desembolso como entidades propias o referencias a sistemas maestros;
9. modelo de usuario/roles;
10. granularidad de auditoría.

## Regla fundamental

El modelo debe permitir reconstruir una decisión histórica sin depender de que los datos actuales del cliente permanezcan iguales.
