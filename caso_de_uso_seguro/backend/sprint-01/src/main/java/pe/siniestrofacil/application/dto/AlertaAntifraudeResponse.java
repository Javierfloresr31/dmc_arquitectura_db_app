package pe.siniestrofacil.application.dto;

import java.time.OffsetDateTime;

public record AlertaAntifraudeResponse(
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
        Long reglaModeloVersionId
) {}
