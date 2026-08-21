# Sprint 01 — Validación y corrección de idempotencia

## Hallazgo

La prueba end-to-end ejecutada contra Cloud SQL demostró que dos solicitudes con el mismo `Idempotency-Key` creaban dos siniestros diferentes.

- Primera solicitud: `Idempotency-Key: sprint01-idempotency-001` → `201 Created` → siniestro `105`.
- Segunda solicitud idéntica: misma `Idempotency-Key` → `201 Created` → siniestro `107`.

Resultado: **idempotencia no cumplida**.

## Corrección aplicada

Se implementó persistencia transaccional de la clave en el esquema técnico `siniestro_facil_meta` mediante `idempotencia_request`.

La solución:

1. calcula una huella SHA-256 del `CrearSiniestroRequest`;
2. registra la `Idempotency-Key` con restricción `UNIQUE`;
3. usa `INSERT ... ON CONFLICT DO NOTHING` para evitar carreras de inserción;
4. si la clave ya existe y la huella coincide, devuelve el siniestro previamente creado;
5. si la clave existe con una huella diferente, devuelve `409 Conflict` con código `IDEMPOTENCY_CONFLICT`;
6. vincula la fila de idempotencia al siniestro dentro de la misma transacción del caso de uso;
7. conserva el registro de idempotencia si el alta termina correctamente y lo revierte si la transacción falla.

## Migración

La migración se encuentra en:

`src/main/resources/db/migration/V1__crear_idempotencia_siniestro.sql`

Flyway se habilitó para ejecutar las migraciones PostgreSQL al iniciar el backend. El esquema técnico se mantiene separado del esquema de dominio `siniestro_facil`, por lo que el modelo físico de dominio continúa teniendo 25 tablas.

## Validación pendiente en Cloud SQL

La corrección fue registrada en GitHub, pero debe validarse en Cloud Shell con:

```bash
mvn clean test
./validation/static_validation.sh
```

Después de levantar el backend contra Cloud SQL, ejecutar el mismo POST dos veces con la misma `Idempotency-Key`.

Resultado esperado:

```text
Primera solicitud  → 201 Created → siniestro N
Segunda solicitud  → 201 Created → mismo siniestro N
```

La consulta de control debe confirmar que no existe un segundo siniestro generado por la segunda solicitud.

También debe probarse el caso:

```text
Misma Idempotency-Key + payload diferente → 409 Conflict
```

## Criterio de cierre

Sprint 01 no se considera cerrado hasta que la prueba contra Cloud SQL confirme ambos escenarios y `mvn clean test` permanezca en PASS.
