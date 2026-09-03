package pe.siniestrofacil.application.dto;

import jakarta.validation.constraints.NotBlank;

public record PresupuestoObservacionRequest(

        @NotBlank
        String responsable,

        @NotBlank
        String observacion
) {}
