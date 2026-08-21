-- Sprint 01: validación funcional de persistencia.
-- Ejecutar sobre dmcsiniestrofacil. No elimina data existente.
BEGIN;
SET LOCAL search_path TO siniestro_facil, public;

-- Precondición: localizar registros sintéticos existentes.
DO $$ BEGIN
  IF NOT EXISTS (SELECT 1 FROM poliza WHERE numero_poliza = 'SYN-POL-000001') THEN
    RAISE NOTICE 'WARN: no existe POLIZA sintética SYN-POL-000001';
  END IF;
END $$;

-- Validar estructura utilizada por Sprint 01.
DO $$ BEGIN
  IF to_regclass('siniestro_facil.siniestro') IS NULL THEN RAISE EXCEPTION 'FAIL: tabla siniestro'; END IF;
  IF to_regclass('siniestro_facil.siniestro_participante') IS NULL THEN RAISE EXCEPTION 'FAIL: tabla siniestro_participante'; END IF;
  IF to_regclass('siniestro_facil.siniestro_estado_historial') IS NULL THEN RAISE EXCEPTION 'FAIL: tabla historial'; END IF;
  IF to_regclass('siniestro_facil.auditoria') IS NULL THEN RAISE EXCEPTION 'FAIL: tabla auditoria'; END IF;
  RAISE NOTICE 'PASS: tablas Sprint 01 presentes';
END $$;

-- Insertar y verificar un expediente aislado. Se revierte al final.
INSERT INTO poliza(numero_poliza, vigencia) VALUES ('SPRINT01-TEST-POL', 'VIGENTE');
INSERT INTO vehiculo(placa) VALUES ('SPR01-01');
INSERT INTO siniestro(poliza_id, vehiculo_id, fecha, ubicacion_aproximada, tipo_evento, danos_aparentes, estado)
SELECT p.id, v.id, current_timestamp, 'Piura - prueba Sprint 01', 'COLISION', 'Daño sintético Sprint 01', 'REPORTADO'
FROM poliza p CROSS JOIN vehiculo v
WHERE p.numero_poliza='SPRINT01-TEST-POL' AND v.placa='SPR01-01';

INSERT INTO siniestro_participante(siniestro_id, persona_tercero, medio_contacto, rol)
SELECT id, 'PARTICIPANTE-TEST', '999999999', 'REPORTANTE'
FROM siniestro WHERE ubicacion_aproximada='Piura - prueba Sprint 01';

INSERT INTO siniestro_estado_historial(siniestro_id, estado, fecha_evento)
SELECT id, estado, current_timestamp FROM siniestro WHERE ubicacion_aproximada='Piura - prueba Sprint 01';

INSERT INTO auditoria(entidad, entidad_id, fecha_evento)
SELECT 'SINIESTRO', id, current_timestamp FROM siniestro WHERE ubicacion_aproximada='Piura - prueba Sprint 01';

DO $$ BEGIN
  IF (SELECT count(*) FROM siniestro WHERE ubicacion_aproximada='Piura - prueba Sprint 01' AND estado='REPORTADO') <> 1 THEN
    RAISE EXCEPTION 'FAIL: siniestro Sprint 01 no creado';
  END IF;
  IF (SELECT count(*) FROM siniestro_participante WHERE persona_tercero='PARTICIPANTE-TEST') <> 1 THEN
    RAISE EXCEPTION 'FAIL: participante Sprint 01';
  END IF;
  IF (SELECT count(*) FROM siniestro_estado_historial h JOIN siniestro s ON s.id=h.siniestro_id WHERE s.ubicacion_aproximada='Piura - prueba Sprint 01') <> 1 THEN
    RAISE EXCEPTION 'FAIL: historial Sprint 01';
  END IF;
  IF (SELECT count(*) FROM auditoria a JOIN siniestro s ON s.id=a.entidad_id WHERE a.entidad='SINIESTRO' AND s.ubicacion_aproximada='Piura - prueba Sprint 01') <> 1 THEN
    RAISE EXCEPTION 'FAIL: auditoria Sprint 01';
  END IF;
  RAISE NOTICE 'PASS: flujo persistencia Sprint 01';
END $$;

ROLLBACK;
