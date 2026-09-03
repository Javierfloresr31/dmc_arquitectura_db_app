create table if not exists siniestro_facil.presupuesto_observacion (
    id bigint generated always as identity primary key,
    presupuesto_id bigint not null references siniestro_facil.presupuesto(id),
    responsable text not null,
    observacion text not null,
    fecha_evento timestamp with time zone not null
);

create index if not exists ix_presupuesto_observacion_presupuesto
    on siniestro_facil.presupuesto_observacion(presupuesto_id);
