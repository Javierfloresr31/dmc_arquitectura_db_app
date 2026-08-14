# Siniestro Fácil — Datos sintéticos

## Objetivo
Crear un conjunto de datos reproducible para desarrollo, pruebas, demostraciones y validación de los flujos de Siniestro Fácil sin utilizar datos personales o financieros reales.

## Regla principal
Los datos sintéticos no deben representar personas reales ni copiar datos productivos. Deben conservar relaciones y escenarios de negocio, no identidades reales.

## Entidades a generar
1. Asegurados.
2. Reportantes.
3. Pólizas.
4. Vehículos.
5. Coberturas.
6. Siniestros.
7. Participantes.
8. Evidencias y versiones derivadas.
9. Proveedores de asistencia.
10. Asistencias.
11. Inspecciones.
12. Talleres.
13. Presupuestos y detalle.
14. Autorizaciones.
15. Alertas antifraude.
16. Señales antifraude.
17. Versiones de reglas/modelos.
18. Revisiones antifraude.
19. Pagos.
20. Historial de estados.
21. Auditoría.
22. Relaciones entre siniestros.

## Escenarios mínimos
### S01 — Caso simple
Reporte válido, póliza vigente, cobertura aplicable, evidencia suficiente, asignación digital, presupuesto y autorización.

### S02 — Evidencia incompleta
El caso se registra con datos mínimos y queda evidencia pendiente.

### S03 — Asistencia con proveedor no disponible
La solicitud requiere reintento y/o reasignación; no debe bloquear indefinidamente al asegurado.

### S04 — Caso complejo
Señales de severidad o características que requieren ajustador.

### S05 — Alerta no confirmada
Se genera una alerta, el investigador la descarta con justificación y el expediente continúa según política.

### S06 — Alerta que requiere revisión
La alerta deriva el caso a investigación; la decisión humana queda registrada.

### S07 — Evidencia transformada
Existe un original, una versión optimizada y metadata de transformación; el original permanece identificable.

### S08 — Casos relacionados
Dos o más siniestros comparten participante, vehículo, taller, teléfono o cuenta bancaria sintética, sin fusionar expedientes.

### S09 — Duplicidad
Dos reportes potencialmente corresponden al mismo evento; el escenario debe permitir probar la futura política de deduplicación sin inventar el algoritmo.

### S10 — Datos inconsistentes
Nombre, placa o ubicación tienen valor declarado y valor normalizado separados.

### S11 — Presupuesto observado/ampliado
El taller presenta presupuesto, recibe observaciones y posteriormente presenta una ampliación.

### S12 — Control de pago
Escenario para probar prevención de pagos duplicados.

## Volumen inicial
El volumen debe definirse en la sesión de datos. No se fija un número ahora porque las entrevistas solo indican aproximadamente 420,000 pólizas activas y cerca de 18,000 reportes mensuales como contexto de negocio, no como volumen requerido del dataset de desarrollo. fileciteturn19file4

## Generación
Se recomienda un generador determinístico con:
- semilla configurable;
- catálogos controlados;
- generadores por entidad;
- generadores por escenario;
- claves referenciales consistentes;
- posibilidad de regenerar exactamente el mismo dataset.

## Calidad
Cada dataset debe validarse mediante:
- integridad referencial;
- unicidad de identificadores sintéticos;
- consistencia temporal;
- cobertura de estados;
- cobertura de escenarios excepcionales;
- cobertura de señales antifraude;
- ausencia de datos reales;
- reproducibilidad mediante semilla.

## Artefactos previstos
- `data/dictionaries/` — diccionario de datos sintéticos.
- `data/scenarios/` — definición de escenarios.
- `data/seeds/` — semillas/versiones.
- `data/generated/` — archivos generados, si el repositorio decide versionarlos.
- `scripts/data-generator/` — generador.
- `README.md` — instrucciones de generación y validación.

## Dependencias
El dataset final debe generarse después de aprobar el modelo lógico y, para escenarios persistentes, después de estabilizar el modelo físico.
