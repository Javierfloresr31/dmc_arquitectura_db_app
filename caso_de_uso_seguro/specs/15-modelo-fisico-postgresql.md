# Siniestro Fácil — Modelo físico PostgreSQL

## Estado
**PROPUESTA TÉCNICA — PostgreSQL.**

El modelo lógico de `14-modelo-logico.md` es la fuente estructural. PostgreSQL se utiliza porque la solicitud de esta sesión define explícitamente ese motor.

## Validación del modelo lógico

El modelo lógico contiene 23 estructuras lógicas. Se materializan las 23 como tablas físicas.

Se aplican únicamente restricciones trazables al modelo lógico o a relaciones estructurales explícitas:

- PK técnica en todas las tablas.
- FK en relaciones explícitas.
- PK compuesta en tablas asociativas.
- `CHECK` únicamente para la invariancia estructural de `SINIESTRO_RELACION`.
- `NOT NULL` en PK y en las FK de pertenencia estructural obligatoria del expediente.
- No se agregan `UNIQUE` sobre póliza, placa, presupuesto u otros identificadores porque permanecen pendientes.
- No se agregan dominios cerrados para estados, severidades o tipos.
- No se implementa todavía la vigencia de 7 días del presupuesto porque el modelo lógico no define de forma suficiente el atributo de recepción necesario.
- No se implementa retención mediante `CHECK`/trigger.
- No se fija una estrategia de almacenamiento binario.

## Convenciones PostgreSQL

- Esquema: `siniestro_facil`.
- PK técnica: `bigint generated always as identity`.
- Momentos: `timestamp with time zone`.
- Fechas calendario: `date`.
- Texto no acotado por la fuente: `text`.
- `ALERTA_SEÑAL` se materializa como `alerta_senal` para evitar identificadores con caracteres especiales.

## DDL

```sql
create schema if not exists siniestro_facil;

create table siniestro_facil.asegurado (
    id bigint generated always as identity primary key
);

create table siniestro_facil.reportante (
    id bigint generated always as identity primary key
);

create table siniestro_facil.poliza (
    id bigint generated always as identity primary key,
    numero_poliza text,
    vigencia text
);

create table siniestro_facil.vehiculo (
    id bigint generated always as identity primary key,
    placa text
);

create table siniestro_facil.poliza_vehiculo (
    poliza_id bigint not null references siniestro_facil.poliza(id),
    vehiculo_id bigint not null references siniestro_facil.vehiculo(id),
    primary key (poliza_id, vehiculo_id)
);

create table siniestro_facil.cobertura (
    id bigint generated always as identity primary key
);

create table siniestro_facil.siniestro (
    id bigint generated always as identity primary key,
    poliza_id bigint not null references siniestro_facil.poliza(id),
    vehiculo_id bigint not null references siniestro_facil.vehiculo(id),
    fecha timestamp with time zone,
    ubicacion_aproximada text,
    tipo_evento text,
    danos_aparentes text,
    estado text
);

create table siniestro_facil.siniestro_participante (
    id bigint generated always as identity primary key,
    siniestro_id bigint not null references siniestro_facil.siniestro(id),
    persona_tercero text,
    medio_contacto text,
    rol text
);

create table siniestro_facil.evidencia (
    id bigint generated always as identity primary key,
    siniestro_id bigint not null references siniestro_facil.siniestro(id),
    contenido_original text,
    hash text,
    metadatos_disponibles text,
    fecha_recepcion timestamp with time zone,
    fuente text,
    transformaciones text
);

create table siniestro_facil.evidencia_version (
    id bigint generated always as identity primary key,
    evidencia_id bigint not null references siniestro_facil.evidencia(id),
    transformacion text
);

create table siniestro_facil.proveedor_asistencia (
    id bigint generated always as identity primary key,
    proveedor text
);

create table siniestro_facil.asistencia (
    id bigint generated always as identity primary key,
    siniestro_id bigint not null references siniestro_facil.siniestro(id),
    proveedor_asistencia_id bigint references siniestro_facil.proveedor_asistencia(id)
);

create table siniestro_facil.inspeccion (
    id bigint generated always as identity primary key,
    siniestro_id bigint not null references siniestro_facil.siniestro(id),
    ajustador_id bigint,
    programacion text,
    resultado text
);

create table siniestro_facil.taller (
    id bigint generated always as identity primary key,
    taller text
);

create table siniestro_facil.presupuesto (
    id bigint generated always as identity primary key,
    siniestro_id bigint not null references siniestro_facil.siniestro(id),
    taller_id bigint not null references siniestro_facil.taller(id),
    presupuesto text,
    diagnostico text,
    vigencia date,
    observaciones text,
    repuestos_alternativos text,
    ampliaciones text
);

create table siniestro_facil.presupuesto_detalle (
    id bigint generated always as identity primary key,
    presupuesto_id bigint not null references siniestro_facil.presupuesto(id)
);

create table siniestro_facil.autorizacion (
    id bigint generated always as identity primary key,
    siniestro_id bigint not null references siniestro_facil.siniestro(id),
    aprobador text
);

create table siniestro_facil.regla_modelo_version (
    id bigint generated always as identity primary key,
    tipo text,
    version text
);

create table siniestro_facil.alerta_antifraude (
    id bigint generated always as identity primary key,
    siniestro_id bigint not null references siniestro_facil.siniestro(id),
    tipo text,
    severidad text,
    explicacion text,
    datos_origen text,
    fecha timestamp with time zone,
    modelo_o_regla text,
    estado text,
    justificacion text,
    regla_modelo_version_id bigint references siniestro_facil.regla_modelo_version(id)
);

create table siniestro_facil.alerta_senal (
    id bigint generated always as identity primary key,
    alerta_antifraude_id bigint not null references siniestro_facil.alerta_antifraude(id),
    senal text
);

create table siniestro_facil.revision_antifraude (
    id bigint generated always as identity primary key,
    alerta_antifraude_id bigint not null references siniestro_facil.alerta_antifraude(id),
    resultado text,
    justificacion text
);

create table siniestro_facil.pago (
    id bigint generated always as identity primary key,
    siniestro_id bigint not null references siniestro_facil.siniestro(id),
    autorizacion text
);

create table siniestro_facil.siniestro_estado_historial (
    id bigint generated always as identity primary key,
    siniestro_id bigint not null references siniestro_facil.siniestro(id),
    estado text,
    fecha_evento timestamp with time zone
);

create table siniestro_facil.auditoria (
    id bigint generated always as identity primary key,
    entidad text,
    entidad_id bigint,
    fecha_evento timestamp with time zone
);

create table siniestro_facil.siniestro_relacion (
    siniestro_id bigint not null references siniestro_facil.siniestro(id),
    siniestro_relacionado_id bigint not null references siniestro_facil.siniestro(id),
    primary key (siniestro_id, siniestro_relacionado_id),
    check (siniestro_id <> siniestro_relacionado_id)
);
```

## Índices estructurales derivados de FK

```sql
create index ix_siniestro_poliza on siniestro_facil.siniestro(poliza_id);
create index ix_siniestro_vehiculo on siniestro_facil.siniestro(vehiculo_id);
create index ix_participante_siniestro on siniestro_facil.siniestro_participante(siniestro_id);
create index ix_evidencia_siniestro on siniestro_facil.evidencia(siniestro_id);
create index ix_evidencia_version_evidencia on siniestro_facil.evidencia_version(evidencia_id);
create index ix_asistencia_siniestro on siniestro_facil.asistencia(siniestro_id);
create index ix_presupuesto_siniestro on siniestro_facil.presupuesto(siniestro_id);
create index ix_presupuesto_taller on siniestro_facil.presupuesto(taller_id);
create index ix_alerta_siniestro on siniestro_facil.alerta_antifraude(siniestro_id);
create index ix_alerta_senal_alerta on siniestro_facil.alerta_senal(alerta_antifraude_id);
create index ix_revision_alerta on siniestro_facil.revision_antifraude(alerta_antifraude_id);
create index ix_pago_siniestro on siniestro_facil.pago(siniestro_id);
create index ix_historial_siniestro on siniestro_facil.siniestro_estado_historial(siniestro_id);
create index ix_auditoria_entidad on siniestro_facil.auditoria(entidad, entidad_id);
create index ix_siniestro_relacion_relacionado on siniestro_facil.siniestro_relacion(siniestro_relacionado_id);
```

## Hallazgos de validación

1. **ASEGURADO, REPORTANTE y COBERTURA** están en el modelo lógico, pero sus atributos y relaciones persistentes no están definidos suficientemente. Se materializan sin inventar columnas ni FK.
2. **INSPECCION.ajustador_id** queda sin FK porque `AJUSTADOR` no aparece como tabla lógica independiente en `14-modelo-logico.md`.
3. `POLIZA_VEHICULO` se conserva como tabla asociativa porque el modelo lógico la define como posible relación N:M/histórica; no se agrega una regla de unicidad adicional.
4. La relación `SINIESTRO_RELACION` tiene PK compuesta y evita la auto-relación mediante `CHECK`.
5. No se convierten preguntas abiertas en `UNIQUE`, catálogos, triggers ni reglas de negocio.

Las preguntas pendientes relevantes del dominio incluyen claves de negocio, roles, estados, tipos de evidencia, estructura económica, almacenamiento de objetos y autorización.
