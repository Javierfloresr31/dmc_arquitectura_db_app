package pe.siniestrofacil.interfaces.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.siniestrofacil.application.dto.ReglaModeloVersionRequest;
import pe.siniestrofacil.application.dto.ReglaModeloVersionResponse;
import pe.siniestrofacil.application.service.ReglaModeloVersionService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reglas-modelos/versiones")
public class ReglaModeloVersionController {

    private final ReglaModeloVersionService service;

    public ReglaModeloVersionController(
            ReglaModeloVersionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ReglaModeloVersionResponse> registrar(
            @RequestBody ReglaModeloVersionRequest request) {
        return ResponseEntity.ok(service.registrar(request));
    }

    @GetMapping
    public ResponseEntity<List<ReglaModeloVersionResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReglaModeloVersionResponse> obtener(
            @PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }
}
