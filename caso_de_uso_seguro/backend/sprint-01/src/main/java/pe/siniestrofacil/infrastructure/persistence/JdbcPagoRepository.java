package pe.siniestrofacil.infrastructure.persistence;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.siniestrofacil.application.exception.IdempotencyConflictException;
import pe.siniestrofacil.application.port.PagoPort;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcPagoRepository implements PagoPort {

    private final JdbcTemplate jdbc;

    public JdbcPagoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Optional<Resultado> buscarPorIdempotencyKey(
            String idempotencyKey,
            String requestHash) {

        return jdbc.query(
                """
                select request_hash,
                       pago_id
                  from siniestro_facil_meta.idempotencia_request
                 where idempotency_key = ?
                """,
                (rs, rowNum) -> new IdempotencyRecord(
                        rs.getString("request_hash"),
                        rs.getObject("pago_id", Long.class)),
                idempotencyKey)
                .stream()
                .findFirst()
                .map(existing -> {

                    if (!requestHash.equals(
                            existing.requestHash())) {

                        throw new IdempotencyConflictException(
                                "La Idempotency-Key ya fue utilizada " +
                                "para una operación diferente");
                    }

                    if (existing.pagoId() == null) {
                        return null;
                    }

                    Pago pago = obtener(existing.pagoId());

                    return new Resultado(
                            pago,
                            false);
                });
    }

    @Override
    public Resultado registrar(
            Long siniestroId,
            Long autorizacionId,
            String idempotencyKey,
            String requestHash,
            String correlationId) {

        try {

            jdbc.update(
                    """
                    insert into siniestro_facil_meta.idempotencia_request
                        (
                            idempotency_key,
                            request_hash,
                            siniestro_id,
                            correlation_id
                        )
                    values (?, ?, ?, ?)
                    """,
                    idempotencyKey,
                    requestHash,
                    siniestroId,
                    correlationId);

        } catch (DuplicateKeyException e) {

            return buscarPorIdempotencyKey(
                    idempotencyKey,
                    requestHash)
                    .orElseThrow(() ->
                            new IllegalStateException(
                                    "No se pudo resolver la Idempotency-Key"));
        }

        Long pagoId =
                jdbc.queryForObject(
                        """
                        insert into siniestro_facil.pago
                            (siniestro_id, autorizacion)
                        values (?, ?)
                        returning id
                        """,
                        Long.class,
                        siniestroId,
                        String.valueOf(autorizacionId));

        jdbc.update(
                """
                update siniestro_facil_meta.idempotencia_request
                   set pago_id = ?,
                       completed_at = current_timestamp
                 where idempotency_key = ?
                """,
                pagoId,
                idempotencyKey);

        return new Resultado(
                obtener(pagoId),
                true);
    }

    @Override
    public boolean existeOperacionEquivalente(
            Long siniestroId,
            Long autorizacionId) {

        Integer cantidad =
                jdbc.queryForObject(
                        """
                        select count(*)
                          from siniestro_facil.pago
                         where siniestro_id = ?
                           and autorizacion = ?
                        """,
                        Integer.class,
                        siniestroId,
                        String.valueOf(autorizacionId));

        return cantidad != null && cantidad > 0;
    }

    @Override
    public List<Pago> listar(
            Long siniestroId) {

        return jdbc.query(
                """
                select id,
                       siniestro_id,
                       autorizacion
                  from siniestro_facil.pago
                 where siniestro_id = ?
                 order by id
                """,
                (rs, rowNum) ->
                        new Pago(
                                rs.getLong("id"),
                                rs.getLong("siniestro_id"),
                                rs.getString("autorizacion")),
                siniestroId);
    }

    private Pago obtener(Long id) {

        return jdbc.queryForObject(
                """
                select id,
                       siniestro_id,
                       autorizacion
                  from siniestro_facil.pago
                 where id = ?
                """,
                (rs, rowNum) ->
                        new Pago(
                                rs.getLong("id"),
                                rs.getLong("siniestro_id"),
                                rs.getString("autorizacion")),
                id);
    }

    private record IdempotencyRecord(
            String requestHash,
            Long pagoId) {
    }
}
