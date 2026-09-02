# Siniestro Fácil — Cierre técnico Sprint 3

## 1. Objetivo

Documentar el cierre técnico del Sprint 3, correspondiente al flujo de **inspección, presupuesto y autorización** del siniestro, incluyendo la validación de idempotencia de autorización.

Este documento registra únicamente lo efectivamente implementado y validado durante el sprint. Las decisiones de negocio provienen de la especificación SDD y de las respuestas de negocio simuladas utilizadas para cerrar brechas.

## 2. Alcance implementado

### 2.1 Inspección

Se implementó:

- registro de inspección asociado a un siniestro;
- asociación opcional de ajustador;
- programación de inspección;
- consulta de inspecciones por siniestro;
- consulta de inspección por identificador;
- registro del resultado de inspección;
- validación de que la inspección se registre cuando el siniestro se encuentra en `EN_EVALUACION`;
- transición de estado a `INSPECCION_PROGRAMADA`.

Endpoints:

```text
POST /api/v1/siniestros/{id}/inspecciones
GET  /api/v1/siniestros/{id}/inspecciones
PUT  /api/v1/inspecciones/{id}/resultado
GET  /api/v1/inspecciones/{id}
```

### 2.2 Presupuesto

Se implementó:

- registro de presupuesto asociado al siniestro y taller;
- diagnóstico, observaciones, repuestos alternativos y ampliaciones según el modelo físico disponible;
- vigencia calculada a 7 días calendario;
- validación de la vigencia recibida contra la vigencia esperada;
- consulta de presupuestos por siniestro;
- consulta de presupuesto por identificador;
- validación de que el presupuesto se registre cuando el siniestro se encuentra en `INSPECCION_PROGRAMADA`;
- transición de estado a `PRESUPUESTO_RECIBIDO`.

Endpoints:

```text
POST /api/v1/siniestros/{id}/presupuestos
GET  /api/v1/siniestros/{id}/presupuestos
GET  /api/v1/presupuestos/{id}
```

### 2.3 Autorización

Se implementó:

- registro de autorización asociado al siniestro;
- identificación del aprobador;
- validación de que una nueva autorización se registre cuando el siniestro se encuentra en `PRESUPUESTO_RECIBIDO`;
- transición de estado a `AUTORIZADO`;
- consulta de autorizaciones por siniestro;
- soporte de `Idempotency-Key`;
- asociación de la solicitud idempotente con la autorización generada;
- rechazo de reutilización de la misma clave con un payload diferente.

Endpoints:

```text
POST /api/v1/siniestros/{id}/autorizacion
GET  /api/v1/siniestros/{id}/autorizacion
```

## 3. Estados de dominio utilizados

El modelo `Siniestro` formaliza los siguientes estados utilizados por el flujo implementado:

```text
REPORTADO
VALIDANDO_COBERTURA
ASISTENCIA_COORDINADA
EVIDENCIA_PENDIENTE
EN_EVALUACION
INSPECCION_PROGRAMADA
PRESUPUESTO_RECIBIDO
AUTORIZADO
```

Las transiciones implementadas en este sprint son:

```text
EN_EVALUACION
    -> INSPECCION_PROGRAMADA

INSPECCION_PROGRAMADA
    -> PRESUPUESTO_RECIBIDO

PRESUPUESTO_RECIBIDO
    -> AUTORIZADO
```

## 4. Idempotencia de autorización

La implementación reutiliza la infraestructura técnica existente de idempotencia (`siniestro_facil_meta.idempotencia_request`) y la extiende mediante `autorizacion_id`.

La migración:

```text
V3__extender_idempotencia_autorizacion.sql
```

incorpora:

- columna `autorizacion_id`;
- clave foránea hacia `siniestro_facil.autorizacion(id)`;
- índice sobre `autorizacion_id`.

La migración utiliza operaciones defensivas porque los objetos ya existían físicamente en la base de datos al momento de reconciliar el esquema con Flyway.

Flyway quedó registrado en versión `3` y posteriormente informó:

```text
Current version of schema "siniestro_facil_meta": 3
Schema "siniestro_facil_meta" is up to date. No migration necessary.
```

## 5. Evidencia E2E

Se utilizó el siniestro sintético `21`, que se encontraba en `PRESUPUESTO_RECIBIDO` y no tenía autorización previa.

### Prueba 1 — Nueva autorización

Solicitud con:

```text
Idempotency-Key: AUTH-S3-E2E-021
X-Correlation-Id: CORR-AUTH-S3-021
aprobador: SYNTH-APROBADOR-001
```

Resultado:

```text
HTTP 200
id = 28
siniestroId = 21
```

### Prueba 2 — Replay idempotente

Se repitió la solicitud con la misma `Idempotency-Key` y el mismo payload.

Resultado:

```text
HTTP 200
id = 28
```

No se generó una segunda autorización.

### Prueba 3 — Conflicto de payload

Se reutilizó la misma `Idempotency-Key` cambiando el aprobador a `SYNTH-APROBADOR-999`.

Resultado:

```text
HTTP 409
code = IDEMPOTENCY_CONFLICT
message = La Idempotency-Key ya fue utilizada con un payload diferente
```

La respuesta también incluyó un `correlationId`.

### Prueba 4 — Persistencia

Se verificó en PostgreSQL:

- una sola autorización para el siniestro `21`;
- asociación de la solicitud `AUTH-S3-E2E-021` con `autorizacion_id = 28`;
- existencia de `completed_at` en la solicitud idempotente.

Resultado: conforme.

## 6. Validación automatizada

### Pruebas Maven

Comando ejecutado:

```bash
mvn clean test
```

Resultado:

```text
Tests run: 7
Failures: 0
Errors: 0
Skipped: 0
BUILD SUCCESS
```

Las pruebas ejecutadas correspondieron a:

- `AsistenciaServiceTest`: 2 pruebas;
- `CoberturaServiceTest`: 2 pruebas;
- `SiniestroServiceTest`: 1 prueba;
- `InspeccionServiceTest`: 2 pruebas.

### Validación estática

Comando ejecutado:

```bash
./validation/static_validation.sh
```

Resultado:

```text
PASS=14
FAIL=0
```

Las validaciones incluyeron existencia de configuración, persistencia, historial, auditoría, `Idempotency-Key`, protección contra duplicados, conflicto de payload y exclusión de Firebase del MVP.

## 7. Arranque de aplicación

La aplicación se ejecutó correctamente mediante:

```bash
mvn spring-boot:run
```

Evidencia principal:

```text
Successfully validated 3 migrations
Current version of schema "siniestro_facil_meta": 3
Schema "siniestro_facil_meta" is up to date. No migration necessary.
Tomcat started on port 8080
Started SiniestroFacilApplication
```

La aplicación se conectó correctamente a PostgreSQL mediante la configuración utilizada en Cloud Shell y Cloud SQL Auth Proxy.

Existe un warning informativo porque PostgreSQL `18.4` es posterior a la última versión de PostgreSQL oficialmente soportada por la versión de Flyway utilizada. No impidió la validación ni el arranque.

## 8. Resultado del Sprint 3

**ESTADO: CERRADO TÉCNICAMENTE.**

El alcance implementado y validado permite continuar con el siguiente sprint sin bloqueos técnicos identificados dentro del alcance actual.

## 9. Aspectos explícitamente no inventados

No se implementaron estructuras que no estén soportadas por el modelo físico actual.

En particular:

- `OBSERVADO` y `RECHAZADO` no se implementaron como decisiones de autorización porque la tabla `autorizacion` actual no contiene un atributo de decisión que permita persistirlas correctamente;
- las ampliaciones de presupuesto no se modelaron como una nueva estructura de autorización porque el modelo físico actual requiere una evolución para representar de forma completa el versionado/ampliación del presupuesto;
- no se agregó un umbral monetario para aprobación de supervisor porque el valor exacto permanece pendiente en la especificación de negocio;
- no se implementó determinación automática de fraude ni decisión automática de rechazo.

Estos puntos permanecen como evolución pendiente y no se consideran fallos del Sprint 3.

## 10. Próximo sprint

El siguiente sprint corresponde a **antifraude y revisión humana**.

El desarrollo deberá preservar como restricciones del SDD:

1. una alerta antifraude no equivale automáticamente a fraude;
2. la decisión sensible requiere revisión humana;
3. las reglas/modelos deben ser versionados;
4. la ejecución debe conservar versión de regla/modelo, fecha, datos de entrada, resultado y decisión humana;
5. debe poder medirse la tasa de falsos positivos;
6. durante el piloto se mantiene como objetivo una tasa de falsos positivos de alertas antifraude de alta severidad menor al 10%.

La implementación tecnológica concreta para antifraude deberá definirse durante el Sprint 4 sin convertir decisiones de negocio pendientes en supuestos técnicos.