import ProductCard from '../molecules/ProductCard'

const products = [
  { name: 'Auriculares Pro', category: 'Tecnología', price: 89990, badge: 'Oferta', tone: 'success' as const },
  { name: 'Silla Ergonómica', category: 'Oficina', price: 159990, badge: 'Nuevo', tone: 'info' as const },
  { name: 'Mouse Ergonómico', category: 'Accesorios', price: 29990, badge: 'Nuevo', tone: 'info' as const },
  { name: 'Cargador USB C', category: 'Tecnología', price: 14990, badge: 'Stock', tone: 'success' as const },
  { name: 'Lámpara LED', category: 'Hogar', price: 34990, badge: 'Top', tone: 'warning' as const },
  { name: 'Teclado Mecánico', category: 'Tecnología', price: 69990, badge: 'Oferta', tone: 'success' as const },
]

export default function ProductGridSection() {
  return (
    <section className="catalog-content">
      <div className="section-header">
        <h2>Catálogo</h2>
        <span>32 productos</span>
      </div>
      <div className="product-grid catalog-grid">
        {products.map((product) => (
          <ProductCard key={product.name} {...product} />
        ))}
      </div>
    </section>
  )
}
