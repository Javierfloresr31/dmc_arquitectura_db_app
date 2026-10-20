# Autovalidación — Siniestro Fácil frontend + BFF

## Entregables

| Elemento | Estado |
|---|---|
| Frontend React + Vite | Implementado en la rama de integración |
| Proxy BFF de mismo origen | Código agregado; requiere build y prueba |
| Contrato GET/POST | Adaptado a `/api/v1/siniestros` y `CrearSiniestroRequest` |
| Identidad de servicio | Requiere cuenta dedicada y permiso `roles/run.invoker` sobre backend |
| Acceso de usuarios de prueba | Requiere proteger frontend+BFF con IAP |
| Firebase Authentication | Fuera del alcance actual |
| Build de contenedor | Pendiente de verificación para la rama de integración |
| Despliegue y conectividad GCP | No ejecutados ni verificados |

## Criterios de aceptación
- [ ] `npm install` termina correctamente.
- [ ] `npm run build` termina con código 0.
- [ ] El modo demo no transmite datos al backend.
- [ ] El formulario envía el contrato real del backend.
- [ ] El BFF obtiene un ID token con la identidad de Cloud Run.
- [ ] El backend permanece privado.
- [ ] El frontend+BFF requiere autenticación mediante IAP.
- [ ] Las llamadas GET y POST funcionan de extremo a extremo.
- [ ] Los logs no muestran errores críticos ni datos sensibles.

## Resultado
La implementación está propuesta en el PR de integración, aún en borrador. No se declara finalizada hasta que CI pase y se verifique la conectividad real en GCP.