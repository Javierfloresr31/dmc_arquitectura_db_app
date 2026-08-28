package pe.siniestrofacil.interfaces.rest;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.siniestrofacil.application.dto.AsistenciaRequest;
import pe.siniestrofacil.application.dto.AsistenciaResponse;
import pe.siniestrofacil.application.service.AsistenciaService;
import pe.siniestrofacil.infrastructure.persistence.JdbcAsistenciaRepository;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AsistenciaController {

    private final AsistenciaService service;
    private final JdbcAsistenciaRepository repository;

    public AsistenciaController(
            AsistenciaService service,
            JdbcAsistenciaRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @PostMapping("/siniestros/{id}/asistencia")
    public ResponseEntity<AsistenciaResponse> solicitar(
            @PathVariable Long id,
            @Valid @RequestBody AsistenciaRequest request) {

        AsistenciaResponse response = AsistenciaResponse.from(
                service.solicitar(
                        id,
                        request.proveedorAsistenciaId()));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/siniestros/{id}/asistencia")
    public ResponseEntity<List<JdbcAsistenciaRepository.AsistenciaRecord>> listar(
            @PathVariable Long id) {

        return ResponseEntity.ok(repository.listar(id));
    }
}
