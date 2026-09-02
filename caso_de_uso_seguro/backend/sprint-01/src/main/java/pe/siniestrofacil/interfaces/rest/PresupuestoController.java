package pe.siniestrofacil.interfaces.rest;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.siniestrofacil.application.dto.PresupuestoRequest;
import pe.siniestrofacil.application.dto.PresupuestoResponse;
import pe.siniestrofacil.application.service.PresupuestoService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PresupuestoController {

    private final PresupuestoService service;

    public PresupuestoController(PresupuestoService service) {
        this.service = service;
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
}
