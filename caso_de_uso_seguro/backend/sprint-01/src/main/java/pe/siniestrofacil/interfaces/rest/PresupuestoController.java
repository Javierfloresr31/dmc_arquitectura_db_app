package pe.siniestrofacil.interfaces.rest;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.siniestrofacil.application.dto.PresupuestoObservacionRequest;
import pe.siniestrofacil.application.dto.PresupuestoObservacionResponse;
import pe.siniestrofacil.application.dto.PresupuestoRequest;
import pe.siniestrofacil.application.dto.PresupuestoResponse;
import pe.siniestrofacil.application.service.PresupuestoObservacionService;
import pe.siniestrofacil.application.service.PresupuestoService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class PresupuestoController {

    private final PresupuestoService service;
    private final PresupuestoObservacionService observacionService;

    public PresupuestoController(
            PresupuestoService service,
            PresupuestoObservacionService observacionService) {

        this.service = service;
        this.observacionService = observacionService;
    }

    @PostMapping("/siniestros/{id}/presupuestos")
    public ResponseEntity<PresupuestoResponse> registrar(
            @PathVariable Long id,
            @Valid @RequestBody PresupuestoRequest request) {

        return ResponseEntity.ok(
                PresupuestoResponse.from(
                        service.registrar(
                                id,
                                request.tallerId(),
                                request.presupuesto(),
                                request.diagnostico(),
                                request.vigencia(),
                                request.observaciones(),
                                request.repuestosAlternativos(),
                                request.ampliaciones())));
    }

    @GetMapping("/siniestros/{id}/presupuestos")
    public ResponseEntity<List<PresupuestoResponse>> listar(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.listar(id)
                        .stream()
                        .map(PresupuestoResponse::from)
                        .toList());
    }

    @GetMapping("/presupuestos/{id}")
    public ResponseEntity<PresupuestoResponse> obtener(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                PresupuestoResponse.from(
                        service.obtener(id)));
    }

    @PostMapping("/presupuestos/{id}/observaciones")
    public ResponseEntity<PresupuestoObservacionResponse> observar(
            @PathVariable Long id,
            @Valid @RequestBody PresupuestoObservacionRequest request,
            @RequestHeader(
                    value = "Idempotency-Key",
                    required = false) String idempotencyKey,
            @RequestHeader(
                    value = "X-Correlation-Id",
                    required = false) String correlationId) {

        String cid =
                correlationId == null || correlationId.isBlank()
                        ? UUID.randomUUID().toString()
                        : correlationId;

        var observacion =
                observacionService.observar(
                        id,
                        request.responsable(),
                        request.observacion(),
                        idempotencyKey,
                        cid);

        var presupuesto = service.obtener(
                observacion.presupuestoId());

        return ResponseEntity.ok(
                PresupuestoObservacionResponse.from(
                        observacion,
                        presupuesto.siniestroId()));
    }
}
