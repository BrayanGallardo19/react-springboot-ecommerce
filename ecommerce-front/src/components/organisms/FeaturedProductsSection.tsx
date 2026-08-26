import ProductCard from '../molecules/ProductCard'
import type { ProductApi } from '../../types'

type FeaturedProductsSectionProps = {
  products: ProductApi[]
}

export default function FeaturedProductsSection({ products }: FeaturedProductsSectionProps) {
  const visible = products.slice(0, 4)

  return (
    <section className="section-block">
      <div className="section-header">
        <h2>Destacados</h2>
        <a href="/catalogo">Ver todo</a>
      </div>
      <div className="product-grid">
        {visible.map((product) => (
          <ProductCard
            key={product.id}
            name={product.name}
            category={product.category?.name ?? 'General'}
            price={product.price}
            badge={product.featured ? 'Destacado' : 'Nuevo'}
            tone={product.featured ? 'success' : 'info'}
            imageUrl={product.imageUrl}
          />
        ))}
      </div>
    </section>
  )
}
