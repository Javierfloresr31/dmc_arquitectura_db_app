# Contratos API — Siniestro Fácil

## 1. Alcance

Contrato inicial del backend derivado de las operaciones descritas en las entrevistas: registrar caso, validar cobertura, coordinar asistencia, recibir evidencia, evaluar, inspeccionar, gestionar presupuesto, antifraude, autorización, pago y consulta del expediente. Las entrevistas identifican estas capacidades y estados. fileciteturn51file13

## 2. Convenciones

Base path propuesta: `/api/v1`.

Headers técnicos:
- `Authorization`: identidad/autorización del canal.
- `X-Correlation-Id`: correlación extremo a extremo.
- `Idempotency-Key`: operaciones mutables idempotentes.

Formato de error conceptual:

```json
{
  "code": "BUSINESS_ERROR",
  "message": "Descripción segura",
  "correlationId": "...",
  "details": []
}
```

## 3. Siniestros

### POST `/api/v1/siniestros`

Crea un expediente.

Entrada mínima derivada de Operaciones: póliza/documento, placa, fecha, ubicación aproximada, tipo de evento y medio de contacto. La evidencia no siempre es obligatoria al inicio. fileciteturn51file13

Respuesta: `201 Created` con `siniestroId`, estado inicial y `correlationId`.

### GET `/api/v1/siniestros/{id}`

Consulta el expediente y su estado actual.

### GET `/api/v1/siniestros`

Consulta operativa con filtros autorizados. Los filtros definitivos deben derivarse de los casos de uso.

### POST `/api/v1/siniestros/{id}/transiciones`

Solicita una transición de estado válida. No se permite modificar `estado` arbitrariamente desde un PATCH genérico.

## 4. Participantes

### POST `/api/v1/siniestros/{id}/participantes`

Registra participantes/terceros.

### GET `/api/v1/siniestros/{id}/participantes`

Consulta participantes autorizados.

## 5. Evidencia

### POST `/api/v1/siniestros/{id}/evidencias`

Registra evidencia y sus metadatos. El original, hash, fecha, fuente y transformaciones forman parte del requisito de trazabilidad. fileciteturn51file9

### GET `/api/v1/siniestros/{id}/evidencias`

Lista evidencia según permisos.

### GET `/api/v1/evidencias/{id}`

Consulta metadatos y referencias de evidencia. La descarga del original es una operación sensible y auditable.

## 6. Asistencia

### POST `/api/v1/siniestros/{id}/asistencia`

Solicita/coordinada asistencia.

### GET `/api/v1/siniestros/{id}/asistencia`

Consulta solicitudes y resultados.

## 7. Evaluación / inspección

### POST `/api/v1/siniestros/{id}/inspecciones`

Programa/registra inspección.

### GET `/api/v1/siniestros/{id}/inspecciones`

Consulta inspecciones.

## 8. Presupuestos

### POST `/api/v1/siniestros/{id}/presupuestos`

Registra presupuesto de taller.

### GET `/api/v1/siniestros/{id}/presupuestos`

Consulta presupuestos.

### POST `/api/v1/presupuestos/{id}/observaciones`

Registra observaciones.

### POST `/api/v1/presupuestos/{id}/ampliaciones`

Registra ampliaciones.

## 9. Antifraude

### GET `/api/v1/siniestros/{id}/alertas`

Consulta alertas autorizadas.

### POST `/api/v1/alertas/{id}/revision`

Registra decisión humana y justificación. Una alerta no equivale a fraude. fileciteturn51file9

## 10. Autorización y pago

### POST `/api/v1/siniestros/{id}/autorizaciones`

Registra decisión de autorización.

### POST `/api/v1/siniestros/{id}/pagos`

Solicita/registrar pago con controles de duplicidad e idempotencia.

## 11. Auditoría

Las operaciones sensibles generan trazabilidad. La descarga de evidencia y consultas antifraude sensibles deben quedar registradas. fileciteturn51file1

## 12. Estados HTTP

- `200`: consulta/operación correcta.
- `201`: recurso creado.
- `400`: solicitud inválida.
- `401`: identidad ausente/inválida.
- `403`: autorización insuficiente.
- `404`: recurso inexistente.
- `409`: conflicto/transición/idempotencia.
- `422`: regla de negocio no satisfecha.
- `502/503`: dependencia externa no disponible.
- `500`: error interno no controlado.

## 13. Pendientes que no se inventan

Quedan por concretar antes de generar OpenAPI definitivo: esquema exacto de payloads, paginación, filtros, formato de errores estándar, mecanismo de autenticación, permisos por endpoint y contratos de terceros.