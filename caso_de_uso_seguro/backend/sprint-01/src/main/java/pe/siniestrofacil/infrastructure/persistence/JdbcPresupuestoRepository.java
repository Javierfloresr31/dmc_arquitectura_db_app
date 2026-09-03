package pe.siniestrofacil.infrastructure.persistence;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.siniestrofacil.application.port.PresupuestoPort;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public class JdbcPresupuestoRepository implements PresupuestoPort {

    private final JdbcTemplate jdbc;

    public JdbcPresupuestoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Presupuesto registrar(
            Long siniestroId,
            Long tallerId,
            String presupuesto,
            String diagnostico,
            LocalDate vigencia,
            String observaciones,
            String repuestosAlternativos,
            String ampliaciones) {

        Long id = jdbc.queryForObject(
                """
                insert into siniestro_facil.presupuesto
                    (
                        siniestro_id,
                        taller_id,
                        presupuesto,
                        diagnostico,
                        vigencia,
                        observaciones,
                        repuestos_alternativos,
                        ampliaciones
                    )
                values (?, ?, ?, ?, ?, ?, ?, ?)
                returning id
                """,
                Long.class,
                siniestroId,
                tallerId,
                presupuesto,
                diagnostico,
                vigencia == null ? null : Date.valueOf(vigencia),
                observaciones,
                repuestosAlternativos,
                ampliaciones);

        return obtener(id);
    }

    @Override
    public List<Presupuesto> listar(Long siniestroId) {

        return jdbc.query(
                """
                select id,
                       siniestro_id,
                       taller_id,
                       presupuesto,
                       diagnostico,
                       vigencia,
                       observaciones,
                       repuestos_alternativos,
                       ampliaciones
                  from siniestro_facil.presupuesto
                 where siniestro_id = ?
                 order by id
                """,
                (rs, rowNum) -> new Presupuesto(
                        rs.getLong("id"),
                        rs.getLong("siniestro_id"),
                        rs.getLong("taller_id"),
                        rs.getString("presupuesto"),
                        rs.getString("diagnostico"),
                        rs.getObject("vigencia", LocalDate.class),
                        rs.getString("observaciones"),
                        rs.getString("repuestos_alternativos"),
                        rs.getString("ampliaciones")),
                siniestroId);
    }

    @Override
    public Presupuesto obtener(Long presupuestoId) {

        return jdbc.queryForObject(
                """
                select id,
                       siniestro_id,
                       taller_id,
                       presupuesto,
                       diagnostico,
                       vigencia,
                       observaciones,
                       repuestos_alternativos,
                       ampliaciones
                  from siniestro_facil.presupuesto
                 where id = ?
                """,
                (rs, rowNum) -> new Presupuesto(
                        rs.getLong("id"),
                        rs.getLong("siniestro_id"),
                        rs.getLong("taller_id"),
                        rs.getString("presupuesto"),
                        rs.getString("diagnostico"),
                        rs.getObject("vigencia", LocalDate.class),
                        rs.getString("observaciones"),
                        rs.getString("repuestos_alternativos"),
                        rs.getString("ampliaciones")),
                presupuestoId);
    }
}
