# 05. Actores

## Actores identificados

| Actor | Interacción identificada | Definición pendiente |
|---|---|---|
| Cliente | Consulta, checkout, pago, entrega/retiro | Identidad y datos mínimos |
| Cajero | Genera movimientos de venta | Permisos e integración |
| Preparador de tienda | Recibe y ejecuta tareas | Permisos y datos visibles |
| Supervisor | Autoriza/gestiona excepciones y ajustes | Matriz de permisos |
| Operador logístico | Participa en despacho/entrega | Interfaz y responsabilidades |
| Sistema de pagos | Autoridad del pago | Sistema concreto y contrato |
| Sistemas de inventario | Proveen movimientos/saldos | Autoridad por dato |

## Actores organizacionales que pueden emerger

No se deben agregar como actores del sistema hasta que sean confirmados. Las entrevistas sí evidencian necesidades de operación, supply chain, e-commerce y dirección ejecutiva.

## Principios de acceso

- El preparador no debe visualizar datos personales del cliente que no necesite para preparar el pedido.
- Conteos y ajustes deben estar autorizados.
- Las operaciones sensibles deben conservar evidencia.

## Preguntas de permisos

1. ¿Qué permisos tiene cada rol?
2. ¿Quién puede crear, liberar, trasladar o cancelar una reserva?
3. ¿Quién puede modificar inventario?
4. ¿Quién autoriza ajustes?
5. ¿Quién puede modificar una promesa?
6. ¿Quién puede aprobar una alternativa al cliente?
