package pe.siniestrofacil.infrastructure.persistence;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcAsistenciaRepository {

    private final JdbcTemplate jdbc;

    public JdbcAsistenciaRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Long registrar(Long siniestroId, Long proveedorAsistenciaId) {
        return jdbc.queryForObject(
                """
                insert into siniestro_facil.asistencia
                    (siniestro_id, proveedor_asistencia_id)
                values (?, ?)
                returning id
                """,
                Long.class,
                siniestroId,
                proveedorAsistenciaId);
    }

    public List<AsistenciaRecord> listar(Long siniestroId) {
        return jdbc.query(
                """
                select id, siniestro_id, proveedor_asistencia_id
                from siniestro_facil.asistencia
                where siniestro_id = ?
                order by id
                """,
                (rs, rowNum) -> new AsistenciaRecord(
                        rs.getLong("id"),
                        rs.getLong("siniestro_id"),
                        rs.getObject("proveedor_asistencia_id", Long.class)),
                siniestroId);
    }

    public record AsistenciaRecord(
            Long id,
            Long siniestroId,
            Long proveedorAsistenciaId) {
    }
}
