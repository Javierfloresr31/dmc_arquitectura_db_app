package pe.siniestrofacil.application.dto;

import jakarta.validation.constraints.NotNull;

public record AsistenciaRequest(
        @NotNull Long proveedorAsistenciaId) {
}
