package pe.siniestrofacil.application;

import org.junit.jupiter.api.Test;
import pe.siniestrofacil.application.port.PresupuestoPort;
import pe.siniestrofacil.application.service.PresupuestoService;
import pe.siniestrofacil.domain.model.Siniestro;
import pe.siniestrofacil.domain.port.SiniestroRepository;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PresupuestoServiceTest {

    @Test
    void registraPresupuestoConVigenciaDeSieteDias() {

        PresupuestoPort presupuestoPort =
                mock(PresupuestoPort.class);

        SiniestroRepository siniestroRepository =
                mock(SiniestroRepository.class);

        Siniestro siniestro = new Siniestro(
                109L,
                1L,
                1L,
                OffsetDateTime.parse("2026-08-27T20:00:00Z"),
                "Lima",
                "COLISION",
                "Daño frontal",
                Siniestro.INSPECCION_PROGRAMADA);

        when(siniestroRepository.findById(109L))
                .thenReturn(Optional.of(siniestro));

        LocalDate vigenciaEsperada =
                LocalDate.now().plusDays(7);

        PresupuestoPort.Presupuesto presupuesto =
                new PresupuestoPort.Presupuesto(
                        1L,
                        109L,
                        10L,
                        "1000.00",
                        "Diagnóstico",
                        vigenciaEsperada,
                        null,
                        null,
                        null);

        when(presupuestoPort.registrar(
                eq(109L),
                eq(10L),
                eq("1000.00"),
                eq("Diagnóstico"),
                eq(vigenciaEsperada),
                isNull(),
                isNull(),
                isNull()))
                .thenReturn(presupuesto);

        PresupuestoService service =
                new PresupuestoService(
                        presupuestoPort,
                        siniestroRepository);

        PresupuestoPort.Presupuesto resultado =
                service.registrar(
                        109L,
                        10L,
                        "1000.00",
                        "Diagnóstico",
                        null,
                        null,
                        null,
                        null);

        assertEquals(1L, resultado.id());
        assertEquals(109L, resultado.siniestroId());
        assertEquals(vigenciaEsperada, resultado.vigencia());

        verify(presupuestoPort).registrar(
                109L,
                10L,
                "1000.00",
                "Diagnóstico",
                vigenciaEsperada,
                null,
                null,
                null);

        verify(siniestroRepository).transition(
                109L,
                Siniestro.PRESUPUESTO_RECIBIDO);
    }

    @Test
    void rechazaVigenciaDiferenteA7Dias() {

        PresupuestoPort presupuestoPort =
                mock(PresupuestoPort.class);

        SiniestroRepository siniestroRepository =
                mock(SiniestroRepository.class);

        Siniestro siniestro = new Siniestro(
                109L,
                1L,
                1L,
                OffsetDateTime.parse("2026-08-27T20:00:00Z"),
                "Lima",
                "COLISION",
                "Daño frontal",
                Siniestro.INSPECCION_PROGRAMADA);

        when(siniestroRepository.findById(109L))
                .thenReturn(Optional.of(siniestro));

        PresupuestoService service =
                new PresupuestoService(
                        presupuestoPort,
                        siniestroRepository);

        LocalDate vigenciaIncorrecta =
                LocalDate.now().plusDays(8);

        assertThrows(
                IllegalArgumentException.class,
                () -> service.registrar(
                        109L,
                        10L,
                        "1000.00",
                        "Diagnóstico",
                        vigenciaIncorrecta,
                        null,
                        null,
                        null));

        verify(presupuestoPort, never()).registrar(
                anyLong(),
                anyLong(),
                anyString(),
                anyString(),
                any(LocalDate.class),
                any(),
                any(),
                any());

        verify(siniestroRepository, never()).transition(
                anyLong(),
                anyString());
    }
}
