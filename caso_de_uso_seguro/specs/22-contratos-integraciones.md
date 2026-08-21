# Contratos de Integraciones — Siniestro Fácil

## 1. Integraciones identificadas

Las entrevistas identifican como dependencias: sistema de pólizas, red de talleres, proveedores de grúa/asistencia, ajustadores, mapas, mensajería y medios de pago. No todas las dependencias disponen de APIs modernas, por lo que el backend debe tolerar lentitud e indisponibilidad. fileciteturn51file3

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
- proveedor destino.

## 3. Sistema de pólizas

**Propósito:** validar identidad/póliza/vehículo y cobertura.

Flujos:
1. solicitud de validación;
2. respuesta de existencia/estado/cobertura;
3. registro del resultado en el expediente;
4. transición posterior según reglas del dominio.

**Pendiente:** contrato real, autenticación, endpoints/canales, SLA y códigos de respuesta.

## 4. Asistencia / grúa

**Propósito:** coordinar asistencia cuando corresponde.

Estados técnicos mínimos:
- SOLICITADA;
- ACEPTADA;
- RECHAZADA;
- SIN_RESPUESTA;
- COMPLETADA;
- FALLIDA.

Operaciones exige reintento, escalamiento o reasignación cuando el proveedor no responde y distinguir aceptación, rechazo y ausencia de respuesta. fileciteturn51file13

**Pendiente:** proveedor/canal concreto y SLA.

## 5. Talleres

**Propósito:** recibir orden, presupuesto, diagnóstico, observaciones, repuestos alternativos y ampliaciones.

El modelo físico soporta `TALLER`, `PRESUPUESTO` y `PRESUPUESTO_DETALLE`; no se inventan campos económicos adicionales. fileciteturn51file12

**Pendiente:** mecanismo de comunicación y contrato del taller.

## 6. Mapas

**Propósito identificado:** ubicación aproximada y soporte de asistencia/operación.

**Pendiente:** proveedor, API, precisión requerida y política de almacenamiento.

## 7. Mensajería

**Propósito:** informar al cliente el siguiente paso y cambios relevantes.

**Pendiente:** canales, plantillas, proveedor, consentimiento y política de reintentos.

## 8. Pagos

**Propósito:** ejecutar/registrar pago o indemnización después de autorización.

Requisitos derivados:
- operación idempotente;
- correlación;
- no duplicidad;
- resultado auditable;
- manejo de timeout/indisponibilidad.

**Pendiente:** proveedor, contrato y confirmación transaccional.

## 9. Integraciones asíncronas

Cuando una dependencia no pueda responder síncronamente sin bloquear al cliente, el caso podrá pasar a coordinación asíncrona. El evento deberá tener identificador único y permitir reprocesamiento seguro.

## 10. Reintentos

No se aplicará retry ciego. Debe clasificarse el error como transitorio, permanente o desconocido. Operaciones exige distinguir intento, aceptación, rechazo y ausencia de respuesta. fileciteturn51file13

## 11. Circuit breaker / degradación

La arquitectura deberá aislar fallas externas para que la indisponibilidad de un proveedor no bloquee todo el expediente. La política concreta de circuit breaker y fallback queda como decisión técnica pendiente.

## 12. Preguntas bloqueantes

Antes de implementar cada adaptador se requiere resolver:
1. contrato del sistema de pólizas;
2. contrato del proveedor de asistencia;
3. canal/contrato de talleres;
4. proveedor de mensajería;
5. proveedor de pagos;
6. proveedor de mapas.

No se inventan URLs, credenciales, payloads ni SLAs.