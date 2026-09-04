package pe.siniestrofacil.interfaces.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.siniestrofacil.application.dto.PagoRequest;
import pe.siniestrofacil.application.dto.PagoResponse;
import pe.siniestrofacil.application.service.PagoService;
import pe.siniestrofacil.application.security.AuthorizationService;

import java.util.List;
import java.util.UUID;
import java.util.Set;

@RestController
@RequestMapping("/api/v1")
public class PagoController {

    private final PagoService service;
    private final AuthorizationService authorizationService;

    public PagoController(PagoService service, AuthorizationService authorizationService) {
        this.service = service;
        this.authorizationService = authorizationService;
    }

    @PostMapping("/siniestros/{id}/pagos")
    public ResponseEntity<PagoResponse> registrar(
            @PathVariable Long id,
            @RequestBody PagoRequest request,
            @RequestHeader("Idempotency-Key")
            String idempotencyKey,
            @RequestHeader(value = "X-Correlation-Id", required = false)
            String correlationId) {
        authorizationService.requireRole(Set.of("OPERADOR", "SUPERVISOR"));

        if (correlationId == null || correlationId.isBlank()) {
            correlationId = UUID.randomUUID().toString();
        }

        return ResponseEntity.ok(
                PagoResponse.from(
                        service.registrar(
                                id,
                                request.autorizacionId(),
                                idempotencyKey,
                                correlationId)));
    }

    @GetMapping("/siniestros/{id}/pagos")
    public ResponseEntity<List<PagoResponse>> listar(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.listar(id)
                        .stream()
                        .map(PagoResponse::from)
                        .toList());
    }
}
