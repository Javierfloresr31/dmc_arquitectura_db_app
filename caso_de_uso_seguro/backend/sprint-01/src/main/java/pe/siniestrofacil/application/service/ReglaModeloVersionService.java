package pe.siniestrofacil.application.service;

import org.springframework.stereotype.Service;
import pe.siniestrofacil.application.dto.ReglaModeloVersionRequest;
import pe.siniestrofacil.application.dto.ReglaModeloVersionResponse;
import pe.siniestrofacil.application.port.ReglaModeloVersionPort;

import java.util.List;

@Service
public class ReglaModeloVersionService {

    private final ReglaModeloVersionPort port;

    public ReglaModeloVersionService(ReglaModeloVersionPort port) {
        this.port = port;
    }

    public ReglaModeloVersionResponse registrar(
            ReglaModeloVersionRequest request) {

        if (request == null ||
                request.tipo() == null ||
                request.tipo().isBlank()) {
            throw new IllegalArgumentException(
                    "El tipo de regla/modelo es obligatorio");
        }

        if (request.version() == null ||
                request.version().isBlank()) {
            throw new IllegalArgumentException(
                    "La versión de regla/modelo es obligatoria");
        }

        String tipo = request.tipo().trim().toUpperCase();

        if (!tipo.equals("REGLA") && !tipo.equals("MODELO")) {
            throw new IllegalArgumentException(
                    "El tipo debe ser REGLA o MODELO");
        }

        var result = port.registrar(tipo, request.version().trim());

        return new ReglaModeloVersionResponse(
                result.id(),
                result.tipo(),
                result.version());
    }

    public List<ReglaModeloVersionResponse> listar() {
        return port.listar()
                .stream()
                .map(r -> new ReglaModeloVersionResponse(
                        r.id(),
                        r.tipo(),
                        r.version()))
                .toList();
    }

    public ReglaModeloVersionResponse obtener(Long id) {
        return port.buscarPorId(id)
                .map(r -> new ReglaModeloVersionResponse(
                        r.id(),
                        r.tipo(),
                        r.version()))
                .orElseThrow(() -> new IllegalArgumentException(
                        "Regla/modelo inexistente"));
    }
}
