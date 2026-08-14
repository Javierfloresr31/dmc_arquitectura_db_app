# Siniestro Fácil — Modelo lógico

> Estado: BORRADOR. Debe validarse contra el modelo conceptual antes de considerarse base de implementación.

## 1. Objetivo

Representar el dominio en estructuras relacionales lógicas, sin elegir todavía un motor de base de datos.

## 2. Entidades lógicas candidatas

### POLIZA
- `poliza_id` — identificador lógico.
- `numero_poliza` — número usado para localizar la póliza.
- atributos de vigencia/estado — pendientes de confirmación.

### VEHICULO
- `vehiculo_id` — identificador lógico.
- `placa_declarada` — valor declarado por usuario/fuente.
- `placa_normalizada` — valor normalizado separado del declarado.
- otros datos del vehículo — pendientes de confirmar.

La separación declarado/normalizado se sustenta en la entrevista de fraude, que exige no reemplazar silenciosamente el valor original. fileciteturn19file1L1-L1

### SINIESTRO
- `siniestro_id` — identificador lógico.
- `poliza_id` — relación a póliza.
- `vehiculo_id` — relación a vehículo.
- fecha/hora del evento — requerida por operación.
- ubicación aproximada — requerida por operación.
- tipo de evento — requerido por operación.
- descripción — requerida por operación.
- daños aparentes — requeridos por operación.
- estado — catálogo pendiente de validar.

### PARTICIPANTE
- `participante_id`.
- `siniestro_id`.
- `persona_id` — si se confirma Persona como entidad independiente.
- rol/tipo de participación — catálogo pendiente.
- datos declarados/normalizados cuando corresponda.

### COBERTURA
- `cobertura_id`.
- `poliza_id`.
- tipo/estado/vigencia — pendientes de definición.

### EVIDENCIA
- `evidencia_id`.
- `siniestro_id`.
- tipo.
- contenido/origen lógico.
- hash del original.
- metadatos disponibles.
- fecha de recepción.
- fuente.
- transformación.
- referencia a versión derivada.

La necesidad de conservar original, hash, metadatos, fecha de recepción, fuente, transformaciones y versiones derivadas está explícitamente sustentada. fileciteturn19file1L1-L1

### ASISTENCIA
- `asistencia_id`.
- `siniestro_id`.
- tipo de asistencia.
- proveedor.
- estado.
- intentos y resultado de coordinación.

### INSPECCION
- `inspeccion_id`.
- `siniestro_id`.
- asignación.
- fecha/programación.
- resultado.
- observaciones.

### TALLER
- `taller_id`.
- datos identificadores pendientes.
- ubicación/capacidades pendientes.

### PRESUPUESTO
- `presupuesto_id`.
- `siniestro_id`.
- `taller_id`.
- versión.
- vigencia.
- diagnóstico.
- monto declarado.
- estado.

La entrevista exige conocer vigencia, quién aprobó cambios, observaciones, repuestos alternativos y ampliaciones. fileciteturn19file7L1-L1

### AUTORIZACION
- `autorizacion_id`.
- `siniestro_id`.
- presupuesto relacionado.
- decisión.
- actor decisor.
- fecha/hora.
- justificación.

### ALERTA
- `alerta_id`.
- `siniestro_id`.
- tipo.
- severidad.
- explicación.
- datos originadores.
- fecha.
- regla/modelo utilizado.
- versión.
- estado de revisión.
- justificación de revisión.

Estos atributos se derivan directamente del requisito de explicabilidad y reproducibilidad de fraude. fileciteturn19file3L1-L1

### PAGO
- `pago_id`.
- `siniestro_id`.
- tipo.
- monto.
- beneficiario/destino lógico.
- estado.
- autorización.
- identificador de operación/idempotencia — pendiente de definición técnica.

### HISTORIAL_SINIESTRO
- `historial_id`.
- `siniestro_id`.
- evento/acción.
- actor.
- fecha/hora.
- estado anterior/nuevo cuando aplique.
- datos relevantes del cambio.
- motivo.

La línea de tiempo completa es un requerimiento operativo explícito. fileciteturn19file7L1-L1

## 3. Catálogos pendientes

- Estado de siniestro.
- Tipo de evento.
- Tipo/rol de participante.
- Tipo de evidencia.
- Tipo/estado de asistencia.
- Tipo/estado de presupuesto.
- Tipo/estado de autorización.
- Tipo/severidad/estado de alerta.
- Tipo/estado de pago.

No se fijan valores porque las entrevistas no los definen exhaustivamente.

## 4. Próxima validación

Antes de DDL se deben confirmar cardinalidades, identificadores oficiales, atributos obligatorios por etapa, catálogos, retención, relación entre casos y reglas de versionado.
