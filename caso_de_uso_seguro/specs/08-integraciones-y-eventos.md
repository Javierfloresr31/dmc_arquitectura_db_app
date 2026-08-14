# 8. Integraciones y eventos

## Dependencias explícitas
Sistema de pólizas; red de talleres; proveedores de grúa; ajustadores; mapas; mensajería; medios de pago.

## Eventos de negocio mencionados
Siniestro reportado; cobertura validada; asistencia solicitada; evidencia recibida; inspección asignada; presupuesto presentado; alerta generada; reparación autorizada; pago emitido.

## Comportamiento de integración
Las integraciones deben tolerar lentitud e indisponibilidad. Cada intento de proveedor debe distinguir aceptación, rechazo o ausencia de respuesta.

## Preguntas abiertas
Q-002: ¿Qué APIs/protocolos/canales existen hoy para cada dependencia?
Q-003: ¿Qué sistema es fuente maestra de pólizas, pagos y talleres?
Q-004: ¿Qué mecanismos de mensajería están disponibles?
