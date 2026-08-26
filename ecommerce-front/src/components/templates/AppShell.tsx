import Header from '../molecules/Header'
import { Route, Routes } from 'react-router-dom'
import { useState } from 'react'
import type { Role } from '../molecules/Header'
import HomePage from '../../pages/public/HomePage'
import CatalogPage from '../../pages/public/CatalogPage'
import LoginPage from '../../pages/public/LoginPage'
import ContactPage from '../../pages/public/ContactPage'
import ClientDashboardPage from '../../pages/client/ClientDashboardPage'
import WorkerDashboardPage from '../../pages/worker/WorkerDashboardPage'
import AdminDashboardPage from '../../pages/admin/AdminDashboardPage'
import FooterBar, { type SiteConfig } from '../organisms/FooterBar'
import CartPage from '../../pages/public/CartPage'
import CheckoutPage from '../../pages/public/CheckoutPage'
import { CartProvider } from '../../contexts/CartContext'

const initialSiteConfig: SiteConfig = {
  companyName: 'Artesanos',
  email: 'soporte@artesanos.cl',
  phone: '+56 9 1234 5678',
  address: 'Av. Providencia 1234, Santiago, Chile',
  quickLinks: [
    { label: 'Inicio', to: '/' },
    { label: 'Catálogo', to: '/catalogo' },
    { label: 'Contacto', to: '/contacto' },
    { label: 'Login', to: '/login' },
  ],
}

export default function AppShell() {
  const [role, setRole] = useState<Role>('cliente')
  const [siteConfig, setSiteConfig] = useState<SiteConfig>(initialSiteConfig)

  return (
    <CartProvider>
      <div className="app-shell">
        <Header role={role} onRoleChange={setRole} />
        <main className="page-shell">
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/catalogo" element={<CatalogPage />} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="/contacto" element={<ContactPage />} />
            <Route path="/carrito" element={<CartPage />} />
            <Route path="/checkout" element={<CheckoutPage />} />
            <Route path="/mi-perfil" element={<ClientDashboardPage />} />
            <Route path="/mis-pedidos" element={<ClientDashboardPage />} />
            <Route path="/cliente" element={<ClientDashboardPage />} />
            <Route path="/trabajador" element={<WorkerDashboardPage />} />
            <Route path="/admin" element={<AdminDashboardPage config={siteConfig} onConfigChange={setSiteConfig} />} />
            <Route path="/tienda" element={<HomePage />} />
            <Route path="*" element={<HomePage />} />
          </Routes>
        </main>
        <FooterBar config={siteConfig} />
      </div>
    </CartProvider>
  )
}
