package pe.siniestrofacil.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.siniestrofacil.application.port.PresupuestoPort;
import pe.siniestrofacil.domain.model.Siniestro;
import pe.siniestrofacil.domain.port.SiniestroRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class PresupuestoService {

    private final PresupuestoPort presupuestoPort;
    private final SiniestroRepository siniestroRepository;

    public PresupuestoService(
            PresupuestoPort presupuestoPort,
            SiniestroRepository siniestroRepository) {

        this.presupuestoPort = presupuestoPort;
        this.siniestroRepository = siniestroRepository;
    }

    @Transactional
    public PresupuestoPort.Presupuesto registrar(
            Long siniestroId,
            Long tallerId,
            String presupuesto,
            String diagnostico,
            LocalDate vigencia,
            String observaciones,
            String repuestosAlternativos,
            String ampliaciones) {

        Siniestro siniestro = siniestroRepository.findById(siniestroId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Siniestro inexistente"));

        if (!Siniestro.INSPECCION_PROGRAMADA.equals(
                siniestro.estado())) {

            throw new IllegalStateException(
                    "El presupuesto solo puede registrarse " +
                    "cuando el siniestro está INSPECCION_PROGRAMADA");
        }

        LocalDate vigenciaCalculada =
                LocalDate.now().plusDays(7);

        if (vigencia != null &&
                !vigencia.equals(vigenciaCalculada)) {

            throw new IllegalArgumentException(
                    "La vigencia del presupuesto debe ser de 7 días calendario");
        }

        PresupuestoPort.Presupuesto resultado =
                presupuestoPort.registrar(
                        siniestroId,
                        tallerId,
                        presupuesto,
                        diagnostico,
                        vigenciaCalculada,
                        observaciones,
                        repuestosAlternativos,
                        ampliaciones);

        siniestroRepository.transition(
                siniestroId,
                Siniestro.PRESUPUESTO_RECIBIDO);

        return resultado;
    }

    public List<PresupuestoPort.Presupuesto> listar(
            Long siniestroId) {

        return presupuestoPort.listar(siniestroId);
    }

    public PresupuestoPort.Presupuesto obtener(
            Long presupuestoId) {

        return presupuestoPort.obtener(presupuestoId);
    }
}
