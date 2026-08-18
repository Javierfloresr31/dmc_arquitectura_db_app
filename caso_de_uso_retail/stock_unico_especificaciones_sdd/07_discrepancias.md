# Discrepancias y Vacíos Pendientes
## Stock Único — NovaRetail

Este archivo contiene únicamente puntos que las entrevistas plantean pero no resuelven. No se convierten en requisitos inventados.

## D-001 — Fórmula exacta de disponibilidad

**Evidencia:** Supply Chain indica que el principio es stock utilizable menos reservas y compromisos, considerando stock de seguridad, pero que la fórmula varía por categoría, tienda, campaña y modalidad.

**Fuente:** líneas 77–79.

**Decisión pendiente:** fórmula exacta por contexto y reglas de stock de seguridad.

---

## D-002 — Autoridad por sistema

**Evidencia:** CEO solicita saber qué dato es autoridad para cada operación y la operación actual utiliza fuentes distintas.

**Fuente:** líneas 58–60.

**Decisión pendiente:** sistema de autoridad para cada dato/operación y estrategia de sincronización.

---

## D-003 — Duración de reservas

**Evidencia:** la reserva debe tener expiración corta, pero no se especifica duración.

**Fuente:** líneas 81–83, 120–122.

**Decisión pendiente:** TTL, renovación, condiciones de expiración y tratamiento del pago tardío.

---

## D-004 — Estrategia de partición de pedidos

**Evidencia:** para un carrito con varios productos se debe decidir si salen juntos, desde ubicaciones distintas o mediante una alternativa.

**Fuente:** líneas 124–126.

**Decisión pendiente:** reglas de split/merge, prioridad, costo y efecto sobre promesa.

---

## D-005 — Compensación pago/reserva/pedido

**Evidencia:** se exige que un pago aprobado no quede sin pedido y que una reserva confirmada conserve trazabilidad.

**Fuente:** líneas 144–146.

**Decisión pendiente:** mecanismo de compensación ante fallos entre las operaciones.

---

## D-006 — Consistencia fuerte vs eventual

**Evidencia:** el reto solicita hacer explícita esta decisión y E-commerce diferencia catálogo eventual de reserva con mayor consistencia.

**Fuente:** líneas 148–150, 170–173.

**Decisión pendiente:** qué operaciones requieren consistencia fuerte, cuáles pueden ser eventuales y cómo se reconcilian.

---

## D-007 — Inventario agregado vs serializado

**Evidencia:** existen productos serializados y otros por lote; se indica que eventualmente se deberá identificar la unidad exacta para serializados.

**Fuente:** líneas 69–71, 85–87.

**Decisión pendiente:** alcance de serialización, lotes y momento de introducción.

---

## D-008 — Reglas de asignación de ubicación

**Evidencia:** se listan múltiples criterios, pero no se define algoritmo ni ponderación.

**Fuente:** líneas 93–95.

**Decisión pendiente:** algoritmo, pesos, desempates y reglas por modalidad/categoría.

---

## D-009 — Modo degradado

**Evidencia:** debe existir operación segura si una integración se retrasa y debe informarse si un dato está actualizado o degradado.

**Fuente:** líneas 101–103, 148–150.

**Decisión pendiente:** estados del modo degradado, operaciones permitidas, límites de confianza y recuperación.

---

## D-010 — Actualización considerada vigente

**Evidencia:** Supply Chain necesita saber si un dato está actualizado.

**Fuente:** líneas 101–103.

**Decisión pendiente:** umbral temporal por tipo de dato/fuente.

---

## D-011 — Roles de autorización de ajustes

**Evidencia:** los conteos y ajustes deben ser autorizados y conservar usuario/evidencia.

**Fuente:** líneas 89–91.

**Decisión pendiente:** quién puede contar, quién puede ajustar, quién aprueba y separación de funciones.

---

## D-012 — Plazo de retiro en tienda

**Evidencia:** la reserva debe liberarse si el cliente no recoge dentro del plazo.

**Fuente:** líneas 136–138.

**Decisión pendiente:** duración del plazo y política de liberación.

---

## D-013 — Reglas de alternativas

**Evidencia:** ante faltante pueden ofrecerse nueva fecha, cambio de tienda, sustituto o devolución.

**Fuente:** líneas 132–134.

**Decisión pendiente:** cuándo se ofrece cada alternativa, quién la decide y qué condiciones debe cumplir un sustituto.

---

## D-014 — Prioridad de tareas

**Evidencia:** tienda necesita una cola priorizada y tiempo objetivo.

**Fuente:** líneas 140–142.

**Decisión pendiente:** fórmula/prioridades, reglas de aging y desempate.

---

## D-015 — KPI y fórmulas

**Evidencia:** se identifican numerosos indicadores, pero no sus fórmulas, dimensiones, ventanas ni fuente oficial.

**Fuente:** líneas 34–36, 152–154.

**Decisión pendiente:** definición matemática, fuente, periodicidad y propietario de cada KPI.

---

## D-016 — Contratos de integración

**Evidencia:** existen movimientos en tiempo real y por lotes desde sistemas antiguos.

**Fuente:** líneas 73–75.

**Decisión pendiente:** interfaces, formatos, frecuencia, reintentos, orden, duplicados, errores y autoridad de cada integración.

---

## D-017 — Arquitectura física

**Evidencia:** se solicita una arquitectura que integre sistemas actuales, pero las entrevistas no especifican tecnología.

**Fuente:** líneas 46–48, 58–60.

**Decisión pendiente:** base de datos, mensajería, cache, API, infraestructura, cloud/on-premise y demás componentes técnicos.

---

## D-018 — Requisitos técnicos cuantitativos

Las entrevistas no definen:
- SLA;
- SLO;
- disponibilidad porcentual;
- RTO;
- RPO;
- límites de latencia por percentil;
- TPS/RPS objetivo;
- concurrencia máxima;
- crecimiento esperado.

**Fuente:** líneas 101–103 y 148–150.

**Decisión pendiente:** convertir las necesidades cualitativas en objetivos verificables.

---

## D-019 — Seguridad técnica

La entrevista únicamente especifica minimizar la exposición de datos personales al preparador.

**Fuente:** líneas 140–142.

**Decisión pendiente:** autenticación, autorización, cifrado, secretos, auditoría de accesos, retención y demás controles técnicos.

---

## D-020 — Analítica histórica

**Evidencia:** el reto diferencia datos operativos en tiempo real de analítica histórica.

**Fuente:** líneas 170–178.

**Decisión pendiente:** arquitectura, fuentes, latencia, retención y modelo analítico.

---

## D-021 — IA y alcance inicial

La CEO indica que la IA puede ayudar con demanda, redistribución y anomalías, pero no define obligatoriedad, modelo, datos, frecuencia ni mecanismo de aprobación.

**Fuente:** líneas 50–52.

**Decisión pendiente:** determinar si entra en primera etapa y bajo qué controles.

---

## D-022 — Alcance geográfico y de categorías posterior

La primera etapa está definida para tecnología y pequeños electrodomésticos en Lima. La expansión a otras categorías y ciudades es posterior, pero no se especifica roadmap.

**Fuente:** líneas 38–40.

**Decisión pendiente:** criterios y etapas de expansión.

---

# Validación de trazabilidad

No se incorporaron requisitos funcionales o reglas de negocio externos a las entrevistas.

Cuando una necesidad requiere una decisión que las entrevistas no contienen, se ha marcado como **Pendiente** y se ha trasladado a este documento.

Las principales incertidumbres coinciden con las identificadas expresamente en las evidencias iniciales: fórmula de disponibilidad, duración de reservas, partición de pedidos, autoridad por sistema y compensación ante fallos de pago.

**Fuente:** línea 166.
