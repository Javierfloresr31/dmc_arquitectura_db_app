# Siniestro Fácil — Discrepancias y Vacíos Pendientes

## 1. Objetivo

Registrar únicamente asuntos que las entrevistas plantean pero no resuelven. Estos puntos deben cerrarse antes de considerar la especificación completa.

## 2. Discrepancias prioritarias

| ID | Discrepancia / vacío | Evidencia | Impacto |
|---|---|---|---|
| D-01 | Política exacta de deduplicación de siniestros | EV-04, OPS-06 | Alto |
| D-02 | Mecanismo para acreditar a un reportante autorizado | CEO-06 | Alto |
| D-03 | Reglas exactas de cobertura y deducible | OPS-01 | Alto |
| D-04 | Transiciones y condiciones de los estados | OPS-04 | Alto |
| D-05 | Criterios/umbrales de asignación de casos | OPS-05 | Alto |
| D-06 | SLA exactos por tipo de siniestro, ubicación y región | OPS-08, EV-04 | Alto |
| D-07 | Reglas de reintento, escalamiento y reasignación de proveedores | OPS-09 | Medio/Alto |
| D-08 | Vigencia exacta de presupuestos | OPS-07 | Medio |
| D-09 | Reglas de aprobación de presupuestos, cambios y ampliaciones | OPS-07 | Alto |
| D-10 | Umbrales antifraude y combinación de señales | FRA-05 | Alto |
| D-11 | Cuándo una alerta puede detener temporalmente un pago | FRA-05 | Alto |
| D-12 | Política de conservación/retención de imágenes originales | EV-04, FRA-03 | Alto |
| D-13 | Matriz de roles y permisos | FRA-07 | Alto |
| D-14 | Métricas objetivo y umbrales de falsos positivos para IA | FRA-06 | Alto |
| D-15 | Modelos/reglas concretos y su versionado técnico | FRA-04, FRA-10 | Medio/Alto |
| D-16 | Procedimiento detallado de inspección | OPS-04 | Medio |
| D-17 | Formatos y contratos de integración con terceros | CEO-09 | Alto |
| D-18 | Comportamiento ante indisponibilidad de cada proveedor | CEO-09, OPS-09 | Alto |
| D-19 | Ciudad y talleres seleccionados para el piloto | CEO-10 | Medio |
| D-20 | Duración y criterios de salida del piloto | CEO-10 | Medio |
| D-21 | Canales/frecuencia de comunicaciones al asegurado | CEO-03, CEO-06 | Medio |
| D-22 | Política de relación entre múltiples reclamos de un mismo accidente | FRA-08 | Alto |
| D-23 | Cardinalidades y claves del modelo de datos | Entrevistas, EV-02/03 | Alto |
| D-24 | Motor de persistencia y modelo físico | Entrevistas | Alto |
| D-25 | Retención legal de datos y evidencias | Entrevistas | Alto |
| D-26 | Requisitos cuantitativos de rendimiento/disponibilidad | Entrevistas | Medio/Alto |
| D-27 | Arquitectura concreta para integraciones sin APIs modernas | CEO-09 | Alto |
| D-28 | Política técnica de seguridad/cifrado | CEO-07, FRA-07 | Alto |

## 3. Tensiones explícitas del reto

### D-29 — Rapidez vs. fraude
Las entrevistas exigen velocidad y control de fraude, pero no definen la política exacta para equilibrarlos.

### D-30 — Simplicidad vs. evidencia suficiente
Operaciones indica que no siempre puede exigirse evidencia al inicio; no existe una matriz que determine qué evidencia es obligatoria por escenario.

### D-31 — Automatización vs. revisión humana
La IA puede recomendar, pero las decisiones sensibles deben ser revisables. No se define qué decisiones se consideran sensibles en cada flujo.

### D-32 — Expediente único vs. casos relacionados
Debe existir relación entre expedientes sin fusionarlos. No se define el modelo operativo para manejar un accidente con varias pólizas/reclamos.

### D-33 — Original vs. versión optimizada
Debe preservarse el original aunque exista compresión/transformación. No se define almacenamiento, retención ni recuperación.

### D-34 — Síncrono vs. asíncrono
Los proveedores pueden estar lentos o indisponibles. No se define el mecanismo de integración ni los límites temporales.

## 4. Información que NO debe agregarse todavía

No existe soporte en las entrevistas para afirmar:

- una arquitectura cloud concreta;
- microservicios;
- API REST;
- Kafka/RabbitMQ;
- Oracle/PostgreSQL u otro motor;
- Kubernetes;
- AWS/Azure/GCP;
- JWT/OAuth;
- AES/RSA;
- SLA numéricos;
- RTO/RPO;
- índices o particiones;
- esquema físico de almacenamiento;
- tecnologías específicas de IA.

Estas decisiones deberán aparecer como arquitectura/diseño únicamente después de cerrar los vacíos correspondientes.

## 5. Validación de trazabilidad

### Tareas cubiertas

| Tarea | Estado | Trazabilidad |
|---|---|---|
| Historias de usuario | Cubierta | CEO, OPS, FRA, EV |
| Criterios de aceptación | Cubierta | CEO, OPS, FRA |
| Requerimientos funcionales | Cubierta | CEO, OPS, FRA |
| Requerimientos no funcionales | Cubierta con límites | CEO, OPS, FRA, EV |
| Casos de uso | Cubierta | CEO, OPS, FRA, EV |
| Mapa de procesos | Cubierta con límites | CEO, OPS, FRA |
| Modelo conceptual | Cubierta | EV, OPS, FRA |
| Modelo lógico | Parcial, solo datos explícitos | EV, OPS, FRA |
| Modelo físico | No definible sin inventar | Vacíos identificados |
| ER conceptual/lógico | Cubierto | Entidades y relaciones explícitas |
| ER físico | Pendiente | D-23/D-24 |
| Discrepancias | Cubierta | Todas derivadas de entrevistas |

## 6. Resultado de autovalidación

**No se identifican funcionalidades o reglas de negocio incorporadas sin origen.**

El principal límite está en el modelo físico y en los parámetros cuantitativos: las entrevistas no contienen información suficiente para definirlos. Se dejan explícitamente como pendientes en lugar de inventarlos.
