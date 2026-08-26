import React, { createContext, useContext, useEffect, useState } from 'react'

export type CartItem = {
  productId: number
  name: string
  price: number
  quantity: number
}

type CartContextType = {
  items: CartItem[]
  addItem: (item: CartItem) => void
  removeItem: (productId: number) => void
  updateQuantity: (productId: number, qty: number) => void
  clear: () => void
  total: () => number
}

const CartContext = createContext<CartContextType | undefined>(undefined)

export function CartProvider({ children }: { children: React.ReactNode }) {
  const [items, setItems] = useState<CartItem[]>(() => {
    try {
      const raw = localStorage.getItem('ecom_cart')
      return raw ? JSON.parse(raw) : []
    } catch {
      return []
    }
  })

  useEffect(() => {
    try {
      localStorage.setItem('ecom_cart', JSON.stringify(items))
    } catch {}
  }, [items])

  function addItem(item: CartItem) {
    setItems((prev) => {
      const found = prev.find((p) => p.productId === item.productId)
      if (found) {
        return prev.map((p) => (p.productId === item.productId ? { ...p, quantity: p.quantity + item.quantity } : p))
      }
      return [...prev, item]
    })
  }

  function removeItem(productId: number) {
    setItems((prev) => prev.filter((p) => p.productId !== productId))
  }

  function updateQuantity(productId: number, qty: number) {
    setItems((prev) => prev.map((p) => (p.productId === productId ? { ...p, quantity: qty } : p)))
  }

  function clear() {
    setItems([])
  }

  function total() {
    return items.reduce((s, it) => s + it.price * it.quantity, 0)
  }

  return (
    <CartContext.Provider value={{ items, addItem, removeItem, updateQuantity, clear, total }}>
      {children}
    </CartContext.Provider>
  )
}

export function useCart() {
  const ctx = useContext(CartContext)
  if (!ctx) throw new Error('useCart must be used within CartProvider')
  return ctx
}
