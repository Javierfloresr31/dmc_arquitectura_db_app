# 5. Criterios de aceptación

## CA-001 Reporte mínimo
Dado un reportante autorizado, cuando inicia un siniestro, entonces el sistema permite registrar póliza/documento, placa, fecha, ubicación aproximada, tipo de evento y contacto.

## CA-002 Evidencia no bloqueante
Dado un usuario en una situación de riesgo, cuando aún no tiene toda la evidencia, entonces puede crear el caso y completar evidencia posteriormente.

## CA-003 Fuera de alcance
Dado un caso con heridos, fallecidos, proceso legal o daño masivo, cuando se identifica esa condición, entonces no se fuerza el flujo digital inicial y se deriva a la ruta especializada.

## CA-004 Cobertura
Dado un caso creado, cuando se inicia validación, entonces se verifica identidad, póliza, vehículo, cobertura y deducible antes de continuar.

## CA-005 Estados
Dado un siniestro, cuando cambia de etapa, entonces se registra uno de los estados operativos definidos y el cliente solo visualiza los estados que correspondan a su experiencia.

## CA-006 Evidencia
Dado que se recibe una evidencia, entonces queda vinculada al siniestro y se conserva el original junto con hash, metadatos disponibles y versiones derivadas cuando existan.

## CA-007 Alerta antifraude
Dado que una regla/modelo genera una alerta, entonces la alerta contiene tipo, severidad, explicación, datos origen, fecha y versión.

## CA-008 Revisión humana
Dado que existe una alerta, entonces un investigador autorizado puede confirmarla, descartarla o solicitar información y debe registrar la justificación.

## CA-009 Proveedor sin respuesta
Dado que un proveedor no responde, entonces se registra el intento y el caso puede seguir mediante reintento, escalamiento o reasignación.

## CA-010 Presupuesto
Dado un presupuesto recibido, entonces se registra su vigencia, diagnóstico, aprobación/observación, cambios y responsable de la decisión.

## CA-011 Auditoría
Dado un expediente, entonces la línea de tiempo permite reconstruir cambios, evidencias, cobertura, proveedores, presupuesto, comunicaciones y pagos.

## CA-012 IA
Dado que una IA emite una recomendación, entonces se almacena como recomendación/alerta revisable y no como decisión definitiva.
