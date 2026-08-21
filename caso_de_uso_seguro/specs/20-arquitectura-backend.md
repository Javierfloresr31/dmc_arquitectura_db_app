# Arquitectura Backend — Siniestro Fácil

## 1. Propósito

Definir el diseño lógico del backend que implementará las especificaciones de Siniestro Fácil sin introducir decisiones de negocio no sustentadas.

## 2. Principios

- El dominio de siniestros es la fuente de las reglas de negocio.
- La API no debe contener reglas de negocio dispersas.
- Persistencia separada del dominio.
- Integraciones externas aisladas mediante adaptadores.
- Eventos y operaciones externas deben ser trazables.
- Las decisiones antifraude son asistidas y revisables.
- La evidencia original debe preservarse y las transformaciones quedar identificadas.
- Los estados del siniestro solo cambian mediante transiciones válidas.

## 3. Arquitectura lógica

```text
Canales / Clientes
        |
        v
API / Controllers
        |
        v
Application / Use Cases
        |
        v
Domain
  |       |        |
  v       v        v
Siniestro Evidencia Antifraude
        |
        v
Ports / Interfaces
   |          |
   v          v
Persistence  Integrations
   |          |
   v          +-- Pólizas
PostgreSQL   +-- Asistencia / grúa
             +-- Talleres
             +-- Mapas
             +-- Mensajería
             +-- Pagos
```

## 4. Capas

### API
Responsable de autenticación contextual, validación sintáctica, serialización, códigos HTTP y correlación.

### Application
Orquesta casos de uso, transacciones, autorización de operación y publicación de eventos.

### Domain
Contiene reglas de negocio, invariantes y transición de estados. No depende de PostgreSQL ni de proveedores externos.

### Infrastructure
Implementa repositorios PostgreSQL, clientes de integraciones, almacenamiento de evidencia y mecanismos de mensajería.

## 5. Agregados iniciales

- Siniestro: expediente, estado, participantes y relaciones.
- Evidencia: original, hash, metadatos y versiones.
- Asistencia: solicitud y proveedor.
- Evaluación: inspección, taller y presupuesto.
- Antifraude: regla/modelo, alerta, señal y revisión.
- Autorización/Pago: decisión y resultado.

La separación es arquitectónica; no implica crear nuevas tablas fuera del modelo físico aprobado.

## 6. Persistencia

PostgreSQL utiliza el esquema `siniestro_facil`. El modelo físico define 23 estructuras y PK/FK estructurales; no se agregarán claves únicas, catálogos cerrados o triggers sin una decisión posterior sustentada. fileciteturn51file5

## 7. Observabilidad mínima

Toda operación relevante debe permitir correlacionar:
- request;
- usuario/actor técnico;
- siniestro;
- evento;
- integración externa;
- resultado;
- error.

## 8. Manejo de errores

Clasificación mínima:
- error de validación;
- recurso no encontrado;
- transición inválida;
- autorización insuficiente;
- conflicto/idempotencia;
- dependencia externa no disponible;
- error persistente;
- error interno.

## 9. Decisiones pendientes

No se inventa tecnología concreta para autenticación, mensajería, almacenamiento de objetos o proveedor de mapas. Deben definirse como ADR/decisión técnica antes de implementar la integración correspondiente.

## 10. Criterio de salida

La arquitectura queda lista para implementación cuando cada HU del sprint tenga identificado su caso de uso, API, servicio de aplicación, componente de dominio, persistencia e integración requerida.