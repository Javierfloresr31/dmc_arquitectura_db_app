package pe.siniestrofacil.application.port;

import java.util.List;
import java.util.Optional;

public interface AutorizacionPort {

    Resultado registrar(
            Long siniestroId,
            String aprobador,
            String idempotencyKey,
            String requestHash,
            String correlationId);

    Optional<Resultado> buscarPorIdempotencyKey(
            String idempotencyKey,
            String requestHash);

    Optional<Autorizacion> buscarPorIdYPorSiniestro(
            Long autorizacionId,
            Long siniestroId);

    List<Autorizacion> listar(Long siniestroId);

    record Autorizacion(
            Long id,
            Long siniestroId,
            String aprobador) {
    }

    record Resultado(
            Autorizacion autorizacion,
            boolean nueva) {
    }
}
