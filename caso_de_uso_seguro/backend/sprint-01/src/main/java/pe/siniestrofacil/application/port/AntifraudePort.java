package pe.siniestrofacil.application.port;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface AntifraudePort {

    Alerta crearAlerta(
            Long siniestroId,
            String tipo,
            String severidad,
            String explicacion,
            String datosOrigen,
            OffsetDateTime fecha,
            String modeloORegla,
            String estado,
            String justificacion,
            Long reglaModeloVersionId);

    void registrarSenal(
            Long alertaAntifraudeId,
            String senal);

    List<Alerta> listarAlertas(Long siniestroId);

    Optional<Alerta> buscarAlerta(Long alertaId);

    List<Senal> listarSenales(Long alertaId);

    record Alerta(
            Long id,
            Long siniestroId,
            String tipo,
            String severidad,
            String explicacion,
            String datosOrigen,
            OffsetDateTime fecha,
            String modeloORegla,
            String estado,
            String justificacion,
            Long reglaModeloVersionId) {
    }

    record Senal(
            Long id,
            Long alertaAntifraudeId,
            String senal) {
    }
}
