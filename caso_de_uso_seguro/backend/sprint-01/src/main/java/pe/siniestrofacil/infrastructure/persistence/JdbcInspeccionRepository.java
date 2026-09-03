package pe.siniestrofacil.infrastructure.persistence;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.siniestrofacil.application.port.InspeccionPort;

import java.util.List;

@Repository
public class JdbcInspeccionRepository implements InspeccionPort {

    private final JdbcTemplate jdbc;

    public JdbcInspeccionRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Inspeccion registrar(
            Long siniestroId,
            Long ajustadorId,
            String programacion) {

        Long id = jdbc.queryForObject(
                """
                insert into siniestro_facil.inspeccion
                    (siniestro_id, ajustador_id, programacion)
                values (?, ?, ?)
                returning id
                """,
                Long.class,
                siniestroId,
                ajustadorId,
                programacion);

        return obtener(id);
    }

    @Override
    public List<Inspeccion> listar(Long siniestroId) {

        return jdbc.query(
                """
                select id,
                       siniestro_id,
                       ajustador_id,
                       programacion,
                       resultado
                  from siniestro_facil.inspeccion
                 where siniestro_id = ?
                 order by id
                """,
                (rs, rowNum) -> new Inspeccion(
                        rs.getLong("id"),
                        rs.getLong("siniestro_id"),
                        rs.getObject("ajustador_id", Long.class),
                        rs.getString("programacion"),
                        rs.getString("resultado")),
                siniestroId);
    }

    @Override
    public Inspeccion registrarResultado(
            Long inspeccionId,
            String resultado) {

        int updated = jdbc.update(
                """
                update siniestro_facil.inspeccion
                   set resultado = ?
                 where id = ?
                """,
                resultado,
                inspeccionId);

        if (updated == 0) {
            throw new IllegalArgumentException(
                    "Inspección inexistente");
        }

        return obtener(inspeccionId);
    }

    @Override
    public Inspeccion obtener(Long inspeccionId) {

        return jdbc.queryForObject(
                """
                select id,
                       siniestro_id,
                       ajustador_id,
                       programacion,
                       resultado
                  from siniestro_facil.inspeccion
                 where id = ?
                """,
                (rs, rowNum) -> new Inspeccion(
                        rs.getLong("id"),
                        rs.getLong("siniestro_id"),
                        rs.getObject("ajustador_id", Long.class),
                        rs.getString("programacion"),
                        rs.getString("resultado")),
                inspeccionId);
    }
}
