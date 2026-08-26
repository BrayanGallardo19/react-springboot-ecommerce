type BadgeProps = {
  children: React.ReactNode
  tone?: 'success' | 'info' | 'warning'
}

export default function Badge({ children, tone = 'info' }: BadgeProps) {
  return <span className={`atom-badge atom-badge--${tone}`}>{children}</span>
}
