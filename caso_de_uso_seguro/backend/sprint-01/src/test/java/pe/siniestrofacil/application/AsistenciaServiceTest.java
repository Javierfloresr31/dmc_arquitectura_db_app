package pe.siniestrofacil.application;

import org.junit.jupiter.api.Test;
import pe.siniestrofacil.application.port.AsistenciaPort;
import pe.siniestrofacil.application.service.AsistenciaService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AsistenciaServiceTest {

    @Test
    void debeSolicitarAsistenciaMediantePuerto() {
        AsistenciaPort port = mock(AsistenciaPort.class);

        when(port.solicitar(108L, 1L))
                .thenReturn(new AsistenciaPort.Resultado(
                        108L, 1L, "SOLICITAR", true));

        AsistenciaService service = new AsistenciaService(port);

        AsistenciaPort.Resultado resultado =
                service.solicitar(108L, 1L);

        assertEquals(108L, resultado.siniestroId());
        assertEquals(1L, resultado.proveedorAsistenciaId());
        assertEquals("SOLICITAR", resultado.operacion());
        assertTrue(resultado.registrada());

        verify(port).solicitar(108L, 1L);
    }

    @Test
    void debePermitirReintentoEscalamientoYReasignacion() {
        AsistenciaPort port = mock(AsistenciaPort.class);

        when(port.reintentar(108L, 1L))
                .thenReturn(new AsistenciaPort.Resultado(
                        108L, 1L, "REINTENTAR", true));

        when(port.escalar(108L, 1L))
                .thenReturn(new AsistenciaPort.Resultado(
                        108L, 1L, "ESCALAR", true));

        when(port.reasignar(108L, 1L))
                .thenReturn(new AsistenciaPort.Resultado(
                        108L, 1L, "REASIGNAR", true));

        AsistenciaService service = new AsistenciaService(port);

        assertEquals(
                "REINTENTAR",
                service.reintentar(108L, 1L).operacion());

        assertEquals(
                "ESCALAR",
                service.escalar(108L, 1L).operacion());

        assertEquals(
                "REASIGNAR",
                service.reasignar(108L, 1L).operacion());

        verify(port).reintentar(108L, 1L);
        verify(port).escalar(108L, 1L);
        verify(port).reasignar(108L, 1L);
    }
}
