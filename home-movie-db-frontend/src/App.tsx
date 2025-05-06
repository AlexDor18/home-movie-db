import { BrowserRouter, Route, Routes } from "react-router"
import NotFoundPage from "./pages/NotFoundPage"
import Layout from "./pages/Layout"
import HomePage from "./pages/HomePage"
import SearchPage from "./pages/SearchPage"

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Layout />}> 
          <Route index element={<HomePage />} />
          <Route path='search' element={<SearchPage />} />
          <Route path='*' element={<NotFoundPage />} />
        </Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App
