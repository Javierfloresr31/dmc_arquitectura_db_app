# Siniestro Fácil — Criterios de Aceptación

## 1. Criterio de construcción

Los criterios se derivan de las condiciones explícitas de las entrevistas. Cuando una condición requiere una regla que no fue definida, se marca como **pendiente** y no se inventa.

## 2. Criterios

### CA-01 — Reporte inicial
**Relacionado:** HU-01, HU-03.  
**Origen:** CEO-03, CEO-06, OPS-02.

- Dado un reportante que inicia un accidente, cuando registra la información mínima disponible, entonces el caso puede ser creado sin exigir evidencia completa al inicio.
- La información mínima indicada es póliza/documento, placa, fecha, ubicación aproximada, tipo de evento y medio de contacto.
- La aplicación debe permitir continuar cuando exigir evidencia inmediata pudiera poner al cliente en una situación de riesgo.

**Pendiente:** validaciones de formato y obligatoriedad exacta por campo.

### CA-02 — Reportante autorizado
**Relacionado:** HU-02.  
**Origen:** CEO-06.

- Dado que el titular no puede reportar, una persona autorizada debe poder iniciar el caso.

**Pendiente:** mecanismo de autenticación y evidencia de autorización.

### CA-03 — Validación operativa
**Relacionado:** HU-04.  
**Origen:** OPS-01.

- Antes de continuar el flujo operativo, debe registrarse la confirmación de identidad, póliza y vehículo.
- Debe registrarse la verificación de cobertura y deducible.

**Pendiente:** reglas concretas de cobertura y deducible.

### CA-04 — Evidencias
**Relacionado:** HU-06, HU-07.  
**Origen:** OPS-03, FRA-03.

- Cada evidencia debe vincularse al siniestro.
- Debe registrarse momento de captura/recepción.
- Cuando sea posible, debe conservarse ubicación y dispositivo.
- Debe preservarse el original, hash, metadatos disponibles, fuente, transformaciones y versiones derivadas.

### CA-05 — Estados
**Relacionado:** HU-09.  
**Origen:** OPS-04.

- El expediente debe poder representar los estados mencionados en la entrevista.
- Los subestados internos no deben exponerse necesariamente al cliente.

**Pendiente:** transiciones permitidas entre estados y condiciones de entrada/salida.

### CA-06 — Visibilidad al cliente
**Relacionado:** HU-08.  
**Origen:** CEO-03, CEO-06.

- El asegurado debe conocer el avance y el siguiente paso.
- La comunicación debe usar lenguaje comprensible para el cliente.

**Pendiente:** canales y frecuencia de notificación.

### CA-07 — Asignación
**Relacionado:** HU-11, HU-12.  
**Origen:** OPS-05, CEO-03.

- La asignación debe considerar ciudad, daño, severidad, cobertura, disponibilidad de proveedores y señales de riesgo.
- Los casos simples pueden seguir vía digital.
- Los complejos requieren ajustador.
- Toda reasignación conserva historial y razón.

**Pendiente:** fórmula, prioridades y umbrales.

### CA-08 — Taller
**Relacionado:** HU-13, HU-14.  
**Origen:** OPS-07.

- El taller puede recibir orden, presentar presupuesto y diagnóstico.
- Deben poder registrarse observaciones, repuestos alternativos y ampliaciones.
- Debe quedar identificado quién aprobó cada cambio.
- Debe poder consultarse la vigencia del presupuesto.

**Pendiente:** duración de vigencia y reglas de aprobación.

### CA-09 — Proveedores
**Relacionado:** HU-15.  
**Origen:** OPS-09.

- Cada intento de integración debe registrar su resultado.
- Debe distinguirse solicitud aceptada, rechazada y sin respuesta.
- Ante falta de respuesta, el flujo debe permitir reintento, escalamiento o reasignación.

**Pendiente:** cantidad de reintentos, tiempos y política de escalamiento.

### CA-10 — Tiempos
**Relacionado:** HU-16.  
**Origen:** OPS-08.

- Deben controlarse primera respuesta, grúa, cobertura, asignación, inspección, presupuesto, autorización y cierre.

**Pendiente:** SLA exactos por tipo, ubicación y región.

### CA-11 — Auditoría
**Relacionado:** HU-17.  
**Origen:** OPS-10.

- Debe existir una línea de tiempo del caso.
- Debe registrar cambios, evidencias, cobertura, proveedores, presupuestos, comunicaciones y pagos autorizados.

### CA-12 — Alertas antifraude
**Relacionado:** HU-18, HU-19.  
**Origen:** FRA-02, FRA-04.

- Una alerta debe contener tipo, severidad, explicación, datos de origen, fecha, modelo o regla y estado.
- El investigador puede confirmarla, descartarla o pedir información adicional.
- La decisión y justificación deben quedar registradas.

### CA-13 — No rechazo automático por inconsistencia
**Relacionado:** HU-19, HU-25.  
**Origen:** FRA-01, CEO-08.

- Una inconsistencia no debe producir rechazo automático.
- Las decisiones sensibles deben ser revisables.
- Una alerta no equivale a fraude.

### CA-14 — Política antifraude
**Relacionado:** HU-20, HU-21.  
**Origen:** FRA-05, FRA-10.

- Las políticas deben poder versionarse.
- Debe poder identificarse la regla/modelo utilizado en una alerta.
- Deben conservarse datos de entrada y evidencia de revisión humana.

**Pendiente:** umbrales concretos y condiciones de bloqueo.

### CA-15 — Relaciones entre casos
**Relacionado:** HU-22.  
**Origen:** FRA-08.

- Debe ser posible relacionar casos por elementos compartidos.
- Relacionar casos no debe implicar fusionar sus expedientes.

### CA-16 — Datos originales y normalizados
**Relacionado:** HU-23.  
**Origen:** FRA-09.

- Deben conservarse los valores declarados.
- Los valores normalizados deben mantenerse separadamente.
- La normalización no debe reemplazar silenciosamente el valor declarado.

### CA-17 — Acceso restringido
**Relacionado:** HU-24.  
**Origen:** CEO-07, FRA-07.

- El acceso ampliado debe estar restringido por rol y necesidad.
- Las descargas de evidencia y consultas sensibles deben registrarse.

### CA-18 — IA
**Relacionado:** HU-25.  
**Origen:** CEO-08, FRA-06.

- La IA puede generar recomendaciones para clasificación, documentos faltantes, resumen, priorización, comparación visual, reutilización de imágenes, extracción y relaciones.
- Las recomendaciones no deben tratarse como verdad absoluta.
- Deben medirse falsos positivos.

**Pendiente:** métricas objetivo, umbrales y criterios de aceptación por modelo.

### CA-19 — Métricas
**Relacionado:** HU-26.  
**Origen:** CEO-04.

Deben poder medirse:
- tiempo a primera asistencia;
- tiempo a decisión;
- porcentaje de casos sin llamadas adicionales;
- satisfacción;
- costo operativo por siniestro;
- pérdidas evitadas por fraude.

### CA-20 — Piloto
**Relacionado:** HU-27.  
**Origen:** CEO-10.

- El piloto debe limitarse a una ciudad y un grupo controlado de talleres.
- Debe comprobarse la experiencia desde el reporte hasta la autorización de reparación.

**Pendiente:** ciudad, talleres, duración y criterios de salida del piloto.
