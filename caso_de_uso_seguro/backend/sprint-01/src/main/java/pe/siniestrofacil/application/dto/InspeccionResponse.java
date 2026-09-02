package pe.siniestrofacil.application.dto;

import pe.siniestrofacil.application.port.InspeccionPort;

public record InspeccionResponse(
        Long id,
        Long siniestroId,
        Long ajustadorId,
        String programacion,
        String resultado) {

    public static InspeccionResponse from(
            InspeccionPort.Inspeccion inspeccion) {

        return new InspeccionResponse(
                inspeccion.id(),
                inspeccion.siniestroId(),
                inspeccion.ajustadorId(),
                inspeccion.programacion(),
                inspeccion.resultado());
    }
}
