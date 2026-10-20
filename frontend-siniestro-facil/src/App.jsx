import { useEffect, useMemo, useState } from 'react';
import { AlertCircle, ArrowUpRight, CarFront, CheckCircle2, ClipboardList, FilePlus2, Search, ShieldCheck, Clock3, RefreshCw } from 'lucide-react';

const API_BASE_URL = (import.meta.env.VITE_API_BASE_URL || '').replace(/\/$/, '');
const DEMO_MODE = import.meta.env.VITE_DEMO_MODE !== 'false';
const demoClaims = [
  { id: 'SF-2026-00124', fecha: '2026-10-18', vehiculo: 'Toyota Corolla · ABC-123', tipo: 'Choque leve', estado: 'En evaluación', progreso: 3 },
  { id: 'SF-2026-00109', fecha: '2026-10-12', vehiculo: 'Kia Rio · BCD-456', tipo: 'Daño material', estado: 'Inspección programada', progreso: 2 },
  { id: 'SF-2026-00087', fecha: '2026-10-05', vehiculo: 'Hyundai Creta · CDE-789', tipo: 'Choque leve', estado: 'Cerrado', progreso: 5 }
];

const statusClass = (status = '') => status.toLowerCase().includes('cerrado') ? 'success' : status.toLowerCase().includes('observ') ? 'warning' : 'info';

const mapBackendClaim = (item) => ({
  id: `SF-${item.siniestroId ?? item.id ?? 'N/D'}`,
  numero: item.siniestroId,
  fecha: item.fecha ? String(item.fecha).slice(0, 10) : '',
  ubicacionAproximada: item.ubicacionAproximada || '',
  tipo: item.tipoEvento || 'Siniestro vehicular',
  estado: item.estado || 'Reportado',
  descripcion: item.danosAparentes || ''
});

export default function App() {
  const [claims, setClaims] = useState(demoClaims);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [query, setQuery] = useState('');
  const [showForm, setShowForm] = useState(false);
  const [form, setForm] = useState({ poliza: '', placa: '', fecha: '', ubicacionAproximada: '', tipo: 'Choque leve', descripcion: '' });
  const [notice, setNotice] = useState('');

  const isDemo = DEMO_MODE;

  async function loadClaims() {
    if (isDemo) return;
    setLoading(true);
    setError('');
    try {
      const response = await fetch(`${API_BASE_URL}/api/v1/siniestros`);
      if (!response.ok) throw new Error(`El backend respondió HTTP ${response.status}`);
      const data = await response.json();
      const items = Array.isArray(data) ? data : (data.items || []);
      setClaims(items.map(mapBackendClaim));
    } catch (e) {
      setError(`No se pudo consultar el backend: ${e.message}. Verifica la URL, CORS y disponibilidad del servicio.`);
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => { loadClaims(); }, []);

  const filteredClaims = useMemo(() => claims.filter(c =>
    [c.id, c.numero, c.vehiculo, c.tipo, c.estado].filter(Boolean).join(' ').toLowerCase().includes(query.toLowerCase())
  ), [claims, query]);

  const activeCount = claims.filter(c => !String(c.estado).toLowerCase().includes('cerrado')).length;
  const reviewCount = claims.filter(c => /evaluación|observado/i.test(c.estado || '')).length;

  function updateField(event) {
    setForm({ ...form, [event.target.name]: event.target.value });
  }

  async function submitClaim(event) {
    event.preventDefault();
    setNotice('');
    setError('');
    const claim = {
      vehiculo: `${form.vehiculo.trim()} · ${form.placa.trim().toUpperCase()}`,
      fecha: form.fecha,
      tipo: form.tipo,
      descripcion: form.descripcion.trim(),
      estado: 'Reportado',
      progreso: 1
    };
    if (isDemo) {
      setClaims([{ id: `SF-DEMO-${String(Date.now()).slice(-6)}`, fecha: form.fecha, vehiculo: form.placa.trim().toUpperCase(), tipo: form.tipo, estado: 'Reportado', progreso: 1 }, ...claims]);
      setNotice('Reporte creado en modo demostración. No se envió información al backend.');
      setShowForm(false);
      setForm({ poliza: '', placa: '', fecha: '', ubicacionAproximada: '', tipo: 'Choque leve', descripcion: '' });
      return;
    }
    try {
      const response = await fetch(`${API_BASE_URL}/api/v1/siniestros`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(claim)
      });
      if (!response.ok) throw new Error(`El backend respondió HTTP ${response.status}`);
      setNotice('El reporte fue enviado al backend.');
      await loadClaims();
      setShowForm(false);
      setForm({ vehiculo: '', placa: '', fecha: '', tipo: 'Choque leve', descripcion: '' });
    } catch (e) {
      setError(`No se pudo registrar el reporte: ${e.message}. Verifica el contrato de la API.`);
    }
  }

  return (
    <div className="app-shell">
      <aside className="sidebar">
        <div className="brand"><div className="brand-icon"><ShieldCheck size={23} /></div><div><strong>Siniestro Fácil</strong><span>Portal de gestión</span></div></div>
        <div className="side-label">MENÚ PRINCIPAL</div>
        <button className="nav-item selected"><ClipboardList size={18} /> Mis siniestros</button>
        <button className="nav-item" onClick={() => setShowForm(true)}><FilePlus2 size={18} /> Reportar siniestro</button>
        <div className="sidebar-bottom"><div className="help-icon">?</div><div><strong>¿Necesitas ayuda?</strong><span>Consulta con nuestro equipo de atención.</span></div></div>
      </aside>

      <main className="main-content">
        <header className="topbar"><div className="breadcrumb">Portal <span>/</span> <b>Mis siniestros</b></div><div className="user-chip"><div className="avatar">US</div><div><strong>Usuario de prueba</strong><span>Cliente</span></div></div></header>

        <section className="welcome">
          <div><p className="eyebrow">CENTRO DE ATENCIÓN</p><h1>Hola, bienvenido</h1><p className="subtitle">Consulta el estado de tus siniestros o registra un nuevo reporte.</p></div>
          <button className="primary-button" onClick={() => { setShowForm(true); setNotice(''); }}><FilePlus2 size={18} /> Reportar siniestro</button>
        </section>

        {isDemo && <div className="demo-banner"><AlertCircle size={17} /><span><b>Modo demostración.</b> Los datos son ficticios y no se envían al backend.</span></div>}
        {notice && <div className="notice success-notice"><CheckCircle2 size={18} />{notice}</div>}
        {error && <div className="notice error-notice"><AlertCircle size={18} />{error}</div>}

        <section className="stats-grid">
          <div className="stat-card"><div className="stat-icon green"><ClipboardList size={20}/></div><div><span>Total de siniestros</span><strong>{claims.length}</strong><small>En tu historial</small></div></div>
          <div className="stat-card"><div className="stat-icon amber"><Clock3 size={20}/></div><div><span>En proceso</span><strong>{activeCount}</strong><small>Pendientes de resolución</small></div></div>
          <div className="stat-card"><div className="stat-icon blue"><CarFront size={20}/></div><div><span>En evaluación</span><strong>{reviewCount}</strong><small>Revisión operativa</small></div></div>
        </section>

        <section className="claims-panel">
          <div className="panel-heading"><div><h2>Mis siniestros</h2><p>Revisa los reportes y su estado actual.</p></div><button className="icon-button" title="Actualizar" onClick={loadClaims} disabled={loading}><RefreshCw size={17} className={loading ? 'spin' : ''}/></button></div>
          <div className="table-toolbar"><div className="search-box"><Search size={17}/><input value={query} onChange={e => setQuery(e.target.value)} placeholder="Buscar por número, vehículo o estado" aria-label="Buscar siniestros"/></div><span>{filteredClaims.length} resultados</span></div>
          <div className="claims-list">
            {filteredClaims.map((claim, index) => <article className="claim-row" key={claim.id || claim.numero || index}>
              <div className="claim-car"><CarFront size={21}/></div>
              <div className="claim-main"><div className="claim-title">{claim.id || claim.numero || 'Siniestro'}</div><div className="claim-meta">{claim.vehiculo || (claim.ubicacionAproximada ? `Ubicación: ${claim.ubicacionAproximada}` : 'Vehículo/ubicación no incluida en respuesta')} <span>·</span> {claim.fecha || 'Fecha no disponible'}</div><div className="claim-type">{claim.tipo || 'Siniestro vehicular'}</div></div>
              <div className="claim-status"><span className={`status-pill ${statusClass(claim.estado)}`}>{claim.estado || 'Estado pendiente'}</span><small>Estado actual</small></div>
              <button className="details-button" onClick={() => setNotice(`Resumen de ${claim.id || claim.numero}: estado ${claim.estado || 'pendiente'}. La vista detallada se conectará cuando se confirme el endpoint de detalle.`)}>Ver detalle <ArrowUpRight size={15}/></button>
            </article>)}
            {!filteredClaims.length && <div className="empty-state"><ClipboardList size={30}/><strong>No se encontraron siniestros</strong><span>Prueba con otro término o registra un reporte nuevo.</span></div>}
          </div>
          <div className="panel-footer">Los estados mostrados dependen de la información disponible en el sistema.</div>
        </section>
        <footer className="page-footer">Siniestro Fácil · Prototipo frontend · Información de demostración</footer>
      </main>

      {showForm && <div className="modal-backdrop" role="presentation" onMouseDown={e => { if (e.target === e.currentTarget) setShowForm(false); }}>
        <section className="modal" role="dialog" aria-modal="true" aria-labelledby="form-title">
          <div className="modal-header"><div><p className="eyebrow">NUEVO REPORTE</p><h2 id="form-title">Reportar siniestro</h2><p>Ingresa los datos básicos del evento.</p></div><button className="close-button" onClick={() => setShowForm(false)} aria-label="Cerrar">×</button></div>
          <form onSubmit={submitClaim}>
            <div className="form-grid"><label>Número de póliza<input name="poliza" value={form.poliza} onChange={updateField} required placeholder="Número de póliza"/></label><label>Placa del vehículo<input name="placa" value={form.placa} onChange={updateField} required placeholder="ABC-123" maxLength="10"/></label></div>
            <div className="form-grid"><label>Fecha del siniestro<input name="fecha" type="date" value={form.fecha} onChange={updateField} required max={new Date().toISOString().slice(0,10)}/></label><label>Ubicación aproximada<input name="ubicacionAproximada" value={form.ubicacionAproximada} onChange={updateField} required placeholder="Distrito, calle o referencia"/></label></div>
            <label>Tipo de evento<select name="tipo" value={form.tipo} onChange={updateField}><option>Choque leve</option><option>Daño material</option><option>Robo de accesorios</option><option>Rotura de lunas</option><option>Otro</option></select></label>
            <label>Descripción breve<textarea name="descripcion" value={form.descripcion} onChange={updateField} rows="3" placeholder="Describe brevemente lo ocurrido."/></label>
            <div className="form-note"><AlertCircle size={16}/> Prototipo: no incluyas información sensible real. La cobertura y autorización aún deben validarse en el backend.</div>
            <div className="modal-actions"><button type="button" className="secondary-button" onClick={() => setShowForm(false)}>Cancelar</button><button type="submit" className="primary-button">Registrar reporte</button></div>
          </form>
        </section>
      </div>}
    </div>
  );
}