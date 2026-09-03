package pe.siniestrofacil.application.dto;

import pe.siniestrofacil.application.port.PresupuestoObservacionPort;

import java.time.OffsetDateTime;

public record PresupuestoObservacionResponse(
        Long id,
        Long presupuestoId,
        Long siniestroId,
        String estado,
        String responsable,
        String observacion,
        OffsetDateTime fechaEvento) {

    public static PresupuestoObservacionResponse from(
            PresupuestoObservacionPort.Observacion observacion,
            Long siniestroId) {

        return new PresupuestoObservacionResponse(
                observacion.id(),
                observacion.presupuestoId(),
                siniestroId,
                "OBSERVADO",
                observacion.responsable(),
                observacion.observacion(),
                observacion.fechaEvento());
    }
}
