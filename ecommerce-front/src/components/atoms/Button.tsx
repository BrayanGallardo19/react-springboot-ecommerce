type ButtonProps = {
  children: React.ReactNode
  variant?: 'primary' | 'secondary' | 'ghost'
  fullWidth?: boolean
  type?: 'button' | 'submit' | 'reset'
  onClick?: () => void
}

export default function Button({
  children,
  variant = 'primary',
  fullWidth = false,
  type = 'button',
  onClick,
}: ButtonProps) {
  return (
    <button
      type={type}
      onClick={onClick}
      className={`atom-button atom-button--${variant} ${fullWidth ? 'full-width' : ''}`}
    >
      {children}
    </button>
  )
}
