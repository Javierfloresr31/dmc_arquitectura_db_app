# Máquina de Estados — Siniestro Fácil

## 1. Fuente

Los estados siguientes derivan de la entrevista de Operaciones. Se formalizan como estados de negocio; los subestados internos no se exponen necesariamente al cliente. fileciteturn51file13

## 2. Estados

```text
REPORTADO
  -> VALIDANDO_COBERTURA
  -> ASISTENCIA_COORDINADA
  -> EVIDENCIA_PENDIENTE
  -> EN_EVALUACION
  -> INSPECCION_PROGRAMADA
  -> PRESUPUESTO_RECIBIDO
  -> AUTORIZADO
  -> EN_REPARACION
  -> LISTO_PARA_ENTREGA
  -> INDEMNIZADO
  -> CERRADO
```

Estados de excepción definidos por Operaciones:
- OBSERVADO;
- RECHAZADO.

## 3. Transiciones iniciales

| Origen | Evento | Destino | Condición conceptual |
|---|---|---|---|
| REPORTADO | iniciar validación | VALIDANDO_COBERTURA | expediente creado |
| VALIDANDO_COBERTURA | cobertura válida | ASISTENCIA_COORDINADA | aplica asistencia |
| VALIDANDO_COBERTURA | cobertura válida sin asistencia | EVIDENCIA_PENDIENTE | no requiere asistencia |
| VALIDANDO_COBERTURA | cobertura no válida | RECHAZADO | decisión de cobertura |
| ASISTENCIA_COORDINADA | asistencia gestionada | EVIDENCIA_PENDIENTE | asistencia coordinada |
| EVIDENCIA_PENDIENTE | evidencia mínima recibida | EN_EVALUACION | requisitos cumplidos |
| EN_EVALUACION | requiere inspección | INSPECCION_PROGRAMADA | evaluación determina necesidad |
| EN_EVALUACION | puede presupuestar | PRESUPUESTO_RECIBIDO | flujo aplicable |
| INSPECCION_PROGRAMADA | inspección completada | EN_EVALUACION | resultado disponible |
| EN_EVALUACION | presupuesto recibido | PRESUPUESTO_RECIBIDO | presupuesto válido |
| PRESUPUESTO_RECIBIDO | observado | OBSERVADO | requiere corrección |
| PRESUPUESTO_RECIBIDO | autorizado | AUTORIZADO | aprobación registrada |
| AUTORIZADO | iniciar reparación | EN_REPARACION | orden vigente |
| EN_REPARACION | reparación terminada | LISTO_PARA_ENTREGA | resultado registrado |
| AUTORIZADO | pago/indemnización | INDEMNIZADO | según modalidad |
| INDEMNIZADO | cierre | CERRADO | condiciones de cierre |
| LISTO_PARA_ENTREGA | cierre | CERRADO | condiciones de cierre |

## 4. Reglas

- Una transición inválida debe rechazarse.
- Cada transición debe conservar actor, fecha, evento y correlación.
- El historial es append-only desde el punto de vista funcional.
- Una reasignación no elimina el historial anterior.
- `OBSERVADO` y `RECHAZADO` requieren motivo/justificación en el flujo aplicable.

## 5. Antifraude

Una alerta no implica automáticamente `RECHAZADO`. Las entrevistas establecen que algunas reglas críticas pueden detener temporalmente un pago y otras solo aumentar prioridad; la política debe ser configurable y versionada. fileciteturn51file9

## 6. Preguntas abiertas

Antes de cerrar la máquina de estados al 100% deben definirse:
- eventos exactos que producen cada transición;
- quién puede ejecutar cada transición;
- estados de excepción adicionales;
- reglas de reapertura de un expediente cerrado;
- comportamiento ante cobertura pendiente;
- interacción exacta entre alerta antifraude y pago.

Estas decisiones no se inventan en esta especificación.