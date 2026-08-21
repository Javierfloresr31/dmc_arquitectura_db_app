# Estrategia de Pruebas Backend — Siniestro Fácil

## 1. Objetivo

Validar que cada historia implementada cumple sus criterios de aceptación y que las reglas, estados, persistencia, seguridad e integraciones se comportan según la especificación.

## 2. Pirámide

### Unitarias
- reglas de dominio;
- transiciones de estado;
- validaciones;
- políticas antifraude;
- idempotencia.

### Integración
- repositorios PostgreSQL;
- transacciones;
- FK/PK/NOT NULL/CHECK;
- adaptadores externos.

El modelo físico define PK/FK y el `CHECK` de `SINIESTRO_RELACION`; existe además un script específico de validación de constraints. fileciteturn51file0

### API
- contrato de request/response;
- códigos HTTP;
- autenticación/autorización;
- errores;
- correlación;
- idempotencia.

### Contrato
Validar que los adaptadores respeten los contratos definidos para pólizas, asistencia, talleres, mensajería, mapas y pagos una vez que estos contratos reales estén disponibles.

### E2E
Validar el flujo completo desde reporte hasta autorización/pago/cierre según alcance del sprint.

## 3. Casos críticos

1. Crear siniestro válido.
2. Rechazar creación sin póliza/vehículo requeridos.
3. Consultar expediente autorizado.
4. Rechazar acceso de rol no autorizado.
5. Registrar evidencia y conservar trazabilidad.
6. Rechazar transición inválida.
7. Registrar alerta antifraude.
8. Registrar revisión humana con justificación.
9. Reintentar operación idempotente sin duplicar efecto.
10. Repetir clave idempotente con payload distinto y obtener conflicto.
11. Simular proveedor sin respuesta y verificar retry/escalamiento.
12. Impedir duplicidad de pago según política aprobada.
13. Validar auditoría de operaciones sensibles.
14. Validar relaciones FK y constraints.

## 4. Datos de prueba

Se dispone de data sintética validada visualmente en Cloud SQL. Las pruebas destructivas de constraints deben ejecutarse en una base/ambiente de prueba separado porque el script `16-validacion-constraints-postgresql.sql` realiza `TRUNCATE ... CASCADE` y termina con `ROLLBACK`. fileciteturn51file15

## 5. Trazabilidad

Cada prueba debe identificar:

```text
HU -> RF -> CA -> caso de prueba -> implementación -> resultado
```

## 6. Calidad mínima por sprint

- pruebas unitarias de reglas modificadas;
- pruebas de integración de persistencia modificada;
- pruebas API de endpoints nuevos;
- pruebas negativas de autorización y validación;
- regresión de estados afectados;
- evidencia de ejecución.

## 7. Seguridad

Incluir pruebas de:
- autenticación inválida;
- autorización por rol;
- acceso a expediente ajeno;
- acceso a evidencia sensible;
- consulta antifraude restringida;
- auditoría.

## 8. Resiliencia

Incluir pruebas de:
- timeout;
- retry;
- proveedor indisponible;
- respuesta duplicada;
- respuesta tardía;
- error permanente;
- reprocesamiento.

## 9. Performance

Los objetivos cuantitativos deben tomarse de RNF aprobados; donde no exista un valor en las fuentes, se debe definir antes de convertirlo en una prueba de performance obligatoria.

## 10. Criterio de salida

Un sprint no se cierra si existe una CA crítica sin prueba, una transición inválida no cubierta, una vulnerabilidad de autorización conocida o un fallo de integración no tratado para el alcance del sprint.