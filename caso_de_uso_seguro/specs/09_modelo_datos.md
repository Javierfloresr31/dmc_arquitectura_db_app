# Roadmap de Datos — Siniestro Fácil

## Propósito

Establecer el artefacto que guiará la construcción progresiva del modelo conceptual, lógico y físico y de los datos sintéticos, manteniendo trazabilidad con las entrevistas y la especificación SDD.

## 1. Modelo conceptual

### Entidades candidatas derivadas de las entrevistas

- Póliza
- Vehículo
- Siniestro
- Participante
- Cobertura
- Evidencia
- Asistencia
- Inspección
- Taller
- Presupuesto
- Autorización
- Alerta antifraude
- Pago
- Comunicación
- Evento de auditoría

### Relaciones que deberán validarse

- Un asegurado/reportante crea o reporta un siniestro.
- Un siniestro se relaciona con una póliza y un vehículo.
- Un siniestro puede involucrar múltiples participantes.
- Un siniestro puede tener múltiples evidencias.
- Un siniestro puede requerir asistencia.
- Un siniestro puede tener inspección/evaluación.
- Un taller puede presentar uno o más presupuestos/versiones para un siniestro.
- Un siniestro puede generar alertas antifraude.
- Un siniestro puede tener autorizaciones y pagos.
- Las comunicaciones y eventos de auditoría se vinculan con el expediente.

Estas relaciones son candidatas y deben contrastarse contra las preguntas abiertas antes de considerarse definitivas.

## 2. Modelo lógico

Debe definir para cada entidad:

- identificador;
- atributos obligatorios/opcionales;
- claves primarias y foráneas;
- cardinalidad;
- dominios y catálogos;
- reglas de unicidad;
- versionado cuando aplique;
- relación con auditoría;
- relación entre valor declarado y valor normalizado cuando aplique.

## 3. Modelo físico

Se construirá solo después de confirmar el motor y restricciones de plataforma. Debe incluir:

- tablas;
- columnas y tipos;
- PK/FK;
- constraints;
- índices;
- estrategia de auditoría;
- estrategia de retención;
- particionamiento si fuera necesario y justificado;
- control de versiones de esquema;
- gestión de blobs/metadatos de evidencias, si corresponde.

## 4. Data sintética

### Objetivos

Proveer datos reproducibles para:

- desarrollo;
- pruebas unitarias y de integración;
- pruebas de aceptación;
- demostraciones;
- pruebas antifraude;
- pruebas de deduplicación;
- pruebas de resiliencia e integraciones.

### Principios

1. No utilizar datos personales reales.
2. Mantener relaciones válidas entre entidades.
3. Permitir escenarios normales y excepcionales.
4. Ser reproducible mediante una semilla o mecanismo equivalente que se definirá en diseño técnico.
5. Permitir limpiar y regenerar el conjunto.
6. Separar datos de referencia de datos transaccionales.

### Escenarios sintéticos mínimos

- siniestro simple con flujo digital;
- siniestro con evidencia pendiente;
- siniestro con asistencia requerida;
- presupuesto recibido y aprobado;
- presupuesto observado y nueva versión;
- caso derivado a ajustador;
- alerta antifraude descartada;
- alerta antifraude confirmada por revisión humana;
- caso relacionado con otro sin fusionar expedientes;
- proveedor que no responde;
- intento de duplicidad de siniestro;
- intento de duplicidad de pago;
- evidencia original y versiones derivadas;
- datos declarados diferentes de datos normalizados.

### Entregables futuros

- esquema de dataset;
- archivos de carga/seed;
- datos de referencia;
- datos transaccionales;
- catálogo de escenarios;
- guía de regeneración;
- controles de consistencia.

## 5. Orden de trabajo

1. cerrar preguntas de negocio que afecten entidades y cardinalidades;
2. construir modelo conceptual;
3. validar modelo conceptual;
4. convertir a modelo lógico;
5. validar integridad y normalización;
6. definir modelo físico según plataforma;
7. crear estrategia de seed;
8. generar data sintética;
9. ejecutar pruebas con los escenarios sintéticos;
10. actualizar el modelo y dataset cada vez que cambie una regla confirmada.
