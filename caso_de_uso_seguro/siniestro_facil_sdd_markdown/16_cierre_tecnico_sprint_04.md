# Cierre técnico — Sprint 04
## Antifraude y revisión humana

**Proyecto:** Siniestro Fácil
**Sprint:** 04
**Rama:** `feature/sprint-04-antifraude-revision-humana`
**Fecha de cierre:** 2026-09-02

---

## 1. Objetivo

Implementar el flujo de evaluación antifraude y revisión humana definido para el Sprint 04, manteniendo la separación entre señal/alerta antifraude y decisión humana.

La implementación no determina automáticamente que exista fraude. La alerta constituye una señal que requiere revisión humana según las reglas definidas en el SDD.

---

## 2. Alcance implementado

Se implementaron:

- evaluación antifraude;
- generación de alertas antifraude;
- generación de señales sintéticas;
- clasificación de severidad;
- versionado de regla/modelo;
- persistencia de alertas;
- persistencia de señales;
- revisión humana;
- justificación de la decisión;
- actualización del estado de la alerta;
- auditoría de operaciones sensibles.

---

## 3. Evaluación antifraude

La evaluación antifraude utiliza un adaptador sintético para el Sprint 04.

La evaluación conserva:

- tipo de alerta;
- severidad;
- explicación;
- datos de origen;
- fecha de evaluación;
- regla o modelo utilizado;
- versión de regla/modelo.

Las nuevas alertas con severidad `BAJA`, `MEDIA` o `ALTA` quedan inicialmente en:

`PENDIENTE_REVISION`

Esto evita interpretar automáticamente una señal como fraude confirmado.

---

## 4. Versionado de regla/modelo

La evaluación recibe una versión de regla/modelo previamente registrada.

La alerta conserva la referencia a dicha versión para permitir trazabilidad de la evaluación.

Evidencia E2E:

- `reglaModeloVersionId = 3`
- regla/modelo: `REGLA_SYN-V5`

---

## 5. Revisión humana

El contrato implementado es:

`POST /api/v1/alertas/{id}/revision`

La revisión requiere:

- resultado;
- justificación.

Resultados soportados:

- `CONFIRMADA`
- `DESCARTADA`
- `MAS_INFORMACION`

Correspondencia:

| Resultado humano | Estado de alerta |
|---|---|
| `CONFIRMADA` | `CONFIRMADA` |
| `DESCARTADA` | `DESCARTADA` |
| `MAS_INFORMACION` | `PENDIENTE_REVISION` |

Una alerta antifraude no equivale por sí misma a fraude confirmado.

---

## 6. Persistencia

Se utilizan las entidades:

- `alerta_antifraude`
- `alerta_senal`
- `revision_antifraude`
- `regla_modelo_version`
- `auditoria`

La justificación de la decisión humana se conserva en `revision_antifraude`.

---

## 7. Integridad transaccional

El registro de una revisión humana se ejecuta mediante `@Transactional`.

La operación coordina como una unidad:

1. registro de la revisión;
2. actualización del estado de la alerta;
3. registro de auditoría.

Esto evita confirmar parcialmente una revisión si una operación posterior falla.

---

## 8. Auditoría

Las operaciones sensibles generan trazabilidad.

En la prueba E2E se obtuvo:

237 | ALERTA_ANTIFRAUDE   | 27
238 | REVISION_ANTIFRAUDE | 27

Por tanto, tanto la creación de la alerta como la revisión humana quedaron registradas en auditoría.
## 9. Evidencia E2E

Se ejecutó una evaluación para el siniestro `27`.

Resultado de evaluación:

alerta:        27
siniestro:     27
tipo:          IMAGEN_REUTILIZADA
severidad:     BAJA
modelo/regla:  REGLA_SYN-V5

La alerta quedó inicialmente en:

`PENDIENTE_REVISION`

Posteriormente se registró la revisión humana:

revisión:      27
resultado:     DESCARTADA

Justificación:

E2E contrato SDD Sprint 04: señal sintetica insuficiente para confirmar fraude

Estado final de la alerta:

`DESCARTADA`

---

## 10. Validación automatizada

### Pruebas Maven

Resultado:

Tests run: 16
Failures: 0
Errors: 0
Skipped: 0
BUILD SUCCESS

### Validación estática

Resultado:

PASS=14
FAIL=0

---

## 11. Alineamiento con el SDD

El endpoint de revisión humana fue alineado con el contrato definido en:

`specs/21-contratos-api.md`

Contrato:

`POST /api/v1/alertas/{id}/revision`

Se retiraron del controlador los endpoints GET de revisiones antifraude que no estaban especificados ni referenciados en el SDD ni utilizados por otros componentes del backend.

---

## 12. Reglas de negocio respetadas

El Sprint 04 mantiene:

- una inconsistencia no implica automáticamente fraude;
- las alertas requieren revisión humana;
- las decisiones sensibles deben ser trazables;
- la justificación humana debe conservarse;
- la versión de regla/modelo debe quedar identificada;
- las operaciones sensibles deben generar auditoría.

---

## 13. Fuera de alcance

No se implementan decisiones que el SDD no haya definido.

En particular:

- determinación automática definitiva de fraude por IA;
- eliminación o fusión automática de siniestros duplicados;
- umbrales económicos no definidos;
- políticas antifraude adicionales no especificadas.

Estos elementos requieren definición funcional y/o arquitectónica antes de incorporarse.

---

## 14. Estado de cierre

**Sprint 04 — CERRADO TÉCNICAMENTE**

Evidencias:

- código compilado;
- 16/16 pruebas exitosas;
- validación estática 14/14;
- endpoint contractual validado;
- E2E ejecutado;
- revisión humana persistida;
- auditoría de alerta y revisión persistida;
- integridad transaccional aplicada.

---

**Documento:** Cierre técnico Sprint 04
**Estado:** Cerrado
