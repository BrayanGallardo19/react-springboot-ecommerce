import type { SiteConfig } from '../../components/organisms/FooterBar'

type Props = {
  config: SiteConfig
  onConfigChange: (next: SiteConfig) => void
}

export default function AdminDashboardPage({ config, onConfigChange }: Props) {
  const handleQuickLinkChange = (index: number, field: 'label' | 'to', value: string) => {
    const updatedLinks = config.quickLinks.map((link, currentIndex) => {
      if (currentIndex !== index) return link
      return { ...link, [field]: value }
    })

    onConfigChange({ ...config, quickLinks: updatedLinks })
  }

  return (
    <section className="panel-page">
      <h2>Panel de administrador</h2>
      <div className="summary-grid">
        <div className="summary-card"><span>Ventas</span><strong>$1.240.000</strong></div>
        <div className="summary-card"><span>Pedidos pendientes</span><strong>18</strong></div>
        <div className="summary-card"><span>Productos activos</span><strong>132</strong></div>
        <div className="summary-card"><span>Descuentos</span><strong>7</strong></div>
      </div>

      <div className="admin-settings-box">
        <h3>Personalización del pie de página</h3>
        <div className="admin-settings-grid">
          <label>
            Nombre de la empresa
            <input
              value={config.companyName}
              onChange={(event) => onConfigChange({ ...config, companyName: event.target.value })}
            />
          </label>
          <label>
            Correo
            <input
              value={config.email}
              onChange={(event) => onConfigChange({ ...config, email: event.target.value })}
            />
          </label>
          <label>
            Teléfono
            <input
              value={config.phone}
              onChange={(event) => onConfigChange({ ...config, phone: event.target.value })}
            />
          </label>
          <label>
            Dirección
            <input
              value={config.address}
              onChange={(event) => onConfigChange({ ...config, address: event.target.value })}
            />
          </label>
        </div>

        <div className="admin-links-editor">
          {config.quickLinks.map((link, index) => (
            <div key={`${link.label}-${index}`} className="admin-link-row">
              <input
                value={link.label}
                onChange={(event) => handleQuickLinkChange(index, 'label', event.target.value)}
                placeholder="Texto"
              />
              <input
                value={link.to}
                onChange={(event) => handleQuickLinkChange(index, 'to', event.target.value)}
                placeholder="/ruta"
              />
            </div>
          ))}
        </div>
      </div>
    </section>
  )
}
