package pe.siniestrofacil.application.service;

import org.springframework.stereotype.Service;
import pe.siniestrofacil.application.dto.AlertaAntifraudeResponse;
import pe.siniestrofacil.application.dto.EvaluacionAntifraudeRequest;
import pe.siniestrofacil.application.dto.SenalResponse;
import pe.siniestrofacil.application.port.AntifraudePort;
import pe.siniestrofacil.application.port.AuditoriaPort;
import pe.siniestrofacil.infrastructure.integration.SyntheticAntifraudeAdapter;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class AntifraudeService {

    private final AntifraudePort port;
    private final AuditoriaPort auditoriaPort;
    private final ReglaModeloVersionService reglaModeloVersionService;
    private final SyntheticAntifraudeAdapter adapter;

    public AntifraudeService(
            AntifraudePort port,
            AuditoriaPort auditoriaPort,
            ReglaModeloVersionService reglaModeloVersionService,
            SyntheticAntifraudeAdapter adapter) {
        this.port = port;
        this.auditoriaPort = auditoriaPort;
        this.reglaModeloVersionService = reglaModeloVersionService;
        this.adapter = adapter;
    }

    public AlertaAntifraudeResponse evaluar(
            Long siniestroId,
            EvaluacionAntifraudeRequest request) {

        if (siniestroId == null) {
            throw new IllegalArgumentException(
                    "El siniestro es obligatorio");
        }

        if (request == null || request.reglaModeloVersionId() == null) {
            throw new IllegalArgumentException(
                    "La versión de regla/modelo es obligatoria");
        }

        var version = reglaModeloVersionService.obtener(
                request.reglaModeloVersionId());

        var resultado = adapter.evaluar(siniestroId);

        String modeloORegla =
                version.tipo() + "_" + version.version();

        String estado = determinarEstado(resultado.severidad());

        String datosOrigen = request.datosOrigen() != null
                && !request.datosOrigen().isBlank()
                ? request.datosOrigen().trim()
                : resultado.datosOrigen();

        String tipo = request.tipo() != null
                && !request.tipo().isBlank()
                ? request.tipo().trim()
                : resultado.tipo();

        var alerta = port.crearAlerta(
                siniestroId,
                tipo,
                resultado.severidad(),
                resultado.explicacion(),
                datosOrigen,
                OffsetDateTime.now(),
                modeloORegla,
                estado,
                null,
                version.id());

        for (String senal : resultado.senales()) {
            port.registrarSenal(alerta.id(), senal);
        }

        auditoriaPort.registrar(
                "ALERTA_ANTIFRAUDE",
                alerta.id());

        return toResponse(alerta);
    }

    public List<AlertaAntifraudeResponse> listar(Long siniestroId) {
        return port.listarAlertas(siniestroId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public AlertaAntifraudeResponse obtener(Long alertaId) {
        return port.buscarAlerta(alertaId)
                .map(this::toResponse)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Alerta antifraude inexistente"));
    }

    public List<SenalResponse> listarSenales(Long alertaId) {
        return port.listarSenales(alertaId)
                .stream()
                .map(s -> new SenalResponse(
                        s.id(),
                        s.alertaAntifraudeId(),
                        s.senal()))
                .toList();
    }

    private String determinarEstado(String severidad) {
        return switch (severidad) {
            case "BAJA", "MEDIA", "ALTA" -> "PENDIENTE_REVISION";
            default -> throw new IllegalArgumentException(
                    "Severidad antifraude no soportada: " + severidad);
        };
    }

    private AlertaAntifraudeResponse toResponse(
            AntifraudePort.Alerta alerta) {

        return new AlertaAntifraudeResponse(
                alerta.id(),
                alerta.siniestroId(),
                alerta.tipo(),
                alerta.severidad(),
                alerta.explicacion(),
                alerta.datosOrigen(),
                alerta.fecha(),
                alerta.modeloORegla(),
                alerta.estado(),
                alerta.justificacion(),
                alerta.reglaModeloVersionId());
    }
}
