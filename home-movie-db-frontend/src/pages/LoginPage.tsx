import { useState } from "react";
import { useNavigate } from "react-router";

const LoginPage = () => {
    const [user, setUser] = useState<string>("");
    const [password, setPassword] = useState<string>("");

    const navigate = useNavigate()

    const handleLogin = () => {
        fetch("/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: new URLSearchParams({
                username: user,
                password: password
            })
        }).then((res) => {
            if(res.redirected){
                const url = new URL(res.url);
                navigate(url.pathname);
            }
        }).catch((err) => console.error(err));
    }

    return (
        <div className="flex-1 flex justify-center items-center">
            <form onSubmit={(e) => {
                e.preventDefault();
                handleLogin();
            }} className="mx-auto max-w-md p-8 bg-white rounded-lg shadow-lg">
            <label className="block text-gray-700 font-bold mb-2">
                Username:{" "}
                <input 
                    type="text" 
                    placeholder="Username" 
                    value={user} 
                    onChange={(e) => setUser(e.target.value)} 
                    className="bg-white border-solid border-[1px] border-[#ccc] w-full py-1.5 px-2" 
                />
            </label>
            <br />
            <label className="block text-gray-700 font-bold mb-2">
                Password:{" "}
                <input 
                    type="password" 
                    placeholder="Password" 
                    value={password} 
                    onChange={(e) => setPassword(e.target.value)} 
                    className="bg-white border-solid border-[1px] border-[#ccc] w-full py-1.5 px-2" 
                />
            </label>
            <br />
            <button 
                type="submit" 
                className="bg-[#3200ee] px-4 py-1.5 text-white"
            >
                Login
            </button>
        </form>
        </div>
    );
}

export default LoginPage;