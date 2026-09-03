package pe.siniestrofacil.application;

import org.junit.jupiter.api.Test;
import pe.siniestrofacil.application.port.CoberturaPort;
import pe.siniestrofacil.application.service.CoberturaService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CoberturaServiceTest {

    @Test
    void debeValidarCoberturaMediantePuerto() {
        CoberturaPort port = mock(CoberturaPort.class);

        when(port.validar("SYN-POL-000013", "SYN-0065"))
                .thenReturn(new CoberturaPort.Resultado(
                        true,
                        true,
                        true,
                        true,
                        false));

        CoberturaService service = new CoberturaService(port);

        CoberturaPort.Resultado resultado =
                service.validar("SYN-POL-000013", "SYN-0065");

        assertTrue(resultado.identidadVerificada());
        assertTrue(resultado.polizaVerificada());
        assertTrue(resultado.vehiculoVerificado());
        assertTrue(resultado.coberturaVerificada());
        assertFalse(resultado.deducibleDisponible());
        assertTrue(resultado.puedeContinuar());

        verify(port).validar("SYN-POL-000013", "SYN-0065");
    }

    @Test
    void noDebePermitirContinuarSiNoSeVerificaCobertura() {
        CoberturaPort port = mock(CoberturaPort.class);

        when(port.validar("SYN-POL-000013", "SYN-0065"))
                .thenReturn(new CoberturaPort.Resultado(
                        true,
                        true,
                        true,
                        false,
                        false));

        CoberturaService service = new CoberturaService(port);

        CoberturaPort.Resultado resultado =
                service.validar("SYN-POL-000013", "SYN-0065");

        assertFalse(resultado.puedeContinuar());
    }
}
