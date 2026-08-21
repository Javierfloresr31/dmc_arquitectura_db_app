create schema if not exists siniestro_facil_meta;

create table if not exists siniestro_facil_meta.idempotencia_request (
    id bigint generated always as identity primary key,
    idempotency_key text not null,
    request_hash text not null,
    siniestro_id bigint,
    correlation_id text not null,
    created_at timestamp with time zone not null default current_timestamp,
    completed_at timestamp with time zone,
    constraint uq_idempotencia_request_key unique (idempotency_key),
    constraint fk_idempotencia_siniestro
        foreign key (siniestro_id)
        references siniestro_facil.siniestro(id)
);

create index if not exists ix_idempotencia_siniestro
    on siniestro_facil_meta.idempotencia_request(siniestro_id);
