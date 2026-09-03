package pe.siniestrofacil.application;

import org.junit.jupiter.api.Test;
import pe.siniestrofacil.application.port.InspeccionPort;
import pe.siniestrofacil.application.service.InspeccionService;
import pe.siniestrofacil.domain.model.Siniestro;
import pe.siniestrofacil.domain.port.SiniestroRepository;

import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InspeccionServiceTest {

    @Test
    void registraInspeccionYProgramaSiniestro() {

        InspeccionPort inspeccionPort =
                mock(InspeccionPort.class);

        SiniestroRepository siniestroRepository =
                mock(SiniestroRepository.class);

        Siniestro siniestro = new Siniestro(
                108L,
                1L,
                1L,
                OffsetDateTime.parse("2026-08-27T20:00:00Z"),
                "Lima",
                "COLISION",
                "Daño frontal",
                Siniestro.EN_EVALUACION);

        when(siniestroRepository.findById(108L))
                .thenReturn(Optional.of(siniestro));

        InspeccionPort.Inspeccion inspeccion =
                new InspeccionPort.Inspeccion(
                        1L,
                        108L,
                        10L,
                        "2026-09-02T10:00:00",
                        null);

        when(inspeccionPort.registrar(
                108L,
                10L,
                "2026-09-02T10:00:00"))
                .thenReturn(inspeccion);

        InspeccionService service =
                new InspeccionService(
                        inspeccionPort,
                        siniestroRepository);

        InspeccionPort.Inspeccion resultado =
                service.registrar(
                        108L,
                        10L,
                        "2026-09-02T10:00:00");

        assertEquals(1L, resultado.id());
        assertEquals(108L, resultado.siniestroId());
        assertEquals(10L, resultado.ajustadorId());

        verify(inspeccionPort).registrar(
                108L,
                10L,
                "2026-09-02T10:00:00");

        verify(siniestroRepository).transition(
                108L,
                Siniestro.INSPECCION_PROGRAMADA);
    }

    @Test
    void rechazaInspeccionSiSiniestroNoEstaEnEvaluacion() {

        InspeccionPort inspeccionPort =
                mock(InspeccionPort.class);

        SiniestroRepository siniestroRepository =
                mock(SiniestroRepository.class);

        Siniestro siniestro = new Siniestro(
                108L,
                1L,
                1L,
                OffsetDateTime.parse("2026-08-27T20:00:00Z"),
                "Lima",
                "COLISION",
                "Daño frontal",
                Siniestro.REPORTADO);

        when(siniestroRepository.findById(108L))
                .thenReturn(Optional.of(siniestro));

        InspeccionService service =
                new InspeccionService(
                        inspeccionPort,
                        siniestroRepository);

        assertThrows(
                IllegalStateException.class,
                () -> service.registrar(
                        108L,
                        10L,
                        "2026-09-02T10:00:00"));

        verify(inspeccionPort, never()).registrar(
                anyLong(),
                any(),
                anyString());

        verify(siniestroRepository, never()).transition(
                anyLong(),
                anyString());
    }
}
