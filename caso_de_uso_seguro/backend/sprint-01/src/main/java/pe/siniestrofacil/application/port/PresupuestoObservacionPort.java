package pe.siniestrofacil.application.port;

import java.time.OffsetDateTime;
import java.util.Optional;

public interface PresupuestoObservacionPort {

    Resultado registrar(
            Long presupuestoId,
            String responsable,
            String observacion,
            String idempotencyKey,
            String requestHash,
            String correlationId
    );

    Optional<Resultado> buscarPorIdempotencyKey(
            String idempotencyKey,
            String requestHash
    );

    record Resultado(
            Observacion observacion,
            boolean nueva
    ) {}

    record Observacion(
            Long id,
            Long presupuestoId,
            String responsable,
            String observacion,
            OffsetDateTime fechaEvento
    ) {}
}
