package pe.siniestrofacil.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.siniestrofacil.application.port.EvidenciaPort;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class EvidenciaService {

    private final EvidenciaPort evidenciaPort;

    public EvidenciaService(EvidenciaPort evidenciaPort) {
        this.evidenciaPort = evidenciaPort;
    }

    @Transactional
    public EvidenciaPort.Evidencia registrar(
            Long siniestroId,
            String contenidoOriginal,
            String hash,
            String metadatosDisponibles,
            OffsetDateTime fechaRecepcion,
            String fuente,
            String transformaciones) {

        return evidenciaPort.registrar(
                siniestroId,
                contenidoOriginal,
                hash,
                metadatosDisponibles,
                fechaRecepcion,
                fuente,
                transformaciones);
    }

    public List<EvidenciaPort.Evidencia> listar(Long siniestroId) {
        return evidenciaPort.listar(siniestroId);
    }

    public EvidenciaPort.Evidencia obtener(Long evidenciaId) {
        return evidenciaPort.obtener(evidenciaId);
    }

    @Transactional
    public EvidenciaPort.Version registrarVersion(
            Long evidenciaId,
            String transformacion) {

        return evidenciaPort.registrarVersion(
                evidenciaId,
                transformacion);
    }
}
