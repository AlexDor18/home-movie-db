import { BrowserRouter, Route, Routes } from "react-router"
import NotFoundPage from "./pages/NotFoundPage"
import Layout from "./pages/Layout"
import HomePage from "./pages/HomePage"

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Layout />}> 
          <Route index element={<HomePage />} />
          <Route path='*' element={<NotFoundPage />} />
        </Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App
