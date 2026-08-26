import { BrowserRouter } from 'react-router-dom'
import AppShell from './components/templates/AppShell'
import './App.css'

function App() {
  return (
    <BrowserRouter>
      <AppShell />
    </BrowserRouter>
  )
}

export default App
