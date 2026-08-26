type SearchInputProps = {
  placeholder?: string
  value?: string
  onChange?: (value: string) => void
}

export default function SearchInput({
  placeholder = 'Buscar productos...',
  value,
  onChange,
}: SearchInputProps) {
  return (
    <div className="atom-search">
      <span className="atom-search__icon">⌕</span>
      <input
        type="text"
        value={value}
        onChange={(event) => onChange?.(event.target.value)}
        placeholder={placeholder}
        aria-label="Buscar productos"
      />
    </div>
  )
}
