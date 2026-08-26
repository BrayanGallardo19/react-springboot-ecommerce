export type ProductCategory = {
  id: number
  name: string
  slug: string
  active: boolean
}

export type ProductApi = {
  id: number
  name: string
  description: string
  price: number
  stock: number
  active: boolean
  featured: boolean
  imageUrl?: string
  discountPercent?: number
  category: ProductCategory
}

export type PromotionApi = {
  id: number
  name: string
  code: string
  discountPercent: number
  active: boolean
}

export type OrderItemRequest = {
  productId: number
  quantity: number
}

export type OrderRequest = {
  items: OrderItemRequest[]
  couponCode?: string | null
  shippingAddress?: string | null
}
