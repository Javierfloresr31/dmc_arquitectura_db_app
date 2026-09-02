package pe.siniestrofacil.infrastructure.persistence;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.siniestrofacil.application.port.RevisionAntifraudePort;

@Repository
public class JdbcRevisionAntifraudeRepository
        implements RevisionAntifraudePort {

    private final JdbcTemplate jdbc;

    public JdbcRevisionAntifraudeRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Revision registrar(
            Long alertaAntifraudeId,
            String resultado,
            String justificacion) {

        return jdbc.queryForObject(
                "insert into siniestro_facil.revision_antifraude " +
                "(alerta_antifraude_id,resultado,justificacion) " +
                "values(?,?,?) " +
                "returning id,alerta_antifraude_id,resultado,justificacion",
                (rs, rowNum) -> new Revision(
                        rs.getLong("id"),
                        rs.getLong("alerta_antifraude_id"),
                        rs.getString("resultado"),
                        rs.getString("justificacion")),
                alertaAntifraudeId,
                resultado,
                justificacion);
    }

    @Override
    public void actualizarEstadoAlerta(
            Long alertaAntifraudeId,
            String estado) {

        int updated = jdbc.update(
                "update siniestro_facil.alerta_antifraude " +
                "set estado=? " +
                "where id=?",
                estado,
                alertaAntifraudeId);

        if (updated == 0) {
            throw new IllegalArgumentException(
                    "Alerta antifraude inexistente");
        }
    }
}
