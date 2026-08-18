# Stock Único — NovaRetail

**Fuente:** entrevistas del caso `NovaRetail: Stock Único`  
**Método:** PROMPT ATLAS v2.0  
**Regla:** contenido sustentado exclusivamente en las entrevistas; los vacíos se mantienen como pendientes.

# Modelo conceptual

Entidades: Producto, SKU, Ubicación, Saldo de inventario, Movimiento, Reserva, Carrito, Pedido, Pago, Promesa, Tarea de preparación y Transferencia.

```mermaid
erDiagram
    PRODUCTO ||--o{ SKU : contiene
    SKU ||--o{ SALDO_INVENTARIO : tiene
    UBICACION ||--o{ SALDO_INVENTARIO : mantiene
    SKU ||--o{ MOVIMIENTO : afecta
    SKU ||--o{ RESERVA : reserva
    PEDIDO ||--o{ RESERVA : utiliza
    PEDIDO ||--o{ PAGO : relaciona
    PEDIDO ||--o{ PROMESA : tiene
    PEDIDO ||--o{ TAREA_PREPARACION : genera
    UBICACION ||--o{ TAREA_PREPARACION : ejecuta
    UBICACION ||--o{ TRANSFERENCIA : participa
```

# Modelo lógico

## Atributos conocidos

**SALDO_INVENTARIO:** SKU, ubicación, stock físico, disponible, reservado, comprometido, bloqueado, dañado, tránsito, pendiente de recepción.

**MOVIMIENTO:** evento de origen, documento relacionado, usuario/sistema, momento, cantidad anterior, cantidad nueva, razón, secuencia.

**RESERVA:** origen, cantidad, ubicación, creación, expiración, estado.

**TRANSFERENCIA:** origen, destino, solicitadas, despachadas, recibidas, diferencias.

**TAREA_PREPARACION:** cola/prioridad, ubicación interna cuando exista, tiempo objetivo, SKU/cantidad, faltante/daño, estado listo.

**PEDIDO/PAGO/PROMESA/CARRITO:** son objetos identificados, pero sus atributos completos no están definidos.

```mermaid
erDiagram
    SKU {
        dato identificacion
        dato serializacion
        dato lote
    }
    UBICACION {
        dato identificacion
    }
    SALDO_INVENTARIO {
        dato sku
        dato ubicacion
        dato stock_fisico
        dato disponible
        dato reservado
        dato comprometido
        dato bloqueado
        dato danado
        dato transito
        dato pendiente_recepcion
    }
    MOVIMIENTO {
        dato evento_origen
        dato documento
        dato usuario_sistema
        dato momento
        dato cantidad_anterior
        dato cantidad_nueva
        dato razon
        dato secuencia
    }
    RESERVA {
        dato origen
        dato cantidad
        dato ubicacion
        dato fecha_creacion
        dato expiracion
        dato estado
    }
    TRANSFERENCIA {
        dato origen
        dato destino
        dato solicitadas
        dato despachadas
        dato recibidas
        dato diferencias
    }
    SKU ||--o{ SALDO_INVENTARIO : posee
    UBICACION ||--o{ SALDO_INVENTARIO : mantiene
    SKU ||--o{ MOVIMIENTO : afecta
    SKU ||--o{ RESERVA : asigna
    UBICACION ||--o{ RESERVA : contiene
    UBICACION ||--o{ TRANSFERENCIA : participa
```

# Modelo físico

**Estado: PENDIENTE DE DEFINICIÓN.**

Las entrevistas no proporcionan motor de BD, tablas físicas, tipos, PK/FK, índices, particionamiento ni estrategia de persistencia. Por tanto, no se inventa un modelo físico implementable.

Los objetos que eventualmente deberán persistirse son los objetos de negocio identificados, sujetos a diseño posterior.

```mermaid
erDiagram
    SKU ||--o{ SALDO_INVENTARIO : posee
    UBICACION ||--o{ SALDO_INVENTARIO : mantiene
    SKU ||--o{ MOVIMIENTO : afecta
    SKU ||--o{ RESERVA : asigna
    PEDIDO ||--o{ RESERVA : utiliza
    PEDIDO ||--o{ PAGO : relaciona
    PEDIDO ||--o{ PROMESA : tiene
    PEDIDO ||--o{ TAREA_PREPARACION : genera
    UBICACION ||--o{ TAREA_PREPARACION : ejecuta
    UBICACION ||--o{ TRANSFERENCIA : participa
```

El diagrama físico es solo un inventario de objetos candidatos, no un esquema técnico aprobado.
