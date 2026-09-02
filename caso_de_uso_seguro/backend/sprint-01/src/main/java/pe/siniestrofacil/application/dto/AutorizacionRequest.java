package pe.siniestrofacil.application.dto;

import jakarta.validation.constraints.NotBlank;

public record AutorizacionRequest(
        @NotBlank String aprobador) {
}
