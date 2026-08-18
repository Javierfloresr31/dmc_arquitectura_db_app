# Stock Único — NovaRetail

**Fuente:** entrevistas del caso `NovaRetail: Stock Único`  
**Método:** PROMPT ATLAS v2.0  
**Regla:** contenido sustentado exclusivamente en las entrevistas; los vacíos se mantienen como pendientes.

## Actores

| ID | Actor | Interacción/responsabilidad mencionada | Estado |
|---|---|---|---|
| ACT-01 | Cliente | Consulta disponibilidad, compra, retiro | CONFIRMADO |
| ACT-02 | Cajero | Origina ventas en caja | CONFIRMADO |
| ACT-03 | Preparador de tienda | Prepara, valida, escanea, reporta faltantes/daños | CONFIRMADO |
| ACT-04 | Supervisor | Participa en conteos/ajustes autorizados | CONFIRMADO |
| ACT-05 | Operador logístico | Participa en operación logística/transferencias | CONFIRMADO |
| ACT-06 | Sistema de pagos | Participa en pago | CONFIRMADO |
| ACT-07 | Sistemas de inventario | Proveen movimientos/datos de inventario | CONFIRMADO |

## Brechas

| ID | Brecha | Evidencia | Impacto | Fuente | Estado |
|---|---|---|---|---|---|
| BR-001 | No existe una única interpretación de disponibilidad | Cada sistema calcula diferente | Cancelaciones/sobreventa | CEO P2 | CONFIRMADO |
| BR-002 | La réplica web queda atrasada frente a campañas | Stock cambia más rápido que réplica | Datos engañosos | E-commerce P1 | CONFIRMADO |
| BR-003 | Reservas pueden duplicarse | Problema actual de concurrencia | Doble asignación | Supply Chain P5 | CONFIRMADO |
| BR-004 | Diferencias entre stock físico y sistema | Se requiere investigación y ajuste con historial | Pérdida de causa/auditoría | Supply Chain P6 | CONFIRMADO |
| BR-005 | La promesa puede incumplirse por faltante en preparación | Hoy muchas veces se cancela | Mala experiencia | E-commerce P5 | CONFIRMADO |
| BR-006 | Integraciones pueden retrasarse | Se requiere operación segura/degradada | Riesgo de sobreventa | CEO P10 / E-commerce P9 | CONFIRMADO |
| BR-007 | Falta trazabilidad entre pago, pedido y reserva | Reintentos/respuestas tardías | Pedido o reserva sin trazabilidad | E-commerce P8 | CONFIRMADO |
| BR-008 | Inventario de tiendas no se aprovecha suficientemente para canal digital | CEO quiere usar unidades disponibles cuando convenga | Oportunidad omnicanal | CEO P3 | CONFIRMADO |

## Objetos de negocio

Producto, SKU, ubicación, saldo de inventario, movimiento, reserva, carrito, pedido, pago, promesa, tarea de preparación y transferencia.

## Eventos mencionados

Stock recibido, venta registrada, reserva creada, reserva vencida, pago aprobado, pedido confirmado, preparación iniciada, faltante reportado y pedido entregado.

## Restricciones explícitas

Concurrencia, idempotencia, alto volumen, baja latencia, auditoría, operación degradada y protección de datos del cliente.

## Alcance

Primera etapa: tecnología y pequeños electrodomésticos en Lima. [CONFIRMADO]

## Fuente

La tabla de evidencias iniciales consolida actores, objetos, eventos, restricciones e incertidumbres de las entrevistas.
