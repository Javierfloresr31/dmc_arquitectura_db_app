# Especificación SDD — Requerimientos No Funcionales
## Stock Único — NovaRetail

> Importante: las entrevistas contienen restricciones de rendimiento, consistencia, auditoría, continuidad y protección de datos, pero no definen todos los valores cuantitativos. No se inventan SLA ni métricas técnicas.

## RNF-001 — Latencia de operaciones críticas
Las operaciones de ventas y reservas deben responder en el orden de **segundos**.

**Fuente:** Supply Chain, pregunta 9, líneas 101–103.  
**Estado:** Definido cualitativamente.

**Pendiente:** valor máximo de latencia, percentil y condiciones de carga.

## RNF-002 — Capacidad durante campañas
La solución debe contemplar el contexto de miles de consultas por segundo y picos de **12,000 pedidos por hora** durante campañas.

**Fuente:** CEO, líneas 54–56; E-commerce, líneas 148–150.

**Pendiente:** TPS/RPS objetivo, concurrencia, margen de crecimiento y distribución de carga.

## RNF-003 — Consistencia de reservas
La confirmación de reservas requiere mayor consistencia que las consultas generales de catálogo/disponibilidad.

**Fuente:** líneas 148–150.

**Pendiente:** definición técnica de consistencia fuerte, límites y modelo transaccional.

## RNF-004 — Concurrencia
Las reservas deben operar con control de concurrencia para evitar doble asignación.

**Fuente:** líneas 85–87.

## RNF-005 — Idempotencia
Las operaciones de reserva deben ser idempotentes frente a reintentos del checkout.

**Fuente:** líneas 85–87.

## RNF-006 — Operación degradada
Ante retrasos de integraciones, el sistema debe degradar de manera controlada y evitar datos engañosos.

**Fuente:** líneas 56–60, 101–103, 148–150.

**Pendiente:** definición del modo degradado, operaciones permitidas/no permitidas y umbral de activación.

## RNF-007 — Integración sin reemplazo masivo
La arquitectura debe permitir integración y evolución gradual con los sistemas actuales.

**Fuente:** líneas 46–48.

## RNF-008 — Auditoría
Los cambios de inventario deben conservar información suficiente para reconstruir causa y secuencia de cambios.

**Fuente:** líneas 89–91, 105–107.

## RNF-009 — Trazabilidad transaccional
Intento de pago, pago, pedido y reserva deben poder correlacionarse.

**Fuente:** líneas 144–146.

## RNF-010 — Protección de datos
El personal de tienda no debe acceder a datos personales del cliente que no necesite para preparar el pedido.

**Fuente:** líneas 140–142.

## RNF-011 — Exactitud de disponibilidad
La solución no debe prometer unidades que no existen y debe distinguir inventario físico de disponibilidad para venta digital.

**Fuente:** líneas 46–52, 77–79.

## RNF-012 — Resiliencia de integraciones
La solución debe continuar vendiendo de manera segura si alguna integración se retrasa.

**Fuente:** líneas 58–60.

**Pendiente:** comportamiento de recuperación, reintentos, compensación y límites.

## RNF-013 — Integridad de eventos
La solución debe poder detectar llegadas duplicadas o fuera de orden.

**Fuente:** líneas 105–107.

## RNF-014 — Rendimiento del checkout
No debe demorarse excesivamente el checkout para conseguir una disponibilidad perfecta; debe equilibrarse rapidez con controles posteriores de coherencia.

**Fuente:** líneas 54–56.

**Pendiente:** umbral concreto de respuesta y estrategia de control posterior.

## RNF-015 — Evolución por etapas
La solución debe soportar ampliación posterior a otras categorías y ciudades sin asumirlas como parte de la primera etapa.

**Fuente:** líneas 38–40.

## Requerimientos no funcionales NO definidos por las entrevistas

No se establecen valores para:
- disponibilidad porcentual;
- SLA/SLO;
- RTO/RPO;
- cifrado específico;
- autenticación/autorización técnica;
- tecnología de base de datos;
- proveedor cloud;
- observabilidad concreta;
- estrategia de backup;
- retención de auditoría;
- límites de tamaño de mensajes;
- tiempos de recuperación;
- número exacto de integraciones;
- contrato API;
- particionamiento;
- estrategia de escalamiento.

Estos puntos quedan en `discrepancias.md` y no se convierten en requisitos inventados.
