# Especificación SDD — Historias de Usuario
## Stock Único — NovaRetail

> **Fuente exclusiva:** entrevistas del caso `NovaRetail: Stock Único`.
> No se agregan historias basadas en conocimiento externo. Las decisiones no resueltas se mantienen como vacíos o discrepancias.

## HU-01 — Consultar disponibilidad confiable

**Como** cliente del canal digital  
**Quiero** conocer la disponibilidad de un producto antes de pagar, incluyendo modalidad, ubicación y fecha prometida  
**Para** tomar una decisión de compra con una promesa confiable.

**Trazabilidad:** CEO, preguntas 3 y 4; E-commerce, preguntas 3 y 9.  
**Fuente:** líneas 32–44, 124–126, 148–150.

---

## HU-02 — Reservar inventario durante el checkout

**Como** cliente que inicia o confirma el pago  
**Quiero** que las unidades necesarias sean reservadas temporalmente  
**Para** evitar que otro canal las venda mientras concluyo la compra.

**Trazabilidad:** Supply Chain, preguntas 2–3; E-commerce, pregunta 2.  
**Fuente:** líneas 71–87, 120–122.

---

## HU-03 — Evitar reservas duplicadas ante reintentos

**Como** sistema de checkout  
**Quiero** que las operaciones de reserva sean idempotentes y tengan control de concurrencia  
**Para** evitar que un mismo intento genere reservas duplicadas.

**Trazabilidad:** Supply Chain, pregunta 5; E-commerce, pregunta 9.  
**Fuente:** líneas 85–87, 146–150.

---

## HU-04 — Confirmar pedido con trazabilidad de pago y reserva

**Como** sistema de comercio electrónico  
**Quiero** correlacionar intento de pago, pago, pedido y reserva  
**Para** evitar que un pago aprobado quede sin pedido o que una reserva confirmada quede sin trazabilidad.

**Trazabilidad:** E-commerce, pregunta 8.  
**Fuente:** líneas 144–146.

---

## HU-05 — Asignar una ubicación para preparar el pedido

**Como** operación de fulfillment  
**Quiero** asignar una ubicación candidata considerando disponibilidad y restricciones operativas  
**Para** cumplir la modalidad y fecha prometidas.

**Trazabilidad:** Supply Chain, pregunta 7; E-commerce, pregunta 3.  
**Fuente:** líneas 93–95, 124–130.

---

## HU-06 — Preparar pedidos desde tienda o centro de distribución

**Como** preparador de tienda  
**Quiero** recibir una cola priorizada de tareas con información suficiente para preparar el pedido  
**Para** recoger, validar, embalar y marcar el pedido como listo.

**Trazabilidad:** E-commerce, preguntas 4 y 7.  
**Fuente:** líneas 128–130, 140–142.

---

## HU-07 — Gestionar faltantes durante la preparación

**Como** operador de tienda  
**Quiero** registrar un faltante y permitir la búsqueda de otra ubicación  
**Para** recalcular la promesa y evitar una cancelación automática.

**Trazabilidad:** E-commerce, pregunta 5; CEO, pregunta 6.  
**Fuente:** líneas 132–134, 42–44.

---

## HU-08 — Gestionar retiro en tienda

**Como** cliente  
**Quiero** elegir una tienda con disponibilidad, recibir una promesa y un código cuando el pedido esté listo  
**Para** retirar mi compra en la tienda seleccionada.

**Trazabilidad:** E-commerce, pregunta 6.  
**Fuente:** líneas 136–138.

---

## HU-09 — Liberar reservas vencidas o no retiradas

**Como** operación de inventarios  
**Quiero** que una reserva pueda vencer y ser liberada según reglas controladas  
**Para** evitar bloquear inventario indefinidamente.

**Trazabilidad:** Supply Chain, pregunta 4; CEO, pregunta 7; E-commerce, pregunta 6.  
**Fuente:** líneas 81–87, 46–48, 136–138.

> **Vacío:** la duración exacta de la reserva y del plazo de recojo no está definida.

---

## HU-10 — Registrar movimientos y ajustes auditables

**Como** responsable de inventarios  
**Quiero** conservar el historial de cada cambio de inventario y de los ajustes autorizados  
**Para** investigar diferencias y conocer su causa.

**Trazabilidad:** Supply Chain, preguntas 6 y 10.  
**Fuente:** líneas 89–91, 105–107.

---

## HU-11 — Procesar movimientos provenientes de múltiples sistemas

**Como** sistema Stock Único  
**Quiero** recibir movimientos de ventas, recepciones, transferencias, devoluciones, anulaciones, conteos, ajustes, daños y robos  
**Para** mantener una representación consolidada del inventario.

**Trazabilidad:** Supply Chain, pregunta 2; CEO, pregunta 10.  
**Fuente:** líneas 73–75, 58–60.

> **Vacío:** no se define el contrato técnico de cada integración ni cuál sistema es autoridad para cada operación.

---

## HU-12 — Gestionar transferencias y stock en tránsito

**Como** responsable de inventarios  
**Quiero** registrar origen, destino, unidades solicitadas, despachadas, recibidas y diferencias  
**Para** controlar las transferencias y evitar considerar tránsito como disponibilidad normal.

**Trazabilidad:** Supply Chain, pregunta 8.  
**Fuente:** líneas 97–99.

---

## HU-13 — Operar con información degradada de forma controlada

**Como** cliente del canal digital  
**Quiero** que el sitio se comporte de manera controlada cuando una integración o dato esté retrasado  
**Para** no recibir información engañosa.

**Trazabilidad:** CEO, preguntas 9–10; Supply Chain, pregunta 9; E-commerce, pregunta 9.  
**Fuente:** líneas 54–60, 101–103, 148–150.

---

## HU-14 — Proteger los datos del cliente en preparación

**Como** preparador de tienda  
**Quiero** visualizar únicamente los datos personales necesarios para preparar el pedido  
**Para** ejecutar la tarea sin exposición innecesaria de información del cliente.

**Trazabilidad:** E-commerce, pregunta 7.  
**Fuente:** líneas 140–142.

---

## HU-15 — Obtener indicadores operativos

**Como** responsable del negocio  
**Quiero** medir cancelaciones, exactitud, promesas cumplidas, ventas omnicanal, rotación, quiebres, costo de preparación y reservas vencidas  
**Para** evaluar el resultado de Stock Único.

**Trazabilidad:** CEO, pregunta 4; E-commerce, pregunta 10.  
**Fuente:** líneas 34–36, 152–154.

---

## HU-16 — Detectar anomalías y apoyar decisiones con IA

**Como** responsable de inventarios  
**Quiero** utilizar IA para anticipar demanda, sugerir redistribución y detectar comportamientos anómalos  
**Para** apoyar decisiones operativas.

**Trazabilidad:** CEO, pregunta 8.  
**Fuente:** líneas 50–52.

> **Restricción explícita:** la predicción no puede crear existencias; la reserva y el descuento de stock deben ser transacciones controladas.
