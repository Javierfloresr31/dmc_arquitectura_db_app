# Data sintética — plan de construcción

> Estado: Planificado. No se utilizarán datos reales de clientes.

## Objetivo
Proporcionar datos reproducibles para desarrollo, pruebas, integración, demostraciones y pruebas de carga de Crédito Ágil 360.

## Dataset base
Se generarán, cuando el modelo lógico esté aprobado, datos sintéticos para:
- clientes;
- ofertas;
- simulaciones;
- solicitudes;
- información de ingresos/situación laboral requerida;
- obligaciones/exposición;
- autorizaciones;
- documentos;
- evaluaciones;
- políticas/versiones de reglas;
- decisiones;
- excepciones;
- contratos;
- desembolsos;
- notificaciones;
- fuentes y respuestas de integración;
- eventos de auditoría;
- usuarios/roles de operación.

## Escenarios de prueba
1. Aprobación automática.
2. Rechazo.
3. Observación por información pendiente.
4. Revisión manual.
5. Excepción aprobada por supervisor.
6. Excepción rechazada.
7. Documento ilegible.
8. Inconsistencia de ingresos.
9. Timeout de fuente externa y reproceso.
10. Reintento idempotente.
11. Cambio de canal.
12. IA con alta confianza.
13. IA bajo umbral y revisión humana.
14. Consulta de decisión histórica después de cambio de datos.

## Reglas de calidad
- 100% ficticio.
- Seeds reproducibles.
- Relaciones referencialmente consistentes.
- Identificadores marcados como sintéticos.
- Dataset pequeño para desarrollo.
- Dataset mediano para integración.
- Dataset ampliado para pruebas de rendimiento.
- Casos de error deliberadamente controlados.

## Entregables futuros
- `seed.*` según tecnología seleccionada.
- archivos CSV/JSON de referencia si resultan útiles para integración.
- generador reproducible.
- catálogo de escenarios.
- documentación de correspondencia escenario → HU/RF/RNF/CA.

## No permitido
No se copiarán nombres, documentos, cuentas, ingresos, teléfonos, direcciones ni otros datos personales reales para fabricar el dataset.
