package pe.siniestrofacil.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.siniestrofacil.application.dto.RevisionAntifraudeRequest;
import pe.siniestrofacil.application.port.AuditoriaPort;
import pe.siniestrofacil.application.port.RevisionAntifraudePort;
import pe.siniestrofacil.application.service.RevisionAntifraudeService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RevisionAntifraudeServiceTest {

    @Mock
    private RevisionAntifraudePort port;

    @Mock
    private AuditoriaPort auditoriaPort;

    private RevisionAntifraudeService service;

    @BeforeEach
    void setUp() {
        service = new RevisionAntifraudeService(
                port,
                auditoriaPort);
    }

    @Test
    void debeRegistrarRevisionConfirmada() {

        when(port.registrar(
                eq(10L),
                eq("CONFIRMADA"),
                eq("Se confirma la señal")))
                .thenReturn(new RevisionAntifraudePort.Revision(
                        100L,
                        10L,
                        "CONFIRMADA",
                        "Se confirma la señal"));

        var response = service.registrar(
                10L,
                new RevisionAntifraudeRequest(
                        "CONFIRMADA",
                        "Se confirma la señal"));

        assertEquals(100L, response.id());
        assertEquals(10L, response.alertaAntifraudeId());
        assertEquals("CONFIRMADA", response.resultado());
        assertEquals("Se confirma la señal", response.justificacion());

        verify(port).actualizarEstadoAlerta(
                10L,
                "CONFIRMADA");

        verify(auditoriaPort).registrar(
                "REVISION_ANTIFRAUDE",
                100L);
    }

    @Test
    void debeRegistrarRevisionDescartada() {

        when(port.registrar(
                eq(11L),
                eq("DESCARTADA"),
                eq("Señal insuficiente")))
                .thenReturn(new RevisionAntifraudePort.Revision(
                        101L,
                        11L,
                        "DESCARTADA",
                        "Señal insuficiente"));

        var response = service.registrar(
                11L,
                new RevisionAntifraudeRequest(
                        "DESCARTADA",
                        "Señal insuficiente"));

        assertEquals("DESCARTADA", response.resultado());

        verify(port).actualizarEstadoAlerta(
                11L,
                "DESCARTADA");

        verify(auditoriaPort).registrar(
                "REVISION_ANTIFRAUDE",
                101L);
    }

    @Test
    void debeMantenerPendienteCuandoSolicitaMasInformacion() {

        when(port.registrar(
                eq(12L),
                eq("MAS_INFORMACION"),
                eq("Se requiere evidencia adicional")))
                .thenReturn(new RevisionAntifraudePort.Revision(
                        102L,
                        12L,
                        "MAS_INFORMACION",
                        "Se requiere evidencia adicional"));

        var response = service.registrar(
                12L,
                new RevisionAntifraudeRequest(
                        "MAS INFORMACION",
                        "Se requiere evidencia adicional"));

        assertEquals("MAS_INFORMACION", response.resultado());

        verify(port).actualizarEstadoAlerta(
                12L,
                "PENDIENTE_REVISION");

        verify(auditoriaPort).registrar(
                "REVISION_ANTIFRAUDE",
                102L);
    }

    @Test
    void debeRechazarResultadoInvalido() {

        var exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.registrar(
                        13L,
                        new RevisionAntifraudeRequest(
                                "FRAUDE_AUTOMATICO",
                                "Prueba")));

        assertTrue(exception.getMessage()
                .toLowerCase().contains("resultado"));

        verifyNoInteractions(port);
        verifyNoInteractions(auditoriaPort);
    }

    @Test
    void debeExigirJustificacion() {

        var exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.registrar(
                        14L,
                        new RevisionAntifraudeRequest(
                                "DESCARTADA",
                                " ")));

        assertTrue(exception.getMessage()
                .contains("justificación"));

        verifyNoInteractions(port);
        verifyNoInteractions(auditoriaPort);
    }
}
