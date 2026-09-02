package pe.siniestrofacil.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.siniestrofacil.application.port.AutorizacionPort;
import pe.siniestrofacil.domain.model.Siniestro;
import pe.siniestrofacil.domain.port.SiniestroRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class AutorizacionService {

    private final AutorizacionPort autorizacionPort;
    private final SiniestroRepository siniestroRepository;

    public AutorizacionService(
            AutorizacionPort autorizacionPort,
            SiniestroRepository siniestroRepository) {

        this.autorizacionPort = autorizacionPort;
        this.siniestroRepository = siniestroRepository;
    }

    @Transactional
    public AutorizacionPort.Autorizacion registrar(
            Long siniestroId,
            String aprobador,
            String idempotencyKey,
            String correlationId) {

        String requestHash =
                calcularRequestHash(
                        siniestroId,
                        aprobador);

        /*
         * Primero resolvemos la idempotencia.
         *
         * Si la solicitud ya fue procesada,
         * devolvemos la autorización existente,
         * incluso si el siniestro ya está AUTORIZADO.
         */
        if (idempotencyKey != null &&
                !idempotencyKey.isBlank()) {

            var existente =
                    autorizacionPort.buscarPorIdempotencyKey(
                            idempotencyKey,
                            requestHash);

            if (existente.isPresent()) {
                return existente.get().autorizacion();
            }
        }

        /*
         * La solicitud es nueva.
         * Validamos el estado de negocio.
         */
        Siniestro siniestro =
                siniestroRepository.findById(siniestroId)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Siniestro inexistente"));

        if (!Siniestro.PRESUPUESTO_RECIBIDO.equals(
                siniestro.estado())) {

            throw new IllegalStateException(
                    "La autorización solo puede registrarse " +
                    "cuando el siniestro está " +
                    "PRESUPUESTO_RECIBIDO");
        }

        /*
         * Registramos la autorización.
         */
        AutorizacionPort.Resultado resultado =
                autorizacionPort.registrar(
                        siniestroId,
                        aprobador,
                        idempotencyKey,
                        requestHash,
                        correlationId);

        /*
         * Solo una autorización nueva provoca
         * la transición del siniestro.
         */
        if (resultado.nueva()) {

            siniestroRepository.transition(
                    siniestroId,
                    Siniestro.AUTORIZADO);
        }

        return resultado.autorizacion();
    }

    public List<AutorizacionPort.Autorizacion> listar(
            Long siniestroId) {

        return autorizacionPort.listar(siniestroId);
    }

    private String calcularRequestHash(
            Long siniestroId,
            String aprobador) {

        String payload =
                String.valueOf(siniestroId) +
                "|" +
                String.valueOf(aprobador);

        try {

            MessageDigest digest =
                    MessageDigest.getInstance("SHA-256");

            byte[] hash =
                    digest.digest(
                            payload.getBytes(
                                    StandardCharsets.UTF_8));

            StringBuilder hex =
                    new StringBuilder(
                            hash.length * 2);

            for (byte b : hash) {
                hex.append(
                        String.format("%02x", b));
            }

            return hex.toString();

        } catch (NoSuchAlgorithmException e) {

            throw new IllegalStateException(
                    "SHA-256 no disponible",
                    e);
        }
    }
}
