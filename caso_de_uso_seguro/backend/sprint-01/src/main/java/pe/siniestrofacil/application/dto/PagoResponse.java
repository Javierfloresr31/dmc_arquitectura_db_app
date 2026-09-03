package pe.siniestrofacil.application.dto;

import pe.siniestrofacil.application.port.PagoPort;

public record PagoResponse(
        Long id,
        Long siniestroId,
        String autorizacion) {

    public static PagoResponse from(
            PagoPort.Pago pago) {

        return new PagoResponse(
                pago.id(),
                pago.siniestroId(),
                pago.autorizacion());
    }
}
