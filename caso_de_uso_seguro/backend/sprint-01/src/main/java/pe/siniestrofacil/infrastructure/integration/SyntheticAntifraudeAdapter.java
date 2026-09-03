package pe.siniestrofacil.infrastructure.integration;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SyntheticAntifraudeAdapter {

    public Resultado evaluar(Long siniestroId) {

        int selector = Math.floorMod(siniestroId.intValue(), 3);

        return switch (selector) {
            case 0 -> new Resultado(
                    "IMAGEN_REUTILIZADA",
                    "BAJA",
                    "Señal sintética de posible reutilización de imagen",
                    "EVALUACION_SINTETICA",
                    List.of("IMAGEN_SIMILAR_DETECTADA"));

            case 1 -> new Resultado(
                    "IMAGEN_REUTILIZADA",
                    "MEDIA",
                    "Señal sintética de posible reutilización de imagen",
                    "EVALUACION_SINTETICA",
                    List.of(
                            "IMAGEN_SIMILAR_DETECTADA",
                            "COINCIDENCIA_PARCIAL"));

            default -> new Resultado(
                    "IMAGEN_REUTILIZADA",
                    "ALTA",
                    "Señal sintética de posible reutilización de imagen",
                    "EVALUACION_SINTETICA",
                    List.of(
                            "IMAGEN_SIMILAR_DETECTADA",
                            "COINCIDENCIA_ALTA"));
        };
    }

    public record Resultado(
            String tipo,
            String severidad,
            String explicacion,
            String datosOrigen,
            List<String> senales) {
    }
}
