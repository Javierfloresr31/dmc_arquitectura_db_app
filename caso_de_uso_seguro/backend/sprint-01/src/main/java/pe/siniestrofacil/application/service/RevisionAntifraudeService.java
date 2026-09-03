package pe.siniestrofacil.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.siniestrofacil.application.dto.RevisionAntifraudeRequest;
import pe.siniestrofacil.application.dto.RevisionAntifraudeResponse;
import pe.siniestrofacil.application.port.AuditoriaPort;
import pe.siniestrofacil.application.port.RevisionAntifraudePort;


@Service
public class RevisionAntifraudeService {

    private final RevisionAntifraudePort port;
    private final AuditoriaPort auditoriaPort;

    public RevisionAntifraudeService(
            RevisionAntifraudePort port,
            AuditoriaPort auditoriaPort) {
        this.port = port;
        this.auditoriaPort = auditoriaPort;
    }

    @Transactional
    public RevisionAntifraudeResponse registrar(
            Long alertaAntifraudeId,
            RevisionAntifraudeRequest request) {

        if (alertaAntifraudeId == null) {
            throw new IllegalArgumentException(
                    "La alerta antifraude es obligatoria");
        }

        if (request == null ||
                request.resultado() == null ||
                request.resultado().isBlank()) {
            throw new IllegalArgumentException(
                    "El resultado de revisión es obligatorio");
        }

        if (request.justificacion() == null ||
                request.justificacion().isBlank()) {
            throw new IllegalArgumentException(
                    "La justificación es obligatoria");
        }

        String resultado = normalizarResultado(request.resultado());

        String estadoAlerta = switch (resultado) {
            case "CONFIRMADA" -> "CONFIRMADA";
            case "DESCARTADA" -> "DESCARTADA";
            case "MAS_INFORMACION" -> "PENDIENTE_REVISION";
            default -> throw new IllegalStateException(
                    "Resultado de revisión no soportado");
        };

        var revision = port.registrar(
                alertaAntifraudeId,
                resultado,
                request.justificacion().trim());

        port.actualizarEstadoAlerta(
                alertaAntifraudeId,
                estadoAlerta);

        auditoriaPort.registrar(
                "REVISION_ANTIFRAUDE",
                revision.id());

        return toResponse(revision);
    }

    private RevisionAntifraudeResponse toResponse(
            RevisionAntifraudePort.Revision revision) {

        return new RevisionAntifraudeResponse(
                revision.id(),
                revision.alertaAntifraudeId(),
                revision.resultado(),
                revision.justificacion());
    }

    private String normalizarResultado(String resultado) {

        String valor = resultado.trim()
                .toUpperCase()
                .replace(' ', '_');

        return switch (valor) {
            case "CONFIRMADA" -> "CONFIRMADA";
            case "DESCARTADA" -> "DESCARTADA";
            case "MAS_INFORMACION", "MAS INFORMACION" ->
                    "MAS_INFORMACION";
            default -> throw new IllegalArgumentException(
                    "Resultado de revisión no soportado: " + resultado);
        };
    }
}
