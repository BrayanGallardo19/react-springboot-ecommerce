import { Link, NavLink } from 'react-router-dom'
import SearchInput from '../atoms/SearchInput'

export type Role = 'admin' | 'trabajador' | 'cliente'

type HeaderProps = {
  role: Role
  onRoleChange: (role: Role) => void
}

const navByRole: Record<Role, { label: string; to: string }[]> = {
  admin: [
    { label: 'Inicio', to: '/' },
    { label: 'Catálogo', to: '/catalogo' },
    { label: 'Productos', to: '/admin/productos' },
    { label: 'Pedidos', to: '/admin/pedidos' },
    { label: 'Promociones', to: '/admin/promociones' },
    { label: 'Usuarios', to: '/admin/usuarios' },
    { label: 'Config', to: '/admin/configuracion' },
    { label: 'Vista tienda', to: '/tienda' },
  ],
  trabajador: [
    { label: 'Inicio', to: '/' },
    { label: 'Pedidos', to: '/trabajador/pedidos' },
    { label: 'Inventario', to: '/trabajador/inventario' },
    { label: 'Clientes', to: '/trabajador/clientes' },
    { label: 'Productos', to: '/trabajador/productos' },
    { label: 'Mi perfil', to: '/mi-perfil' },
    { label: 'Vista tienda', to: '/tienda' },
  ],
  cliente: [
    { label: 'Inicio', to: '/' },
    { label: 'Categorías', to: '/catalogo' },
    { label: 'Mis pedidos', to: '/mis-pedidos' },
    { label: 'Mi perfil', to: '/mi-perfil' },
    { label: 'Contacto', to: '/contacto' },
    { label: 'Login', to: '/login' },
  ],
}

export default function Header({ role, onRoleChange }: HeaderProps) {
  const navItems = navByRole[role]

  // cart count from context
  let cartCount = 0
  try {
    // lazy read localStorage (avoid importing context here to keep component simple)
    const raw = localStorage.getItem('ecom_cart')
    const items = raw ? JSON.parse(raw) : []
    cartCount = items.reduce((s: number, it: any) => s + (it.quantity || 0), 0)
  } catch {
    cartCount = 0
  }

  // read user info from localStorage to determine logged-in state
  let currentUser: { fullName?: string; email?: string; role?: string } | null = null
  try {
    const ru = localStorage.getItem('ecom_user')
    currentUser = ru ? JSON.parse(ru) : null
  } catch {
    currentUser = null
  }

  function logout() {
    try {
      localStorage.removeItem('ecom_user')
      localStorage.removeItem('ecom_token')
    } catch {}
    // reload to reset app state
    window.location.href = '/'
  }

  return (
    <header className="topbar">
      <div className="brand-row">
        <div className="brand-box">
          <span className="brand-mark">A</span>
          <div>
            <strong>Artesanos</strong>
            <small>Tienda digital</small>
          </div>
        </div>

        <SearchInput placeholder="Buscar productos..." />

        <div className="header-actions">
          <Link to="/carrito" className="cart-button" aria-label="Carrito">
            🛒
            <span>{cartCount}</span>
          </Link>
          {currentUser ? (
            <>
              <span className="login-user">{currentUser.fullName ?? currentUser.email}</span>
              <a role="button" className="login-link" onClick={logout} style={{ cursor: 'pointer' }}>
                Salir
              </a>
            </>
          ) : (
            <Link to="/login" className="login-link">Ingresar</Link>
          )}
        </div>
      </div>

      <nav className="nav-row" aria-label="Navegación principal">
        {navItems.map((item) => (
          <NavLink
            key={item.to}
            to={item.to}
            className={({ isActive }) => `nav-link ${isActive ? 'active' : ''}`}
          >
            {item.label}
          </NavLink>
        ))}
      </nav>

      <div className="role-switcher" aria-label="Cambiar vista de rol">
        <label htmlFor="role-selector">Vista:</label>
        <select
          id="role-selector"
          value={role}
          onChange={(event) => onRoleChange(event.target.value as Role)}
        >
          <option value="cliente">Cliente</option>
          <option value="trabajador">Trabajador</option>
          <option value="admin">Administrador</option>
        </select>
      </div>
    </header>
  )
}
