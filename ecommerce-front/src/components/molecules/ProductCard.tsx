import React from 'react'
import Badge from '../atoms/Badge'
import Button from '../atoms/Button'
import { useCart } from '../../contexts/CartContext'

type ProductCardProps = {
  productId: number
  name: string
  category: string
  price: number
  badge?: string
  tone?: 'success' | 'info' | 'warning'
  imageUrl?: string
}

function formatPrice(value: number) {
  return new Intl.NumberFormat('es-CL', {
    style: 'currency',
    currency: 'CLP',
    maximumFractionDigits: 0,
  }).format(value)
}

export default function ProductCard({
  productId,
  name,
  category,
  price,
  badge = 'Nuevo',
  tone = 'info',
  imageUrl,
}: ProductCardProps) {
  const { addItem } = useCart()
  const [added, setAdded] = React.useState(false)

  function handleAdd() {
    addItem({ productId, name, price, quantity: 1 })
    setAdded(true)
    setTimeout(() => setAdded(false), 1400)
  }

  return (
    <article className="molecule-product-card">
      <div
        className="molecule-product-card__image"
        aria-hidden="true"
        style={
          imageUrl
            ? {
                backgroundImage: `linear-gradient(135deg, rgba(15,23,42,0.12), rgba(37,99,235,0.12)), url(${imageUrl})`,
                backgroundSize: 'cover',
                backgroundPosition: 'center',
              }
            : undefined
        }
      />
      <Badge tone={tone}>{badge}</Badge>
      <h3>{name}</h3>
      <p>{category}</p>
      <div className="molecule-product-card__meta">
        <strong>{formatPrice(price)}</strong>
        <Button variant="primary" onClick={handleAdd} disabled={added}>
          {added ? 'Añadido' : 'Agregar al carrito'}
        </Button>
      </div>
    </article>
  )
}
