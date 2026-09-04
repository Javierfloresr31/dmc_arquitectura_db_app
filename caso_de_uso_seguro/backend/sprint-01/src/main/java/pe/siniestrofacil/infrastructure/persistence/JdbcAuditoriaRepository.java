package pe.siniestrofacil.infrastructure.persistence;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.siniestrofacil.application.port.AuditoriaPort;

@Repository
public class JdbcAuditoriaRepository implements AuditoriaPort {

    private final JdbcTemplate jdbc;

    public JdbcAuditoriaRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void registrar(String entidad, Long entidadId) {
        jdbc.update(
                "insert into siniestro_facil.auditoria " +
                "(entidad, entidad_id, fecha_evento) " +
                "values (?, ?, current_timestamp)",
                entidad,
                entidadId);
    }

    @Override
    public void registrar(
            String entidad,
            Long entidadId,
            String evento,
            String actor,
            String correlationId) {

        jdbc.update(
                "insert into siniestro_facil.auditoria " +
                "(entidad, entidad_id, evento, actor, fecha_evento, correlation_id) " +
                "values (?, ?, ?, ?, current_timestamp, ?)",
                entidad,
                entidadId,
                evento,
                actor,
                correlationId);
    }
}
