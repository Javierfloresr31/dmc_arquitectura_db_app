# Modelos de datos y data sintética — Siniestro Fácil

## 1. Objetivo
Definir la ruta de modelado desde el dominio hasta la persistencia y preparar datos sintéticos reproducibles para desarrollo, pruebas y demostración.

## 2. Modelo conceptual

### Fuente
El modelo conceptual se construirá exclusivamente desde los objetos de negocio y procesos confirmados.

### Entidades candidatas iniciales
- Póliza
- Vehículo
- Siniestro
- Participante
- Cobertura
- Evidencia
- Asistencia
- Inspección
- Presupuesto
- Autorización
- Alerta
- Pago

### Relaciones a determinar
Las cardinalidades exactas deberán validarse contra las preguntas abiertas y las reglas de negocio antes de considerarse definitivas.

### Entregables
- diagrama conceptual;
- glosario de entidades;
- relaciones y cardinalidades;
- identificación de agregados cuando corresponda;
- trazabilidad entidad ↔ proceso/HU/RF/regla;
- preguntas pendientes del modelo.

## 3. Modelo lógico

### Objetivo
Convertir el modelo conceptual validado en un modelo relacional lógico, sin depender todavía de un motor concreto.

### Entregables
- entidades/tablas lógicas;
- atributos y dominios lógicos;
- PK/FK;
- relaciones 1:1, 1:N y N:M resueltas mediante estructuras intermedias cuando corresponda;
- normalización;
- restricciones de integridad;
- catálogos y estados;
- diccionario de datos;
- matriz de trazabilidad.

## 4. Modelo físico

### Objetivo
Implementar el modelo lógico en el motor de persistencia que se confirme para la solución.

### Entregables
- DDL;
- tipos de datos;
- PK/FK/UNIQUE/CHECK/NOT NULL;
- índices;
- particionamiento si se justifica;
- auditoría;
- versionado/migraciones;
- estrategia de almacenamiento de evidencias y metadatos;
- scripts de carga de datos sintéticos.

**TBD:** motor de base de datos y decisiones físicas específicas no están confirmados por las entrevistas.

## 5. Data sintética

### Principios
1. No utilizar datos personales reales.
2. Ser reproducible mediante una semilla/versionado de dataset.
3. Ser coherente con las relaciones y restricciones del modelo físico.
4. Permitir escenarios positivos, negativos y de excepción.
5. Identificar claramente que los valores son sintéticos.
6. No convertir valores inventados en reglas de negocio.

### Cobertura mínima
El dataset deberá poder representar:

- asegurados y reportantes;
- pólizas vigentes;
- vehículos;
- coberturas y deducibles;
- siniestros;
- participantes/terceros;
- evidencias originales y derivados;
- asistencia y proveedores;
- inspecciones;
- talleres;
- presupuestos y versiones;
- autorizaciones;
- alertas antifraude;
- revisiones humanas;
- pagos;
- comunicaciones;
- eventos de auditoría;
- transiciones de estado.

### Escenarios sintéticos
- S01: siniestro material simple, flujo exitoso.
- S02: cobertura no aplicable.
- S03: evidencia incompleta.
- S04: siniestro con múltiples evidencias.
- S05: solicitud de asistencia.
- S06: inspección y presupuesto.
- S07: presupuesto observado/rechazado.
- S08: alerta de riesgo que requiere revisión humana.
- S09: posible duplicidad de siniestro/caso relacionado.
- S10: integración externa con timeout/rechazo.
- S11: reparación y cierre.
- S12: indemnización/pago.

La activación de cada escenario se validará contra las reglas y criterios definitivos.

## 6. Artefactos futuros

- `modelo_conceptual.md` / diagrama Mermaid.
- `modelo_logico.md` / diagrama Mermaid.
- `modelo_fisico.sql`.
- `diccionario_datos.md`.
- `data_sintetica/README.md`.
- scripts de generación/carga.
- datasets por escenario.
- matriz escenario → HU → criterio → datos.

## 7. Regla de actualización
El modelo no se considera cerrado si una decisión de negocio posterior contradice entidades, cardinalidades, estados o restricciones. Toda modificación deberá propagarse de conceptual → lógico → físico → datos sintéticos → pruebas.
