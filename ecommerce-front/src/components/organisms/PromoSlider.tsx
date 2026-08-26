import { useEffect, useState } from 'react'

const recommended = [
  { name: 'Mouse Ergonómico', category: 'Accesorios', price: 29990, badge: 'Nuevo', tone: 'info' as const },
  { name: 'Estación de trabajo', category: 'Oficina', price: 429990, badge: 'Top', tone: 'warning' as const },
  { name: 'Alfombra de ratón', category: 'Accesorios', price: 19990, badge: 'Oferta', tone: 'success' as const },
  { name: 'Cargador USB C', category: 'Tecnología', price: 14990, badge: 'Stock', tone: 'success' as const },
  { name: 'Monitor 27"', category: 'Tecnología', price: 249990, badge: 'Nuevo', tone: 'info' as const },
  { name: 'Cafetera', category: 'Hogar', price: 59990, badge: 'Oferta', tone: 'success' as const },
]

export default function PromoSlider() {
  const [currentIndex, setCurrentIndex] = useState(0)

  useEffect(() => {
    const timer = window.setInterval(() => {
      setCurrentIndex((previous) => (previous + 1) % recommended.length)
    }, 3000)

    return () => window.clearInterval(timer)
  }, [])

  const visibleProducts = [
    recommended[currentIndex],
    recommended[(currentIndex + 1) % recommended.length],
    recommended[(currentIndex + 2) % recommended.length],
  ]

  return (
    <section className="slider-strip">
      <div className="section-header">
        <h2>Productos recomendados</h2>
        <a href="/catalogo">Explorar</a>
      </div>

      <div className="promo-slider" aria-live="polite">
        {visibleProducts.map((product) => (
          <article key={`${product.name}-${product.price}`} className="mini-product promo-slide">
            <div className="mini-image" aria-hidden="true" />
            <strong>{product.name}</strong>
            <span>{new Intl.NumberFormat('es-CL', { style: 'currency', currency: 'CLP', maximumFractionDigits: 0 }).format(product.price)}</span>
          </article>
        ))}
      </div>
    </section>
  )
}
