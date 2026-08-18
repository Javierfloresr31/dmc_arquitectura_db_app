# 12. Preguntas abiertas

Estas preguntas bloquean decisiones que no pueden inventarse.

## Disponibilidad

1. ¿Cuál es la fórmula exacta por categoría, tienda, campaña y modalidad?
2. ¿Qué significa y cómo se parametriza el stock de seguridad?
3. ¿Qué estados se descuentan en cada modalidad?
4. ¿Qué sistema es autoridad para cada dato?
5. ¿Cuál es la tolerancia máxima de antigüedad?

## Reserva

6. ¿Cuándo exactamente se crea?
7. ¿Cuál es su duración?
8. ¿Qué ocurre si vence durante el pago?
9. ¿Cuándo puede trasladarse?
10. ¿Cuál es la clave de idempotencia?
11. ¿Qué garantía de concurrencia se requiere?

## Pago y pedido

12. ¿Qué sistema es autoridad del pago?
13. ¿Qué ocurre si pago aprueba y reserva falla?
14. ¿Qué ocurre si reserva confirma y pago falla?
15. ¿Qué ocurre si pedido se crea y una integración se retrasa?
16. ¿Cuáles son las compensaciones obligatorias?

## Multi-SKU

17. ¿Los productos salen juntos, separados o ambas opciones?
18. ¿Cuándo se permite dividir?
19. ¿Cómo se calcula la promesa con varias ubicaciones?
20. ¿Existe un máximo de ubicaciones por pedido?

## Asignación y promesa

21. ¿Cómo se ponderan los factores de selección?
22. ¿Qué restricciones aplican por producto?
23. ¿Qué tiendas venden y preparan por horario?
24. ¿Qué significa operativo/comercialmente "conveniente y rentable"?

## Tienda y retiro

25. ¿Cuál es el SLA de preparación?
26. ¿Cómo se prioriza la cola?
27. ¿Qué datos necesita el preparador?
28. ¿Qué evidencia requiere un faltante/daño?
29. ¿Qué permisos tiene cada rol?
30. ¿Cuál es el plazo de recojo?
31. ¿Qué ocurre al vencerlo?
32. ¿Cómo se valida al retirante?

## Inventario/eventos

33. ¿Qué sistema origina cada movimiento?
34. ¿Qué fuentes son tiempo real y cuáles lote?
35. ¿Cómo se ordenan eventos fuera de secuencia?
36. ¿Cómo se resuelven duplicados?
37. ¿Qué evidencia acompaña un ajuste?
38. ¿Cuál es la política para serializados y lotes?

## Degradación

39. ¿Qué operaciones siguen permitidas con una fuente retrasada?
40. ¿Cuándo se bloquea una venta por falta de frescura?
41. ¿Qué mensaje/alternativa recibe el cliente?
42. ¿Cómo se recupera coherencia?

## Rendimiento y continuidad

43. ¿Cuál es el máximo de consultas por segundo?
44. ¿Cuál es p95/p99 objetivo?
45. ¿Cuál es el SLO?
46. ¿Qué RTO/RPO se requiere?
47. ¿Cuánto se conservan auditoría y eventos?

## Seguridad

48. ¿Qué datos personales llegan a preparación?
49. ¿Qué políticas/regulación aplican?
50. ¿Qué autenticación/autorización se requiere?
51. ¿Qué evidencias requieren operaciones sensibles?

## IA

52. ¿Qué caso de IA es prioritario?
53. ¿Qué históricos están disponibles?
54. ¿Quién aprueba recomendaciones?
55. ¿Qué umbral o revisión humana se requiere?
56. ¿Qué métricas validan utilidad?

## Piloto

57. ¿Qué tiendas concretas de Lima participan?
58. ¿Qué SKU/categorías exactas?
59. ¿Qué sistemas se integran primero?
60. ¿Cuál es el criterio de expansión?
