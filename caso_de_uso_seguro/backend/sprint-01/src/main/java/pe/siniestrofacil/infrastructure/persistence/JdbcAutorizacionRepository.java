package pe.siniestrofacil.infrastructure.persistence;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.siniestrofacil.application.exception.IdempotencyConflictException;
import pe.siniestrofacil.application.port.AutorizacionPort;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcAutorizacionRepository implements AutorizacionPort {

    private final JdbcTemplate jdbc;

    public JdbcAutorizacionRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Resultado registrar(
            Long siniestroId,
            String aprobador,
            String idempotencyKey,
            String requestHash,
            String correlationId) {

        /*
         * Sin Idempotency-Key se conserva el comportamiento existente.
         */
        if (idempotencyKey == null || idempotencyKey.isBlank()) {

            Long id = insertarAutorizacion(
                    siniestroId,
                    aprobador);

            return new Resultado(
                    obtener(id),
                    true);
        }

        /*
         * Registramos la operación de forma atómica.
         */
        jdbc.update(
                """
                insert into siniestro_facil_meta.idempotencia_request
                    (idempotency_key,
                     request_hash,
                     siniestro_id,
                     correlation_id)
                values (?, ?, ?, ?)
                on conflict (idempotency_key) do nothing
                """,
                idempotencyKey,
                requestHash,
                siniestroId,
                correlationId);

        IdempotencyRecord existing =
                jdbc.query(
                        """
                        select request_hash,
                               siniestro_id,
                               autorizacion_id
                          from siniestro_facil_meta.idempotencia_request
                         where idempotency_key = ?
                        """,
                        (rs, rowNum) ->
                                new IdempotencyRecord(
                                        rs.getString("request_hash"),
                                        (Long) rs.getObject("siniestro_id"),
                                        (Long) rs.getObject("autorizacion_id")),
                        idempotencyKey)
                .stream()
                .findFirst()
                .orElseThrow(() ->
                        new IllegalStateException(
                                "No se pudo registrar la Idempotency-Key"));

        /*
         * Misma clave pero payload diferente.
         */
        if (!existing.requestHash().equals(requestHash)) {

            throw new IdempotencyConflictException(
                    "La Idempotency-Key ya fue utilizada " +
                    "con un payload diferente");
        }

        /*
         * Operación ya completada.
         */
        if (existing.autorizacionId() != null) {

            return new Resultado(
                    obtener(existing.autorizacionId()),
                    false);
        }

        /*
         * La clave es nueva y todavía no tiene autorización.
         */
        Long id = insertarAutorizacion(
                siniestroId,
                aprobador);

        jdbc.update(
                """
                update siniestro_facil_meta.idempotencia_request
                   set autorizacion_id = ?,
                       completed_at = current_timestamp
                 where idempotency_key = ?
                """,
                id,
                idempotencyKey);

        return new Resultado(
                obtener(id),
                true);
    }

    private Long insertarAutorizacion(
            Long siniestroId,
            String aprobador) {

        return jdbc.queryForObject(
                """
                insert into siniestro_facil.autorizacion
                    (siniestro_id, aprobador)
                values (?, ?)
                returning id
                """,
                Long.class,
                siniestroId,
                aprobador);
    }

@Override
public Optional<Resultado> buscarPorIdempotencyKey(
        String idempotencyKey,
        String requestHash) {

    if (idempotencyKey == null || idempotencyKey.isBlank()) {
        return Optional.empty();
    }

    IdempotencyRecord existing =
            jdbc.query(
                    """
                    select request_hash,
                           siniestro_id,
                           autorizacion_id
                      from siniestro_facil_meta.idempotencia_request
                     where idempotency_key = ?
                    """,
                    (rs, rowNum) ->
                            new IdempotencyRecord(
                                    rs.getString("request_hash"),
                                    (Long) rs.getObject("siniestro_id"),
                                    (Long) rs.getObject("autorizacion_id")),
                    idempotencyKey)
            .stream()
            .findFirst()
            .orElse(null);

    if (existing == null) {
        return Optional.empty();
    }

    if (!existing.requestHash().equals(requestHash)) {
        throw new IdempotencyConflictException(
                "La Idempotency-Key ya fue utilizada " +
                "con un payload diferente");
    }

    if (existing.autorizacionId() == null) {
        return Optional.empty();
    }

    return Optional.of(
            new Resultado(
                    obtener(existing.autorizacionId()),
                    false));
}

    @Override
    public Optional<Autorizacion> buscarPorIdYPorSiniestro(
            Long autorizacionId,
            Long siniestroId) {

        return jdbc.query(
                """
                select id,
                       siniestro_id,
                       aprobador
                  from siniestro_facil.autorizacion
                 where id = ?
                   and siniestro_id = ?
                """,
                (rs, rowNum) ->
                        new Autorizacion(
                                rs.getLong("id"),
                                rs.getLong("siniestro_id"),
                                rs.getString("aprobador")),
                autorizacionId,
                siniestroId)
                .stream()
                .findFirst();
    }

    @Override
    public List<Autorizacion> listar(
            Long siniestroId) {

        return jdbc.query(
                """
                select id,
                       siniestro_id,
                       aprobador
                  from siniestro_facil.autorizacion
                 where siniestro_id = ?
                 order by id
                """,
                (rs, rowNum) ->
                        new Autorizacion(
                                rs.getLong("id"),
                                rs.getLong("siniestro_id"),
                                rs.getString("aprobador")),
                siniestroId);
    }

    private Autorizacion obtener(Long id) {

        return jdbc.queryForObject(
                """
                select id,
                       siniestro_id,
                       aprobador
                  from siniestro_facil.autorizacion
                 where id = ?
                """,
                (rs, rowNum) ->
                        new Autorizacion(
                                rs.getLong("id"),
                                rs.getLong("siniestro_id"),
                                rs.getString("aprobador")),
                id);
    }

    private record IdempotencyRecord(
            String requestHash,
            Long siniestroId,
            Long autorizacionId) {
    }
}
