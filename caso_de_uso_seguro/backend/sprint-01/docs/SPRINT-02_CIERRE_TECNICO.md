# Sprint 02 — Cierre técnico

## 1. Identificación

- **Proyecto:** Siniestro Fácil
- **Repositorio:** `Javierfloresr31/dmc_arquitectura_db_app`
- **Branch:** `feature/sprint-02-cobertura-asistencia-evidencia`
- **Componente:** `caso_de_uso_seguro/backend/sprint-01`
- **Sprint:** 02
- **Fecha de cierre técnico:** 2026-08-28
- **Commit de implementación:** `fdcac339aa7e44fd6f8d194bde91014018ed470b`
- **Commit previo de cobertura:** `08936231505e4a6def7431934988a4a5158eb3c7`

---

## 2. Objetivo del Sprint 02

Completar el incremento técnico correspondiente a:

1. Validación sintética de cobertura.
2. Registro y consulta de evidencias de un siniestro.
3. Persistencia de evidencias y versiones de transformación.
4. Solicitud y consulta de asistencia.
5. Validación automatizada mediante pruebas unitarias.
6. Validación estática del baseline del proyecto.
7. Evidencia de ejecución contra PostgreSQL/Cloud SQL.
8. Cierre limpio del código en Git, excluyendo artefactos generados por Maven.

El alcance se implementó manteniendo la separación entre aplicación, puertos e infraestructura y utilizando adaptadores sintéticos para las integraciones todavía no conectadas a proveedores reales.

---

## 3. Estado funcional implementado

### 3.1 Cobertura

Se incorporó una abstracción `CoberturaPort` con el resultado de validación:

- identidad verificada;
- póliza verificada;
- vehículo verificado;
- cobertura verificada;
- deducible disponible.

La regla implementada por `puedeContinuar()` exige identidad, póliza, vehículo y cobertura verificadas. El deducible no es condición para continuar en el escenario sintético implementado.

Se incorporó `CoberturaService` y `SyntheticCoberturaAdapter`.

El adaptador sintético devuelve validación positiva para póliza y placa no vacías y devuelve `deducibleDisponible=false`. Para entradas vacías devuelve todas las verificaciones como falsas.

### 3.2 Evidencia

Se incorporó el flujo de evidencia:

- registrar evidencia asociada a un siniestro;
- listar evidencias de un siniestro;
- obtener una evidencia por ID;
- registrar una versión de transformación de evidencia.

Componentes:

- `RegistrarEvidenciaRequest`
- `EvidenciaResponse`
- `EvidenciaPort`
- `EvidenciaService`
- `JdbcEvidenciaRepository`
- `EvidenciaController`

La persistencia utiliza las tablas existentes:

- `siniestro_facil.evidencia`
- `siniestro_facil.evidencia_version`

No se agregó una nueva migración para estas tablas porque durante la validación se confirmó que ambas ya existen en la base de datos objetivo.

### 3.3 Asistencia

Se incorporó el flujo de asistencia para:

- solicitar asistencia;
- listar asistencias registradas.

Componentes:

- `AsistenciaRequest`
- `AsistenciaResponse`
- `AsistenciaPort`
- `AsistenciaService`
- `SyntheticAsistenciaAdapter`
- `JdbcAsistenciaRepository`
- `AsistenciaController`

El flujo REST validado en este sprint es:

```text
POST /api/v1/siniestros/{id}/asistencia
GET  /api/v1/siniestros/{id}/asistencia
```

La solicitud se persiste en `siniestro_facil.asistencia`.

---

## 4. Operaciones diferidas

El puerto y servicio de asistencia contienen también operaciones:

- `REINTENTAR`
- `ESCALAR`
- `REASIGNAR`

Sin embargo, estas operaciones **no están expuestas mediante endpoints REST en el Sprint 02 y no forman parte de la validación funcional realizada para el cierre**.

Quedan como trabajo potencial de una segunda fase si el SDD o el alcance funcional posterior confirma que son necesarias.

Esto evita ampliar el alcance del sprint sin evidencia de que dichas operaciones sean obligatorias para el incremento actual.

---

## 5. Persistencia y base de datos

La aplicación fue ejecutada contra Cloud SQL PostgreSQL mediante el proxy local.

Conexión utilizada durante la validación:

```text
PostgreSQL server: 18.4
Database: dmcsiniestrofacil
Host de aplicación: 127.0.0.1
Puerto del proxy: 9470
Aplicación: 8080
```

La aplicación inició correctamente y Flyway validó las migraciones disponibles.

### 5.1 Evidencia

Se confirmó la existencia de las tablas:

```text
siniestro_facil.evidencia
siniestro_facil.evidencia_version
```

La estructura observada fue:

#### `siniestro_facil.evidencia`

- `id bigint generated always as identity primary key`
- `siniestro_id bigint not null`
- `contenido_original text`
- `hash text`
- `metadatos_disponibles text`
- `fecha_recepcion timestamp with time zone`
- `fuente text`
- `transformaciones text`

FK:

```text
siniestro_id -> siniestro_facil.siniestro(id)
```

#### `siniestro_facil.evidencia_version`

- `id bigint generated always as identity primary key`
- `evidencia_id bigint not null`
- `transformacion text`

FK:

```text
evidencia_id -> siniestro_facil.evidencia(id)
```

### 5.2 Registro de evidencia validado

Se registró una evidencia sintética para el siniestro `108`.

Resultado persistido:

```text
id                    = 101
siniestro_id          = 108
contenido_original    = synthetic-reference-sprint02-001
hash                  = sha256-synthetic-sprint02-001
metadatos_disponibles = tipo=imagen;origen=synthetic
fuente                = SINTETICO
transformaciones      = NINGUNA
fecha_recepcion       = 2026-08-27 23:35:00+00
```

### 5.3 Versión de evidencia validada

Se registró directamente en PostgreSQL una versión de transformación para demostrar la relación entre evidencia y versión:

```text
id             = 101
evidencia_id   = 101
transformacion = TRANSFORMACION-SINTETICA-SPRINT-02
siniestro_id   = 108
```

La relación fue comprobada mediante `JOIN` entre `evidencia_version` y `evidencia`.

### 5.4 Asistencia validada

Para el siniestro `108` y proveedor `1` se confirmó persistencia en:

```text
siniestro_facil.asistencia
```

Se observaron registros:

```text
id | siniestro_id | proveedor_asistencia_id
52 | 108          | 1
51 | 108          | 1
```

La duplicidad observada corresponde a dos ejecuciones de prueba del mismo endpoint durante la validación. No se definió en este sprint una restricción de unicidad para asistencia.

---

## 6. Validación de endpoints

### 6.1 Evidencia — registrar

Solicitud utilizada:

```http
POST /api/v1/siniestros/108/evidencias
Content-Type: application/json
```

Resultado:

```text
HTTP/1.1 201
Location: /api/v1/evidencias/101
```

### 6.2 Evidencia — listar

```http
GET /api/v1/siniestros/108/evidencias
```

Resultado:

```text
HTTP/1.1 200
```

La respuesta incluyó la evidencia `101` asociada al siniestro `108`.

### 6.3 Evidencia — obtener por ID

```http
GET /api/v1/evidencias/101
```

Resultado:

```text
HTTP/1.1 200
```

Se verificó la recuperación completa de los datos persistidos.

### 6.4 Asistencia — solicitar

```http
POST /api/v1/siniestros/108/asistencia
Content-Type: application/json
```

Payload utilizado:

```json
{
  "proveedorAsistenciaId": 1
}
```

Resultado:

```text
HTTP/1.1 200
```

Respuesta validada:

```json
{
  "siniestroId": 108,
  "proveedorAsistenciaId": 1,
  "operacion": "SOLICITAR",
  "registrada": true
}
```

### 6.5 Asistencia — listar

```http
GET /api/v1/siniestros/108/asistencia
```

Resultado:

```text
HTTP/1.1 200
```

La respuesta confirmó los registros persistidos en la tabla `asistencia`.

### 6.6 Validación de error de path variable

Se realizó también una prueba negativa utilizando literalmente `{ID}` en:

```http
GET /api/v1/evidencias/{ID}
```

La aplicación respondió `422 BUSINESS_ERROR` porque el valor `ID` no puede convertirse a `Long`.

La prueba confirma que el error provenía del valor enviado y no del endpoint. Con el ID real `101`, el endpoint respondió `200`.

---

## 7. Pruebas automatizadas

Comando ejecutado:

```bash
mvn clean test
```

Resultado final:

```text
Tests run: 5
Failures: 0
Errors: 0
Skipped: 0
BUILD SUCCESS
```

Pruebas ejecutadas:

```text
AsistenciaServiceTest  -> 2 tests
CoberturaServiceTest   -> 2 tests
SiniestroServiceTest   -> 1 test
```

Total:

```text
5/5 tests exitosos
```

---

## 8. Validación estática

Script utilizado:

```bash
./validation/static_validation.sh
```

Resultado:

```text
[PASS] pom.xml existe
[PASS] Dockerfile existe
[PASS] driver PostgreSQL
[PASS] Flyway PostgreSQL
[PASS] migración idempotencia existe
[PASS] POST siniestros
[PASS] GET siniestros
[PASS] tabla siniestro
[PASS] historial
[PASS] auditoria
[PASS] Idempotency-Key
[PASS] protección contra duplicados
[PASS] conflicto de payload
[PASS] Firebase excluido del MVP
PASS=14 FAIL=0
```

**Nota:** esta validación estática comprueba 14 condiciones del baseline del MVP. No sustituye las pruebas funcionales específicas de cobertura, evidencia y asistencia descritas en este documento.

---

## 9. Configuración y ejecución

`application.yml` mantiene configuración parametrizada mediante variables de entorno:

```yaml
spring:
  datasource:
    url: ${DB_URL:jdbc:postgresql://localhost:5432/dmcsiniestrofacil}
    username: ${DB_USERNAME:postgres}
    password: ${DB_PASSWORD:}
```

El servidor mantiene:

```yaml
server:
  port: ${PORT:8080}
```

Para la validación contra Cloud SQL se utilizó:

```text
DB_URL=jdbc:postgresql://127.0.0.1:9470/dmcsiniestrofacil
```

No se documentan contraseñas ni secretos en el repositorio.

---

## 10. Migraciones

La migración existente:

```text
src/main/resources/db/migration/V1__crear_idempotencia_siniestro.sql
```

crea el esquema `siniestro_facil_meta` y la tabla `idempotencia_request`.

Durante el arranque del Sprint 02 Flyway informó:

```text
Successfully validated 2 migrations
Current version of schema "siniestro_facil_meta": 1
Schema "siniestro_facil_meta" is up to date.
```

También se recibió una advertencia de compatibilidad porque la base usa PostgreSQL 18.4 y la versión de Flyway utilizada declara soporte probado hasta PostgreSQL 17.

La advertencia no impidió la ejecución ni la validación de las migraciones.

---

## 11. Mockito / Byte Buddy

Durante `mvn clean test` se observaron advertencias relacionadas con el mecanismo inline de Mockito y la carga dinámica de agentes:

```text
Mockito is currently self-attaching to enable the inline-mock-maker.
Dynamic loading of agents will be disallowed by default in a future release.
```

Los tests finalizaron correctamente con `5/5`.

Este asunto queda identificado como **deuda técnica de compatibilidad futura del entorno de pruebas**, no como defecto funcional del Sprint 02.

---

## 12. Control de artefactos generados

Se incorporó el archivo `.gitignore` en la raíz del repositorio con:

```gitignore
# Maven
**/target/
```

Se verificó que los archivos `.class` generados bajo `target/` no fueran incluidos en Git.

La comprobación realizada con `git check-ignore` confirmó que:

```text
caso_de_uso_seguro/backend/sprint-01/target
```

queda correctamente ignorado.

---

## 13. Control de calidad Git

Antes del commit se verificó:

```bash
git diff --cached --check
```

Resultado: sin errores.

El staging final contenía 15 archivos:

```text
.gitignore
AsistenciaRequest.java
AsistenciaResponse.java
EvidenciaResponse.java
RegistrarEvidenciaRequest.java
AsistenciaPort.java
EvidenciaPort.java
AsistenciaService.java
EvidenciaService.java
SyntheticAsistenciaAdapter.java
JdbcAsistenciaRepository.java
JdbcEvidenciaRepository.java
AsistenciaController.java
EvidenciaController.java
AsistenciaServiceTest.java
```

No se incluyó `target/`.

---

## 14. Historial Git del Sprint 02

El branch quedó con los siguientes commits relevantes:

```text
fdcac33 feat(sprint-02): complete coverage assistance and evidence
0893623 feat(sprint-02): add synthetic coverage validation
aa7ddea docs(sprint-02): resolve coverage and assistance gaps
```

El commit `fdcac33` fue publicado en:

```text
feature/sprint-02-cobertura-asistencia-evidencia
```

El branch remoto quedó actualizado después del `git push`.

---

## 15. Matriz de cierre

| Área | Evidencia | Estado |
|---|---|---|
| Cobertura sintética | Port + Service + Adapter + 2 tests | CERRADO |
| Evidencia registro | POST devuelve 201 | CERRADO |
| Evidencia listado | GET devuelve 200 | CERRADO |
| Evidencia consulta | GET por ID devuelve 200 | CERRADO |
| Evidencia persistencia | Registro `101` en PostgreSQL | CERRADO |
| Versionado de evidencia | `evidencia_version` validada | CERRADO |
| Asistencia solicitud | POST devuelve 200 y `registrada=true` | CERRADO |
| Asistencia listado | GET devuelve 200 | CERRADO |
| Asistencia persistencia | Registros en `asistencia` | CERRADO |
| Tests automatizados | 5/5 exitosos | CERRADO |
| Validación estática | 14/14 PASS | CERRADO |
| Artefactos Maven | `target/` ignorado | CERRADO |
| Reintentar asistencia | No expuesto por REST | DIFERIDO |
| Escalar asistencia | No expuesto por REST | DIFERIDO |
| Reasignar asistencia | No expuesto por REST | DIFERIDO |
| Mockito/Byte Buddy | Warning de compatibilidad futura | DEUDA TÉCNICA |
| Flyway/PostgreSQL 18.4 | Warning de soporte probado | OBSERVACIÓN |

---

## 16. Criterio de cierre

El Sprint 02 se considera **técnicamente cerrado** porque:

1. El código compila correctamente.
2. Las pruebas automatizadas terminan con `5/5` exitosas.
3. La validación estática termina con `PASS=14 FAIL=0`.
4. La aplicación arranca correctamente en puerto `8080`.
5. La aplicación se conecta a PostgreSQL 18.4 mediante Cloud SQL Proxy.
6. Evidencia se registra, lista y consulta mediante REST.
7. Evidencia y su relación de versionado fueron verificadas en PostgreSQL.
8. Asistencia se registra y lista mediante REST.
9. La persistencia de asistencia fue comprobada directamente en PostgreSQL.
10. Los artefactos Maven quedan excluidos mediante `.gitignore`.
11. El código fue organizado en un commit limpio y publicado en el branch del Sprint 02.

Las operaciones de asistencia `REINTENTAR`, `ESCALAR` y `REASIGNAR` quedan fuera de la superficie REST validada en este incremento y podrán abordarse en una segunda fase si el alcance funcional las requiere.

---

## 17. Referencias Git

- Commit de cobertura: `08936231505e4a6def7431934988a4a5158eb3c7`
- Commit de asistencia/evidencia: `fdcac339aa7e44fd6f8d194bde91014018ed470b`
- Branch: `feature/sprint-02-cobertura-asistencia-evidencia`
