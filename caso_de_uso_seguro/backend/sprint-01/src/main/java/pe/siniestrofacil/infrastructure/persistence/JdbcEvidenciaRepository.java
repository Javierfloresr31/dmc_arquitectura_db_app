package pe.siniestrofacil.infrastructure.persistence;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.siniestrofacil.application.port.EvidenciaPort;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.List;

@Repository
public class JdbcEvidenciaRepository implements EvidenciaPort {

    private final JdbcTemplate jdbc;

    public JdbcEvidenciaRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Evidencia registrar(
            Long siniestroId,
            String contenidoOriginal,
            String hash,
            String metadatosDisponibles,
            OffsetDateTime fechaRecepcion,
            String fuente,
            String transformaciones) {

        Long id = jdbc.queryForObject(
                """
                insert into siniestro_facil.evidencia
                    (siniestro_id,
                     contenido_original,
                     hash,
                     metadatos_disponibles,
                     fecha_recepcion,
                     fuente,
                     transformaciones)
                values (?, ?, ?, ?, ?, ?, ?)
                returning id
                """,
                Long.class,
                siniestroId,
                contenidoOriginal,
                hash,
                metadatosDisponibles,
                Timestamp.from(fechaRecepcion.toInstant()),
                fuente,
                transformaciones);

        return obtener(id);
    }

    @Override
    public List<Evidencia> listar(Long siniestroId) {
        return jdbc.query(
                """
                select id,
                       siniestro_id,
                       contenido_original,
                       hash,
                       metadatos_disponibles,
                       fecha_recepcion,
                       fuente,
                       transformaciones
                  from siniestro_facil.evidencia
                 where siniestro_id = ?
                 order by id
                """,
                (rs, rowNum) -> new Evidencia(
                        rs.getLong("id"),
                        rs.getLong("siniestro_id"),
                        rs.getString("contenido_original"),
                        rs.getString("hash"),
                        rs.getString("metadatos_disponibles"),
                        rs.getObject(
                                "fecha_recepcion",
                                OffsetDateTime.class),
                        rs.getString("fuente"),
                        rs.getString("transformaciones")),
                siniestroId);
    }

    @Override
    public Evidencia obtener(Long evidenciaId) {
        return jdbc.queryForObject(
                """
                select id,
                       siniestro_id,
                       contenido_original,
                       hash,
                       metadatos_disponibles,
                       fecha_recepcion,
                       fuente,
                       transformaciones
                  from siniestro_facil.evidencia
                 where id = ?
                """,
                (rs, rowNum) -> new Evidencia(
                        rs.getLong("id"),
                        rs.getLong("siniestro_id"),
                        rs.getString("contenido_original"),
                        rs.getString("hash"),
                        rs.getString("metadatos_disponibles"),
                        rs.getObject(
                                "fecha_recepcion",
                                OffsetDateTime.class),
                        rs.getString("fuente"),
                        rs.getString("transformaciones")),
                evidenciaId);
    }

    @Override
    public Version registrarVersion(
            Long evidenciaId,
            String transformacion) {

        Long id = jdbc.queryForObject(
                """
                insert into siniestro_facil.evidencia_version
                    (evidencia_id, transformacion)
                values (?, ?)
                returning id
                """,
                Long.class,
                evidenciaId,
                transformacion);

        return new Version(id, evidenciaId, transformacion);
    }
}
