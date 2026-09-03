package pe.siniestrofacil.infrastructure.persistence;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.siniestrofacil.application.exception.IdempotencyConflictException;
import pe.siniestrofacil.application.port.PresupuestoObservacionPort;

import java.time.OffsetDateTime;
import java.util.Optional;

@Repository
public class JdbcPresupuestoObservacionRepository
        implements PresupuestoObservacionPort {

    private final JdbcTemplate jdbc;

    public JdbcPresupuestoObservacionRepository(
            JdbcTemplate jdbc) {

        this.jdbc = jdbc;
    }

    @Override
    public Optional<Resultado> buscarPorIdempotencyKey(
            String idempotencyKey,
            String requestHash) {

        if (idempotencyKey == null ||
                idempotencyKey.isBlank()) {

            return Optional.empty();
        }

        IdempotencyRecord existing =
                jdbc.query(
                        """
                        select request_hash,
                               presupuesto_observacion_id
                          from siniestro_facil_meta.idempotencia_request
                         where idempotency_key = ?
                        """,
                        (rs, rowNum) ->
                                new IdempotencyRecord(
                                        rs.getString("request_hash"),
                                        (Long) rs.getObject(
                                                "presupuesto_observacion_id")),
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

        if (existing.presupuestoObservacionId() == null) {
            return Optional.empty();
        }

        return Optional.of(
                new Resultado(
                        obtener(
                                existing.presupuestoObservacionId()),
                        false));
    }

    @Override
    public Resultado registrar(
            Long presupuestoId,
            String responsable,
            String observacion,
            String idempotencyKey,
            String requestHash,
            String correlationId) {

        /*
         * Sin Idempotency-Key se conserva
         * el comportamiento normal.
         */
        if (idempotencyKey == null ||
                idempotencyKey.isBlank()) {

            Long id = insertarObservacion(
                    presupuestoId,
                    responsable,
                    observacion);

            return new Resultado(
                    obtener(id),
                    true);
        }

        /*
         * Registramos la solicitud de forma atómica.
         */
        jdbc.update(
                """
                insert into siniestro_facil_meta.idempotencia_request
                    (idempotency_key,
                     request_hash,
                     siniestro_id,
                     correlation_id)
                select ?, ?, p.siniestro_id, ?
                  from siniestro_facil.presupuesto p
                 where p.id = ?
                on conflict (idempotency_key) do nothing
                """,
                idempotencyKey,
                requestHash,
                correlationId,
                presupuestoId);

        IdempotencyRecord existing =
                jdbc.query(
                        """
                        select request_hash,
                               presupuesto_observacion_id
                          from siniestro_facil_meta.idempotencia_request
                         where idempotency_key = ?
                        """,
                        (rs, rowNum) ->
                                new IdempotencyRecord(
                                        rs.getString("request_hash"),
                                        (Long) rs.getObject(
                                                "presupuesto_observacion_id")),
                        idempotencyKey)
                .stream()
                .findFirst()
                .orElseThrow(() ->
                        new IllegalStateException(
                                "No se pudo registrar la Idempotency-Key"));

        /*
         * Misma clave con payload diferente.
         */
        if (!existing.requestHash().equals(requestHash)) {

            throw new IdempotencyConflictException(
                    "La Idempotency-Key ya fue utilizada " +
                    "con un payload diferente");
        }

        /*
         * La operación ya fue completada.
         */
        if (existing.presupuestoObservacionId() != null) {

            return new Resultado(
                    obtener(
                            existing.presupuestoObservacionId()),
                    false);
        }

        /*
         * Nueva observación.
         */
        Long id = insertarObservacion(
                presupuestoId,
                responsable,
                observacion);

        jdbc.update(
                """
                update siniestro_facil_meta.idempotencia_request
                   set presupuesto_observacion_id = ?,
                       completed_at = current_timestamp
                 where idempotency_key = ?
                """,
                id,
                idempotencyKey);

        return new Resultado(
                obtener(id),
                true);
    }

    private Long insertarObservacion(
            Long presupuestoId,
            String responsable,
            String observacion) {

        return jdbc.queryForObject(
                """
                insert into siniestro_facil.presupuesto_observacion
                    (presupuesto_id,
                     responsable,
                     observacion,
                     fecha_evento)
                values (?, ?, ?, current_timestamp)
                returning id
                """,
                Long.class,
                presupuestoId,
                responsable,
                observacion);
    }

    private Observacion obtener(Long id) {

        return jdbc.queryForObject(
                """
                select id,
                       presupuesto_id,
                       responsable,
                       observacion,
                       fecha_evento
                  from siniestro_facil.presupuesto_observacion
                 where id = ?
                """,
                (rs, rowNum) -> new Observacion(
                        rs.getLong("id"),
                        rs.getLong("presupuesto_id"),
                        rs.getString("responsable"),
                        rs.getString("observacion"),
                        rs.getObject(
                                "fecha_evento",
                                OffsetDateTime.class)
                ),
                id);
    }

    private record IdempotencyRecord(
            String requestHash,
            Long presupuestoObservacionId) {
    }
}
