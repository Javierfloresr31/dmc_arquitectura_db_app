package pe.siniestrofacil.application.dto;

public record EvaluacionAntifraudeRequest(
        Long reglaModeloVersionId,
        String tipo,
        String datosOrigen
) {}
