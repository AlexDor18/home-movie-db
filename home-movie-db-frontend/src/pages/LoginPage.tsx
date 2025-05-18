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
        <div>
            <input type="text" placeholder="Username" value={user} onChange={(e) => setUser(e.target.value)} />
            <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} />
            <button onClick={() => handleLogin()}>Login</button>
        </div>
    );
}

export default LoginPage;