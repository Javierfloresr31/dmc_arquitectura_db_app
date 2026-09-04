ALTER TABLE siniestro_facil.auditoria
    ADD COLUMN IF NOT EXISTS evento text;

ALTER TABLE siniestro_facil.auditoria
    ADD COLUMN IF NOT EXISTS actor text;

ALTER TABLE siniestro_facil.auditoria
    ADD COLUMN IF NOT EXISTS correlation_id text;

CREATE INDEX IF NOT EXISTS ix_auditoria_entidad_entidad_id
    ON siniestro_facil.auditoria(entidad, entidad_id);
