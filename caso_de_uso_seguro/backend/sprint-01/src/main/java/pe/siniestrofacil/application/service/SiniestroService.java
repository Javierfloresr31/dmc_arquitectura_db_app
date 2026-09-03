package pe.siniestrofacil.application.service;

import pe.siniestrofacil.application.dto.CrearSiniestroRequest;
import pe.siniestrofacil.application.port.PagoPort;
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
    private final PagoPort pagoPort;

    public SiniestroService(
            SiniestroRepository repository,
            PagoPort pagoPort) {

        this.repository = repository;
        this.pagoPort = pagoPort;
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

        if (Siniestro.CERRADO.equals(state)) {

            Siniestro siniestro = repository.findById(id)
                    .orElseThrow(() ->
                            new IllegalArgumentException(
                                    "Siniestro inexistente"));

            boolean estadoValido =
                    Siniestro.LISTO_PARA_ENTREGA.equals(siniestro.estado()) ||
                    Siniestro.INDEMNIZADO.equals(siniestro.estado());

            if (!estadoValido) {
                throw new IllegalStateException(
                        "El siniestro solo puede cerrarse desde " +
                        "LISTO_PARA_ENTREGA o INDEMNIZADO");
            }

            if (pagoPort.listar(id).isEmpty()) {
                throw new IllegalStateException(
                        "No existe resultado económico registrado");
            }
        }

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
