package pe.siniestrofacil.application.port;

public interface AsistenciaPort {

    Resultado solicitar(Long siniestroId, Long proveedorAsistenciaId);

    Resultado reintentar(Long siniestroId, Long proveedorAsistenciaId);

    Resultado escalar(Long siniestroId, Long proveedorAsistenciaId);

    Resultado reasignar(Long siniestroId, Long proveedorAsistenciaId);

    record Resultado(
            Long siniestroId,
            Long proveedorAsistenciaId,
            String operacion,
            boolean registrada) {
    }
}
