# Stock Único — NovaRetail

**Fuente:** entrevistas del caso `NovaRetail: Stock Único`  
**Método:** PROMPT ATLAS v2.0  
**Regla:** contenido sustentado exclusivamente en las entrevistas; los vacíos se mantienen como pendientes.

## Documentos

| # | Archivo | Propósito |
|---|---|---|
| 01 | `01_descubrimiento_y_brechas.md` | Actores, problemas, brechas, objetos y eventos |
| 02 | `02_historias_usuario.md` | Historias de usuario trazables |
| 03 | `03_criterios_aceptacion.md` | Criterios verificables |
| 04 | `04_requerimientos_funcionales.md` | Requerimientos funcionales |
| 05 | `05_requerimientos_no_funcionales.md` | Requerimientos de calidad |
| 06 | `06_reglas_negocio.md` | Reglas explícitas y pendientes |
| 07 | `07_casos_uso.md` | Casos de uso |
| 08 | `08_modelo_dominio.md` | Modelo conceptual de dominio |
| 09 | `09_modelos_datos_mermaid.md` | Modelos conceptual, lógico y físico |
| 10 | `10_integraciones.md` | Sistemas e intercambios identificados |
| 11 | `11_arquitectura_solucion.md` | Necesidades y decisiones arquitectónicas |
| 12 | `12_arquitectura_ia.md` | Capacidades IA expresadas |
| 13 | `13_matriz_trazabilidad.md` | Trazabilidad extremo a extremo |
| 14 | `14_matriz_cobertura.md` | Control de cobertura |
| 15 | `15_discrepancias.md` | Vacíos, contradicciones y decisiones pendientes |
| 16 | `16_autovalidacion.md` | Resultado de la autovalidación |

## Estado

La especificación inicial puede construirse, pero existen decisiones críticas pendientes sobre disponibilidad, autoridad de datos, reservas, partición de pedidos, compensación, consistencia, serialización y modo degradado.

## Alcance inicial confirmado

Tecnología y pequeños electrodomésticos en Lima.

## Fuente de incertidumbres

Las entrevistas enumeran expresamente las incertidumbres sobre fórmula de disponibilidad, duración de reservas, partición de pedidos, autoridad por sistema y compensación ante fallas de pago; además solicitan decisiones sobre consistencia, serialización, transacciones distribuidas, modo degradado y analítica. [CONFIRMADO]
