-- Siniestro Fácil — Validación de constraints PostgreSQL
-- Ejecutar después de 15-modelo-fisico-postgresql.md
-- Las pruebas negativas capturan la excepción esperada.
-- El ROLLBACK final elimina todos los datos sintéticos de la prueba.

begin;

truncate table
    siniestro_facil.siniestro_relacion,
    siniestro_facil.siniestro_estado_historial,
    siniestro_facil.pago,
    siniestro_facil.revision_antifraude,
    siniestro_facil.alerta_senal,
    siniestro_facil.alerta_antifraude,
    siniestro_facil.regla_modelo_version,
    siniestro_facil.autorizacion,
    siniestro_facil.presupuesto_detalle,
    siniestro_facil.presupuesto,
    siniestro_facil.taller,
    siniestro_facil.inspeccion,
    siniestro_facil.asistencia,
    siniestro_facil.proveedor_asistencia,
    siniestro_facil.evidencia_version,
    siniestro_facil.evidencia,
    siniestro_facil.siniestro_participante,
    siniestro_facil.siniestro,
    siniestro_facil.poliza_vehiculo,
    siniestro_facil.cobertura,
    siniestro_facil.vehiculo,
    siniestro_facil.poliza,
    siniestro_facil.asegurado,
    siniestro_facil.reportante
restart identity cascade;

-- 1. Datos base válidos
insert into siniestro_facil.poliza (numero_poliza, vigencia)
values ('POL-TEST-001', 'VIGENTE');

insert into siniestro_facil.vehiculo (placa)
values ('ABC-123');

insert into siniestro_facil.siniestro (
    poliza_id, vehiculo_id, fecha, ubicacion_aproximada,
    tipo_evento, danos_aparentes, estado
)
values (
    1, 1, current_timestamp, 'Ubicación sintética',
    'COLISION', 'Daño frontal sintético', 'REPORTADO'
);

-- 2. PK
 do $$
begin
    begin
        insert into siniestro_facil.poliza (id, numero_poliza, vigencia)
        values (1, 'POL-TEST-DUP', 'VIGENTE');
        raise exception 'FAIL: PK POLIZA permitió id duplicado';
    exception when unique_violation then
        raise notice 'PASS: PK POLIZA rechaza id duplicado';
    end;
end $$;

-- 3. NOT NULL
 do $$
begin
    begin
        insert into siniestro_facil.siniestro (poliza_id, vehiculo_id, estado)
        values (null, 1, 'REPORTADO');
        raise exception 'FAIL: SINIESTRO.poliza_id permitió NULL';
    exception when not_null_violation then
        raise notice 'PASS: SINIESTRO.poliza_id rechaza NULL';
    end;
end $$;

 do $$
begin
    begin
        insert into siniestro_facil.siniestro (poliza_id, vehiculo_id, estado)
        values (1, null, 'REPORTADO');
        raise exception 'FAIL: SINIESTRO.vehiculo_id permitió NULL';
    exception when not_null_violation then
        raise notice 'PASS: SINIESTRO.vehiculo_id rechaza NULL';
    end;
end $$;

-- 4. FK SINIESTRO
 do $$
begin
    begin
        insert into siniestro_facil.siniestro (poliza_id, vehiculo_id, estado)
        values (999999, 1, 'REPORTADO');
        raise exception 'FAIL: FK SINIESTRO.poliza_id permitió padre inexistente';
    exception when foreign_key_violation then
        raise notice 'PASS: FK SINIESTRO.poliza_id';
    end;
end $$;

 do $$
begin
    begin
        insert into siniestro_facil.siniestro (poliza_id, vehiculo_id, estado)
        values (1, 999999, 'REPORTADO');
        raise exception 'FAIL: FK SINIESTRO.vehiculo_id permitió padre inexistente';
    exception when foreign_key_violation then
        raise notice 'PASS: FK SINIESTRO.vehiculo_id';
    end;
end $$;

-- 5. FK EVIDENCIA
 do $$
begin
    begin
        insert into siniestro_facil.evidencia (siniestro_id, contenido_original)
        values (999999, 'contenido sintético');
        raise exception 'FAIL: FK EVIDENCIA.siniestro_id';
    exception when foreign_key_violation then
        raise notice 'PASS: FK EVIDENCIA.siniestro_id';
    end;
end $$;

-- 6. FK PRESUPUESTO
insert into siniestro_facil.taller (taller)
values ('Taller Sintético');

insert into siniestro_facil.presupuesto (
    siniestro_id, taller_id, presupuesto, diagnostico
)
values (
    1, 1, 'PRESUPUESTO-SYN-001', 'Diagnóstico sintético'
);

 do $$
begin
    begin
        insert into siniestro_facil.presupuesto (
            siniestro_id, taller_id, presupuesto
        ) values (999999, 1, 'PRESUPUESTO-INVALIDO');
        raise exception 'FAIL: FK PRESUPUESTO.siniestro_id';
    exception when foreign_key_violation then
        raise notice 'PASS: FK PRESUPUESTO.siniestro_id';
    end;
end $$;

 do $$
begin
    begin
        insert into siniestro_facil.presupuesto (
            siniestro_id, taller_id, presupuesto
        ) values (1, 999999, 'PRESUPUESTO-INVALIDO');
        raise exception 'FAIL: FK PRESUPUESTO.taller_id';
    exception when foreign_key_violation then
        raise notice 'PASS: FK PRESUPUESTO.taller_id';
    end;
end $$;

-- 7. PK compuesta POLIZA_VEHICULO
insert into siniestro_facil.poliza_vehiculo (poliza_id, vehiculo_id)
values (1, 1);

 do $$
begin
    begin
        insert into siniestro_facil.poliza_vehiculo (poliza_id, vehiculo_id)
        values (1, 1);
        raise exception 'FAIL: PK compuesta POLIZA_VEHICULO';
    exception when unique_violation then
        raise notice 'PASS: PK compuesta POLIZA_VEHICULO';
    end;
end $$;

-- 8. CHECK SINIESTRO_RELACION
 do $$
begin
    begin
        insert into siniestro_facil.siniestro_relacion (
            siniestro_id, siniestro_relacionado_id
        ) values (1, 1);
        raise exception 'FAIL: CHECK permitió auto-relación';
    exception when check_violation then
        raise notice 'PASS: CHECK evita auto-relación';
    end;
end $$;

-- 9. FK EVIDENCIA_VERSION
insert into siniestro_facil.evidencia (
    siniestro_id, contenido_original, hash, fecha_recepcion, fuente
)
values (
    1, 'original sintético', 'hash-sintetico', current_timestamp, 'TEST'
);

insert into siniestro_facil.evidencia_version (
    evidencia_id, transformacion
)
values (1, 'compresión sintética');

 do $$
begin
    begin
        insert into siniestro_facil.evidencia_version (
            evidencia_id, transformacion
        ) values (999999, 'transformación inválida');
        raise exception 'FAIL: FK EVIDENCIA_VERSION.evidencia_id';
    exception when foreign_key_violation then
        raise notice 'PASS: FK EVIDENCIA_VERSION.evidencia_id';
    end;
end $$;

-- 10. FK ALERTA / SEÑAL / REVISIÓN
insert into siniestro_facil.regla_modelo_version (tipo, version)
values ('REGLA', 'v1');

insert into siniestro_facil.alerta_antifraude (
    siniestro_id, tipo, severidad, explicacion, fecha,
    estado, regla_modelo_version_id
)
values (
    1, 'IMAGEN_REUTILIZADA', 'MEDIA', 'Alerta sintética',
    current_timestamp, 'PENDIENTE', 1
);

insert into siniestro_facil.alerta_senal (
    alerta_antifraude_id, senal
)
values (1, 'HASH_COINCIDENTE');

insert into siniestro_facil.revision_antifraude (
    alerta_antifraude_id, resultado, justificacion
)
values (1, 'DESCARTADA', 'Prueba sintética');

 do $$
begin
    begin
        insert into siniestro_facil.revision_antifraude (
            alerta_antifraude_id, resultado
        ) values (999999, 'INVALIDA');
        raise exception 'FAIL: FK REVISION_ANTIFRAUDE.alerta_antifraude_id';
    exception when foreign_key_violation then
        raise notice 'PASS: FK REVISION_ANTIFRAUDE.alerta_antifraude_id';
    end;
end $$;

-- 11. Relación válida entre dos siniestros
insert into siniestro_facil.siniestro (
    poliza_id, vehiculo_id, fecha, tipo_evento, estado
)
values (1, 1, current_timestamp, 'EVENTO_RELACIONADO', 'REPORTADO');

insert into siniestro_facil.siniestro_relacion (
    siniestro_id, siniestro_relacionado_id
)
values (1, 2);

-- Resultado: si no aparece un ERROR no controlado, las pruebas
-- negativas fueron rechazadas por el constraint esperado.
rollback;
