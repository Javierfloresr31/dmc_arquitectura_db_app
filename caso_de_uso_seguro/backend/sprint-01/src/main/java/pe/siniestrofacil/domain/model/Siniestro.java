package pe.siniestrofacil.domain.model;
import java.time.OffsetDateTime;
public record Siniestro(Long id,Long polizaId,Long vehiculoId,OffsetDateTime fecha,String ubicacionAproximada,String tipoEvento,String danosAparentes,String estado){ public static final String REPORTADO="REPORTADO"; }