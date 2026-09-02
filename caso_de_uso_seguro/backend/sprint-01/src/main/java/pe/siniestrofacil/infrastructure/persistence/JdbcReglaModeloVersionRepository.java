package pe.siniestrofacil.infrastructure.persistence;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.siniestrofacil.application.port.ReglaModeloVersionPort;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcReglaModeloVersionRepository implements ReglaModeloVersionPort {

    private final JdbcTemplate jdbc;

    public JdbcReglaModeloVersionRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ReglaModeloVersion registrar(String tipo, String version) {
        Long id = jdbc.queryForObject(
                "insert into siniestro_facil.regla_modelo_version " +
                "(tipo, version) values (?, ?) returning id",
                Long.class,
                tipo,
                version);

        return buscarPorId(id).orElseThrow(
                () -> new IllegalStateException(
                        "No se pudo recuperar la regla/modelo registrada"));
    }

    @Override
    public List<ReglaModeloVersion> listar() {
        return jdbc.query(
                "select id, tipo, version " +
                "from siniestro_facil.regla_modelo_version " +
                "order by id",
                (rs, rowNum) -> new ReglaModeloVersion(
                        rs.getLong("id"),
                        rs.getString("tipo"),
                        rs.getString("version")));
    }

    @Override
    public Optional<ReglaModeloVersion> buscarPorId(Long id) {
        return jdbc.query(
                "select id, tipo, version " +
                "from siniestro_facil.regla_modelo_version " +
                "where id=?",
                (rs, rowNum) -> new ReglaModeloVersion(
                        rs.getLong("id"),
                        rs.getString("tipo"),
                        rs.getString("version")),
                id)
                .stream()
                .findFirst();
    }
}
