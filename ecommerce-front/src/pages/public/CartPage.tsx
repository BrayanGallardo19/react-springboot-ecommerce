import { Link } from 'react-router-dom'
import { useCart } from '../../contexts/CartContext'

export default function CartPage() {
  const { items, removeItem, updateQuantity, total } = useCart()

  return (
    <section className="cart-page">
      <h1>Carrito</h1>
      {items.length === 0 ? (
        <div>
          <p>Tu carrito está vacío.</p>
          <Link to="/">Ir al catálogo</Link>
        </div>
      ) : (
        <div>
          <ul className="cart-list">
            {items.map((it) => (
              <li key={it.productId} className="cart-item">
                <div>
                  <strong>{it.name}</strong>
                  <div>${it.price.toFixed(0)} x {it.quantity}</div>
                </div>
                <div className="cart-actions">
                  <input type="number" min={1} value={it.quantity} onChange={(e) => updateQuantity(it.productId, Number(e.target.value))} />
                  <button onClick={() => removeItem(it.productId)}>Eliminar</button>
                </div>
              </li>
            ))}
          </ul>

          <div className="cart-summary">
            <div>Total: ${total().toFixed(0)}</div>
            <Link to="/checkout"><button>Ir a pagar</button></Link>
          </div>
        </div>
      )}
    </section>
  )
}
