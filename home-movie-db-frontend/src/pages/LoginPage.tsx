import { useState } from "react";
import { useForm } from "react-hook-form";
import { NavLink, useNavigate } from "react-router";
import LoadingSpinner from "../components/LoadingSpinner/LoadingSpinner";

const LoginPage = () => {
    const [user, setUser] = useState<string>("");
    const [password, setPassword] = useState<string>("");
    const [error, setError] = useState<boolean>(false);
    const [isLoading, setIsLoading] = useState<boolean>(false);

    const {register, handleSubmit} = useForm();
    const navigate = useNavigate()

    const handleLogin = () => {
        const csrfToken = document.cookie.replace(/(?:(?:^|.*;\s*)XSRF-TOKEN\s*=\s*([^;]*).*$)|^.*$/, '$1')
        setIsLoading(true);

        fetch("/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
                "X-XSRF-TOKEN": csrfToken
            },
            body: new URLSearchParams({
                username: user,
                password: password
            })
        }).then((res) => {
            setIsLoading(false)

            const url = new URL(res.url);
            if(url.searchParams.get("error") === ""){
                console.error("Invalid auth credentials");
                setError(true);
            }

            if(res.redirected){
                const url = new URL(res.url);
                navigate(url.pathname);
            }
        }).catch((err) => {
            setIsLoading(false);
            console.error(err)}
        );
    }

    return (
        <div className="flex-1 flex justify-center flex-col items-center my-6">
            <h2 className="text-3xl font-bold mb-8 flex-block">Willkommen in der Home Movie DB</h2>

            <p className="mb-4">Noch kein Nutzer vorhanden? <NavLink className="underline" to="/signup">Hier Registrieren</NavLink></p>

            <form onSubmit={handleSubmit(handleLogin)} className="mx-auto max-w-md p-8 bg-white rounded-lg shadow-lg">
            <label className="block text-gray-700 font-bold mb-2">
                Username:{" "}
                <input 
                    {...register("username", {required: true})}
                    type="text" 
                    placeholder="Username" 
                    value={user} 
                    onChange={(e) => setUser(e.target.value)} 
                    className="bg-white border-solid border-[1px] border-[#ccc] w-full py-1.5 px-2" 
                    disabled={isLoading}
                />
            </label>
            <br />
            <label className="block text-gray-700 font-bold mb-2">
                Password:{" "}
                <input 
                    {...register("password", { required: true })}
                    type="password" 
                    placeholder="Password" 
                    value={password} 
                    onChange={(e) => setPassword(e.target.value)} 
                    className="bg-white border-solid border-[1px] border-[#ccc] w-full py-1.5 px-2" 
                    disabled={isLoading}
                />
            </label>
            {error && <p className="text-red-500">Invalid auth credentials</p>}
            <br />
            {!isLoading && <button 
                type="submit" 
                className="bg-[#3200ee] px-4 py-1.5 text-white"
            >
                Login
            </button>}
            {isLoading && <LoadingSpinner />}
        </form>
        </div>
    );
}

export default LoginPage;