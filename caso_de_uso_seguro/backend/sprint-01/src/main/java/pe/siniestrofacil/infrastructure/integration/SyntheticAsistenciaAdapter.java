package pe.siniestrofacil.infrastructure.integration;

import org.springframework.stereotype.Component;
import pe.siniestrofacil.application.port.AsistenciaPort;
import pe.siniestrofacil.infrastructure.persistence.JdbcAsistenciaRepository;

@Component
public class SyntheticAsistenciaAdapter implements AsistenciaPort {

    private final JdbcAsistenciaRepository repository;

    public SyntheticAsistenciaAdapter(JdbcAsistenciaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Resultado solicitar(Long siniestroId, Long proveedorAsistenciaId) {
        repository.registrar(siniestroId, proveedorAsistenciaId);
        return resultado(siniestroId, proveedorAsistenciaId, "SOLICITAR");
    }

    @Override
    public Resultado reintentar(Long siniestroId, Long proveedorAsistenciaId) {
        repository.registrar(siniestroId, proveedorAsistenciaId);
        return resultado(siniestroId, proveedorAsistenciaId, "REINTENTAR");
    }

    @Override
    public Resultado escalar(Long siniestroId, Long proveedorAsistenciaId) {
        repository.registrar(siniestroId, proveedorAsistenciaId);
        return resultado(siniestroId, proveedorAsistenciaId, "ESCALAR");
    }

    @Override
    public Resultado reasignar(Long siniestroId, Long proveedorAsistenciaId) {
        repository.registrar(siniestroId, proveedorAsistenciaId);
        return resultado(siniestroId, proveedorAsistenciaId, "REASIGNAR");
    }

    private Resultado resultado(
            Long siniestroId,
            Long proveedorAsistenciaId,
            String operacion) {

        return new Resultado(
                siniestroId,
                proveedorAsistenciaId,
                operacion,
                true);
    }
}
