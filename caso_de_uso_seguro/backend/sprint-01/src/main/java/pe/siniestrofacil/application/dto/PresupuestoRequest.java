package pe.siniestrofacil.application.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PresupuestoRequest(
        @NotNull Long tallerId,
        String presupuesto,
        String diagnostico,
        LocalDate vigencia,
        String observaciones,
        String repuestosAlternativos,
        String ampliaciones) {
}
