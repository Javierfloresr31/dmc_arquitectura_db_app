# Decisiones GCP y FinOps — Siniestro Fácil

## 1. Objetivo

Registrar decisiones técnicas para cerrar las preguntas de infraestructura, retención de evidencias, seguridad y gobierno de costos sin inventar requisitos de negocio.

## 2. Retención de documentos — decisión

**Decisión:** los documentos y evidencias binarias se almacenarán en **Google Cloud Storage**.

La Specification establece que el original de evidencia debe conservarse y que la retención inicial es de **10 años desde el cierre del siniestro**. El modelo físico no implementa esta regla mediante `CHECK` o trigger. La decisión se implementará en la capa de almacenamiento.

### Recomendación técnica

Usar un bucket dedicado para evidencias originales y habilitar protección de retención por objeto. La fecha `retain-until` debe corresponder a:

`fecha_cierre_siniestro + 10 años`

Esto es preferible a aplicar una retención uniforme de 10 años desde la carga cuando el requisito de negocio se expresa desde el cierre.

Google Cloud Storage soporta Object Retention Lock para retenciones por objeto y Bucket Lock para una retención uniforme por bucket. El bloqueo permanente es irreversible; por ello no debe activarse en producción hasta validar legal/compliance el periodo definitivo. 

### Protección recomendada

- Uniform bucket-level access.
- Prevención de acceso público.
- Object Retention Lock para originales cuando corresponda.
- Soft delete como protección adicional contra eliminación accidental/maliciosa.
- Object Versioning cuando el caso de uso requiera recuperación de versiones.
- Cloud Audit Logs para accesos sensibles.
- Cifrado administrado por Google como baseline; evaluar Cloud KMS/CMEK si seguridad/compliance lo exige.
- Separar originales de derivados cuando la política de retención y acceso sea diferente.

Las versiones derivadas no reemplazan al original; esto está soportado por las entrevistas y el modelo lógico.

## 3. FinOps — principios adoptados

Se adopta un enfoque FinOps desde el inicio, priorizando visibilidad, responsabilidad, optimización continua y prevención del cloud sprawl.

### Controles mínimos

- Etiquetar/organizar recursos por `application`, `environment`, `owner`, `cost-center` y `component` cuando el servicio lo permita.
- Crear Budget y alertas de presupuesto para el proyecto.
- Revisar costos por servicio: Cloud Run, Cloud SQL, Cloud Storage, Artifact Registry, Cloud Build, API Gateway y observabilidad.
- Medir unit economics cuando existan métricas suficientes, por ejemplo costo por siniestro procesado.
- Evitar sobreaprovisionamiento de Cloud Run y revisar CPU/memoria/concurrencia con datos reales.
- Revisar Cloud SQL sizing y almacenamiento periódicamente.
- Limitar retención de logs no críticos según necesidad operativa.
- Usar lifecycle de Cloud Storage para mover objetos poco consultados a clases de almacenamiento más económicas, sin violar la retención.
- Revisar imágenes antiguas de Artifact Registry y conservar únicamente las necesarias para rollback/auditoría.

## 4. Política recomendada de Cloud Storage

Para evidencias originales:

```text
Carga
  ↓
Standard
  ↓
menor frecuencia de acceso
  ↓
Nearline / Coldline / Archive según medición
  ↓
retención cumplida
  ↓
eliminación permitida por política
```

No se fija todavía una transición exacta por días porque la frecuencia real de consulta durante investigación y auditoría no está disponible. Se recomienda medir el patrón durante el piloto y luego establecer lifecycle rules.

Cloud Storage permite Object Lifecycle Management para cambiar de clase o eliminar objetos cuando cumplen condiciones; las reglas de lifecycle no eliminan un objeto antes de satisfacer una política de retención.

## 5. Firebase / RBAC — mejores prácticas adoptadas

- Firebase Authentication es la fuente de identidad.
- Firebase UID identifica al usuario autenticado.
- Custom Claims se utilizarán **solo para información de autorización**, principalmente roles/perfiles; no para almacenar datos de negocio o perfiles extensos.
- Los datos de perfil y negocio se mantendrán fuera de Custom Claims.
- El backend Cloud Run debe validar el token y aplicar autorización funcional.
- El cliente nunca determina por sí solo el rol efectivo ni el propietario de un expediente.
- La matriz de permisos se mantendrá como especificación y se probará por endpoint.

## 6. Decisiones recomendadas para preguntas abiertas

| Tema | Recomendación | Estado |
|---|---|---|
| Responsable de roles Firebase | Administración centralizada por función administrativa autorizada | RECOMENDADO / confirmar responsable |
| Claims | Solo roles/permisos mínimos; no datos de negocio | CERRADO TÉCNICAMENTE |
| Permisos por endpoint | RBAC + control de pertenencia al expediente | A DEFINIR POR ENDPOINT |
| Step-up authentication | Aplicarlo a operaciones de alto impacto, especialmente acciones sensibles de autorización/pago cuando el riesgo lo justifique | RECOMENDADO |
| Datos sensibles | Clasificar PII, evidencia original, información antifraude y datos de pago como sensibles | RECOMENDADO |
| Retención documentos | Cloud Storage; original protegido; 10 años desde cierre según Specification | CERRADO |
| Deduplicación de siniestros | Detectar candidatos y permitir decisión/confirmación; no fusionar automáticamente | RECOMENDADO y alineado con modelo |
| Pagos con respuesta desconocida | No repetir ciegamente; consultar estado/reconciliar antes de reintentar | CERRADO COMO PRINCIPIO TÉCNICO |
| Mensajería | Arquitectura asíncrona y reintentos controlados | RECOMENDADO |
| Mapas | Elegir proveedor por cobertura, SLA, precio, límites y requisitos de privacidad durante Sprint 6 | ABIERTO |
| Proveedor de pagos | Definir mediante contrato y capacidades de idempotencia/reconciliación | ABIERTO |
| Proveedor de pólizas | Adaptador aislado; contrato real antes de integración | ABIERTO |
| Talleres | Preferir API; si no existe, adaptador/canal controlado sin acoplar dominio | ABIERTO |
| Asistencia/grúa | Reintento + escalamiento + reasignación | CERRADO COMO PRINCIPIO |
| Eventos | Usar identificador único y reprocesamiento seguro | CERRADO COMO PRINCIPIO |

## 7. Criterio FinOps para decisiones futuras

Cada nuevo servicio GCP debe justificar:

1. necesidad funcional;
2. costo esperado;
3. alternativa evaluada;
4. impacto en operación;
5. impacto en seguridad;
6. estrategia de apagado/reducción cuando no sea necesario;
7. métrica que permita comprobar su valor.

No se adoptará un servicio administrado adicional solo por conveniencia técnica si no aporta valor funcional u operativo demostrable.

## 8. Fuentes externas de referencia

- Google Cloud Storage — Bucket Lock / retención.
- Google Cloud Storage — Object Lifecycle Management.
- Google Cloud Storage — protección, versionado y soft delete.
- Firebase Authentication — Custom Claims y control de acceso.
- Google Cloud FinOps — accountability, medición y optimización.

Estas fuentes respaldan las recomendaciones técnicas; las reglas de negocio de Siniestro Fácil continúan teniendo como fuente primaria las entrevistas y Specifications del repositorio.
