package pe.siniestrofacil.domain.model;

import java.time.OffsetDateTime;

public record Siniestro(
        Long id,
        Long polizaId,
        Long vehiculoId,
        OffsetDateTime fecha,
        String ubicacionAproximada,
        String tipoEvento,
        String danosAparentes,
        String estado) {

    public static final String REPORTADO = "REPORTADO";
    public static final String VALIDANDO_COBERTURA = "VALIDANDO_COBERTURA";
    public static final String ASISTENCIA_COORDINADA = "ASISTENCIA_COORDINADA";
    public static final String EVIDENCIA_PENDIENTE = "EVIDENCIA_PENDIENTE";
    public static final String EN_EVALUACION = "EN_EVALUACION";
    public static final String INSPECCION_PROGRAMADA = "INSPECCION_PROGRAMADA";
    public static final String PRESUPUESTO_RECIBIDO = "PRESUPUESTO_RECIBIDO";
    public static final String AUTORIZADO = "AUTORIZADO";
}
