package pe.siniestrofacil.infrastructure.persistence;

import pe.siniestrofacil.application.dto.CrearSiniestroRequest;
import pe.siniestrofacil.application.exception.IdempotencyConflictException;
import pe.siniestrofacil.domain.model.Siniestro;
import pe.siniestrofacil.domain.port.SiniestroRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.*;

@Repository
public class JdbcSiniestroRepository implements SiniestroRepository {
    private final JdbcTemplate jdbc;

    public JdbcSiniestroRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Siniestro createByBusinessKeys(
            String numeroPoliza,
            String placa,
            Siniestro s,
            CrearSiniestroRequest.ParticipanteRequest p,
            String idempotencyKey,
            String requestHash,
            String correlationId) {

        if (idempotencyKey != null && !idempotencyKey.isBlank()) {
            jdbc.update(
                    "insert into siniestro_facil_meta.idempotencia_request " +
                    "(idempotency_key,request_hash,correlation_id) values(?,?,?) " +
                    "on conflict (idempotency_key) do nothing",
                    idempotencyKey, requestHash, correlationId);

            IdempotencyRecord existing = jdbc.query(
                    "select request_hash,siniestro_id from siniestro_facil_meta.idempotencia_request " +
                    "where idempotency_key=?",
                    (rs, n) -> new IdempotencyRecord(
                            rs.getString("request_hash"),
                            (Long) rs.getObject("siniestro_id")),
                    idempotencyKey).stream().findFirst().orElseThrow(
                            () -> new IllegalStateException("No se pudo registrar la Idempotency-Key"));

            if (!existing.requestHash().equals(requestHash)) {
                throw new IdempotencyConflictException(
                        "La Idempotency-Key ya fue utilizada con un payload diferente");
            }

            if (existing.siniestroId() != null) {
                return findById(existing.siniestroId()).orElseThrow(
                        () -> new IllegalStateException("El registro de idempotencia apunta a un siniestro inexistente"));
            }
        }

        Long pid = jdbc.queryForObject(
                "select id from siniestro_facil.poliza where numero_poliza=? order by id limit 1",
                Long.class, numeroPoliza);
        Long vid = jdbc.queryForObject(
                "select id from siniestro_facil.vehiculo where placa=? order by id limit 1",
                Long.class, placa);

        Long id = jdbc.queryForObject(
                "insert into siniestro_facil.siniestro " +
                "(poliza_id,vehiculo_id,fecha,ubicacion_aproximada,tipo_evento,danos_aparentes,estado) " +
                "values(?,?,?,?,?,?,?) returning id",
                Long.class,
                pid,
                vid,
                Timestamp.from(s.fecha().toInstant()),
                s.ubicacionAproximada(),
                s.tipoEvento(),
                s.danosAparentes(),
                Siniestro.REPORTADO);

        if (p != null) {
            jdbc.update(
                    "insert into siniestro_facil.siniestro_participante " +
                    "(siniestro_id,persona_tercero,medio_contacto,rol) values(?,?,?,?)",
                    id, p.personaTercero(), p.medioContacto(), p.rol());
        }

        jdbc.update(
                "insert into siniestro_facil.siniestro_estado_historial " +
                "(siniestro_id,estado,fecha_evento) values(?,?,current_timestamp)",
                id, Siniestro.REPORTADO);

        jdbc.update(
                "insert into siniestro_facil.auditoria(entidad,entidad_id,fecha_evento) " +
                "values(?,?,current_timestamp)",
                "SINIESTRO", id);

        if (idempotencyKey != null && !idempotencyKey.isBlank()) {
            jdbc.update(
                    "update siniestro_facil_meta.idempotencia_request " +
                    "set siniestro_id=?, completed_at=current_timestamp " +
                    "where idempotency_key=?",
                    id, idempotencyKey);
        }

        return findById(id).orElseThrow();
    }

    public Optional<Siniestro> findById(long id) {
        return jdbc.query(
                "select id,poliza_id,vehiculo_id,fecha,ubicacion_aproximada,tipo_evento,danos_aparentes,estado " +
                "from siniestro_facil.siniestro where id=?",
                (rs, n) -> new Siniestro(
                        rs.getLong("id"),
                        rs.getLong("poliza_id"),
                        rs.getLong("vehiculo_id"),
                        rs.getObject("fecha", OffsetDateTime.class),
                        rs.getString("ubicacion_aproximada"),
                        rs.getString("tipo_evento"),
                        rs.getString("danos_aparentes"),
                        rs.getString("estado")),
                id).stream().findFirst();
    }

    public List<Siniestro> findAll() {
        return jdbc.query(
                "select id,poliza_id,vehiculo_id,fecha,ubicacion_aproximada,tipo_evento,danos_aparentes,estado " +
                "from siniestro_facil.siniestro order by id",
                (rs, n) -> new Siniestro(
                        rs.getLong("id"),
                        rs.getLong("poliza_id"),
                        rs.getLong("vehiculo_id"),
                        rs.getObject("fecha", OffsetDateTime.class),
                        rs.getString("ubicacion_aproximada"),
                        rs.getString("tipo_evento"),
                        rs.getString("danos_aparentes"),
                        rs.getString("estado")));
    }

    public void transition(long id, String state) {
        Integer count = jdbc.queryForObject(
                "select count(*) from siniestro_facil.siniestro where id=?",
                Integer.class, id);
        if (count == null || count == 0) {
            throw new IllegalArgumentException("Siniestro inexistente");
        }
        jdbc.update("update siniestro_facil.siniestro set estado=? where id=?", state, id);
        jdbc.update(
                "insert into siniestro_facil.siniestro_estado_historial " +
                "(siniestro_id,estado,fecha_evento) values(?,?,current_timestamp)",
                id, state);
        jdbc.update(
                "insert into siniestro_facil.auditoria(entidad,entidad_id,fecha_evento) " +
                "values(?,?,current_timestamp)",
                "SINIESTRO", id);
    }

    private record IdempotencyRecord(String requestHash, Long siniestroId) {}
}
