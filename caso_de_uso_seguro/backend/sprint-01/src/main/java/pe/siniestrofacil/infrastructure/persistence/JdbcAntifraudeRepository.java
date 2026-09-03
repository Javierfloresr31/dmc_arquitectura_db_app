package pe.siniestrofacil.infrastructure.persistence;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.siniestrofacil.application.port.AntifraudePort;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcAntifraudeRepository implements AntifraudePort {

    private final JdbcTemplate jdbc;

    public JdbcAntifraudeRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Alerta crearAlerta(
            Long siniestroId,
            String tipo,
            String severidad,
            String explicacion,
            String datosOrigen,
            OffsetDateTime fecha,
            String modeloORegla,
            String estado,
            String justificacion,
            Long reglaModeloVersionId) {

        Long id = jdbc.queryForObject(
                "insert into siniestro_facil.alerta_antifraude " +
                "(siniestro_id,tipo,severidad,explicacion,datos_origen,fecha," +
                "modelo_o_regla,estado,justificacion,regla_modelo_version_id) " +
                "values(?,?,?,?,?,?,?,?,?,?) returning id",
                Long.class,
                siniestroId,
                tipo,
                severidad,
                explicacion,
                datosOrigen,
                fecha == null ? null : Timestamp.from(fecha.toInstant()),
                modeloORegla,
                estado,
                justificacion,
                reglaModeloVersionId);

        return buscarAlerta(id).orElseThrow(
                () -> new IllegalStateException(
                        "No se pudo recuperar la alerta antifraude creada"));
    }

    @Override
    public void registrarSenal(
            Long alertaAntifraudeId,
            String senal) {

        jdbc.update(
                "insert into siniestro_facil.alerta_senal " +
                "(alerta_antifraude_id,senal) values(?,?)",
                alertaAntifraudeId,
                senal);
    }

    @Override
    public List<Alerta> listarAlertas(Long siniestroId) {
        return jdbc.query(
                "select id,siniestro_id,tipo,severidad,explicacion,datos_origen," +
                "fecha,modelo_o_regla,estado,justificacion,regla_modelo_version_id " +
                "from siniestro_facil.alerta_antifraude " +
                "where siniestro_id=? order by id",
                (rs, rowNum) -> mapAlerta(rs),
                siniestroId);
    }

    @Override
    public Optional<Alerta> buscarAlerta(Long alertaId) {
        return jdbc.query(
                "select id,siniestro_id,tipo,severidad,explicacion,datos_origen," +
                "fecha,modelo_o_regla,estado,justificacion,regla_modelo_version_id " +
                "from siniestro_facil.alerta_antifraude " +
                "where id=?",
                (rs, rowNum) -> mapAlerta(rs),
                alertaId)
                .stream()
                .findFirst();
    }

    @Override
    public List<Senal> listarSenales(Long alertaId) {
        return jdbc.query(
                "select id,alerta_antifraude_id,senal " +
                "from siniestro_facil.alerta_senal " +
                "where alerta_antifraude_id=? order by id",
                (rs, rowNum) -> new Senal(
                        rs.getLong("id"),
                        rs.getLong("alerta_antifraude_id"),
                        rs.getString("senal")),
                alertaId);
    }

    private Alerta mapAlerta(java.sql.ResultSet rs)
            throws java.sql.SQLException {

        Timestamp timestamp = rs.getTimestamp("fecha");

        return new Alerta(
                rs.getLong("id"),
                rs.getLong("siniestro_id"),
                rs.getString("tipo"),
                rs.getString("severidad"),
                rs.getString("explicacion"),
                rs.getString("datos_origen"),
                timestamp == null
                        ? null
                        : timestamp.toInstant()
                            .atOffset(java.time.ZoneOffset.UTC),
                rs.getString("modelo_o_regla"),
                rs.getString("estado"),
                rs.getString("justificacion"),
                rs.getObject("regla_modelo_version_id", Long.class));
    }
}
