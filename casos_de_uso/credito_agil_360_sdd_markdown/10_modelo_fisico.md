# Crédito Ágil 360 — Modelo Físico

## Estado

**NO APROBADO / BLOQUEADO POR INFORMACIÓN DE ENTREVISTAS.**

Las entrevistas permiten identificar objetos de negocio, pero no proporcionan información suficiente para definir un modelo físico sin inventar decisiones técnicas o de datos.

## Información que sí está soportada

Los objetos mencionados son: cliente, solicitud, oferta, simulación, documento, evaluación, decisión, excepción, contrato, desembolso, notificación, evento, fuente de información, reglas/políticas, score y usuarios intervinientes.

## Información que NO está definida

- Motor de base de datos.
- Esquemas físicos.
- Tablas definitivas.
- Columnas completas.
- Tipos de datos.
- Longitudes y precisiones.
- Claves primarias.
- Claves foráneas.
- Índices.
- Particionamiento.
- Estrategia de versionado físico.
- Cifrado específico.
- Política de retención.
- Archivado.
- Volúmenes de datos.
- Estrategia de alta disponibilidad de base de datos.

Definir cualquiera de estos elementos sería inventar información no proporcionada.

## Diagrama ER físico provisional

El siguiente diagrama **no representa un DDL aprobado**. Solo muestra los objetos candidatos identificados en las entrevistas.

```mermaid
erDiagram
    CLIENTE {
        PENDIENTE datos_cliente
    }
    SOLICITUD {
        PENDIENTE identificador_unico
        PENDIENTE estado
        PENDIENTE canal_origen
    }
    OFERTA {
        PENDIENTE datos_oferta
    }
    SIMULACION {
        PENDIENTE datos_simulacion
    }
    DOCUMENTO {
        PENDIENTE origen_documento
        PENDIENTE confianza_extraccion
    }
    EVALUACION {
        PENDIENTE fecha_hora
        PENDIENTE version_reglas
        PENDIENTE score
    }
    DECISION {
        PENDIENTE resultado
        PENDIENTE razones_internas
        PENDIENTE vigencia
    }
    EXCEPCION {
        PENDIENTE justificacion
    }
    CONTRATO {
        PENDIENTE datos_contrato
    }
    DESEMBOLSO {
        PENDIENTE datos_desembolso
    }
    NOTIFICACION {
        PENDIENTE canal
    }
    EVENTO {
        PENDIENTE tipo_evento
    }
    FUENTE_INFORMACION {
        PENDIENTE fuente
    }
    REGLA_POLITICA {
        PENDIENTE version
        PENDIENTE vigencia
    }
    SCORE {
        PENDIENTE valor
    }
    USUARIO {
        PENDIENTE identificador_usuario
    }

    CLIENTE ||--o{ SOLICITUD : inicia
    OFERTA ||--o{ SOLICITUD : origina
    SIMULACION ||--o{ SOLICITUD : origina
    SOLICITUD ||--o{ DOCUMENTO : contiene
    SOLICITUD ||--o{ EVALUACION : tiene
    EVALUACION }o--o{ FUENTE_INFORMACION : consulta
    EVALUACION }o--o{ REGLA_POLITICA : aplica
    EVALUACION ||--|| DECISION : produce
    DECISION ||--o{ EXCEPCION : requiere
    EXCEPCION }o--|| USUARIO : autoriza
    SOLICITUD ||--o| CONTRATO : genera
    SOLICITUD ||--o| DESEMBOLSO : genera
    SOLICITUD ||--o{ NOTIFICACION : genera
    SOLICITUD ||--o{ EVENTO : registra
    DECISION }o--o{ SCORE : utiliza
    DECISION }o--o{ USUARIO : interviene
```

## Criterio de cierre

Este documento debe convertirse en modelo físico aprobado únicamente después de resolver las discrepancias listadas en `11_discrepancias.md`.
