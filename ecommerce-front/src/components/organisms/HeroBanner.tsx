import Button from '../atoms/Button'

export default function HeroBanner() {
  return (
    <section className="hero-banner">
      <div className="hero-copy">
        <span className="eyebrow">Productos destacados</span>
        <h1>Compra inteligente para tu negocio y tu hogar</h1>
        <p>
          Ofrecemos soluciones útiles, herramientas y productos de calidad para pymes,
          equipos y clientes finales.
        </p>
        <div className="hero-actions">
          <Button variant="primary">Ver catálogo</Button>
          <Button variant="secondary">Contactar</Button>
        </div>
      </div>

      <div className="hero-panel">
        <div className="mini-card">
          <span>🏷️</span>
          <div>
            <strong>Promociones</strong>
            <span>Hasta un 30% off</span>
          </div>
        </div>
        <div className="mini-card">
          <span>📦</span>
          <div>
            <strong>Envíos</strong>
            <span>Rápidos y seguros</span>
          </div>
        </div>
      </div>
    </section>
  )
}
