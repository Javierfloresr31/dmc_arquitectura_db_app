package pe.siniestrofacil.application.port;

import java.time.LocalDate;
import java.util.List;

public interface PresupuestoPort {

    Presupuesto registrar(
            Long siniestroId,
            Long tallerId,
            String presupuesto,
            String diagnostico,
            LocalDate vigencia,
            String observaciones,
            String repuestosAlternativos,
            String ampliaciones);

    List<Presupuesto> listar(Long siniestroId);

    Presupuesto obtener(Long presupuestoId);

    record Presupuesto(
            Long id,
            Long siniestroId,
            Long tallerId,
            String presupuesto,
            String diagnostico,
            LocalDate vigencia,
            String observaciones,
            String repuestosAlternativos,
            String ampliaciones) {
    }
}
