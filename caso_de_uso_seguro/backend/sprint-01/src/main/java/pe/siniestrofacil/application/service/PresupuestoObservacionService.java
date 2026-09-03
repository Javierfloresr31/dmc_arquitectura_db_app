package pe.siniestrofacil.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.siniestrofacil.application.port.PresupuestoObservacionPort;
import pe.siniestrofacil.application.port.PresupuestoPort;
import pe.siniestrofacil.domain.model.Siniestro;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class PresupuestoObservacionService {

    private final PresupuestoPort presupuestoPort;
    private final PresupuestoObservacionPort observacionPort;
    private final SiniestroService siniestroService;

    public PresupuestoObservacionService(
            PresupuestoPort presupuestoPort,
            PresupuestoObservacionPort observacionPort,
            SiniestroService siniestroService) {

        this.presupuestoPort = presupuestoPort;
        this.observacionPort = observacionPort;
        this.siniestroService = siniestroService;
    }

    @Transactional
    public PresupuestoObservacionPort.Observacion observar(
            Long presupuestoId,
            String responsable,
            String observacion,
            String idempotencyKey,
            String correlationId) {

        if (responsable == null || responsable.isBlank()) {
            throw new IllegalArgumentException(
                    "El responsable es obligatorio");
        }

        if (observacion == null || observacion.isBlank()) {
            throw new IllegalArgumentException(
                    "La observación es obligatoria");
        }

        String requestHash =
                calcularRequestHash(
                        presupuestoId,
                        responsable,
                        observacion);

        /*
         * Idempotencia primero.
         */
        if (idempotencyKey != null &&
                !idempotencyKey.isBlank()) {

            var existente =
                    observacionPort.buscarPorIdempotencyKey(
                            idempotencyKey,
                            requestHash);

            if (existente.isPresent()) {

                return existente.get()
                        .observacion();
            }
        }

        PresupuestoPort.Presupuesto presupuesto =
                presupuestoPort.obtener(presupuestoId);

        if (presupuesto == null) {
            throw new IllegalArgumentException(
                    "Presupuesto inexistente");
        }

        Long siniestroId = presupuesto.siniestroId();

        Siniestro siniestro =
                siniestroService.findById(siniestroId)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Siniestro inexistente"));

        if (!Siniestro.PRESUPUESTO_RECIBIDO.equals(
                siniestro.estado())) {

            throw new IllegalArgumentException(
                    "El presupuesto solo puede observarse desde " +
                    "PRESUPUESTO_RECIBIDO");
        }

        PresupuestoObservacionPort.Resultado resultado =
                observacionPort.registrar(
                        presupuestoId,
                        responsable,
                        observacion,
                        idempotencyKey,
                        requestHash,
                        correlationId);

        /*
         * Solo una operación nueva cambia el estado.
         */
        if (resultado.nueva()) {

            siniestroService.transition(
                    siniestroId,
                    Siniestro.OBSERVADO);
        }

        return resultado.observacion();
    }

    private String calcularRequestHash(
            Long presupuestoId,
            String responsable,
            String observacion) {

        String payload =
                String.valueOf(presupuestoId) +
                "|" +
                String.valueOf(responsable) +
                "|" +
                String.valueOf(observacion);

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
