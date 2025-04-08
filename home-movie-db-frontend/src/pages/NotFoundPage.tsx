import { useLocation } from "react-router"

export default function NotFoundPage() {
    
    const location = useLocation();
  
    return (
    <>
        <h1 className="text-3xl font-semibold mb-2">404 Not Found</h1>
        <p className="italic">Nothing found on route {location.pathname}</p>
    </>
  )
}
