# Seguridad y RBAC — Siniestro Fácil

## 1. Base

Las entrevistas establecen acceso restringido por rol y necesidad, especialmente para información de fraude y evidencia. Las descargas de evidencia y consultas sensibles deben quedar registradas.

## 2. Identidad — decisión cerrada

Se utilizará **Firebase Authentication** como mecanismo de autenticación del sistema.

Flujo:

```text
Cliente
  ↓
Firebase Authentication
  ↓
Firebase ID Token
  ↓
API Gateway
  ↓
Cloud Run
  ↓
Validación de identidad
  ↓
Firebase UID + claims
  ↓
Autorización funcional RBAC
```

El backend no confiará en un `userId` enviado por el cliente para determinar la identidad del actor. La identidad efectiva se obtiene del contexto autenticado.

## 3. Actores identificados

- ASEGURADO
- REPORTANTE_AUTORIZADO
- OPERADOR
- AJUSTADOR
- INVESTIGADOR_FRAUDE
- TALLER
- PROVEEDOR_ASISTENCIA
- SUPERVISOR

Estos roles representan el baseline funcional. La asignación definitiva de permisos por endpoint se cerrará antes de publicar cada API.

## 4. Matriz inicial

| Capacidad | Asegurado | Reportante | Operador | Ajustador | Fraude | Taller | Proveedor | Supervisor |
|---|---:|---:|---:|---:|---:|---:|---:|---:|
| Crear/consultar propio siniestro | ✓ | ✓ | ✓ | - | - | - | - | ✓ |
| Consultar expediente operativo | propio | propio | ✓ | ✓ | ampliado | asignado | asignado | ✓ |
| Registrar evidencia | ✓ | ✓ | ✓ | ✓ | ✓ | según flujo | - | ✓ |
| Descargar evidencia original | propio/autorizado | autorizado | según necesidad | según necesidad | ✓ | - | - | ✓ |
| Gestionar alerta antifraude | - | - | - | - | ✓ | - | - | ✓ |
| Registrar revisión antifraude | - | - | - | - | ✓ | - | - | ✓ |
| Registrar presupuesto | - | - | ✓ | ✓ | - | ✓ | - | ✓ |
| Autorizar reparación | - | - | según delegación | según delegación | - | - | - | ✓ |
| Gestionar pago | - | - | según proceso | - | - | - | - | ✓ |
| Consultar auditoría sensible | - | - | restringido | restringido | ✓ | - | - | ✓ |

Los permisos marcados como `según delegación`, `según necesidad` o `autorizado` requieren definición final antes del endpoint productivo.

## 5. Principio de mínimo privilegio

- El cliente solo accede a su expediente.
- Un operador no obtiene automáticamente acceso ampliado a investigación de fraude.
- Un investigador puede consultar información ampliada según necesidad.
- Acciones sensibles generan auditoría.
- La autorización de pago no debe depender únicamente de una recomendación de IA.

## 6. Datos personales

Las entrevistas identifican riesgo de exposición de datos personales y exigen controles.

La implementación deberá separar autorización funcional de filtrado de campos sensibles cuando corresponda.

## 7. Claims y autorización

Firebase Authentication proporciona la identidad y los claims disponibles para autorización. El backend Cloud Run será responsable de aplicar las reglas funcionales y de impedir acceso a recursos fuera del alcance del actor.

No se define todavía quién administra los claims ni el catálogo definitivo de permisos, porque esa decisión no está soportada por las fuentes disponibles.

## 8. Auditoría

Como mínimo deberán trazarse las operaciones sensibles sobre evidencia, antifraude, autorización, pago y cambios relevantes del expediente, conforme al modelo de auditoría aprobado.

## 9. Decisiones pendientes no bloqueantes del baseline

1. Responsable operativo de asignación de roles/claims.
2. Permisos exactos por endpoint.
3. Campos considerados sensibles por cada recurso.
4. Operaciones que requieren step-up authentication.
5. Política de revocación/desactivación de cuentas.
