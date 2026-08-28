package pe.siniestrofacil.application.dto;

import pe.siniestrofacil.application.port.EvidenciaPort;

import java.time.OffsetDateTime;

public record EvidenciaResponse(
        Long id,
        Long siniestroId,
        String contenidoOriginal,
        String hash,
        String metadatosDisponibles,
        OffsetDateTime fechaRecepcion,
        String fuente,
        String transformaciones) {

    public static EvidenciaResponse from(EvidenciaPort.Evidencia evidencia) {
        return new EvidenciaResponse(
                evidencia.id(),
                evidencia.siniestroId(),
                evidencia.contenidoOriginal(),
                evidencia.hash(),
                evidencia.metadatosDisponibles(),
                evidencia.fechaRecepcion(),
                evidencia.fuente(),
                evidencia.transformaciones());
    }
}
