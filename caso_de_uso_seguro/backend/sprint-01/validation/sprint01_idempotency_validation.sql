\set ON_ERROR_STOP on

BEGIN;

SET search_path TO siniestro_facil, public;

DO $$
BEGIN
    IF to_regclass('siniestro_facil_meta.idempotencia_request') IS NULL THEN
        RAISE EXCEPTION 'FAIL: tabla de idempotencia no existe';
    END IF;
    RAISE NOTICE 'PASS: tabla de idempotencia presente';
END $$;

INSERT INTO siniestro_facil_meta.idempotencia_request
    (idempotency_key, request_hash, correlation_id)
VALUES
    ('validation-key-sprint01', 'hash-001', 'validation-correlation-001')
ON CONFLICT (idempotency_key) DO NOTHING;

INSERT INTO siniestro_facil_meta.idempotencia_request
    (idempotency_key, request_hash, correlation_id)
VALUES
    ('validation-key-sprint01', 'hash-001', 'validation-correlation-002')
ON CONFLICT (idempotency_key) DO NOTHING;

DO $$
DECLARE
    v_count integer;
    v_hash text;
BEGIN
    SELECT count(*), max(request_hash)
      INTO v_count, v_hash
      FROM siniestro_facil_meta.idempotencia_request
     WHERE idempotency_key = 'validation-key-sprint01';

    IF v_count <> 1 THEN
        RAISE EXCEPTION 'FAIL: Idempotency-Key duplicada';
    END IF;

    IF v_hash <> 'hash-001' THEN
        RAISE EXCEPTION 'FAIL: payload existente fue alterado';
    END IF;

    RAISE NOTICE 'PASS: Idempotency-Key única y payload preservado';
END $$;

ROLLBACK;

SELECT 'PASS - rollback idempotencia correcto' AS resultado;
