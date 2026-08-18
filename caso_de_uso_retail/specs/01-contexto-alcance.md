# 1. Contexto y alcance

## 1.1 Problema

NovaRetail presenta diferencias entre el stock mostrado en línea y la disponibilidad real. Las entrevistas identifican fuentes distintas por canal, reservas no siempre sincronizadas y ajustes pendientes en tiendas. El efecto reportado incluye cancelaciones, sobreventa, traslados urgentes y deterioro de la experiencia del cliente.

El problema de negocio central no es solamente almacenar existencias: es definir una interpretación operativa coherente de qué significa **disponible para vender**.

## 1.2 Objetivo

Construir la especificación inicial de una solución denominada **Stock Único** que permita:

- consolidar la consulta de disponibilidad;
- administrar reservas temporales y trazables;
- soportar promesas de entrega;
- soportar despacho a domicilio, retiro en tienda y preparación desde tienda;
- integrar los sistemas actuales de forma gradual;
- procesar eventos de inventario casi en tiempo real;
- mantener operación segura cuando una integración se retrase.

## 1.3 Alcance inicial declarado por negocio

La CEO plantea iniciar con **tecnología y pequeños electrodomésticos en Lima**. La expansión a otras categorías y ciudades se plantea como una etapa posterior.

No se define en las entrevistas una lista concreta de tiendas, SKU, sistemas o proveedores que participarán en el piloto.

## 1.4 Datos declarados en las entrevistas

| Dato | Evidencia | Estado |
|---|---|---|
| 78 tiendas físicas | Contexto | Declarado; confirmar |
| 1 canal e-commerce | Contexto | Declarado; confirmar |
| 2 centros de distribución | Contexto | Declarado; confirmar |
| ~65.000 SKU | Contexto | Declarado; confirmar |
| >12.000 pedidos/hora en campañas | Contexto | Declarado; confirmar |
| Miles de consultas por segundo en campañas | E-commerce | Declarado; cuantificación exacta pendiente |

## 1.5 Actores identificados

- Cliente.
- Cajero.
- Preparador de tienda.
- Supervisor.
- Operador logístico.
- Sistema de pagos.
- Sistemas de inventario.

Los roles administrativos adicionales y sus permisos no fueron definidos.

## 1.6 Objetos de negocio identificados

Producto, SKU, ubicación, saldo de inventario, movimiento, reserva, carrito, pedido, pago, promesa, tarea de preparación y transferencia.

## 1.7 Estados o conceptos de inventario mencionados

Stock físico, disponible, reservado, comprometido, bloqueado, dañado, en tránsito y pendiente de recepción.

También se menciona la coexistencia de productos serializados y productos gestionados por lote.

## 1.8 Eventos mencionados

- Stock recibido.
- Venta registrada.
- Reserva creada.
- Reserva vencida.
- Pago aprobado.
- Pedido confirmado.
- Preparación iniciada.
- Faltante reportado.
- Pedido entregado.

La lista es una base de discovery; no implica que estos sean todos los eventos del sistema final.

## 1.9 Modalidades de atención

- Despacho a domicilio.
- Retiro en tienda.
- Preparación desde tienda.

## 1.10 Límites explícitos

No se plantea reemplazar todos los sistemas actuales en una sola etapa. La solución debe integrarse y evolucionar gradualmente.

La IA puede recomendar demanda, redistribución o anomalías, pero no debe inventar existencias ni ejecutar por sí misma las transacciones de reserva o descuento de stock.