alter table siniestro_facil_meta.idempotencia_request
    add column pago_id bigint;

alter table siniestro_facil_meta.idempotencia_request
    add constraint fk_idempotencia_pago
    foreign key (pago_id)
    references siniestro_facil.pago(id);

create index if not exists ix_idempotencia_pago
    on siniestro_facil_meta.idempotencia_request(pago_id);
