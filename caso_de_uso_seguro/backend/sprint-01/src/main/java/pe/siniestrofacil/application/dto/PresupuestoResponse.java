package pe.siniestrofacil.application.dto;

import pe.siniestrofacil.application.port.PresupuestoPort;

import java.time.LocalDate;

public record PresupuestoResponse(
        Long id,
        Long siniestroId,
        Long tallerId,
        String presupuesto,
        String diagnostico,
        LocalDate vigencia,
        String observaciones,
        String repuestosAlternativos,
        String ampliaciones) {

    public static PresupuestoResponse from(
            PresupuestoPort.Presupuesto presupuesto) {

        return new PresupuestoResponse(
                presupuesto.id(),
                presupuesto.siniestroId(),
                presupuesto.tallerId(),
                presupuesto.presupuesto(),
                presupuesto.diagnostico(),
                presupuesto.vigencia(),
                presupuesto.observaciones(),
                presupuesto.repuestosAlternativos(),
                presupuesto.ampliaciones());
    }
}
