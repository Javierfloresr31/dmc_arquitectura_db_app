package pe.siniestrofacil.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pe.siniestrofacil.application.port.PresupuestoObservacionPort;
import pe.siniestrofacil.application.port.PresupuestoPort;
import pe.siniestrofacil.application.service.PresupuestoObservacionService;
import pe.siniestrofacil.application.service.SiniestroService;
import pe.siniestrofacil.domain.model.Siniestro;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PresupuestoObservacionServiceTest {

    @Mock
    private PresupuestoPort presupuestoPort;

    @Mock
    private PresupuestoObservacionPort observacionPort;

    @Mock
    private SiniestroService siniestroService;

    private PresupuestoObservacionService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        service = new PresupuestoObservacionService(
                presupuestoPort,
                observacionPort,
                siniestroService);
    }

    @Test
    void debeObservarPresupuestoCuandoEstaRecibido() {

        Long presupuestoId = 10L;
        Long siniestroId = 100L;

        PresupuestoPort.Presupuesto presupuesto =
                new PresupuestoPort.Presupuesto(
                        presupuestoId,
                        siniestroId,
                        20L,
                        "5000",
                        "Diagnostico",
                        LocalDate.now().plusDays(7),
                        null,
                        null,
                        null);

        Siniestro siniestro = new Siniestro(
                siniestroId,
                null,
                null,
                OffsetDateTime.now(),
                "Lima",
                "CHOQUE",
                "Danos",
                Siniestro.PRESUPUESTO_RECIBIDO);

        PresupuestoObservacionPort.Observacion observacion =
                new PresupuestoObservacionPort.Observacion(
                        50L,
                        presupuestoId,
                        "operador01",
                        "Corregir mano de obra",
                        OffsetDateTime.now());

        when(presupuestoPort.obtener(presupuestoId))
                .thenReturn(presupuesto);

        when(siniestroService.findById(siniestroId))
                .thenReturn(Optional.of(siniestro));

        when(observacionPort.buscarPorIdempotencyKey(
                eq("key-001"),
                anyString()))
                .thenReturn(Optional.empty());

        when(observacionPort.registrar(
                eq(presupuestoId),
                eq("operador01"),
                eq("Corregir mano de obra"),
                eq("key-001"),
                anyString(),
                eq("corr-001")))
                .thenReturn(
                        new PresupuestoObservacionPort.Resultado(
                                observacion,
                                true));

        PresupuestoObservacionPort.Observacion response =
                service.observar(
                        presupuestoId,
                        "operador01",
                        "Corregir mano de obra",
                        "key-001",
                        "corr-001");

        assertSame(observacion, response);

        verify(observacionPort).registrar(
                eq(presupuestoId),
                eq("operador01"),
                eq("Corregir mano de obra"),
                eq("key-001"),
                anyString(),
                eq("corr-001"));

        verify(siniestroService).transition(
                siniestroId,
                Siniestro.OBSERVADO);
    }

    @Test
    void debeDevolverObservacionExistenteCuandoSeRepiteIdempotencyKey() {

        Long presupuestoId = 10L;

        PresupuestoObservacionPort.Observacion observacion =
                new PresupuestoObservacionPort.Observacion(
                        50L,
                        presupuestoId,
                        "operador01",
                        "Corregir mano de obra",
                        OffsetDateTime.now());

        when(observacionPort.buscarPorIdempotencyKey(
                eq("key-001"),
                anyString()))
                .thenReturn(Optional.of(
                        new PresupuestoObservacionPort.Resultado(
                                observacion,
                                false)));

        PresupuestoObservacionPort.Observacion response =
                service.observar(
                        presupuestoId,
                        "operador01",
                        "Corregir mano de obra",
                        "key-001",
                        "corr-001");

        assertSame(observacion, response);

        verify(observacionPort, never()).registrar(
                anyLong(),
                anyString(),
                anyString(),
                anyString(),
                anyString(),
                anyString());

        verifyNoInteractions(presupuestoPort, siniestroService);
    }

    @Test
    void debeRechazarCuandoPresupuestoNoExiste() {

        when(observacionPort.buscarPorIdempotencyKey(
                eq("key-001"),
                anyString()))
                .thenReturn(Optional.empty());

        when(presupuestoPort.obtener(10L))
                .thenReturn(null);

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> service.observar(
                                10L,
                                "operador01",
                                "Corregir presupuesto",
                                "key-001",
                                "corr-001"));

        assertEquals(
                "Presupuesto inexistente",
                exception.getMessage());

        verify(observacionPort, never()).registrar(
                anyLong(),
                anyString(),
                anyString(),
                anyString(),
                anyString(),
                anyString());

        verify(siniestroService, never())
                .transition(anyLong(), anyString());
    }

    @Test
    void debeRechazarCuandoSiniestroNoEstaEnPresupuestoRecibido() {

        Long presupuestoId = 10L;
        Long siniestroId = 100L;

        PresupuestoPort.Presupuesto presupuesto =
                new PresupuestoPort.Presupuesto(
                        presupuestoId,
                        siniestroId,
                        20L,
                        "5000",
                        "Diagnostico",
                        LocalDate.now().plusDays(7),
                        null,
                        null,
                        null);

        Siniestro siniestro = new Siniestro(
                siniestroId,
                null,
                null,
                OffsetDateTime.now(),
                "Lima",
                "CHOQUE",
                "Danos",
                Siniestro.AUTORIZADO);

        when(observacionPort.buscarPorIdempotencyKey(
                eq("key-001"),
                anyString()))
                .thenReturn(Optional.empty());

        when(presupuestoPort.obtener(presupuestoId))
                .thenReturn(presupuesto);

        when(siniestroService.findById(siniestroId))
                .thenReturn(Optional.of(siniestro));

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> service.observar(
                                presupuestoId,
                                "operador01",
                                "Corregir presupuesto",
                                "key-001",
                                "corr-001"));

        assertTrue(exception.getMessage()
                .contains("solo puede observarse"));

        verify(observacionPort, never()).registrar(
                anyLong(),
                anyString(),
                anyString(),
                anyString(),
                anyString(),
                anyString());

        verify(siniestroService, never())
                .transition(anyLong(), anyString());
    }

    @Test
    void debeRechazarResponsableVacio() {

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> service.observar(
                                10L,
                                "",
                                "Corregir presupuesto",
                                "key-001",
                                "corr-001"));

        assertEquals(
                "El responsable es obligatorio",
                exception.getMessage());

        verifyNoInteractions(
                presupuestoPort,
                observacionPort,
                siniestroService);
    }

    @Test
    void debeRechazarObservacionVacia() {

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> service.observar(
                                10L,
                                "operador01",
                                "",
                                "key-001",
                                "corr-001"));

        assertEquals(
                "La observación es obligatoria",
                exception.getMessage());

        verifyNoInteractions(
                presupuestoPort,
                observacionPort,
                siniestroService);
    }
}
