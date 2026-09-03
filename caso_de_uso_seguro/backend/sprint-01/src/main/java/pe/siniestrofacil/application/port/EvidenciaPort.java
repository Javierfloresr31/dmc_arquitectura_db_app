package pe.siniestrofacil.application.port;

import java.time.OffsetDateTime;
import java.util.List;

public interface EvidenciaPort {

    Evidencia registrar(
            Long siniestroId,
            String contenidoOriginal,
            String hash,
            String metadatosDisponibles,
            OffsetDateTime fechaRecepcion,
            String fuente,
            String transformaciones);

    List<Evidencia> listar(Long siniestroId);

    Evidencia obtener(Long evidenciaId);

    Version registrarVersion(Long evidenciaId, String transformacion);

    record Evidencia(
            Long id,
            Long siniestroId,
            String contenidoOriginal,
            String hash,
            String metadatosDisponibles,
            OffsetDateTime fechaRecepcion,
            String fuente,
            String transformaciones) {
    }

    record Version(
            Long id,
            Long evidenciaId,
            String transformacion) {
    }
}
