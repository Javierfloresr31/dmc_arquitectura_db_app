# Idempotencia y Correlación — Siniestro Fácil

## 1. Justificación

Operaciones reporta casos duplicados y proveedores que pueden no responder. También se requieren controles contra pagos duplicados. Por ello, las operaciones críticas deben poder reintentarse sin producir efectos duplicados.

## 2. Identificadores técnicos

- `X-Correlation-Id`: correlación de una operación de extremo a extremo.
- `Idempotency-Key`: clave enviada por el cliente para una operación mutable idempotente.
- `eventId`: identificador único de evento.
- `requestId`: identificador de una solicitud técnica.

No se agregan todavía columnas físicas para estos identificadores porque el modelo lógico/físico aprobado no las define.

## 3. Operaciones idempotentes

Como mínimo:
- creación de siniestro cuando el canal pueda reintentar;
- carga/registro de evidencia;
- solicitud de asistencia;
- registro de presupuesto;
- autorización;
- pago;
- consumo/reprocesamiento de eventos externos.

## 4. Semántica

Primera solicitud válida:
`PENDING -> PROCESSING -> SUCCEEDED/FAILED`.

Repetición con la misma clave y mismo payload:
- no crea un segundo efecto;
- devuelve el resultado asociado a la operación original cuando sea posible.

Repetición con misma clave y payload diferente:
- `409 Conflict`.

## 5. Alcance de la clave

Recomendación: la clave debe estar acotada al actor autenticado, operación y recurso lógico cuando corresponda. Debe almacenarse el hash del payload para detectar reutilización de una clave con contenido diferente.

El TTL exacto se definirá con base en el comportamiento de cada operación; para pagos debe cubrir el periodo necesario para reconciliación y reintentos seguros.

## 6. Integraciones

Un retry solo se ejecutará si el error es potencialmente transitorio o la operación del proveedor es explícitamente idempotente.

Nunca se debe repetir ciegamente un pago cuya respuesta sea desconocida. Se debe reconciliar el estado con el proveedor o utilizar su mecanismo de idempotencia.

## 7. Duplicidad de siniestros

La entrevista identifica duplicados por reportes del asegurado, corredor y taller. La política exacta de deduplicación de negocio sigue abierta; el backend no inventará una regla basada en placa, fecha o ubicación sin aprobación.

Recomendación: generar candidatos de duplicidad y permitir resolución controlada, manteniendo expedientes separados hasta que exista una decisión explícita de negocio.

## 8. Auditoría

Los reintentos y conflictos relevantes deben quedar correlacionados y las acciones sensibles auditadas.

## 9. Preguntas pendientes

- TTL exacto de Idempotency-Key por operación;
- estrategia ante respuesta desconocida de pagos;
- algoritmo de deduplicación de siniestros;
- persistencia de eventos fuera de orden.
