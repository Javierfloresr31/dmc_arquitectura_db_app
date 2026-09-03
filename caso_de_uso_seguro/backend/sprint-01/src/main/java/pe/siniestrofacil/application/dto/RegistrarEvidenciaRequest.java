package pe.siniestrofacil.application.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.OffsetDateTime;

public record RegistrarEvidenciaRequest(
        String contenidoOriginal,
        @NotBlank String hash,
        String metadatosDisponibles,
        OffsetDateTime fechaRecepcion,
        String fuente,
        String transformaciones) {
}
