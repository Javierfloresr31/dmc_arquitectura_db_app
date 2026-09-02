package pe.siniestrofacil.interfaces.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.siniestrofacil.application.dto.RevisionAntifraudeRequest;
import pe.siniestrofacil.application.dto.RevisionAntifraudeResponse;
import pe.siniestrofacil.application.service.RevisionAntifraudeService;

@RestController
@RequestMapping("/api/v1")
public class RevisionAntifraudeController {

    private final RevisionAntifraudeService service;

    public RevisionAntifraudeController(
            RevisionAntifraudeService service) {
        this.service = service;
    }

    @PostMapping("/alertas/{id}/revision")
    public ResponseEntity<RevisionAntifraudeResponse> registrar(
            @PathVariable Long id,
            @RequestBody RevisionAntifraudeRequest request) {

        return ResponseEntity.ok(
                service.registrar(id, request));
    }
}
