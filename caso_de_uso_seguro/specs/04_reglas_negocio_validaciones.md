# Reglas de negocio, validaciones y decisiones pendientes

## Reglas confirmadas por entrevistas
- RB-001 El piloto cubre clientes directos y pólizas vigentes.
- RB-002 El piloto cubre daños materiales sin lesiones graves.
- RB-003 Heridos, fallecidos, procesos legales y daños masivos siguen rutas especializadas.
- RB-004 Una inconsistencia no equivale automáticamente a fraude.
- RB-005 Las alertas deben ser explicables y revisables.
- RB-006 Algunas reglas críticas pueden detener temporalmente un pago o derivar un caso; la política debe ser configurable y versionada.
- RB-007 Los originales de evidencia no deben ser sustituidos por versiones comprimidas/optimizadas.
- RB-008 Deben conservarse datos declarados y normalizados por separado.
- RB-009 Reasignaciones conservan historial y razón.
- RB-010 Acceso a información ampliada se limita por rol/necesidad.

## Validaciones derivadas directamente de las entrevistas
- Validar identidad del reportante.
- Validar póliza.
- Validar vehículo.
- Validar cobertura y deducible.
- Detectar o advertir casos duplicados.
- Validar presencia/estado de evidencias requeridas según el caso.
- Validar vigencia de presupuesto.
- Validar responsable autorizado para aprobar cambios/presupuesto/pago.
- Registrar versión de regla/modelo usada para una alerta.

## No definidos — no implementar como supuesto
1. Algoritmo/política exacta de deduplicación.
2. Campos y método de autenticación del reportante autorizado.
3. SLA exactos por región, tipo de siniestro y proveedor.
4. Umbrales de severidad y monto que activan revisión o bloqueo.
5. Catálogo de reglas antifraude críticas/no críticas.
6. Política de conservación y eliminación de imágenes/evidencias.
7. Reglas exactas de pago duplicado.
8. Contratos/API/protocolos y timeouts de cada proveedor.
9. Catálogo de talleres/proveedores y criterios de disponibilidad.
10. Campos obligatorios por tipo de evidencia y tipo de siniestro.
11. Canales y plantillas de mensajería.
12. Requisitos regulatorios específicos aplicables al almacenamiento y tratamiento de datos.
13. Ciudad del piloto y conjunto de talleres participantes.
14. Tecnología frontend/backend, nube, base de datos y mecanismo de almacenamiento de evidencias.
15. Definición cuantitativa de las métricas de éxito y sus fórmulas.
