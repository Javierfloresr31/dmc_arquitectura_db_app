package pe.siniestrofacil.application.port;

import java.util.List;
import java.util.Optional;

public interface PagoPort {

    Resultado registrar(
            Long siniestroId,
            Long autorizacionId,
            String idempotencyKey,
            String requestHash,
            String correlationId);

    Optional<Resultado> buscarPorIdempotencyKey(
            String idempotencyKey,
            String requestHash);

    boolean existeOperacionEquivalente(
            Long siniestroId,
            Long autorizacionId);

    List<Pago> listar(Long siniestroId);

    record Pago(
            Long id,
            Long siniestroId,
            String autorizacion) {
    }

    record Resultado(
            Pago pago,
            boolean nuevo) {
    }
}
