# Crédito Ágil 360 — Matriz de Trazabilidad

## Leyenda de origen

- CEO-Pn = Entrevista CEO, pregunta n.
- RIESGOS-Pn = Entrevista Riesgos de Crédito, pregunta n.
- CANALES-Pn = Entrevista Canales Digitales, pregunta n.

## Trazabilidad principal

| Origen | Historia(s) | RF | RNF | CU / proceso |
|---|---|---|---|---|
| CEO-P1 | HU-001 | RF-001 | — | CU-001 |
| CEO-P3 | HU-006 | RF-021/RF-022 | — | CU-005 |
| CEO-P4 | HU-021 | RF-030 | RNF-018 | CU-009 |
| CEO-P5 | HU-022 | — | — | Alcance MVP |
| CEO-P6 | HU-002/HU-006 | RF-002/RF-003/RF-004/RF-021 | — | CU-001/CU-005 |
| CEO-P7 | HU-016/HU-017 | RF-011/RF-017 | RNF-003/RNF-004/RNF-008/RNF-009/RNF-012 | CU-002/CU-003 |
| CEO-P8 | HU-020 | RF-032/RF-034 | RNF-012/RNF-013/RNF-014 | CU-004 |
| CEO-P9 | HU-022 | — | — | Alcance MVP |
| CEO-P10 | HU-016/HU-035 | RF-011/RF-035 | RNF-020 | CU-002 |
| RIESGOS-P1 | HU-013/HU-016 | RF-008/RF-009/RF-011 | RNF-001 | CU-002 |
| RIESGOS-P2 | HU-016 | RF-009/RF-010 | — | CU-002 |
| RIESGOS-P3 | HU-016 | RF-011 | RNF-012 | CU-002 |
| RIESGOS-P4 | HU-016/HU-017 | RF-012/RF-013/RF-014 | RNF-012 | CU-002 |
| RIESGOS-P5 | HU-017 | RF-017/RF-018 | RNF-001/RNF-002 | CU-002 |
| RIESGOS-P6 | HU-005/HU-013 | RF-007/RF-015 | — | CU-002/CU-003 |
| RIESGOS-P7 | HU-014/HU-015 | RF-016 | RNF-003 | CU-003 |
| RIESGOS-P8 | HU-018 | RF-019 | RNF-010 | CU-008 |
| RIESGOS-P9 | HU-020 | RF-032/RF-033 | RNF-014 | CU-004 |
| RIESGOS-P10 | HU-017/HU-018/HU-019 | RF-017/RF-019/RF-020 | RNF-001/RNF-003/RNF-006/RNF-010/RNF-011 | CU-002/CU-008 |
| CANALES-P1 | HU-001/HU-002 | RF-001/RF-002 | — | CU-001 |
| CANALES-P2 | HU-004/HU-005/HU-008/HU-009 | RF-006/RF-007/RF-026/RF-027 | — | CU-001/CU-006 |
| CANALES-P3 | HU-003 | RF-005 | — | CU-001 |
| CANALES-P4 | HU-002/HU-011 | RF-002/RF-003/RF-028 | — | CU-001/CU-005 |
| CANALES-P5 | HU-006 | RF-021/RF-022 | RNF-016 | CU-005 |
| CANALES-P6 | HU-007/HU-009 | RF-023/RF-024/RF-025 | RNF-005 | CU-006/CU-007 |
| CANALES-P7 | HU-010/HU-018/HU-019 | RF-019/RF-020 | RNF-010/RNF-011/RNF-017 | CU-008 |
| CANALES-P8 | — | — | RNF-007 | Capacidad |
| CANALES-P9 | HU-011/HU-012 | RF-028/RF-029 | RNF-015/RNF-016 | CU-005 |
| CANALES-P10 | HU-021 | RF-030/RF-031 | RNF-018/RNF-019 | CU-009 |

## Validación de cobertura

- Todas las historias tienen al menos un origen de entrevista.
- Todos los RF tienen origen de entrevista.
- Todos los RNF tienen origen de entrevista.
- Todos los CU derivan de una o más historias/requisitos con origen.
- Los modelos de datos no deben interpretarse como requisitos adicionales; se limitan a objetos mencionados por las entrevistas.

## Elementos sin trazabilidad cerrada

No se agregaron elementos de diseño que no puedan rastrearse. Los siguientes temas permanecen como discrepancias/vacíos: fuentes externas exactas, retención, deduplicación, límites de actualización, SLA, autenticación/autorización detallada, integración de desembolso, mecanismo de aceptación/firma, parámetros de IA y modelo físico.
