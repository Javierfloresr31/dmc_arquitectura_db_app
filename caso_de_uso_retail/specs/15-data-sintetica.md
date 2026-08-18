# 15. Data sintética

## Objetivo

Generar datos ficticios, reproducibles y controlados para desarrollar y probar Stock Único sin utilizar información real de clientes o inventario.

## Principios

- Todo dato debe estar identificado como sintético.
- Debe existir una semilla reproducible.
- Debe poder generarse un dataset pequeño para desarrollo y uno mayor para carga.
- Los datos deben respetar las reglas cerradas del dominio.
- Los escenarios negativos deben ser intencionales y trazables.

## Dominios mínimos

1. Catálogo/SKU.
2. Ubicaciones.
3. Inventario.
4. Movimientos.
5. Reservas.
6. Carritos.
7. Pedidos y líneas.
8. Pagos.
9. Promesas.
10. Tareas de preparación.
11. Transferencias.
12. Eventos.
13. Excepciones.

## Escenarios de prueba

### Escenario A — disponibilidad normal

Inventario consistente y fuente actualizada.

### Escenario B — sobreventa potencial

Varias solicitudes concurrentes sobre la misma disponibilidad.

### Escenario C — reintento

Mismo checkout enviado más de una vez.

### Escenario D — reserva vencida

Reserva sin confirmación dentro de la duración configurada.

### Escenario E — faltante

La tienda no encuentra la unidad reservada y se inicia reasignación.

### Escenario F — evento duplicado

El mismo movimiento llega nuevamente.

### Escenario G — evento fuera de orden

Eventos relacionados llegan en secuencia distinta.

### Escenario H — integración retrasada

La vista de inventario queda desactualizada y se activa el modo degradado.

### Escenario I — multi-SKU

Pedido con líneas que pueden requerir una o varias ubicaciones.

### Escenario J — serializado/lote

Productos con diferentes formas de control de unidad.

## Entregables

- generador reproducible;
- archivos de carga;
- dataset de desarrollo;
- dataset de pruebas;
- dataset de carga/performance;
- documentación de escenarios;
- validación de integridad contra el modelo físico.

## Dependencias

La data sintética detallada depende de cerrar el modelo conceptual, modelo lógico y reglas de negocio. No se deben inventar atributos definitivos antes de esa validación.
