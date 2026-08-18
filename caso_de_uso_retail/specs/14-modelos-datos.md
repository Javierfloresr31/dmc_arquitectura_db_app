# 14. Modelos de datos — hoja de ruta

## 1. Modelo conceptual

### Objetivo
Representar el dominio de Stock Único sin detalles tecnológicos.

### Conceptos candidatos derivados de entrevistas

- Producto
- SKU
- Ubicación
- Inventario
- Movimiento de inventario
- Reserva
- Carrito
- Pedido
- Línea de pedido
- Pago
- Promesa
- Tarea de preparación
- Transferencia
- Evento
- Excepción

### Relaciones que deberán validarse

- SKU ↔ ubicación ↔ inventario.
- Inventario ↔ movimientos.
- Pedido ↔ líneas ↔ SKU.
- Pedido ↔ reserva.
- Pedido ↔ pago.
- Pedido ↔ promesa.
- Pedido ↔ tareas de preparación.
- Transferencia ↔ origen/destino.
- Evento ↔ movimiento/operación.

Las cardinalidades y atributos definitivos no deben inventarse; se definirán a partir de las preguntas y reglas cerradas.

## 2. Modelo lógico

Se construirá cuando el modelo conceptual sea validado. Incluirá:

- entidades y atributos confirmados;
- claves;
- relaciones;
- cardinalidades;
- dominios;
- estados;
- restricciones de integridad;
- trazabilidad de eventos;
- representación de serializados y lotes.

## 3. Modelo físico

Se construirá después del modelo lógico y del cierre de requisitos de rendimiento. Incluirá:

- tablas;
- PK/FK;
- índices;
- restricciones;
- estructuras para auditoría/eventos;
- estrategia de particionado si corresponde;
- retención;
- estructuras necesarias para concurrencia e idempotencia.

No se selecciona todavía motor de base de datos ni tecnología de persistencia.

## Criterio de validación

Cada elemento del modelo debe poder trazarse a una historia, RF/RNF, regla, proceso o pregunta resuelta. Si una entidad no tiene trazabilidad, debe eliminarse o convertirse en pregunta.
