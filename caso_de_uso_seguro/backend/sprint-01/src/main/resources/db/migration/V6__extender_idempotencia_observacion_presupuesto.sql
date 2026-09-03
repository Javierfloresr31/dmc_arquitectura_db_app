ALTER TABLE siniestro_facil_meta.idempotencia_request
ADD COLUMN IF NOT EXISTS presupuesto_observacion_id bigint;

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM pg_constraint
        WHERE conname = 'fk_idempotencia_presupuesto_observacion'
    ) THEN
        ALTER TABLE siniestro_facil_meta.idempotencia_request
        ADD CONSTRAINT fk_idempotencia_presupuesto_observacion
        FOREIGN KEY (presupuesto_observacion_id)
        REFERENCES siniestro_facil.presupuesto_observacion(id);
    END IF;
END
$$;

CREATE INDEX IF NOT EXISTS ix_idempotencia_presupuesto_observacion
    ON siniestro_facil_meta.idempotencia_request(presupuesto_observacion_id);
