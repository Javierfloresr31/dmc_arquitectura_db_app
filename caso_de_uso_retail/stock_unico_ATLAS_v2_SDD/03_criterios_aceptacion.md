# Stock Único — NovaRetail

**Fuente:** entrevistas del caso `NovaRetail: Stock Único`  
**Método:** PROMPT ATLAS v2.0  
**Regla:** contenido sustentado exclusivamente en las entrevistas; los vacíos se mantienen como pendientes.

## CA-HU-001

- Dado un producto y modalidad, cuando se consulte disponibilidad, entonces debe poder informarse disponibilidad y promesa según la información disponible.
- La respuesta no debe mostrar datos engañosos cuando la fuente esté retrasada.

**Fuente:** CEO P6/P10; E-commerce P1/P9.

## CA-HU-002

- Una reserva debe contener origen, cantidad, ubicación, creación, expiración y estado.
- Debe poder confirmarse, liberarse, vencer o trasladarse según reglas controladas.
- No debe reservarse únicamente por visitar la página.

**Fuente:** Supply Chain P4; E-commerce P2.

**Pendiente:** duración y transición exactas.

## CA-HU-003

- Un reintento no debe generar otra reserva para la misma operación.
- Debe existir control de concurrencia.

**Fuente:** Supply Chain P5.

## CA-HU-004

- Intento, pago, pedido y reserva deben poder correlacionarse.
- Un pago aprobado no puede terminar sin pedido.
- Una reserva confirmada debe conservar trazabilidad.

**Fuente:** E-commerce P8.

## CA-HU-005

La asignación debe considerar disponibilidad, distancia, capacidad, horario, costo, prioridad, fecha prometida y restricciones del producto.

**Fuente:** Supply Chain P7.

**Pendiente:** algoritmo/pesos/desempates.

## CA-HU-006

- Debe generarse tarea tras confirmación/asignación.
- El preparador debe poder recoger, validar SKU/cantidad, embalar y marcar listo.
- Debe poder reportar faltantes/daños y escanear SKU.

**Fuente:** E-commerce P4/P7.

## CA-HU-007

Si existe faltante, debe registrarse excepción, buscar otra ubicación y recalcular promesa. Deben existir las alternativas mencionadas: nueva fecha, cambio de tienda, sustituto o devolución.

**Fuente:** E-commerce P5.

## CA-HU-008

- Selección de tienda con disponibilidad.
- Promesa.
- Código cuando esté listo.
- Validación de retirante.
- Registro de entrega.
- Liberación si no recoge dentro del plazo.

**Fuente:** E-commerce P6.

**Pendiente:** plazo de recojo.

## CA-HU-009

Cada ajuste debe conservar motivo, usuario y evidencia; cada cambio de cantidad debe conservar evento, documento, momento, cantidades anterior/nueva y razón.

**Fuente:** Supply Chain P6/P10.

## CA-HU-010

Una transferencia debe conservar origen, destino, solicitadas, despachadas, recibidas y diferencias. El tránsito no debe aparecer como disponible hasta recepción bajo el modelo actual.

**Fuente:** Supply Chain P8.

## CA-HU-011

Ventas/reservas requieren latencia en segundos; campañas contemplan miles de consultas por segundo y 12,000 pedidos/hora.

**Fuente:** Supply Chain P9; E-commerce P9.

**Pendiente:** umbrales técnicos exactos.

## CA-HU-012

El preparador no debe visualizar datos personales innecesarios.

**Fuente:** E-commerce P7.

## CA-HU-013

Debe poder medirse el conjunto de indicadores expresado por CEO y E-commerce.

**Fuente:** CEO P4; E-commerce P10.

**Pendiente:** fórmula, fuente y periodicidad de cada KPI.

## CA-HU-016

La IA puede recomendar demanda/redistribución/anomalías, pero no puede inventar existencias ni sustituir las transacciones controladas de reserva/descuento.

**Fuente:** CEO P8.
