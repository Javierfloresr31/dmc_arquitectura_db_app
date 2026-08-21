# Contratos de Integraciones — Siniestro Fácil

## 1. Integraciones identificadas

Las entrevistas identifican como dependencias: sistema de pólizas, red de talleres, proveedores de grúa/asistencia, ajustadores, mapas, mensajería y medios de pago. No todas las dependencias disponen de APIs modernas, por lo que el backend debe tolerar lentitud e indisponibilidad.

## 2. Patrón común

Cada integración deberá aislarse detrás de un puerto/adaptador y manejar:
- correlationId;
- requestId;
- timeout;
- resultado aceptado/rechazado/sin respuesta;
- retry cuando sea seguro;
- idempotencia;
- error técnico;
- fecha/hora del intento;
- proveedor destino;
- métricas de latencia, error y costo cuando estén disponibles.

## 3. Sistema de pólizas

**Propósito:** validar identidad/póliza/vehículo y cobertura.

Flujos:
1. solicitud de validación;
2. respuesta de existencia/estado/cobertura;
3. registro del resultado en el expediente;
4. transición posterior según reglas del dominio.

**Pendiente:** contrato real, autenticación, endpoints/canales, SLA y códigos de respuesta.

**Recomendación:** priorizar API síncrona para validaciones de corta duración; si existe indisponibilidad, aplicar timeout controlado y degradación sin bloquear permanentemente el expediente.

## 4. Asistencia / grúa

**Propósito:** coordinar asistencia cuando corresponde.

Estados técnicos mínimos:
- SOLICITADA;
- ACEPTADA;
- RECHAZADA;
- SIN_RESPUESTA;
- COMPLETADA;
- FALLIDA.

Operaciones exige reintento, escalamiento o reasignación cuando el proveedor no responde y distinguir aceptación, rechazo y ausencia de respuesta.

**Recomendación:** integración asíncrona cuando la respuesta no pueda garantizarse rápidamente; mantener historial de intentos y no bloquear el expediente por un único proveedor.

**Pendiente:** proveedor/canal concreto y SLA.

## 5. Talleres

**Propósito:** recibir orden, presupuesto, diagnóstico, observaciones, repuestos alternativos y ampliaciones.

El modelo físico soporta `TALLER`, `PRESUPUESTO` y `PRESUPUESTO_DETALLE`; no se inventan campos económicos adicionales.

**Recomendación:** API REST si el taller la soporta; de lo contrario, utilizar un adaptador controlado para el canal disponible sin acoplar el dominio al proveedor.

**Pendiente:** mecanismo de comunicación y contrato del taller.

## 6. Mapas

**Propósito identificado:** ubicación aproximada y soporte de asistencia/operación.

**Recomendación de selección:** cobertura geográfica, precisión requerida, costo por transacción, límites/cuotas, SLA, privacidad y facilidad de integración. No se selecciona proveedor hasta disponer del requerimiento concreto y comparar alternativas.

**Pendiente:** proveedor, API, precisión requerida y política de almacenamiento.

## 7. Mensajería

**Propósito:** informar al cliente el siguiente paso y cambios relevantes.

**Recomendación:** arquitectura asíncrona, plantillas versionadas, trazabilidad de entrega y reintentos con backoff. No bloquear una transacción de negocio crítica esperando confirmación de entrega del mensaje.

**Pendiente:** canales, plantillas, proveedor, consentimiento y política de reintentos.

## 8. Pagos

**Propósito:** ejecutar/registrar pago o indemnización después de autorización.

Requisitos:
- operación idempotente;
- correlación;
- no duplicidad;
- resultado auditable;
- manejo de timeout/indisponibilidad;
- reconciliación ante respuesta desconocida.

**Regla:** nunca repetir ciegamente una operación financiera si el resultado del proveedor es desconocido. Primero consultar/reconciliar el estado o utilizar el mecanismo idempotente oficial del proveedor.

**Pendiente:** proveedor, contrato y confirmación transaccional.

## 9. Integraciones asíncronas

Cuando una dependencia no pueda responder síncronamente sin bloquear al cliente, el caso podrá pasar a coordinación asíncrona. El evento deberá tener identificador único y permitir reprocesamiento seguro.

## 10. Reintentos

No se aplicará retry ciego. Debe clasificarse el error como transitorio, permanente o desconocido. Operaciones exige distinguir intento, aceptación, rechazo y ausencia de respuesta.

Recomendación técnica:
- exponential backoff;
- jitter;
- límite de intentos;
- dead-letter/error handling cuando corresponda;
- métricas de retry;
- circuit breaker para dependencias inestables.

## 11. Circuit breaker / degradación

La arquitectura deberá aislar fallas externas para que la indisponibilidad de un proveedor no bloquee todo el expediente.

La degradación debe preservar el expediente y registrar el estado de dependencia para permitir recuperación posterior.

## 12. FinOps de integraciones

Cada integración externa deberá medir, cuando sea posible:
- número de llamadas;
- costo por llamada;
- tasa de error;
- latencia;
- reintentos;
- volumen de datos;
- costo por siniestro.

Esto permitirá comparar costo y valor antes de incrementar frecuencia de llamadas o adoptar proveedores más costosos.

## 13. Preguntas abiertas

Antes de implementar cada adaptador se requiere resolver:
1. contrato del sistema de pólizas;
2. contrato del proveedor de asistencia;
3. canal/contrato de talleres;
4. proveedor de mensajería;
5. proveedor de pagos;
6. proveedor de mapas;
7. SLA por integración y tipo de operación;
8. requisitos de consentimiento/privacidad por canal.

No se inventan URLs, credenciales, payloads ni SLAs.
