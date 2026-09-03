# Contratos API — Siniestro Fácil

## 1. Alcance

Contrato inicial del backend derivado de las operaciones descritas en las entrevistas: registrar caso, validar cobertura, coordinar asistencia, recibir evidencia, evaluar, inspeccionar, gestionar presupuesto, antifraude, autorización, pago y consulta del expediente.

## 2. Plataforma de exposición

- API Gateway: punto de entrada de las APIs.
- Backend: Cloud Run.
- Base path propuesta: `/api/v1`.
- Autenticación: Firebase Authentication.
- Identidad de aplicación: Firebase ID Token.

## 3. Convenciones

Headers técnicos:
- `Authorization: Bearer <Firebase ID Token>`.
- `X-Correlation-Id`: correlación extremo a extremo.
- `Idempotency-Key`: operaciones mutables que admitan repetición segura.

El backend obtiene el actor autenticado a partir del token validado. No se acepta un identificador de usuario enviado por el cliente como sustituto de la identidad autenticada.

Formato de error conceptual:

```json
{
  "code": "BUSINESS_ERROR",
  "message": "Descripción segura",
  "correlationId": "...",
  "details": []
}
```

## 4. Siniestros

### POST `/api/v1/siniestros`

Crea un expediente.

Entrada mínima derivada de Operaciones: póliza/documento, placa, fecha, ubicación aproximada, tipo de evento y medio de contacto. La evidencia no siempre es obligatoria al inicio.

Respuesta: `201 Created` con `siniestroId`, estado inicial y `correlationId`.

### GET `/api/v1/siniestros/{id}`

Consulta el expediente y su estado actual, sujeto a RBAC.

### GET `/api/v1/siniestros`

Consulta operativa con filtros autorizados. Los filtros definitivos deben derivarse de los casos de uso.

### POST `/api/v1/siniestros/{id}/transiciones`

Solicita una transición de estado válida. No se permite modificar `estado` arbitrariamente desde un PATCH genérico.

## 5. Participantes

### POST `/api/v1/siniestros/{id}/participantes`

Registra participantes/terceros.

### GET `/api/v1/siniestros/{id}/participantes`

Consulta participantes autorizados.

## 6. Evidencia

### POST `/api/v1/siniestros/{id}/evidencias`

Registra evidencia y sus metadatos. El original, hash, fecha, fuente y transformaciones forman parte del requisito de trazabilidad.

### GET `/api/v1/siniestros/{id}/evidencias`

Lista evidencia según permisos.

### GET `/api/v1/evidencias/{id}`

Consulta metadatos y referencias de evidencia. La descarga del original es una operación sensible y auditable.

## 7. Asistencia

### POST `/api/v1/siniestros/{id}/asistencia`

Solicita/coordinada asistencia.

### GET `/api/v1/siniestros/{id}/asistencia`

Consulta solicitudes y resultados.

## 8. Evaluación / inspección

### POST `/api/v1/siniestros/{id}/inspecciones`

Programa/registra inspección.

### GET `/api/v1/siniestros/{id}/inspecciones`

Consulta inspecciones.

## 9. Presupuestos

### POST `/api/v1/siniestros/{id}/presupuestos`

Registra presupuesto de taller.

### GET `/api/v1/siniestros/{id}/presupuestos`

Consulta presupuestos.

### POST `/api/v1/presupuestos/{id}/observaciones`

Registra la decisión de observar un presupuesto cuando requiere corrección, manteniendo la trazabilidad del responsable y la fecha de la decisión.

El payload definitivo y los permisos asociados permanecen pendientes de definición contractual.

### POST `/api/v1/presupuestos/{id}/ampliaciones`

Registra ampliaciones.

## 10. Antifraude

### GET `/api/v1/siniestros/{id}/alertas`

Consulta alertas autorizadas.

### POST `/api/v1/alertas/{id}/revision`

Registra decisión humana y justificación. Una alerta no equivale a fraude.

## 11. Autorización y pago

### POST `/api/v1/siniestros/{id}/autorizaciones`

Registra decisión de autorización.

### POST `/api/v1/siniestros/{id}/pagos`

Solicita/registra pago con controles de duplicidad e idempotencia.

## 12. Auditoría

Las operaciones sensibles generan trazabilidad. La descarga de evidencia y consultas antifraude sensibles deben quedar registradas.

## 13. Estados HTTP

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

## 14. Pendientes que no se inventan

Quedan por concretar antes de generar OpenAPI definitivo: esquema exacto de payloads, paginación, filtros, formato de errores estándar, permisos por endpoint, claims definitivos de Firebase y contratos de terceros.
