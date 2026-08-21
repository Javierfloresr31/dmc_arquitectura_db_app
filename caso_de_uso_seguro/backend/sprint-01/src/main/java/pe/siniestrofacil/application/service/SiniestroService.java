package pe.siniestrofacil.application.service;

import pe.siniestrofacil.application.dto.CrearSiniestroRequest;
import pe.siniestrofacil.domain.model.Siniestro;
import pe.siniestrofacil.domain.port.SiniestroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class SiniestroService {
    private final SiniestroRepository repository;

    public SiniestroService(SiniestroRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Siniestro create(CrearSiniestroRequest r, String idempotencyKey, String correlationId) {
        Siniestro draft = new Siniestro(
                null, null, null, r.fecha(), r.ubicacionAproximada(),
                r.tipoEvento(), r.danosAparentes(), Siniestro.REPORTADO);

        String requestHash = fingerprint(r);

        return repository.createByBusinessKeys(
                r.poliza().numero(),
                r.vehiculo().placa(),
                draft,
                r.reportante(),
                idempotencyKey,
                requestHash,
                correlationId);
    }

    public Optional<Siniestro> findById(long id) {
        return repository.findById(id);
    }

    public List<Siniestro> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void transition(long id, String state) {
        repository.transition(id, state);
    }

    private String fingerprint(CrearSiniestroRequest request) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = digest.digest(request.toString().getBytes(StandardCharsets.UTF_8));
            StringBuilder hex = new StringBuilder(bytes.length * 2);
            for (byte b : bytes) {
                hex.append(String.format("%02x", b));
            }
            return hex.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 no disponible", e);
        }
    }
}
