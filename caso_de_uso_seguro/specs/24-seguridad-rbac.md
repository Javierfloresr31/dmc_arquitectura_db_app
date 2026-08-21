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

## 6. Claims Firebase

Firebase Custom Claims se utilizarán únicamente para control de acceso, principalmente roles/perfiles mínimos. No se utilizarán para almacenar datos de perfil extensos, información de negocio ni atributos que deban cambiar frecuentemente.

El backend debe tratar los claims como entrada de autorización y aplicar además reglas de pertenencia/alcance del recurso. Tener un rol válido no implica acceso irrestricto a cualquier siniestro.

## 7. Step-up authentication

**Recomendación:** evaluar step-up authentication para operaciones de alto impacto, especialmente autorización de pagos, cambios críticos y acciones sensibles sobre evidencia. La activación concreta queda pendiente de riesgo y política de seguridad.

## 8. Datos sensibles

Como baseline de seguridad se consideran sensibles:
- datos personales;
- documentos de identidad;
- evidencia original;
- información y decisiones antifraude;
- información asociada a pagos;
- auditoría de operaciones sensibles.

La clasificación definitiva debe validarse con seguridad/legal.

## 9. Auditoría

Como mínimo deberán trazarse las operaciones sensibles sobre evidencia, antifraude, autorización, pago y cambios relevantes del expediente.

## 10. Administración de roles

Recomendación: administración centralizada de roles mediante una función administrativa controlada, con mínimo privilegio y trazabilidad. No se recomienda permitir que un usuario final modifique sus propios claims.

## 11. Decisiones pendientes

1. Responsable operativo de administración de roles/claims.
2. Permisos exactos por endpoint.
3. Campos sensibles definitivos.
4. Operaciones que requieren step-up authentication.
5. Política de revocación/desactivación de cuentas.
