# Stock Único — NovaRetail

**Fuente:** entrevistas del caso `NovaRetail: Stock Único`  
**Método:** PROMPT ATLAS v2.0  
**Regla:** contenido sustentado exclusivamente en las entrevistas; los vacíos se mantienen como pendientes.

## Sistemas/actores de integración identificados

| ID | Sistema/actor | Interacción | Tiempo | Estado |
|---|---|---|---|---|
| INT-01 | Sistema de pagos | Pago/aprobación y correlación con pedido/reserva | No definido | CONFIRMADO / contrato pendiente |
| INT-02 | Sistemas de inventario | Movimientos y datos de inventario | Tiempo real o lote | CONFIRMADO |
| INT-03 | Sistemas antiguos | Envío de movimientos por lotes | Lote | CONFIRMADO |
| INT-04 | Caja | Ventas registradas | Tiempo real/lote no uniformemente definido | CONFIRMADO |
| INT-05 | E-commerce | Consultas y pedidos web | Operativo | CONFIRMADO |
| INT-06 | Tienda/CD | Aceptación y preparación | Operativo | CONFIRMADO |

## Datos conocidos

- Movimientos de inventario.
- Información de disponibilidad.
- Intento/pago/pedido/reserva.
- Datos de preparación.
- Transferencias.

## Vacíos de integración

No están definidos:
- contratos API;
- endpoints;
- payloads;
- protocolos;
- autenticación;
- reintentos;
- timeouts;
- DLQ;
- versionado;
- contratos de eventos;
- estrategia exacta de idempotencia en cada integración;
- autoridad por sistema.

Todos quedan en discrepancias.
