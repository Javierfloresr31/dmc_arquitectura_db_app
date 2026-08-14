# Siniestro Fácil — Especificación SDD en 12 bloques

> Estado: versión inicial para validación.
> Fuente de verdad: entrevistas y evidencias entregadas. Los elementos no confirmados se marcan como pendientes.

## 01. Identidad

**Producto:** Siniestro Fácil  
**Dominio:** seguros vehiculares  
**Propósito:** modernizar el registro, seguimiento y gestión de siniestros vehiculares de daños materiales sin lesiones graves.

La solución busca proporcionar una experiencia guiada al asegurado y una vista única del expediente para la operación. El piloto previsto por negocio es en una ciudad y con un grupo controlado de talleres.

## 02. Contexto

El proceso actual es manual y fragmentado. Existen reportes por teléfono, correo, aplicación móvil o corredor; la información se transcribe varias veces y las fotografías pueden quedar en ubicaciones distintas. Esto genera poca transparencia, duplicidad y dificultades de auditoría. fileciteturn19file4L1-L1

Las entrevistas identifican como tensiones principales: rapidez vs. control de fraude, experiencia simple vs. evidencia suficiente, automatización vs. revisión humana, expediente único vs. múltiples participantes/reclamos relacionados y procesos síncronos vs. coordinaciones asíncronas con terceros. fileciteturn19file12L1-L1

## 03. Objetivos

1. Permitir reportar el accidente desde el teléfono.
2. Facilitar asistencia, carga de evidencias y seguimiento.
3. Proporcionar una vista única del caso a los equipos internos.
4. Permitir que los casos simples sigan una vía rápida y que los riesgosos reciban revisión especializada.
5. Reducir llamadas adicionales mediante información clara del siguiente paso.
6. Mantener controles contra fraude, pagos incorrectos y manipulación de evidencia.
7. Mantener decisiones sensibles revisables y explicables.
8. Medir tiempos de asistencia y decisión, resolución sin llamadas adicionales, satisfacción, costo operativo y pérdidas evitadas por fraude. fileciteturn19file6L1-L1

## 04. Alcance

### Incluido
- Siniestros vehiculares con daños materiales y sin lesiones graves.
- Clientes directos con pólizas vigentes.
- Reporte, validación inicial, cobertura, deducible, asistencia, evidencias, asignación, evaluación, inspección, presupuesto, autorización, reparación, indemnización y cierre.
- Gestión de alertas de riesgo y revisión humana.
- Relación entre casos y participantes sin fusionar expedientes incorrectamente.
- Trazabilidad de cambios, evidencias, proveedores, presupuestos, comunicaciones y pagos.

### Fuera del alcance inicial
Casos con heridos, fallecidos, procesos legales o daños masivos, que continúan por rutas especializadas. fileciteturn19file6L1-L1

### Pendiente
Ciudad del piloto, talleres del piloto, criterios exactos de expansión y demás parámetros no definidos en entrevistas.

## 05. Actores

| Actor | Participación sustentada |
|---|---|
| Asegurado | Reporta y consulta el siniestro |
| Reportante autorizado | Puede reportar si el titular no puede hacerlo |
| Operador | Gestiona el expediente y coordinación operativa |
| Ajustador | Atiende casos complejos |
| Investigador de fraude | Investiga alertas y relaciones sospechosas |
| Taller | Recibe orden, presenta presupuesto y diagnóstico |
| Proveedor de grúa | Atiende asistencia |
| Supervisor | Participa en supervisión operativa |

Actores adicionales deberán confirmarse antes del diseño definitivo de autorización y roles.

## 06. Procesos

1. Reportar siniestro.
2. Confirmar identidad, póliza y vehículo.
3. Registrar fecha, hora, ubicación aproximada, participantes, descripción y daños aparentes.
4. Validar cobertura y deducible.
5. Coordinar asistencia cuando corresponda.
6. Asignar el caso según ciudad, daño, severidad, cobertura, disponibilidad y señales de riesgo.
7. Recopilar evidencias.
8. Programar inspección cuando corresponda.
9. Recibir y gestionar presupuesto del taller.
10. Autorizar, observar o rechazar según reglas aún por cerrar.
11. Gestionar reparación, entrega, indemnización y cierre.
12. Registrar comunicaciones y línea de tiempo completa.
13. Ejecutar controles de riesgo/fraude y revisión humana.

Los estados de negocio identificados incluyen Reportado, Validando cobertura, Asistencia coordinada, Evidencia pendiente, En evaluación, Inspección programada, Presupuesto recibido, Autorizado, Observado, Rechazado, En reparación, Listo para entrega, Indemnizado y Cerrado. fileciteturn19file10L1-L1

## 07. Historias

Las historias se mantienen en `01_historias_usuario.md`. El catálogo deberá crecer únicamente cuando una nueva necesidad tenga fuente en entrevista o en una decisión de negocio explícitamente aprobada.

## 08. RF

Los requerimientos funcionales se mantienen en `02_requerimientos.md`. Deben expresar qué debe hacer la solución, sin convertir decisiones técnicas no aprobadas en requisitos.

## 09. RNF

Los requerimientos no funcionales se mantienen en `02_requerimientos.md`. Las entrevistas sustentan, entre otros, privacidad, trazabilidad, evidencia inmutable, tolerancia a indisponibilidad de proveedores y revisión humana. La tecnología concreta queda pendiente.

## 10. Reglas

Las reglas confirmadas incluyen: conservar evidencia original; conservar hash, metadatos, recepción, fuente y transformaciones; no considerar una alerta como fraude confirmado; permitir revisión humana; registrar justificación de la revisión; restringir acceso según necesidad; conservar valor declarado y valor normalizado por separado; y mantener reproducibilidad de las alertas mediante versiones, datos de entrada y evidencia de revisión. fileciteturn19file1L1-L1

Los umbrales, combinación de señales y política exacta de bloqueo/priorización de alertas deben ser configurables y versionados, pero sus valores concretos no están definidos. fileciteturn19file3L1-L1

## 11. Criterios

Los criterios de aceptación se mantienen en `03_criterios_aceptacion.md`. Todo RF nuevo debe tener criterios verificables y trazabilidad hacia una historia o fuente de negocio.

## 12. Preguntas

Las preguntas abiertas se mantienen en `05_preguntas_abiertas.md`. No deben resolverse mediante supuestos técnicos.

Preguntas críticas adicionales para los modelos:

- ¿Cuál es la definición oficial de identidad y reportante autorizado?
- ¿Qué identificadores son únicos para póliza, vehículo, persona, siniestro y participante?
- ¿Cuál es la política de deduplicación y relación entre expedientes?
- ¿Qué atributos son obligatorios por etapa?
- ¿Cuál es la política de conservación y eliminación de evidencias?
- ¿Cuál es la estructura oficial de cobertura/deducible?
- ¿Qué reglas determinan los estados y transiciones?
- ¿Qué datos de talleres, grúas, ajustadores y pagos son autoridad en cada sistema?

## Control de fuente

No se incorporan como hechos datos técnicos, tecnologías, motores de base de datos, APIs concretas, algoritmos específicos ni SLA numéricos no contenidos en las entrevistas. Esto sigue la regla ATLAS de no inventar información. fileciteturn19file9L1-L1
