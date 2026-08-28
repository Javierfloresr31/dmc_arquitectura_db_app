package pe.siniestrofacil.application.service;

import org.springframework.stereotype.Service;
import pe.siniestrofacil.application.port.AsistenciaPort;

@Service
public class AsistenciaService {

    private final AsistenciaPort asistenciaPort;

    public AsistenciaService(AsistenciaPort asistenciaPort) {
        this.asistenciaPort = asistenciaPort;
    }

    public AsistenciaPort.Resultado solicitar(
            Long siniestroId,
            Long proveedorAsistenciaId) {
        return asistenciaPort.solicitar(
                siniestroId,
                proveedorAsistenciaId);
    }

    public AsistenciaPort.Resultado reintentar(
            Long siniestroId,
            Long proveedorAsistenciaId) {
        return asistenciaPort.reintentar(
                siniestroId,
                proveedorAsistenciaId);
    }

    public AsistenciaPort.Resultado escalar(
            Long siniestroId,
            Long proveedorAsistenciaId) {
        return asistenciaPort.escalar(
                siniestroId,
                proveedorAsistenciaId);
    }

    public AsistenciaPort.Resultado reasignar(
            Long siniestroId,
            Long proveedorAsistenciaId) {
        return asistenciaPort.reasignar(
                siniestroId,
                proveedorAsistenciaId);
    }
}
