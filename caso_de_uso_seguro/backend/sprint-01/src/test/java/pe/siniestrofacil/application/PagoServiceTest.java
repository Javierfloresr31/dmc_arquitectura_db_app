package pe.siniestrofacil.application;

import org.junit.jupiter.api.Test;
import pe.siniestrofacil.application.exception.EconomicOperationConflictException;
import pe.siniestrofacil.application.port.PagoPort;
import pe.siniestrofacil.application.service.PagoService;
import pe.siniestrofacil.domain.model.Siniestro;
import pe.siniestrofacil.domain.port.SiniestroRepository;

import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PagoServiceTest {

    @Test
    void registraPagoConSiniestroAutorizado() {

        PagoPort pagoPort = mock(PagoPort.class);
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
                Siniestro.AUTORIZADO);

        when(siniestroRepository.findById(109L))
                .thenReturn(Optional.of(siniestro));

        PagoPort.Pago pago = new PagoPort.Pago(
                1L,
                109L,
                "10");

        when(pagoPort.buscarPorIdempotencyKey(
                "KEY-001",
                "HASH-001"))
                .thenReturn(Optional.empty());

        when(pagoPort.existeOperacionEquivalente(109L, 10L))
                .thenReturn(false);

        when(pagoPort.registrar(
	        eq(109L),
        	eq(10L),
       	        eq("KEY-001"),
        	anyString(),
        	eq("CORR-001")))
        	.thenReturn(new PagoPort.Resultado(pago, true));


        PagoService service =
                new PagoService(
                        pagoPort,
                        siniestroRepository);

        PagoPort.Pago resultado =
                service.registrar(
                        109L,
                        10L,
                        "KEY-001",
                        "CORR-001");

        assertEquals(1L, resultado.id());
        assertEquals(109L, resultado.siniestroId());
        assertEquals("10", resultado.autorizacion());

        verify(pagoPort).existeOperacionEquivalente(
                109L,
                10L);

        verify(pagoPort).registrar(
                eq(109L),
                eq(10L),
                eq("KEY-001"),
                anyString(),
                eq("CORR-001"));

        verify(siniestroRepository).transition(
                109L,
                Siniestro.INDEMNIZADO,
                "PAGO_REGISTRADO",
                null,
                "CORR-001");
    }

    @Test
    void rechazaPagoSinAutorizacion() {

        PagoPort pagoPort = mock(PagoPort.class);
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
                Siniestro.AUTORIZADO);

        when(siniestroRepository.findById(109L))
                .thenReturn(Optional.of(siniestro));

        PagoService service =
                new PagoService(
                        pagoPort,
                        siniestroRepository);

        assertThrows(
                IllegalArgumentException.class,
                () -> service.registrar(
                        109L,
                        null,
                        "KEY-002",
                        "CORR-002"));

        verify(pagoPort, never())
                .registrar(
                        anyLong(),
                        anyLong(),
                        anyString(),
                        anyString(),
                        anyString());

        verify(siniestroRepository, never())
                .transition(anyLong(), anyString());
    }

    @Test
    void rechazaPagoSiSiniestroNoEstaAutorizado() {

        PagoPort pagoPort = mock(PagoPort.class);
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
                Siniestro.PRESUPUESTO_RECIBIDO);

        when(siniestroRepository.findById(109L))
                .thenReturn(Optional.of(siniestro));

        PagoService service =
                new PagoService(
                        pagoPort,
                        siniestroRepository);

        assertThrows(
                IllegalStateException.class,
                () -> service.registrar(
                        109L,
                        10L,
                        "KEY-003",
                        "CORR-003"));

        verify(pagoPort, never())
                .registrar(
                        anyLong(),
                        anyLong(),
                        anyString(),
                        anyString(),
                        anyString());

        verify(siniestroRepository, never())
                .transition(anyLong(), anyString());
    }

    @Test
    void rechazaOperacionEconomicaEquivalente() {

        PagoPort pagoPort = mock(PagoPort.class);
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
                Siniestro.AUTORIZADO);

        when(siniestroRepository.findById(109L))
                .thenReturn(Optional.of(siniestro));

        when(pagoPort.buscarPorIdempotencyKey(
                "KEY-004",
                "HASH-004"))
                .thenReturn(Optional.empty());

        when(pagoPort.existeOperacionEquivalente(109L, 10L))
                .thenReturn(true);

        PagoService service =
                new PagoService(
                        pagoPort,
                        siniestroRepository);

        assertThrows(
                EconomicOperationConflictException.class,
                () -> service.registrar(
                        109L,
                        10L,
                        "KEY-004",
                        "CORR-004"));

        verify(pagoPort)
                .existeOperacionEquivalente(
                        109L,
                        10L);

        verify(pagoPort, never())
                .registrar(
                        anyLong(),
                        anyLong(),
                        anyString(),
                        anyString(),
                        anyString());

        verify(siniestroRepository, never())
                .transition(anyLong(), anyString());
    }
}
