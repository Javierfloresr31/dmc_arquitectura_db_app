# Sprint 6 — Seguridad RBAC y contratos API

## 1. Estado

PLANIFICADO

## 2. Objetivo

Cerrar los pendientes técnicos del piloto relacionados con autenticación,
autorización funcional RBAC y contratos API, sin incorporar nuevas
funcionalidades de negocio ni integraciones externas no contratadas.

## 3. Alcance

### S06-01 — Roles Firebase

Definir los roles mínimos que podrán utilizarse mediante Firebase Custom
Claims para la autorización funcional del backend.

Roles considerados:

- ASEGURADO
- REPORTANTE_AUTORIZADO
- OPERADOR
- AJUSTADOR
- INVESTIGADOR_FRAUDE
- TALLER
- PROVEEDOR_ASISTENCIA
- SUPERVISOR

Los Custom Claims contendrán únicamente información mínima de
autorización.

### S06-02 — Permisos por endpoint

Definir la matriz de acceso para los endpoints existentes.

El backend deberá considerar:

1. identidad autenticada;
2. rol/perfil;
3. pertenencia o alcance sobre el siniestro;
4. operación solicitada.

Un rol válido no implica acceso irrestricto a todos los expedientes.

### S06-03 — Contrato de autorización

Cerrar el payload mínimo de:

POST `/api/v1/siniestros/{id}/autorizaciones`

La identidad del actor deberá obtenerse del contexto autenticado y no de
un identificador enviado por el cliente como fuente de confianza.

### S06-04 — Contrato de pago/indemnización

Endpoint:

POST `/api/v1/siniestros/{id}/pagos`

Request mínimo:

```json
{
  "autorizacionId": 21
}
```

El campo `autorizacionId` identifica la autorización aplicable al pago y debe corresponder al mismo siniestro indicado en la URL. No se recibe desde el cliente información del actor que ejecuta la operación; la identidad se obtiene del contexto autenticado.

Headers requeridos:

- `Authorization: Bearer <Google OIDC ID Token>`
- `Idempotency-Key`
- `X-Correlation-Id` opcional.

Controles mínimos:

- debe existir una autorización aplicable al siniestro;
- no debe existir una operación económica equivalente para el mismo siniestro y autorización;
- una misma `Idempotency-Key` no debe generar un segundo resultado económico;
- la operación debe quedar registrada en auditoría;
- ante una respuesta desconocida de un proveedor externo no se realizará un reintento ciego.

Respuesta exitosa: `200` con el resultado del pago registrado.

No se define en este sprint un proveedor financiero externo ni nuevos campos económicos no presentes en la implementación existente.

### S06-05 — Respuestas de seguridad

Validar como mínimo:

- `401` cuando no existe una identidad válida;
- `403` cuando la identidad está autenticada pero no tiene autorización suficiente;
- `404` cuando el recurso no existe;
- `409` para conflictos de idempotencia o reglas de operación;
- `422` para reglas de negocio no satisfechas.

### S06-06 — Alcance del recurso

Validar que el acceso a un siniestro respete las reglas de pertenencia y
alcance definidas para cada rol.

No se permitirá utilizar un rol válido como sustituto del control de
pertenencia al expediente.

### S06-07 — Contrato API

Actualizar el contrato API con los permisos definitivos, payloads mínimos
y comportamiento de seguridad.

El objetivo es dejar preparada la especificación para generar un contrato
OpenAPI definitivo.

## 4.1 Decisión S06-01 — Roles Firebase

Los roles Firebase del piloto serán los ocho actores ya definidos en la especificación de seguridad:

- `ASEGURADO`
- `REPORTANTE_AUTORIZADO`
- `OPERADOR`
- `AJUSTADOR`
- `INVESTIGADOR_FRAUDE`
- `TALLER`
- `PROVEEDOR_ASISTENCIA`
- `SUPERVISOR`

El Custom Claim mínimo de autorización utilizará el atributo `role`. Ejemplo:

```json
{
  "role": "OPERADOR"
}
```

Los Custom Claims no almacenarán datos personales, información de negocio, información del siniestro ni atributos que deban cambiar frecuentemente.

La identidad del usuario se determinará mediante el Firebase UID del contexto autenticado. El `role` se utilizará para autorización funcional, pero no sustituirá las reglas de pertenencia o alcance sobre el siniestro.

## 4.2 Decisión S06-02 — Permisos por endpoint

La autorización de los endpoints existentes se determinará mediante la combinación de identidad autenticada, rol Firebase, permiso sobre la operación y alcance sobre el recurso.

| Endpoint | Roles permitidos | Alcance mínimo |
|---|---|---|
| POST `/api/v1/siniestros` | ASEGURADO, REPORTANTE_AUTORIZADO, OPERADOR, SUPERVISOR | Propio/autorizado según actor |
| GET `/api/v1/siniestros/{id}` | ASEGURADO, REPORTANTE_AUTORIZADO, OPERADOR, AJUSTADOR, INVESTIGADOR_FRAUDE, TALLER, PROVEEDOR_ASISTENCIA, SUPERVISOR | Propio, autorizado o asignado |
| GET `/api/v1/siniestros` | OPERADOR, AJUSTADOR, INVESTIGADOR_FRAUDE, SUPERVISOR | Expedientes dentro del alcance |
| POST `/api/v1/siniestros/{id}/transiciones` | OPERADOR, AJUSTADOR, SUPERVISOR | Operación y expediente dentro del alcance |
| POST `/api/v1/siniestros/{id}/participantes` | OPERADOR, AJUSTADOR, SUPERVISOR | Expediente dentro del alcance |
| GET `/api/v1/siniestros/{id}/participantes` | Roles con acceso al expediente | Según alcance |
| POST `/api/v1/siniestros/{id}/evidencias` | ASEGURADO, REPORTANTE_AUTORIZADO, OPERADOR, AJUSTADOR, INVESTIGADOR_FRAUDE, TALLER, SUPERVISOR | Propio, autorizado, asignado o según flujo |
| GET `/api/v1/siniestros/{id}/evidencias` | Roles con acceso al expediente | Según alcance |
| GET `/api/v1/evidencias/{id}` | Según autorización sobre el expediente | Evidencia sensible y auditable |
| POST `/api/v1/siniestros/{id}/asistencia` | ASEGURADO, REPORTANTE_AUTORIZADO, OPERADOR, SUPERVISOR | Propio, autorizado u operativo |
| GET `/api/v1/siniestros/{id}/asistencia` | Roles con acceso al expediente | Según alcance |
| POST `/api/v1/siniestros/{id}/inspecciones` | OPERADOR, AJUSTADOR, SUPERVISOR | Expediente dentro del alcance |
| GET `/api/v1/siniestros/{id}/inspecciones` | Roles con acceso al expediente | Según alcance |
| POST `/api/v1/siniestros/{id}/presupuestos` | OPERADOR, AJUSTADOR, TALLER, SUPERVISOR | Expediente dentro del alcance |
| GET `/api/v1/siniestros/{id}/presupuestos` | OPERADOR, AJUSTADOR, TALLER, SUPERVISOR | Expediente dentro del alcance |
| POST `/api/v1/presupuestos/{id}/observaciones` | OPERADOR, AJUSTADOR, SUPERVISOR | Presupuesto dentro del alcance |
| POST `/api/v1/presupuestos/{id}/ampliaciones` | OPERADOR, AJUSTADOR, TALLER, SUPERVISOR | Presupuesto dentro del alcance |
| GET `/api/v1/siniestros/{id}/alertas` | INVESTIGADOR_FRAUDE, SUPERVISOR | Expediente dentro del alcance antifraude |
| POST `/api/v1/alertas/{id}/revision` | INVESTIGADOR_FRAUDE, SUPERVISOR | Alerta autorizada |
| POST `/api/v1/siniestros/{id}/autorizaciones` | OPERADOR, AJUSTADOR, SUPERVISOR | Según delegación y alcance |
| POST `/api/v1/siniestros/{id}/pagos` | OPERADOR, SUPERVISOR | Según proceso y alcance |

### Regla transversal de autorización

Todos los endpoints protegidos deberán validar, en este orden:

1. Firebase ID Token válido.
2. Identidad mediante Firebase UID.
3. Rol Firebase.
4. Permiso sobre la operación.
5. Pertenencia o alcance sobre el recurso.

Un rol válido por sí solo no concede acceso irrestricto al expediente.

### Respuestas de seguridad

- `401`: identidad ausente o inválida.
- `403`: identidad autenticada sin rol, permiso o alcance suficiente.
- `404`: recurso inexistente.
- `409`: conflicto de transición o idempotencia.
- `422`: regla funcional no satisfecha.



## 4.3 Decisión S06-03 — Contrato de autorización

El endpoint `POST /api/v1/siniestros/{id}/autorizaciones` no recibirá el identificador del aprobador como dato confiable desde el cliente.

El request funcional no requiere campos de negocio adicionales y podrá representarse mediante un objeto JSON vacío:

```json
{}
```

La identidad del actor se obtendrá exclusivamente del contexto autenticado. Para el piloto de Sprint 6 se utilizará Cloud Run IAM con un Google OIDC ID Token validado por el backend. La implementación definitiva con Firebase Authentication queda fuera de este piloto.

El valor persistido actualmente en `autorizacion.aprobador` deberá corresponder a la identidad autenticada y no a un valor enviado libremente por el cliente.

Se mantienen los headers técnicos existentes:

- `Authorization: Bearer <Google OIDC ID Token>`
- `Idempotency-Key`
- `X-Correlation-Id`

Se mantienen las capacidades existentes de idempotencia, transición a `AUTORIZADO` y auditoría. No se modifica el modelo persistente por esta decisión.

### Respuestas

- `401`: identidad ausente o inválida.
- `403`: rol o alcance insuficiente para autorizar.
- `404`: siniestro inexistente.
- `409`: conflicto de idempotencia.
- `422`: el siniestro no cumple las condiciones funcionales para autorización.


## 4.4 Evidencia S06-03 / S06-05 — Seguridad del piloto

Validaciones realizadas durante el sprint:

- `POST /api/v1/siniestros/{id}/autorizaciones` sin `Authorization` → HTTP `401`.
- `POST /api/v1/siniestros/{id}/autorizaciones` con Bearer inválido → HTTP `401`.
- Rol `OPERADOR` → permiso de autorización validado mediante prueba automatizada.
- Rol `ASEGURADO` → rechazo RBAC mediante `SecurityException`, expuesto como HTTP `403` por `RestExceptionHandler`.
- El campo `aprobador` ya no forma parte del request.
- El actor utilizado por la operación procede del `AuthenticationContext`.

La validación end-to-end con un Google OIDC ID Token emitido para el servicio Cloud Run queda pendiente del despliegue del servicio piloto, ya que actualmente no existe un servicio Cloud Run desplegado en el proyecto.


## 4. Fuera de alcance

No forman parte de este sprint:

- integración financiera externa;
- step-up authentication;
- nuevos estados del siniestro;
- nueva entidad de reparación;
- nuevas reglas de negocio no sustentadas;
- nuevas estructuras persistentes por inferencia;
- cambios en la lógica funcional ya cerrada en Sprint 5.





## 5. Criterios de cierre

Sprint 6 podrá cerrarse cuando:

1. Los roles Firebase estén definidos.
2. Los permisos por endpoint estén documentados.
3. Los payloads mínimos de autorización y pago estén definidos.
4. Existan pruebas de `401` y `403`.
5. Se valide el alcance del recurso.
6. El contrato API esté actualizado.
7. Las pruebas automatizadas existentes continúen exitosas.
8. No existan cambios pendientes de especificación necesarios para el alcance del piloto.

## 6. Pendientes fuera del sprint

Quedan sujetos a decisiones posteriores:

- responsable operativo de administración de roles Firebase;
- step-up authentication;
- integración con proveedor financiero;
- contratos definitivos de terceros;
- política de revocación/desactivación de cuentas.
