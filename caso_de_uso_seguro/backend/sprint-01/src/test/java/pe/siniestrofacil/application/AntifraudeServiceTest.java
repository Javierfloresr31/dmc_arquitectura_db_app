package pe.siniestrofacil.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.siniestrofacil.application.dto.EvaluacionAntifraudeRequest;
import pe.siniestrofacil.application.port.AntifraudePort;
import pe.siniestrofacil.application.port.AuditoriaPort;
import pe.siniestrofacil.application.service.AntifraudeService;
import pe.siniestrofacil.application.service.ReglaModeloVersionService;
import pe.siniestrofacil.infrastructure.integration.SyntheticAntifraudeAdapter;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AntifraudeServiceTest {

    @Mock
    private AntifraudePort port;

    @Mock
    private AuditoriaPort auditoriaPort;

    @Mock
    private ReglaModeloVersionService reglaModeloVersionService;

    private SyntheticAntifraudeAdapter adapter;
    private AntifraudeService service;

    @BeforeEach
    void setUp() {
        adapter = new SyntheticAntifraudeAdapter();

        service = new AntifraudeService(
                port,
                auditoriaPort,
                reglaModeloVersionService,
                adapter);
    }

    @Test
    void debeCrearAlertaYRegistrarAuditoria() {

        when(reglaModeloVersionService.obtener(3L))
                .thenReturn(new pe.siniestrofacil.application.dto.ReglaModeloVersionResponse(
                        3L,
                        "REGLA",
                        "SYN-V5"));

        when(port.crearAlerta(
                eq(21L),
                eq("IMAGEN_REUTILIZADA"),
                eq("BAJA"),
                eq("Señal sintética de posible reutilización de imagen"),
                eq("EVIDENCIA_SINTETICA_SPRINT_04"),
                any(OffsetDateTime.class),
                eq("REGLA_SYN-V5"),
                eq("PENDIENTE_REVISION"),
                isNull(),
                eq(3L)))
                .thenReturn(new AntifraudePort.Alerta(
                        300L,
                        21L,
                        "IMAGEN_REUTILIZADA",
                        "BAJA",
                        "Señal sintética de posible reutilización de imagen",
                        "EVIDENCIA_SINTETICA_SPRINT_04",
                        OffsetDateTime.now(),
                        "REGLA_SYN-V5",
                        "PENDIENTE_REVISION",
                        null,
                        3L));

        var response = service.evaluar(
                21L,
                new EvaluacionAntifraudeRequest(
                        3L,
                        "IMAGEN_REUTILIZADA",
                        "EVIDENCIA_SINTETICA_SPRINT_04"));

        assertEquals(300L, response.id());
        assertEquals(21L, response.siniestroId());
        assertEquals("IMAGEN_REUTILIZADA", response.tipo());
        assertEquals("BAJA", response.severidad());
        assertEquals("PENDIENTE_REVISION", response.estado());
        assertEquals("REGLA_SYN-V5", response.modeloORegla());

        verify(port).registrarSenal(
                300L,
                "IMAGEN_SIMILAR_DETECTADA");

        verify(auditoriaPort).registrar(
                "ALERTA_ANTIFRAUDE",
                300L);
    }

    @Test
    void debeGenerarSeñalesSegunSeveridadMedia() {

        when(reglaModeloVersionService.obtener(3L))
                .thenReturn(new pe.siniestrofacil.application.dto.ReglaModeloVersionResponse(
                        3L,
                        "REGLA",
                        "SYN-V5"));

        when(port.crearAlerta(
                eq(22L),
                eq("IMAGEN_REUTILIZADA"),
                eq("MEDIA"),
                anyString(),
                anyString(),
                any(OffsetDateTime.class),
                eq("REGLA_SYN-V5"),
                eq("PENDIENTE_REVISION"),
                isNull(),
                eq(3L)))
                .thenReturn(new AntifraudePort.Alerta(
                        301L,
                        22L,
                        "IMAGEN_REUTILIZADA",
                        "MEDIA",
                        "Señal sintética de posible reutilización de imagen",
                        "EVALUACION_SINTETICA",
                        OffsetDateTime.now(),
                        "REGLA_SYN-V5",
                        "PENDIENTE_REVISION",
                        null,
                        3L));

        service.evaluar(
                22L,
                new EvaluacionAntifraudeRequest(
                        3L,
                        null,
                        null));

        verify(port).registrarSenal(
                301L,
                "IMAGEN_SIMILAR_DETECTADA");

        verify(port).registrarSenal(
                301L,
                "COINCIDENCIA_PARCIAL");

        verify(auditoriaPort).registrar(
                "ALERTA_ANTIFRAUDE",
                301L);
    }

    @Test
    void debeRechazarSiniestroNulo() {

        var exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.evaluar(
                        null,
                        new EvaluacionAntifraudeRequest(
                                3L,
                                null,
                                null)));

        assertTrue(exception.getMessage()
                .toLowerCase()
                .contains("siniestro"));

        verifyNoInteractions(port);
        verifyNoInteractions(auditoriaPort);
        verifyNoInteractions(reglaModeloVersionService);
    }

    @Test
    void debeExigirVersionReglaModelo() {

        var exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.evaluar(
                        21L,
                        new EvaluacionAntifraudeRequest(
                                null,
                                null,
                                null)));

        assertTrue(exception.getMessage()
                .toLowerCase()
                .contains("versión"));

        verifyNoInteractions(port);
        verifyNoInteractions(auditoriaPort);
    }
}
