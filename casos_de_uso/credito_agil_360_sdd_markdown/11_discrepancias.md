# Crédito Ágil 360 — Discrepancias y Vacíos Pendientes

## Propósito

Registrar únicamente información que las entrevistas identifican como incierta, incompleta o pendiente de definición. No se completan mediante supuestos.

## Discrepancias

| ID | Tema | Discrepancia / vacío | Impacto | Entrevista origen | Responsable sugerido |
|---|---|---|---|---|---|
| D-001 | Fuentes externas | No se identifican las fuentes externas exactas que se consultarán. | Integraciones, seguridad, datos y evaluación | Riesgos P2 | Riesgos / Arquitectura |
| D-002 | Retención | No está definida la política de retención de solicitudes, documentos, decisiones y eventos. | Datos, almacenamiento y cumplimiento | Evidencias iniciales | Cumplimiento / Datos |
| D-003 | Deduplicación | No están definidas las reglas para distinguir solicitud nueva vs reintento. | Idempotencia y prevención de duplicados | Riesgos P8 | Riesgos / Arquitectura |
| D-004 | Actualización de datos | No está definido cuándo un dato se considera antiguo/vigente ni qué campos puede actualizar el cliente. | UX, datos y riesgo | Canales P3 | Negocio / Riesgos |
| D-005 | SLA | No existe SLA por etapa ni tiempos objetivo de integración. | Arquitectura, operación y NFR cuantitativos | Evidencias iniciales | Negocio / Operaciones |
| D-006 | Integraciones | No están definidos contratos, interfaces, protocolos ni capacidades de los sistemas internos/externos. | Diseño de solución | Riesgos P8, CEO P7 | Arquitectura |
| D-007 | Desembolso | No está especificada la integración ni el contrato con el core/sistema de desembolso. | Cierre end-to-end | CEO P7/P9, Canales P2 | Operaciones / Arquitectura |
| D-008 | Autenticación | No se define mecanismo técnico de autenticación para cliente, asesor y contact center. | Seguridad y continuidad omnicanal | Canales P4/P9 | Seguridad |
| D-009 | Autorización | No están definidos roles/permisos detallados ni matriz de acceso. | Segregación y mínimo privilegio | Riesgos P7/P10, Canales P4/P9 | Seguridad / Riesgos |
| D-010 | Documentos | No están definidos tipos, formatos, tamaños, almacenamiento, versionado ni retención. | Implementación de carga y gestión documental | Riesgos P6, Canales P2 | Operaciones / Datos |
| D-011 | IA | No se define modelo, proveedor, umbral de confianza, monitoreo, responsable ni límites operativos. | Funcionalidad y gobierno de IA | CEO P8, Riesgos P9 | Riesgos / Seguridad / Arquitectura |
| D-012 | Reglas | No están documentadas las reglas concretas de elegibilidad, campañas, apetito de riesgo y vigencias. | Motor de decisión | Riesgos P3/P4 | Riesgos |
| D-013 | Excepciones | No están definidos niveles de riesgo ni matriz de aprobación de supervisor. | Flujo de excepción | Riesgos P7 | Riesgos |
| D-014 | Rechazo | Se sabe que existen razones internas, pero no se define catálogo ni traducción al cliente. | UX y cumplimiento | Riesgos P4, Canales P5 | Riesgos / Canales |
| D-015 | Contrato | No se define mecanismo de aceptación/firma, versionado ni evidencia. | Desembolso | Canales P2 | Legal / Operaciones |
| D-016 | Notificaciones | No se definen preferencias, reintentos, plantillas, horarios o proveedor. | Integración y operación | Canales P6 | Canales |
| D-017 | Estados | Se identifican estados visibles, pero no se define máquina de estados completa, transiciones, estados internos ni eventos que las disparan. | Workflow | Canales P5 | Canales / Riesgos |
| D-018 | Síncrono/asíncrono | Las entrevistas no clasifican formalmente las etapas e integraciones como síncronas o asíncronas. | Arquitectura de integración | Reto del equipo | Arquitectura |
| D-019 | Datos maestros vs transaccionales | Las entrevistas solicitan distinguirlos, pero no indican qué sistema será maestro de cada dato. | Modelo de datos e integración | Reto del equipo | Datos / Arquitectura |
| D-020 | Modelo físico | No hay información suficiente para definir tablas, tipos, claves, índices o motor de base de datos. | Diseño físico | Todas | Arquitectura / Datos |
| D-021 | Seguridad técnica | Se exige protección de datos, pero no se definen cifrado, gestión de claves, secretos, mascaramiento ni controles técnicos. | NFR de seguridad | CEO P7, Riesgos P10 | Seguridad |
| D-022 | Disponibilidad | Se exige disponibilidad en campañas, pero no se proporciona objetivo porcentual ni RTO/RPO. | Infraestructura/DevOps | Riesgos P10 | DevOps |
| D-023 | Rendimiento | Se conoce volumen normal (8,000 simulaciones/día y 1,500 solicitudes/día) y pico de 5x, pero no se define latencia objetivo. | Capacidad | Canales P8 | Arquitectura / DevOps |
| D-024 | Analítica | Se enumeran eventos, pero no se define catálogo, almacenamiento, retención ni plataforma analítica. | Observabilidad de negocio | Canales P10 | Datos / Canales |
| D-025 | Acceso asesor/contact center | Se exige acceso autorizado, pero no se define qué información puede visualizar cada rol. | Seguridad y privacidad | Canales P4/P9 | Seguridad / Negocio |
| D-026 | Cumplimiento | CEO menciona cumplimiento y Riesgos menciona regulación, pero no se especifican obligaciones regulatorias concretas aplicables al flujo. | Reglas y controles | CEO P2/P8, Riesgos P3 | Cumplimiento |
| D-027 | MVP | Se define foco en clientes existentes con ingresos recurrentes y campaña de abono de sueldo, pero no se especifican criterios formales de inclusión/exclusión. | Alcance | CEO P5/P9 | Producto / Riesgos |
| D-028 | Métricas | Se identifican conversión, tiempo medio, abandono, mora temprana e intervención manual, pero no se definen fórmulas, fuentes ni metas. | Medición de éxito | CEO P4 | Producto / Datos |

## Discrepancias críticas para cerrar antes de desarrollo

**Críticas:** D-001, D-003, D-006, D-007, D-008, D-009, D-011, D-012, D-013, D-015, D-017, D-020, D-021, D-026.

## Lo que NO se debe asumir

No debe asumirse como requisito aprobado:

- proveedor de identidad;
- proveedor de fuentes externas;
- proveedor de OCR/IA;
- motor de reglas concreto;
- base de datos concreta;
- arquitectura de microservicios;
- API REST;
- Kafka/event broker;
- nube;
- JWT/OAuth;
- cifrado AES;
- Kubernetes;
- SLA numérico;
- políticas de retención;
- reglas de deduplicación.

Estos elementos pueden ser alternativas de diseño posteriormente, pero no están sustentados por las entrevistas.

## Estado de cierre

La especificación funcional inicial puede considerarse **trazable a entrevistas**, pero la especificación técnica y el modelo físico permanecen **abiertos** hasta resolver las discrepancias críticas.
