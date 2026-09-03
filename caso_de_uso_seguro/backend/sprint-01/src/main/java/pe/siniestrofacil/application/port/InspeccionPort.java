package pe.siniestrofacil.application.port;

import java.util.List;

public interface InspeccionPort {

    Inspeccion registrar(
            Long siniestroId,
            Long ajustadorId,
            String programacion);

    List<Inspeccion> listar(Long siniestroId);

    Inspeccion registrarResultado(
            Long inspeccionId,
            String resultado);

    Inspeccion obtener(Long inspeccionId);

    record Inspeccion(
            Long id,
            Long siniestroId,
            Long ajustadorId,
            String programacion,
            String resultado) {
    }
}
