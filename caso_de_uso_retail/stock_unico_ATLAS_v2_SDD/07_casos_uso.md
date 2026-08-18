# Stock Único — NovaRetail

**Fuente:** entrevistas del caso `NovaRetail: Stock Único`  
**Método:** PROMPT ATLAS v2.0  
**Regla:** contenido sustentado exclusivamente en las entrevistas; los vacíos se mantienen como pendientes.

## CU-001 — Consultar disponibilidad y promesa

**Actor:** Cliente  
**Precondición:** existe producto/cantidad a consultar.  
**Flujo:** consultar → determinar ubicación/modalidad → informar disponibilidad/promesa.  
**Alternativa:** si la fuente está retrasada, aplicar operación degradada.  
**Pendiente:** fórmula exacta y degradación.

## CU-002 — Crear y gestionar reserva

**Actor:** Cliente/checkout.  
**Flujo:** iniciar pago → verificar disponibilidad → crear reserva → controlar concurrencia/idempotencia → confirmar/liberar/vencer/trasladar.  
**Pendiente:** TTL y transiciones.

## CU-003 — Correlacionar pago, pedido y reserva

**Actores:** Cliente, sistema de pagos, Stock Único.  
**Flujo:** intento → pago → correlación → pedido → trazabilidad.  
**Pendiente:** compensación ante fallos distribuidos.

## CU-004 — Asignar ubicación

**Actor:** operación de fulfillment.  
**Criterios:** disponibilidad, distancia, capacidad, horario, costo, prioridad, fecha prometida, restricciones.  
**Pendiente:** algoritmo y pesos.

## CU-005 — Preparar pedido

**Actor:** Preparador.  
**Flujo:** recibir tarea → cola priorizada → ubicar → escanear → validar → embalar → marcar listo.

## CU-006 — Gestionar faltante

**Actor:** Preparador/operación.  
**Flujo:** faltante → excepción → buscar otra ubicación → recalcular promesa → ofrecer alternativas.

## CU-007 — Retiro en tienda

**Actor:** Cliente/Preparador.  
**Flujo:** seleccionar tienda → promesa → preparación → código → validar retirante → entregar → liberar por no recojo.

## CU-008 — Registrar movimiento

**Actores:** Cajero/sistemas de inventario.  
**Movimientos:** venta, recepción, transferencia, devolución, anulación, conteo, ajuste, daño, robo, cambio de estado.

## CU-009 — Conteo y ajuste

**Actor:** usuario autorizado/supervisor.  
**Flujo:** detectar diferencia → contar → ajustar → conservar motivo/usuario/evidencia → auditar.  
**Pendiente:** roles de autorización.

## CU-010 — Gestionar transferencia

**Actor:** operador logístico/inventarios.  
**Flujo:** origen/destino → solicitar → despachar → tránsito → recibir → registrar diferencias.

## CU-011 — Operar en modo degradado

**Actor:** Cliente/sistema.  
**Condición:** fuente/integración retrasada.  
**Resultado:** continuar de forma segura sin datos engañosos.  
**Pendiente:** estados y límites.

## CU-012 — Consultar indicadores

**Actor:** negocio/operación.  
**Resultado:** visualizar/medir indicadores definidos por las entrevistas.  
**Pendiente:** fórmulas y fuentes oficiales.
