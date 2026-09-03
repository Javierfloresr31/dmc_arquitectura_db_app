package pe.siniestrofacil.interfaces.rest;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.siniestrofacil.application.dto.EvidenciaResponse;
import pe.siniestrofacil.application.dto.RegistrarEvidenciaRequest;
import pe.siniestrofacil.application.service.EvidenciaService;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EvidenciaController {

    private final EvidenciaService service;

    public EvidenciaController(EvidenciaService service) {
        this.service = service;
    }

    @PostMapping("/siniestros/{id}/evidencias")
    public ResponseEntity<EvidenciaResponse> registrar(
            @PathVariable Long id,
            @Valid @RequestBody RegistrarEvidenciaRequest request) {

        OffsetDateTime fecha = request.fechaRecepcion() != null
                ? request.fechaRecepcion()
                : OffsetDateTime.now();

        EvidenciaResponse response = EvidenciaResponse.from(
                service.registrar(
                        id,
                        request.contenidoOriginal(),
                        request.hash(),
                        request.metadatosDisponibles(),
                        fecha,
                        request.fuente(),
                        request.transformaciones()));

        return ResponseEntity
                .created(URI.create(
                        "/api/v1/evidencias/" + response.id()))
                .body(response);
    }

    @GetMapping("/siniestros/{id}/evidencias")
    public ResponseEntity<List<EvidenciaResponse>> listar(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.listar(id)
                        .stream()
                        .map(EvidenciaResponse::from)
                        .toList());
    }

    @GetMapping("/evidencias/{id}")
    public ResponseEntity<EvidenciaResponse> obtener(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                EvidenciaResponse.from(service.obtener(id)));
    }
}
