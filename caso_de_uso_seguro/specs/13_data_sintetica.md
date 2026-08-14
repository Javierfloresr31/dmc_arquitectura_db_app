# Siniestro Fácil — Plan y especificación de data sintética

> Estado: PLAN. Los valores de ejemplo no representan datos reales.

## 1. Objetivo

Crear datos reproducibles que permitan desarrollar y probar la aplicación sin utilizar información real. La data debe cubrir tanto el camino feliz como excepciones, trazabilidad, evidencias y controles de riesgo.

## 2. Principios

- 100% sintética.
- Identificadores artificiales.
- No reutilizar datos personales reales.
- Integridad referencial.
- Capacidad de regeneración determinística.
- Escenarios etiquetados para pruebas.
- Separación entre datos de negocio y datos derivados de modelos/reglas.

## 3. Escenarios

| ID | Escenario | Objetivo |
|---|---|---|
| DS-001 | Siniestro simple | Validar flujo digital completo |
| DS-002 | Evidencia incompleta | Validar estado Evidencia pendiente |
| DS-003 | Asistencia | Validar solicitud, respuesta y seguimiento de grúa |
| DS-004 | Caso complejo | Validar asignación a ajustador |
| DS-005 | Presupuesto | Validar recepción, observación, versión y autorización |
| DS-006 | Alerta de riesgo | Validar alerta, explicación y revisión humana |
| DS-007 | Casos relacionados | Validar relación sin fusión de expedientes |
| DS-008 | Evidencia derivada | Validar original, hash y transformación |
| DS-009 | Proveedor sin respuesta | Validar reintento, escalamiento y reasignación |
| DS-010 | Pago duplicado | Validar control de duplicidad |
| DS-011 | Rechazo | Validar decisión y trazabilidad |
| DS-012 | Indemnizado/cerrado | Validar cierre del ciclo |

Los escenarios reflejan problemas y eventos expresamente descritos en las entrevistas. fileciteturn19file7L1-L1

## 4. Dataset mínimo

La generación deberá poder producir conjuntos relacionados para:

- pólizas;
- vehículos;
- siniestros;
- participantes;
- coberturas;
- evidencias;
- asistencias;
- inspecciones;
- talleres;
- presupuestos;
- autorizaciones;
- alertas;
- pagos;
- historial de siniestros;
- relaciones entre siniestros.

## 5. Calidad de datos

Se deben generar intencionalmente algunos escenarios de calidad mencionados por Fraude: nombres con distintas formas, placas con errores, ubicaciones aproximadas, documentos incompletos y registros duplicados. Debe conservarse el valor declarado y el valor normalizado por separado. fileciteturn19file1L1-L1

## 6. Datos de riesgo/IA

Los datos sintéticos de alertas deberán conservar:

- señal o señales originadoras;
- tipo;
- severidad;
- explicación;
- fecha;
- versión de regla/modelo;
- datos de entrada usados;
- resultado de revisión humana;
- justificación.

Esto permite probar el requisito de reproducibilidad. fileciteturn19file3L1-L1

## 7. Entregables posteriores

1. `data/seed/*.csv` o formato equivalente según tecnología.
2. Scripts de carga.
3. Dataset de prueba de integración.
4. Dataset para aceptación.
5. Catálogo de escenarios.
6. Validaciones de integridad.

Los formatos definitivos quedan pendientes de la tecnología seleccionada.
