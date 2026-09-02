package pe.siniestrofacil.application.port;

public interface RevisionAntifraudePort {

    Revision registrar(
            Long alertaAntifraudeId,
            String resultado,
            String justificacion);

    void actualizarEstadoAlerta(
            Long alertaAntifraudeId,
            String estado);

    record Revision(
            Long id,
            Long alertaAntifraudeId,
            String resultado,
            String justificacion) {
    }
}
