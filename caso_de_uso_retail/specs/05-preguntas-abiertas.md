# 5. Preguntas abiertas y brechas de discovery

Las siguientes preguntas deben resolverse antes de convertir la especificación en diseño técnico cerrado.

## Disponibilidad

1. ¿Cuál es la fórmula exacta de disponibilidad por categoría, tienda, campaña y modalidad?
2. ¿Qué significa exactamente "stock de seguridad" y cómo se parametriza?
3. ¿Qué estados de inventario se descuentan de disponibilidad en cada modalidad?
4. ¿Qué dato es autoridad para stock físico, reservas, compromisos, tránsito y recepción?
5. ¿Cuál es la tolerancia máxima de antigüedad de una vista para cada tipo de consulta?

## Reservas

6. ¿En qué instante exacto se crea la reserva: inicio del pago, autorización de pago, confirmación de pedido u otro?
7. ¿Cuál es la duración exacta de una reserva?
8. ¿Qué ocurre cuando una reserva vence durante un pago en curso?
9. ¿Qué reglas permiten trasladar una reserva de una ubicación a otra?
10. ¿Cómo se identifica la idempotencia: por checkout, pedido, intento, combinación de SKU/cantidad u otra clave?
11. ¿Qué garantía de concurrencia se requiere por SKU, ubicación y, para serializados, por unidad?

## Pago y pedido

12. ¿Qué sistema es autoridad del estado del pago?
13. ¿Qué ocurre si el pago es aprobado pero la reserva no puede confirmarse?
14. ¿Qué ocurre si la reserva queda confirmada pero el pago falla o expira?
15. ¿Qué ocurre si el pedido se crea pero la integración posterior se retrasa?
16. ¿Cuáles son las compensaciones obligatorias en cada fallo?

## Pedido multi-SKU

17. ¿Un carrito con varios productos debe salir junto, dividido por ubicación o permitir ambas alternativas?
18. ¿Cuándo se considera aceptable dividir un pedido?
19. ¿Cómo se calcula una promesa cuando las líneas salen de distintas ubicaciones?
20. ¿Existe un límite operativo para la cantidad de ubicaciones de un pedido?

## Asignación y promesa

21. ¿Cómo se ponderan disponibilidad, distancia, capacidad, horario, costo, prioridad, fecha prometida y restricciones?
22. ¿Qué restricciones específicas aplican por producto?
23. ¿Qué tiendas pueden vender en línea y cuáles pueden preparar durante cada horario?
24. ¿Qué algoritmo/regla define "conveniente y rentable"?

## Preparación y tienda

25. ¿Cuál es el SLA/tiempo objetivo de preparación por modalidad y tienda?
26. ¿Cómo se prioriza la cola?
27. ¿Qué información exacta necesita el preparador para validar un pedido?
28. ¿Qué evidencia se requiere para un faltante o daño?
29. ¿Qué permisos tienen preparador, supervisor, cajero y operador logístico?

## Retiro

30. ¿Cuál es el plazo de recojo?
31. ¿Qué ocurre exactamente al vencer el plazo?
32. ¿Qué mecanismos de validación del retirante son obligatorios?

## Inventario y eventos

33. ¿Qué sistemas actuales generan cada tipo de movimiento?
34. ¿Qué sistemas envían eventos en tiempo real y cuáles por lote?
35. ¿Cómo se ordenan eventos fuera de secuencia?
36. ¿Cómo se resuelven eventos duplicados?
37. ¿Qué evidencia debe acompañar un ajuste?
38. ¿Cuál es la política de inventario serializado y por lote?

## Modo degradado

39. ¿Qué operaciones siguen permitidas cuando la fuente de inventario está retrasada?
40. ¿Cuándo se debe bloquear una venta por falta de frescura?
41. ¿Qué mensaje o alternativa se presenta al cliente?
42. ¿Cómo se recupera la coherencia después de una degradación?

## Rendimiento y disponibilidad

43. ¿Cuál es el pico máximo de consultas por segundo que debe soportarse?
44. ¿Cuál es el objetivo de latencia p95/p99 para consulta y reserva?
45. ¿Cuál es el SLO de disponibilidad del servicio?
46. ¿Qué RTO/RPO se requiere?
47. ¿Cuánto tiempo deben conservarse los datos de auditoría y eventos?

## Seguridad y cumplimiento

48. ¿Qué datos personales llegan al flujo de preparación?
49. ¿Qué políticas internas y regulatorias aplican a esos datos?
50. ¿Qué autenticación y autorización se requieren para cada rol?
51. ¿Qué evidencias deben quedar para operaciones sensibles?

## IA

52. ¿Qué casos de uso de IA tienen prioridad en la primera etapa?
53. ¿Qué datos históricos están disponibles para entrenar/evaluar modelos?
54. ¿Quién aprueba una recomendación de redistribución?
55. ¿Qué umbral de confianza o mecanismo de revisión humana se requiere?
56. ¿Qué métricas determinan que una recomendación de IA es útil?

## Alcance del piloto

57. ¿Qué tiendas concretas de Lima participan?
58. ¿Qué SKU/categorías exactas forman parte del piloto de tecnología y pequeños electrodomésticos?
59. ¿Qué sistemas actuales se integran en la primera etapa?
60. ¿Cuál es el criterio para ampliar a otras categorías y ciudades?