# 04. Arquitectura preliminar

> Diseño conceptual para orientar SDD. No es todavía un diseño físico ni una selección tecnológica aprobada.

## Principios
1. La solicitud es el agregado transaccional central y debe tener identificador único.
2. La decisión crediticia pertenece a políticas controladas y auditables, no a IA generativa.
3. Las reglas deben poder cambiar sin reconstruir toda la aplicación; el mecanismo concreto queda por decidir.
4. Los sistemas legados se integran detrás de capacidades/adaptadores para evitar dependencia de cambios simultáneos.
5. Los procesos que puedan tardar o fallar externamente deben preservar el estado de la solicitud y permitir reintento seguro.
6. Los datos usados para una decisión deben conservar una representación histórica inmutable o equivalente.

## Capacidades lógicas
```text
Canales (App / Web / Agencia / Contact Center)
              |
       Experiencia de solicitud
              |
       Orquestación de solicitud
       /       |        \
  Identidad  Documentos  Estado/Notificaciones
       \       |        /
        Datos y consultas
              |
      Evaluación crediticia
       /              \
 Motor de reglas      Revisión manual
       |                    |
       +---- Decisión ------+
              |
       Aceptación / Contrato
              |
          Desembolso
```

## Componentes/capacidades que deben existir conceptualmente
- Gestión de solicitud.
- Identidad/autenticación y autorización.
- Gestión documental.
- Orquestación de consultas internas/externas.
- Motor de reglas/políticas.
- Evaluación y decisión.
- Bandeja de revisión manual.
- Gestión de excepciones.
- Trazabilidad/auditoría.
- Notificaciones.
- Contrato/aceptación.
- Desembolso.
- Analítica de eventos.
- Capacidad de IA asistida para documentos y resumen.

## Síncrono vs asíncrono — propuesta a validar
**Potencialmente síncrono:** validaciones de formulario, recuperación de solicitud, confirmaciones y consultas que tengan respuesta inmediata.

**Potencialmente asíncrono:** consultas externas lentas, procesamiento documental, tareas de revisión, notificaciones y cualquier integración cuya respuesta pueda producir timeout.

La entrevista no define contratos de integración ni SLA; por tanto, esta separación es una decisión arquitectónica preliminar, no un requisito cerrado.

## Modelo de estados
```text
Borrador
  -> Información pendiente
  -> En evaluación
  -> Requiere documento / Requiere validación
  -> Aprobado / No aprobado
  -> Pendiente de aceptación
  -> Listo para desembolso
  -> Desembolsado

Cualquier etapa aplicable -> Cancelado
```

Los estados exactos de transición, eventos que los disparan y transiciones reversibles deben validarse.

## Auditoría
Una decisión debe poder reconstruirse con: datos usados, fuente, fecha/hora, versión de reglas, score, excepciones y usuarios intervinientes. La arquitectura debe impedir que cambios posteriores en datos operativos alteren la evidencia histórica.

## IA
Usos permitidos por entrevista: extracción de campos documentales, detección de inconsistencias, orientación al cliente y resumen para analista. Reglas: conservar documento origen y confianza; revisión humana bajo umbral acordado; no delegar decisión crediticia a IA.

## Temas técnicos aún no decididos
- Tecnología y contrato del motor de reglas.
- Mecanismo de persistencia y versionado de decisiones.
- Protocolo de integración con core y fuentes externas.
- Mecanismo de autenticación/autorización por canal.
- Almacenamiento documental.
- Bus/event broker, si corresponde.
- Estrategia de observabilidad.
- Retención y borrado.
- SLA, RTO/RPO y límites de concurrencia.
