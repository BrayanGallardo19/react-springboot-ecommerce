import { Link } from 'react-router-dom'
import HeroBanner from '../organisms/HeroBanner'
import FeaturedProductsSection from '../organisms/FeaturedProductsSection'
import PromoSlider from '../organisms/PromoSlider'
import type { ProductApi } from '../../types'

type StoreLayoutProps = {
  products?: ProductApi[]
}

export default function StoreLayout({ products = [] }: StoreLayoutProps) {
  return (
    <>
      <HeroBanner />
      <FeaturedProductsSection products={products} />
      <PromoSlider />
      <section className="info-grid">
        <div className="info-card">
          <h3>Gestión simple</h3>
          <p>Dashboard claro para ventas, stock y pedidos.</p>
        </div>
        <div className="info-card">
          <h3>Boleta digital</h3>
          <p>Emisión y seguimiento de documentos para clientes finales.</p>
        </div>
        <div className="info-card">
          <h3>Permisos flexibles</h3>
          <p>Control de accesos por rol para admin y trabajadores.</p>
        </div>
      </section>
      <div className="section-footer-link">
        <Link to="/catalogo">Explorar catálogo</Link>
      </div>
    </>
  )
}
