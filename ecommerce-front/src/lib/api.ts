import axios from 'axios'
import type { ProductApi, PromotionApi, OrderRequest } from '../types'

const api = axios.create({
  // Use proxy in development when VITE_API_BASE_URL is not set. This allows calls to '/api/..' to be proxied to the backend by Vite.
  baseURL: import.meta.env.VITE_API_BASE_URL ?? '/api',
  timeout: 10000,
})

// Attach token from localStorage (simple approach). In production prefer httpOnly cookies.
api.interceptors.request.use((config) => {
  const token = localStorage.getItem('ecom_token')
  if (token && config.headers) {
    // Add Authorization header when token exists. Use concatenation to avoid template-expansion during file writes.
    config.headers['Authorization'] = 'Bearer ' + token
  }
  return config
})

export function setAuthToken(token: string | null) {
  if (token) localStorage.setItem('ecom_token', token)
  else localStorage.removeItem('ecom_token')
}

export async function login(email: string, password: string) {
  const { data } = await api.post('/auth/login', { email, password })
  return data // expected { token, ... }
}

export async function register(payload: { firstName: string; lastName: string; email: string; password: string }) {
  const { data } = await api.post('/auth/register', payload)
  return data
}

export async function getProducts(): Promise<ProductApi[]> {
  const { data } = await api.get<ProductApi[]>('/products')
  return data
}

export async function getPromotions(): Promise<PromotionApi[]> {
  const { data } = await api.get<PromotionApi[]>('/promotions')
  return data
}

// Orders
export async function createOrder(order: OrderRequest) {
  const { data } = await api.post('/client/orders', order)
  return data
}

export async function getOrders() {
  const { data } = await api.get('/client/orders')
  return data
}

export async function getOrderReceipt(orderId: number) {
  const { data } = await api.get(`/client/orders/${orderId}/receipt`)
  return data
}
