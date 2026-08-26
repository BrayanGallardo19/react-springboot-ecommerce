export default function WorkerDashboardPage() {
  return (
    <section className="panel-page">
      <h2>Panel del trabajador</h2>
      <div className="summary-grid">
        <div className="summary-card"><span>Pedidos hoy</span><strong>12</strong></div>
        <div className="summary-card"><span>Inventario</span><strong>94%</strong></div>
        <div className="summary-card"><span>Clientes atendidos</span><strong>25</strong></div>
      </div>
    </section>
  )
}
