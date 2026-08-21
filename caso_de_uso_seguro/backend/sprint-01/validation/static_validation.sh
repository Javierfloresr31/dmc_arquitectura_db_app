#!/bin/bash
set -euo pipefail
ROOT="$(cd "$(dirname "$0")/.." && pwd)"; PASS=0; FAIL=0
check(){ if eval "$1" >/dev/null 2>&1; then echo "[PASS] $2"; PASS=$((PASS+1)); else echo "[FAIL] $2"; FAIL=$((FAIL+1)); fi; }
check "test -f '$ROOT/pom.xml'" 'pom.xml existe'
check "test -f '$ROOT/Dockerfile'" 'Dockerfile existe'
check "grep -q 'postgresql' '$ROOT/pom.xml'" 'driver PostgreSQL'
check "grep -q '@PostMapping' '$ROOT/src/main/java/pe/siniestrofacil/interfaces/rest/SiniestroController.java'" 'POST siniestros'
check "grep -q '@GetMapping' '$ROOT/src/main/java/pe/siniestrofacil/interfaces/rest/SiniestroController.java'" 'GET siniestros'
check "grep -q 'siniestro_facil.siniestro' '$ROOT/src/main/java/pe/siniestrofacil/infrastructure/persistence/JdbcSiniestroRepository.java'" 'tabla siniestro'
check "grep -q 'siniestro_estado_historial' '$ROOT/src/main/java/pe/siniestrofacil/infrastructure/persistence/JdbcSiniestroRepository.java'" 'historial'
check "grep -q 'auditoria' '$ROOT/src/main/java/pe/siniestrofacil/infrastructure/persistence/JdbcSiniestroRepository.java'" 'auditoria'
check "grep -q 'Idempotency-Key' '$ROOT/src/main/java/pe/siniestrofacil/interfaces/rest/SiniestroController.java'" 'Idempotency-Key'
check "! grep -Rqi 'firebase' '$ROOT/src/main/java'" 'Firebase excluido del MVP'
echo "PASS=$PASS FAIL=$FAIL"; test "$FAIL" -eq 0
