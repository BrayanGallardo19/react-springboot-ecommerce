import { useEffect, useState } from 'react'
import StoreLayout from '../../components/templates/StoreLayout'
import { getProducts } from '../../lib/api'
import type { ProductApi } from '../../types'

export default function HomePage() {
  const [products, setProducts] = useState<ProductApi[]>([])

  useEffect(() => {
    const loadProducts = async () => {
      try {
        const data = await getProducts()
        setProducts(data)
      } catch (error) {
        console.error('No se pudieron cargar los productos', error)
      }
    }

    loadProducts()
  }, [])

  return <StoreLayout products={products} />
}
