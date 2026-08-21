# Seguridad y RBAC — Siniestro Fácil

## 1. Base

Las entrevistas establecen acceso restringido por rol y necesidad, especialmente para información de fraude y evidencia. Las descargas de evidencia y consultas sensibles deben quedar registradas. fileciteturn51file1

## 2. Actores identificados

- ASEGURADO
- REPORTANTE_AUTORIZADO
- OPERADOR
- AJUSTADOR
- INVESTIGADOR_FRAUDE
- TALLER
- PROVEEDOR_ASISTENCIA
- SUPERVISOR

No se asignan permisos concretos que no estén soportados por las entrevistas; esta matriz es baseline para refinamiento.

## 3. Matriz inicial

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

## 4. Principio de mínimo privilegio

- El cliente solo accede a su expediente.
- Un operador no obtiene automáticamente acceso ampliado a investigación de fraude.
- Un investigador puede consultar información ampliada según necesidad.
- Acciones sensibles generan auditoría.
- La autorización de pago no debe depender únicamente de una recomendación de IA.

## 5. Datos personales

Las entrevistas identifican riesgo de exposición de datos personales y exigen controles. fileciteturn51file3

La implementación deberá separar autorización funcional de filtrado de campos sensibles cuando corresponda.

## 6. Autenticación

No se fija todavía proveedor, protocolo concreto ni servidor de identidad. Debe definirse como decisión de arquitectura antes del Sprint 1 productivo.

## 7. Preguntas bloqueantes

1. ¿Qué mecanismo de identidad se utilizará?
2. ¿Cómo se vincula una identidad con asegurado/reportante/empleado?
3. ¿Qué permisos exactos tiene cada rol?
4. ¿Qué campos se consideran sensibles?
5. ¿Qué acciones requieren autorización adicional/step-up?
