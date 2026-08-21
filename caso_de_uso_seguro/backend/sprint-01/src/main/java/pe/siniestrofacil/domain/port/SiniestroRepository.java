package pe.siniestrofacil.domain.port;

import pe.siniestrofacil.application.dto.CrearSiniestroRequest;
import pe.siniestrofacil.domain.model.Siniestro;
import java.util.*;

public interface SiniestroRepository {
    Siniestro createByBusinessKeys(
            String numeroPoliza,
            String placa,
            Siniestro siniestro,
            CrearSiniestroRequest.ParticipanteRequest reportante,
            String idempotencyKey,
            String requestHash,
            String correlationId);

    Optional<Siniestro> findById(long id);
    List<Siniestro> findAll();
    void transition(long id, String estado);
}
