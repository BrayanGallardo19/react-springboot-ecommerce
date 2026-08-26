export type FooterLink = {
  label: string
  to: string
}

export type SiteConfig = {
  companyName: string
  email: string
  phone: string
  address: string
  quickLinks: FooterLink[]
}

type FooterBarProps = {
  config: SiteConfig
}

export default function FooterBar({ config }: FooterBarProps) {
  return (
    <footer className="footer-bar">
      <div className="footer-bar__inner">
        <div>
          <p className="footer-title">{config.companyName}</p>
          <p>{config.address}</p>
        </div>

        <div>
          <p className="footer-title">Accesos rápidos</p>
          <ul className="footer-links">
            {config.quickLinks.map((link) => (
              <li key={link.label}>
                <a href={link.to}>{link.label}</a>
              </li>
            ))}
          </ul>
        </div>

        <div>
          <p className="footer-title">Contacto</p>
          <ul className="footer-links">
            <li>{config.email}</li>
            <li>{config.phone}</li>
          </ul>
        </div>
      </div>
    </footer>
  )
}
