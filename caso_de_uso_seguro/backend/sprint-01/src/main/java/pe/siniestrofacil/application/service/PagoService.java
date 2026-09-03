package pe.siniestrofacil.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.siniestrofacil.application.exception.EconomicOperationConflictException;
import pe.siniestrofacil.application.port.PagoPort;
import pe.siniestrofacil.domain.model.Siniestro;
import pe.siniestrofacil.domain.port.SiniestroRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.List;

@Service
public class PagoService {

    private final PagoPort pagoPort;
    private final SiniestroRepository siniestroRepository;

    public PagoService(
            PagoPort pagoPort,
            SiniestroRepository siniestroRepository) {

        this.pagoPort = pagoPort;
        this.siniestroRepository = siniestroRepository;
    }

    @Transactional
    public PagoPort.Pago registrar(
            Long siniestroId,
            Long autorizacionId,
            String idempotencyKey,
            String correlationId) {

        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            throw new IllegalArgumentException(
                    "Idempotency-Key es obligatorio");
        }

        String requestHash =
                calcularHash(siniestroId, autorizacionId);

        var existente =
                pagoPort.buscarPorIdempotencyKey(
                        idempotencyKey,
                        requestHash);

        if (existente.isPresent()) {
            return existente.get().pago();
        }

        Siniestro siniestro =
                siniestroRepository.findById(siniestroId)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Siniestro inexistente"));

        if (!Siniestro.AUTORIZADO.equals(siniestro.estado())) {
            throw new IllegalStateException(
                    "El pago solo puede registrarse " +
                    "cuando el siniestro está AUTORIZADO");
        }

        if (autorizacionId == null) {
            throw new IllegalArgumentException(
                    "La autorización es obligatoria");
        }

        if (pagoPort.existeOperacionEquivalente(
                siniestroId,
                autorizacionId)) {

            throw new EconomicOperationConflictException(
                    "Ya existe una operación económica " +
                    "equivalente para el siniestro y autorización");
        }

        PagoPort.Resultado resultado =
                pagoPort.registrar(
                        siniestroId,
                        autorizacionId,
                        idempotencyKey,
                        requestHash,
                        correlationId);

        if (resultado.nuevo()) {
            siniestroRepository.transition(
                    siniestroId,
                    Siniestro.INDEMNIZADO);
        }

        return resultado.pago();
    }

    public List<PagoPort.Pago> listar(
            Long siniestroId) {

        return pagoPort.listar(siniestroId);
    }

    private String calcularHash(
            Long siniestroId,
            Long autorizacionId) {

        String contenido =
                String.valueOf(siniestroId)
                        + "|"
                        + String.valueOf(autorizacionId);

        try {
            MessageDigest digest =
                    MessageDigest.getInstance("SHA-256");

            return HexFormat.of().formatHex(
                    digest.digest(
                            contenido.getBytes(
                                    StandardCharsets.UTF_8)));

        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(
                    "SHA-256 no disponible", e);
        }
    }
}
