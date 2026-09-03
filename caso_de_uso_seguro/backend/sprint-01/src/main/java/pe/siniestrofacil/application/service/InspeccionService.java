package pe.siniestrofacil.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.siniestrofacil.application.port.InspeccionPort;
import pe.siniestrofacil.domain.model.Siniestro;
import pe.siniestrofacil.domain.port.SiniestroRepository;

import java.util.List;

@Service
public class InspeccionService {

    private final InspeccionPort inspeccionPort;
    private final SiniestroRepository siniestroRepository;

    public InspeccionService(
            InspeccionPort inspeccionPort,
            SiniestroRepository siniestroRepository) {

        this.inspeccionPort = inspeccionPort;
        this.siniestroRepository = siniestroRepository;
    }

    @Transactional
    public InspeccionPort.Inspeccion registrar(
            Long siniestroId,
            Long ajustadorId,
            String programacion) {

        Siniestro siniestro = siniestroRepository.findById(siniestroId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Siniestro inexistente"));

        if (!Siniestro.EN_EVALUACION.equals(siniestro.estado())) {
            throw new IllegalStateException(
                    "La inspección solo puede programarse " +
                    "cuando el siniestro está EN_EVALUACION");
        }

        InspeccionPort.Inspeccion inspeccion =
                inspeccionPort.registrar(
                        siniestroId,
                        ajustadorId,
                        programacion);

        siniestroRepository.transition(
                siniestroId,
                Siniestro.INSPECCION_PROGRAMADA);

        return inspeccion;
    }

    public List<InspeccionPort.Inspeccion> listar(
            Long siniestroId) {

        return inspeccionPort.listar(siniestroId);
    }

    @Transactional
    public InspeccionPort.Inspeccion registrarResultado(
            Long inspeccionId,
            String resultado) {

        return inspeccionPort.registrarResultado(
                inspeccionId,
                resultado);
    }

    public InspeccionPort.Inspeccion obtener(
            Long inspeccionId) {

        return inspeccionPort.obtener(inspeccionId);
    }
}
