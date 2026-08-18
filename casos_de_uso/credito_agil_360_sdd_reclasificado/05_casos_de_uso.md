# Crédito Ágil 360 — Casos de Uso

## Actores identificados

- Cliente.
- Asesor autorizado.
- Analista de Riesgos.
- Supervisor.
- Contact center.
- Motor de reglas.
- Sistemas externos.
- Sistemas internos / core bancario.

## CU-001 — Iniciar y continuar solicitud

**Objetivo:** iniciar una solicitud y continuarla posteriormente sin perder información.

**Precondición:** el cliente utiliza uno de los canales identificados.

**Flujo principal:**
1. El cliente inicia la solicitud.
2. El sistema registra la solicitud.
3. Se asigna/obtiene el identificador único.
4. El cliente confirma sus datos.
5. El cliente completa información faltante.
6. El cliente puede cambiar de canal.
7. La solicitud se recupera mediante autenticación.
8. El proceso continúa desde el estado existente.

**Alternativas:**
- Datos antiguos: permitir confirmación/actualización.
- Falla de integración: conservar lo registrado y solicitar solo la acción necesaria.

**Origen:** CEO P6; Canales P1-P4/P7.

## CU-002 — Evaluar solicitud

**Objetivo:** obtener una decisión crediticia aplicando información y políticas.

**Flujo principal:**
1. Identificar solicitante y verificar si es cliente.
2. Consultar información requerida.
3. Aplicar políticas de elegibilidad.
4. Obtener resultado.
5. Registrar datos, fuentes, hora, versión de reglas, score y demás elementos de auditoría.
6. Emitir aprobado, rechazado, observado o revisión manual.

**Alternativas:**
- Información inconsistente/incompleta.
- Alertas de identidad.
- Exposición cercana al límite.
- Documento ilegible.
- Excepción de campaña.
- Combinaciones de monto y perfil que requieren revisión.

**Origen:** Riesgos P1-P6.

## CU-003 — Gestionar revisión manual y excepción

**Flujo principal:**
1. La solicitud es derivada a revisión manual.
2. El analista revisa la información.
3. Si corresponde, recomienda una excepción.
4. Registra justificación y documentos considerados.
5. El supervisor aprueba cuando el nivel de riesgo lo exige.
6. Se registra el usuario autorizador.

**Origen:** Riesgos P6-P7.

**Pendiente:** niveles de riesgo y matriz de aprobación.

## CU-004 — Gestionar documentos con apoyo de IA

**Flujo:**
1. El cliente carga documento.
2. La solución puede extraer campos.
3. Cada campo conserva documento de origen y confianza.
4. No se completan valores inexistentes en el documento.
5. Los casos por debajo del umbral pasan a revisión humana.

**Origen:** CEO P8; Riesgos P9.

**Condicional:** solo aplica si se decide utilizar IA.

## CU-005 — Consultar estado y acciones

**Flujo:**
1. Cliente/asesor/contact center accede a la solicitud.
2. La solución muestra estado.
3. La solución muestra acción pendiente.
4. Los estados internos se traducen a lenguaje simple.
5. Contact center puede registrar una incidencia sin modificar la decisión de Riesgos.

**Origen:** CEO P6; Canales P4/P5/P9.

## CU-006 — Aceptar condiciones y desembolsar

**Flujo:**
1. Cliente recibe aprobación con vigencia.
2. Revisa condiciones.
3. Contrato queda disponible.
4. Cliente acepta contrato.
5. Solicitud pasa a lista para desembolso.
6. Se procesa desembolso.
7. Cliente recibe confirmación.

**Origen:** Canales P2/P6.

**Pendiente:** mecanismo de aceptación, firma, integración de desembolso y reglas de vigencia.

## CU-007 — Notificar eventos

**Eventos:** inicio, información pendiente, cambio relevante de estado, aprobación con vigencia, contrato disponible y desembolso.

**Canales:** app, correo o SMS.

**Restricción:** no enviar datos sensibles.

**Origen:** Canales P6.

## CU-008 — Reprocesar integración fallida

**Flujo:**
1. Una consulta o integración falla.
2. Se conserva la información ya registrada.
3. Se determina si continuar procesando o solicitar reintento.
4. Se reprocesa la consulta cuando corresponda.
5. La idempotencia evita doble evaluación o desembolso.

**Origen:** Canales P7; Riesgos P10.

## CU-009 — Analizar recorrido

**Flujo:**
1. Registrar eventos de etapa.
2. Registrar abandono, error, documento rechazado, reintento, tiempo de respuesta y conversión.
3. Analizar el recorrido sin mezclar más información financiera sensible de la necesaria.

**Origen:** CEO P4; Canales P10.
