package pe.siniestrofacil.interfaces.rest;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.siniestrofacil.application.dto.InspeccionRequest;
import pe.siniestrofacil.application.dto.InspeccionResponse;
import pe.siniestrofacil.application.dto.InspeccionResultadoRequest;
import pe.siniestrofacil.application.service.InspeccionService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class InspeccionController {

    private final InspeccionService service;

    public InspeccionController(
            InspeccionService service) {

        this.service = service;
    }

    @PostMapping("/siniestros/{id}/inspecciones")
    public ResponseEntity<InspeccionResponse> registrar(
            @PathVariable Long id,
            @Valid @RequestBody InspeccionRequest request) {

        InspeccionResponse response =
                InspeccionResponse.from(
                        service.registrar(
                                id,
                                request.ajustadorId(),
                                request.programacion()));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/siniestros/{id}/inspecciones")
    public ResponseEntity<List<InspeccionResponse>> listar(
            @PathVariable Long id) {

        List<InspeccionResponse> response =
                service.listar(id)
                        .stream()
                        .map(InspeccionResponse::from)
                        .toList();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/inspecciones/{id}/resultado")
    public ResponseEntity<InspeccionResponse> resultado(
            @PathVariable Long id,
            @Valid @RequestBody InspeccionResultadoRequest request) {

        return ResponseEntity.ok(
                InspeccionResponse.from(
                        service.registrarResultado(
                                id,
                                request.resultado())));
    }

    @GetMapping("/inspecciones/{id}")
    public ResponseEntity<InspeccionResponse> obtener(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                InspeccionResponse.from(
                        service.obtener(id)));
    }
}
