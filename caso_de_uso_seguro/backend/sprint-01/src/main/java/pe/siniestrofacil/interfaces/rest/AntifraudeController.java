package pe.siniestrofacil.interfaces.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.siniestrofacil.application.dto.AlertaAntifraudeResponse;
import pe.siniestrofacil.application.dto.EvaluacionAntifraudeRequest;
import pe.siniestrofacil.application.dto.SenalResponse;
import pe.siniestrofacil.application.service.AntifraudeService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AntifraudeController {

    private final AntifraudeService service;

    public AntifraudeController(AntifraudeService service) {
        this.service = service;
    }

    @PostMapping("/siniestros/{id}/antifraude/evaluaciones")
    public ResponseEntity<AlertaAntifraudeResponse> evaluar(
            @PathVariable Long id,
            @RequestBody EvaluacionAntifraudeRequest request) {

        return ResponseEntity.ok(service.evaluar(id, request));
    }

    @GetMapping("/siniestros/{id}/alertas-antifraude")
    public ResponseEntity<List<AlertaAntifraudeResponse>> listar(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.listar(id));
    }

    @GetMapping("/alertas-antifraude/{id}")
    public ResponseEntity<AlertaAntifraudeResponse> obtener(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.obtener(id));
    }

    @GetMapping("/alertas-antifraude/{id}/senales")
    public ResponseEntity<List<SenalResponse>> listarSenales(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.listarSenales(id));
    }
}
