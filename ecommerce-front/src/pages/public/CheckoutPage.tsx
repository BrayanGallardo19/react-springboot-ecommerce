import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { useCart } from '../../contexts/CartContext'
import { createOrder, getOrderReceipt } from '../../lib/api'

export default function CheckoutPage() {
  const { items, clear, total } = useCart()
  const navigate = useNavigate()
  const [address, setAddress] = useState('')
  const [coupon, setCoupon] = useState<string | null>(null)
  const [loading, setLoading] = useState(false)
  const [receipt, setReceipt] = useState<any | null>(null)
  const [error, setError] = useState<string | null>(null)

  async function handleCheckout(e: React.FormEvent) {
    e.preventDefault()
    setError(null)
    if (items.length === 0) return setError('Carrito vacío')

    setLoading(true)
    try {
      const payload = {
        items: items.map((it) => ({ productId: it.productId, quantity: it.quantity })),
        couponCode: coupon,
        shippingAddress: address,
      }
      const order = await createOrder(payload)
      // fetch receipt
      const r = await getOrderReceipt(order.id ?? order['id'])
      setReceipt(r)
      // clear cart
      clear()
    } catch (err: any) {
      setError(err?.response?.data?.message ?? 'Error al procesar el pago')
    } finally {
      setLoading(false)
    }
  }

  if (receipt) {
    return (
      <section className="receipt">
        <h2>Boleta / Comprobante</h2>
        <pre>{JSON.stringify(receipt, null, 2)}</pre>
        <button onClick={() => navigate('/')}>Volver al inicio</button>
      </section>
    )
  }

  return (
    <section className="checkout-page">
      <h1>Checkout</h1>
      <form onSubmit={handleCheckout}>
        <label>
          Dirección de envío
          <input value={address} onChange={(e) => setAddress(e.target.value)} required />
        </label>

        <label>
          Cupón (opcional)
          <input value={coupon ?? ''} onChange={(e) => setCoupon(e.target.value || null)} />
        </label>

        <div>Total a pagar: ${total().toFixed(0)}</div>

        {error && <div className="error">{error}</div>}
        <button type="submit" disabled={loading}>{loading ? 'Procesando...' : 'Pagar'}</button>
      </form>
    </section>
  )
}
