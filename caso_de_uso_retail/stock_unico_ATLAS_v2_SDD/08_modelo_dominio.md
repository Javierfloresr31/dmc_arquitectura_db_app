# Stock Único — NovaRetail

**Fuente:** entrevistas del caso `NovaRetail: Stock Único`  
**Método:** PROMPT ATLAS v2.0  
**Regla:** contenido sustentado exclusivamente en las entrevistas; los vacíos se mantienen como pendientes.

## Entidades de dominio identificadas

- Producto
- SKU
- Ubicación
- Saldo de inventario
- Movimiento
- Reserva
- Carrito
- Pedido
- Pago
- Promesa
- Tarea de preparación
- Transferencia

## Relaciones derivadas directamente de las entrevistas

- SKU ↔ ubicación ↔ saldo de inventario.
- Movimiento afecta el inventario.
- Reserva asigna unidades a una intención/pedido y ubicación.
- Pedido se relaciona con pago, reserva, promesa y preparación.
- Tarea de preparación se ejecuta en una ubicación.
- Transferencia relaciona ubicación origen/destino.

## Estados explícitamente mencionados

### Inventario
físico, disponible, reservado, comprometido, bloqueado, dañado, en tránsito, pendiente de recepción.

### Reserva
creada, confirmada, liberada, vencida y trasladada aparecen como estados/operaciones mencionados; las transiciones exactas están pendientes.

### Pedido/preparación
confirmado, preparación iniciada, listo y entregado aparecen en eventos/flujo; el catálogo completo de estados no está definido.

## Modelo conceptual Mermaid

```mermaid
erDiagram
    PRODUCTO ||--o{ SKU : contiene
    SKU ||--o{ SALDO_INVENTARIO : tiene
    UBICACION ||--o{ SALDO_INVENTARIO : mantiene
    SKU ||--o{ MOVIMIENTO : afecta
    UBICACION ||--o{ MOVIMIENTO : registra
    SKU ||--o{ RESERVA : asigna
    UBICACION ||--o{ RESERVA : contiene
    PEDIDO ||--o{ RESERVA : utiliza
    PEDIDO ||--o{ PAGO : relaciona
    PEDIDO ||--o{ PROMESA : tiene
    PEDIDO ||--o{ TAREA_PREPARACION : genera
    UBICACION ||--o{ TAREA_PREPARACION : ejecuta
    UBICACION ||--o{ TRANSFERENCIA : origen
    UBICACION ||--o{ TRANSFERENCIA : destino
    CARRITO ||--o{ PEDIDO : origina
```

**Nota:** las cardinalidades son una representación estructural preliminar y requieren validación; las entrevistas no definen un modelo cardinal completo.
