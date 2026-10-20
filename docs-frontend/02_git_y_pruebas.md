# Tarea 2 — Git y pruebas

## Repositorio
Repositorio de trabajo: https://github.com/Javierfloresr31/dmc_arquitectura_db_app

## Rama de trabajo
`feat/siniestro-facil-frontend`

El frontend se encuentra bajo `frontend-siniestro-facil/` para aislarlo del resto del repositorio.

## Comandos para trabajar localmente
```bash
git clone https://github.com/Javierfloresr31/dmc_arquitectura_db_app.git
cd dmc_arquitectura_db_app
git checkout feat/siniestro-facil-frontend
cd frontend-siniestro-facil
npm install
npm run build
```

## Publicar cambios desde una copia local
```bash
git status
git add frontend-siniestro-facil docs-frontend
git commit -m "feat: add Siniestro Fácil frontend"
git push origin feat/siniestro-facil-frontend
```

## Validación
```bash
npm ci
npm run build
```
Si no existe `package-lock.json` todavía, ejecutar primero `npm install` para generarlo y luego usar `npm ci`.

## Checklist funcional manual
- [ ] Abrir el portal en modo demostración.
- [ ] Confirmar que se visualicen tres siniestros ficticios.
- [ ] Buscar por número y por vehículo.
- [ ] Abrir el formulario de reporte.
- [ ] Validar campos obligatorios.
- [ ] Registrar un reporte de demostración.
- [ ] Verificar que aparezca en el listado.
- [ ] Revisar vista móvil.
- [ ] Configurar URL backend válida y verificar GET/POST.
- [ ] Verificar CORS y autenticación cuando aplique.

## Resultado real de pruebas
No marcar la compilación o las pruebas como aprobadas hasta ejecutar los comandos en un entorno con Node/npm y confirmar el resultado.
