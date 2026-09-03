ALTER TABLE siniestro_facil_meta.idempotencia_request
ADD COLUMN IF NOT EXISTS autorizacion_id bigint;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM pg_constraint
        WHERE conname = 'fk_idempotencia_autorizacion'
    ) THEN
        ALTER TABLE siniestro_facil_meta.idempotencia_request
        ADD CONSTRAINT fk_idempotencia_autorizacion
        FOREIGN KEY (autorizacion_id)
        REFERENCES siniestro_facil.autorizacion(id);
    END IF;
END
$$;

CREATE INDEX IF NOT EXISTS ix_idempotencia_autorizacion
    ON siniestro_facil_meta.idempotencia_request(autorizacion_id);
