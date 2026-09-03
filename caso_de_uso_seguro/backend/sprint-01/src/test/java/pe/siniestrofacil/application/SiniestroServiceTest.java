package pe.siniestrofacil.application;

import org.junit.jupiter.api.Test;
import pe.siniestrofacil.application.dto.CrearSiniestroRequest;
import pe.siniestrofacil.application.port.PagoPort;
import pe.siniestrofacil.application.service.SiniestroService;
import pe.siniestrofacil.domain.model.Siniestro;
import pe.siniestrofacil.domain.port.SiniestroRepository;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class SiniestroServiceTest {
    @Test
    void creaConEstadoReportado() {
        SiniestroRepository repo = mock(SiniestroRepository.class);
        PagoPort pagoPort = mock(PagoPort.class);
        SiniestroService service = new SiniestroService(repo, pagoPort);

        var req = new CrearSiniestroRequest(
                new CrearSiniestroRequest.PolizaRequest("POL-1"),
                new CrearSiniestroRequest.VehiculoRequest("ABC-123"),
                OffsetDateTime.parse("2026-08-20T10:00:00-05:00"),
                "Piura",
                "COLISION",
                "Daño frontal",
                new CrearSiniestroRequest.ParticipanteRequest("P1", "999", "REPORTANTE"));

        var expected = new Siniestro(
                10L, 1L, 2L, req.fecha(), req.ubicacionAproximada(),
                req.tipoEvento(), req.danosAparentes(), "REPORTADO");

        when(repo.createByBusinessKeys(
                eq("POL-1"),
                eq("ABC-123"),
                any(),
                eq(req.reportante()),
                eq("key-001"),
                anyString(),
                eq("corr-001"))).thenReturn(expected);

        var result = service.create(req, "key-001", "corr-001");

        assertEquals(10L, result.id());
        assertEquals("REPORTADO", result.estado());
        verify(repo).createByBusinessKeys(
                eq("POL-1"),
                eq("ABC-123"),
                any(),
                eq(req.reportante()),
                eq("key-001"),
                anyString(),
                eq("corr-001"));
    }

    @Test
    void cierraSiniestroListoParaEntregaConResultadoEconomico() {

        SiniestroRepository repo = mock(SiniestroRepository.class);
        PagoPort pagoPort = mock(PagoPort.class);
        SiniestroService service = new SiniestroService(repo, pagoPort);

        Siniestro siniestro = new Siniestro(
                109L, 1L, 1L,
                OffsetDateTime.parse("2026-08-27T20:00:00Z"),
                "Lima", "COLISION", "Daño frontal",
                Siniestro.LISTO_PARA_ENTREGA);

        when(repo.findById(109L))
                .thenReturn(java.util.Optional.of(siniestro));

        when(pagoPort.listar(109L))
                .thenReturn(java.util.List.of(
                        new PagoPort.Pago(1L, 109L, "10")));

        service.transition(109L, Siniestro.CERRADO);

        verify(repo).transition(109L, Siniestro.CERRADO);
    }

    @Test
    void cierraSiniestroIndemnizadoConResultadoEconomico() {

        SiniestroRepository repo = mock(SiniestroRepository.class);
        PagoPort pagoPort = mock(PagoPort.class);
        SiniestroService service = new SiniestroService(repo, pagoPort);

        Siniestro siniestro = new Siniestro(
                110L, 1L, 1L,
                OffsetDateTime.parse("2026-08-27T20:00:00Z"),
                "Lima", "COLISION", "Daño frontal",
                Siniestro.INDEMNIZADO);

        when(repo.findById(110L))
                .thenReturn(java.util.Optional.of(siniestro));

        when(pagoPort.listar(110L))
                .thenReturn(java.util.List.of(
                        new PagoPort.Pago(2L, 110L, "11")));

        service.transition(110L, Siniestro.CERRADO);

        verify(repo).transition(110L, Siniestro.CERRADO);
    }

    @Test
    void rechazaCierreSinResultadoEconomico() {

        SiniestroRepository repo = mock(SiniestroRepository.class);
        PagoPort pagoPort = mock(PagoPort.class);
        SiniestroService service = new SiniestroService(repo, pagoPort);

        Siniestro siniestro = new Siniestro(
                111L, 1L, 1L,
                OffsetDateTime.parse("2026-08-27T20:00:00Z"),
                "Lima", "COLISION", "Daño frontal",
                Siniestro.LISTO_PARA_ENTREGA);

        when(repo.findById(111L))
                .thenReturn(java.util.Optional.of(siniestro));

        when(pagoPort.listar(111L))
                .thenReturn(java.util.List.of());

        assertThrows(
                IllegalStateException.class,
                () -> service.transition(
                        111L, Siniestro.CERRADO));

        verify(repo, never()).transition(
                anyLong(), eq(Siniestro.CERRADO));
    }

    @Test
    void rechazaCierreDesdeEstadoNoPermitido() {

        SiniestroRepository repo = mock(SiniestroRepository.class);
        PagoPort pagoPort = mock(PagoPort.class);
        SiniestroService service = new SiniestroService(repo, pagoPort);

        Siniestro siniestro = new Siniestro(
                112L, 1L, 1L,
                OffsetDateTime.parse("2026-08-27T20:00:00Z"),
                "Lima", "COLISION", "Daño frontal",
                Siniestro.AUTORIZADO);

        when(repo.findById(112L))
                .thenReturn(java.util.Optional.of(siniestro));

        assertThrows(
                IllegalStateException.class,
                () -> service.transition(
                        112L, Siniestro.CERRADO));

        verify(pagoPort, never()).listar(112L);

        verify(repo, never()).transition(
                anyLong(), eq(Siniestro.CERRADO));
    }

}
