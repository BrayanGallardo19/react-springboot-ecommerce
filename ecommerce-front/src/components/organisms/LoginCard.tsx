import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import Button from '../atoms/Button'
import { login, setAuthToken } from '../../lib/api'

export default function LoginCard() {
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [error, setError] = useState<string | null>(null)
  const navigate = useNavigate()

  async function handleSubmit(e: React.FormEvent) {
    e.preventDefault()
    setError(null)
    try {
      const res = await login(email, password)
      if (res && res.token) {
        setAuthToken(res.token)
        // Persist some basic user info for UI (name, email, role)
        try {
          localStorage.setItem('ecom_user', JSON.stringify({
            userId: res.userId ?? null,
            email: res.email ?? null,
            fullName: res.fullName ?? null,
            role: res.role ?? null,
          }))
        } catch {}
        // reload the page so header reads the new localStorage state
        window.location.href = '/'
      } else {
        setError('Respuesta inválida del servidor')
      }
    } catch (err: any) {
      setError(err?.response?.data?.message ?? 'Error al iniciar sesión')
    }
  }

  return (
    <section className="auth-page">
      <div className="auth-card">
        <h2>Iniciar sesión</h2>
        <form className="auth-form" onSubmit={handleSubmit}>
          <label>
            Correo electrónico
            <input value={email} onChange={(e) => setEmail(e.target.value)} type="email" placeholder="correo@empresa.cl" />
          </label>
          <label>
            Contraseña
            <input value={password} onChange={(e) => setPassword(e.target.value)} type="password" placeholder="••••••••" />
          </label>
          <Button variant="primary" fullWidth type="submit">
            Entrar
          </Button>
        </form>
        {error && <div className="auth-error">{error}</div>}
        <p className="auth-footnote">¿No tienes cuenta? Regístrate como cliente.</p>
      </div>
    </section>
  )
}
