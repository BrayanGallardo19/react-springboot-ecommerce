export default function ClientDashboardPage() {
  return (
    <section className="panel-page">
      <h2>Mi cuenta</h2>
      <div className="summary-grid">
        <div className="summary-card"><span>Pedidos totales</span><strong>8</strong></div>
        <div className="summary-card"><span>Ultimo pago</span><strong>$89.990</strong></div>
        <div className="summary-card"><span>Boletas</span><strong>3</strong></div>
      </div>
    </section>
  )
}
