package pe.siniestrofacil.application.dto;

import pe.siniestrofacil.application.port.AsistenciaPort;

public record AsistenciaResponse(
        Long siniestroId,
        Long proveedorAsistenciaId,
        String operacion,
        boolean registrada) {

    public static AsistenciaResponse from(
            AsistenciaPort.Resultado resultado) {

        return new AsistenciaResponse(
                resultado.siniestroId(),
                resultado.proveedorAsistenciaId(),
                resultado.operacion(),
                resultado.registrada());
    }
}
