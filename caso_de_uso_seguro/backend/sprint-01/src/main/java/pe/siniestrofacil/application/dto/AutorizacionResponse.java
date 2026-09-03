package pe.siniestrofacil.application.dto;

import pe.siniestrofacil.application.port.AutorizacionPort;

public record AutorizacionResponse(
        Long id,
        Long siniestroId,
        String aprobador) {

    public static AutorizacionResponse from(
            AutorizacionPort.Autorizacion autorizacion) {

        return new AutorizacionResponse(
                autorizacion.id(),
                autorizacion.siniestroId(),
                autorizacion.aprobador());
    }
}
