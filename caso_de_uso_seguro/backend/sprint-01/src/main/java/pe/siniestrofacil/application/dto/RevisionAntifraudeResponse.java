package pe.siniestrofacil.application.dto;

public record RevisionAntifraudeResponse(
        Long id,
        Long alertaAntifraudeId,
        String resultado,
        String justificacion
) {}
