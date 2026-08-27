package pe.siniestrofacil.infrastructure.integration;

import org.springframework.stereotype.Component;
import pe.siniestrofacil.application.port.CoberturaPort;

@Component
public class SyntheticCoberturaAdapter implements CoberturaPort {

    @Override
    public Resultado validar(String numeroPoliza, String placa) {
        if (numeroPoliza == null || numeroPoliza.isBlank()
                || placa == null || placa.isBlank()) {
            return new Resultado(false, false, false, false, false);
        }

        return new Resultado(
                true,
                true,
                true,
                true,
                false);
    }
}
