package pe.siniestrofacil.application.service;

import org.springframework.stereotype.Service;
import pe.siniestrofacil.application.port.CoberturaPort;

@Service
public class CoberturaService {

    private final CoberturaPort coberturaPort;

    public CoberturaService(CoberturaPort coberturaPort) {
        this.coberturaPort = coberturaPort;
    }

    public CoberturaPort.Resultado validar(String numeroPoliza, String placa) {
        return coberturaPort.validar(numeroPoliza, placa);
    }
}
