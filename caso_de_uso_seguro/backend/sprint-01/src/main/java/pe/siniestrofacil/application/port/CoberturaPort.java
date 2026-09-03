package pe.siniestrofacil.application.port;

public interface CoberturaPort {

    Resultado validar(String numeroPoliza, String placa);

    record Resultado(
            boolean identidadVerificada,
            boolean polizaVerificada,
            boolean vehiculoVerificado,
            boolean coberturaVerificada,
            boolean deducibleDisponible) {

        public boolean puedeContinuar() {
            return identidadVerificada
                    && polizaVerificada
                    && vehiculoVerificado
                    && coberturaVerificada;
        }
    }
}
