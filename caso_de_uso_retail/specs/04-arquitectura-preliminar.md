# 4. Arquitectura preliminar SDD

## 4.1 Principio arquitectónico

La solución debe separar la **consulta de disponibilidad** de la **confirmación transaccional de reserva**. Las entrevistas indican que el catálogo puede tolerar cierta eventualidad, mientras que la reserva requiere mayor consistencia.

Esta separación es una conclusión arquitectónica derivada de las entrevistas, no una decisión tecnológica definitiva.

## 4.2 Contexto lógico propuesto

```text
Canales / actores
    |
    +--> Consulta de disponibilidad
    |        |
    |        +--> Vista operativa de disponibilidad
    |
    +--> Checkout
             |
             +--> Reserva transaccional
             +--> Pago
             +--> Pedido
             +--> Promesa
                      |
                      +--> Asignación de ubicación
                      +--> Tarea de preparación
                      +--> Excepciones / reasignación

Fuentes actuales
    |
    +--> Ventas POS
    +--> Inventario
    +--> Recepciones
    +--> Transferencias
    +--> Devoluciones
    +--> Conteos / ajustes
    |
    +--> Eventos de inventario
              |
              +--> actualización de vista operativa
              +--> auditoría / secuencia
```

## 4.3 Capacidades lógicas

### C1 — Disponibilidad

Responsable de exponer una interpretación operativa de disponibilidad por SKU y ubicación/modalidad, incluyendo indicador de frescura.

### C2 — Reserva

Responsable de asignación temporal, expiración, idempotencia, concurrencia, confirmación y liberación.

### C3 — Pedido y pago

Responsable de correlacionar checkout, pago, pedido y reserva. La coordinación distribuida y compensaciones siguen abiertas.

### C4 — Promesa y asignación

Responsable de seleccionar ubicación candidata y calcular/recalcular promesa usando factores de negocio.

### C5 — Preparación

Responsable de generar tareas, cola priorizada, validación, faltantes y daños.

### C6 — Inventario y eventos

Responsable de recibir movimientos en tiempo real o lote, mantener saldos y secuencia de eventos y conservar auditoría.

### C7 — Excepciones

Responsable de reasignaciones, cambios de promesa y alternativas al cliente.

### C8 — Analítica e IA

Responsable de indicadores y recomendaciones. No debe ser autoridad transaccional sobre stock.

## 4.4 Consistencia

### Consulta

Se acepta consistencia eventual para información de catálogo/disponibilidad, siempre que la interfaz identifique frescura y no presente una vista degradada como certeza.

### Reserva

Debe tener mayor consistencia y control de concurrencia. El mecanismo técnico concreto queda abierto.

### Eventos

Debe soportarse idempotencia y detección de duplicados/eventos fuera de orden.

## 4.5 Transacciones distribuidas

La entrevista identifica la tensión entre reserva, pago y pedido. No se debe imponer todavía una tecnología o patrón específico. Antes del desarrollo se debe decidir:

- qué operación es autoridad para cada estado;
- qué sucede si pago aprueba y reserva falla;
- qué sucede si reserva confirma y pago falla;
- qué sucede si el pedido se crea y la integración posterior se retrasa;
- cómo se ejecutan las compensaciones;
- qué garantías necesita cada transición.

## 4.6 Modelo de inventario

El modelo conceptual debe soportar al menos los estados mencionados: físico, disponible, reservado, comprometido, bloqueado, dañado, en tránsito y pendiente de recepción.

Debe contemplar que algunos productos son serializados y otros se manejan por lote. La estrategia exacta de persistencia y el momento en que una unidad pasa a nivel serializado son preguntas abiertas.

## 4.7 Integración gradual

No se recomienda plantear reemplazo total de sistemas. La solución debe integrarse con las fuentes actuales y permitir evolución por etapas.

Los sistemas que son autoridad para cada dato aún no están identificados. Esta definición es un prerrequisito para diseño detallado.

## 4.8 IA

Se identifican tres capacidades candidatas:

- anticipación de demanda;
- sugerencia de redistribución;
- detección de anomalías.

Son capacidades de recomendación. No se autoriza a la IA, a partir de las entrevistas, a crear reservas, modificar saldos o ejecutar descuentos de stock.

## 4.9 Decisiones que no deben cerrarse todavía

- Tecnología de mensajería/eventos.
- Motor de base de datos.
- Estrategia de caché.
- Patrón exacto de consistencia.
- Patrón de transacciones distribuidas.
- Algoritmo de asignación.
- Algoritmo de promesa.
- Modelo de datos físico.
- Estrategia de particionado.
- Mecanismo de autenticación/autorización.

Estas decisiones requieren información técnica que no está en las entrevistas.