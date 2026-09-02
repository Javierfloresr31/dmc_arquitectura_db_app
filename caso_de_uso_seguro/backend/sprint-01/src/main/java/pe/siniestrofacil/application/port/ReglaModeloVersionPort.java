package pe.siniestrofacil.application.port;

import java.util.List;
import java.util.Optional;

public interface ReglaModeloVersionPort {

    ReglaModeloVersion registrar(String tipo, String version);

    List<ReglaModeloVersion> listar();

    Optional<ReglaModeloVersion> buscarPorId(Long id);

    record ReglaModeloVersion(
            Long id,
            String tipo,
            String version) {
    }
}
