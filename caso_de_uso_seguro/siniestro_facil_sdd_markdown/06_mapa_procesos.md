# Siniestro Fácil — Mapa de Procesos

## 1. Alcance

El mapa representa únicamente el flujo que puede reconstruirse a partir de las entrevistas.

## 2. Proceso de alto nivel

```mermaid
flowchart TD
    A[Reporte del accidente] --> B[Confirmar identidad, póliza y vehículo]
    B --> C[Validar cobertura y deducible]
    C --> D{¿Corresponde asistencia?}
    D -->|Sí| E[Coordinar asistencia]
    D -->|No| F[Continuar gestión]
    E --> F
    F --> G[Recopilar evidencia]
    G --> H[Asignar caso]
    H --> I{¿Caso simple o complejo/riesgoso?}
    I -->|Simple| J[Flujo digital]
    I -->|Complejo/riesgoso| K[Revisión especializada / ajustador]
    J --> L[Evaluación / inspección]
    K --> L
    L --> M[Presupuesto del taller]
    M --> N{¿Aprobado?}
    N -->|Observado| O[Observaciones / cambios]
    O --> M
    N -->|Sí| P[Reparación]
    P --> Q[Entrega / indemnización]
    Q --> R[Cierre]
```

**Origen:** OPS-01, OPS-04, OPS-05, OPS-07; CEO-03, CEO-05.

## 3. Proceso antifraude transversal

```mermaid
flowchart TD
    A[Datos y evidencias del siniestro] --> B[Señales de riesgo]
    B --> C[Alerta]
    C --> D[Investigación humana]
    D --> E{Resultado de revisión}
    E -->|Confirmada| F[Aplicar política antifraude]
    E -->|Descartada| G[Continuar proceso]
    E -->|Más información| H[Solicitar información]
    H --> D
    F --> I[Según política: priorizar o detener temporalmente pago]
```

**Origen:** FRA-02, FRA-04, FRA-05.

## 4. Proceso de proveedores

```mermaid
flowchart TD
    A[Solicitud a proveedor] --> B{Respuesta}
    B -->|Aceptada| C[Continuar]
    B -->|Rechazada| D[Gestionar alternativa]
    B -->|Sin respuesta| E[Reintentar / escalar / reasignar]
    D --> F[Registrar resultado]
    E --> F
    C --> F
```

**Origen:** OPS-09.

## 5. Proceso de auditoría

```mermaid
flowchart LR
    A[Registro] --> T[Línea de tiempo]
    B[Cambio] --> T
    C[Evidencia] --> T
    D[Cobertura] --> T
    E[Proveedor] --> T
    F[Presupuesto] --> T
    G[Comunicación] --> T
    H[Pago autorizado] --> T
```

**Origen:** OPS-10.

## 6. Procesos que no pueden detallarse aún

- Reglas de transición de estados.
- Procedimiento completo de inspección.
- Política exacta de cobertura/deducible.
- Política de deduplicación.
- SLA exactos.
- Política de aprobación de presupuestos.
- Política antifraude cuantificada.
- Retención de evidencias.
