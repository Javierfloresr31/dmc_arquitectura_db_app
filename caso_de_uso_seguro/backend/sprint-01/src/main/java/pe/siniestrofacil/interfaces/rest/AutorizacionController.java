package pe.siniestrofacil.interfaces.rest;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.siniestrofacil.application.dto.AutorizacionRequest;
import pe.siniestrofacil.application.dto.AutorizacionResponse;
import pe.siniestrofacil.application.service.AutorizacionService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class AutorizacionController {

    private final AutorizacionService service;

    public AutorizacionController(
            AutorizacionService service) {

        this.service = service;
    }

    @PostMapping("/siniestros/{id}/autorizaciones")
    public ResponseEntity<AutorizacionResponse> autorizar(
            @PathVariable Long id,
            @Valid @RequestBody AutorizacionRequest request,
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

        return ResponseEntity.ok(
                AutorizacionResponse.from(
                        service.registrar(
                                id,
                                request.aprobador(),
                                idempotencyKey,
                                cid)));
    }

    @GetMapping("/siniestros/{id}/autorizacion")
    public ResponseEntity<List<AutorizacionResponse>> listar(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.listar(id)
                        .stream()
                        .map(AutorizacionResponse::from)
                        .toList());
    }
}
