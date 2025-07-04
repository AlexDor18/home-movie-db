import { BrowserRouter, Route, Routes } from "react-router"
import Layout from "./pages/Layout"
import LoginPage from "./pages/LoginPage"
import NotFoundPage from "./pages/NotFoundPage"
import SearchPage from "./pages/SearchPage"
import HomePage from "./pages/HomePage"
import SignupPage from "./pages/SignupPage"
import MovieDetailsPage from "./pages/MovieDetailsPage"

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Layout />}> 
          <Route index element={<LoginPage />} />
          <Route path='movie/:id' element={<MovieDetailsPage />} />
          <Route path='signup' element={<SignupPage />} />
          <Route path='home' element={<HomePage />} />
          <Route path='search' element={<SearchPage />} />
          <Route path='*' element={<NotFoundPage />} />
        </Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App
