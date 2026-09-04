package pe.siniestrofacil.application.port;

public interface AuditoriaPort {

    void registrar(String entidad, Long entidadId);

    void registrar(
            String entidad,
            Long entidadId,
            String evento,
            String actor,
            String correlationId);
}
