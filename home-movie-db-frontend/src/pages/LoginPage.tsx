import { useState } from "react";
import { useLoginMutation } from "../redux/api/loginApi";

const LoginPage = () => {

    const [login] = useLoginMutation();
    const [user, setUser] = useState<string>("");
    const [password, setPassword] = useState<string>("");

    return (
        <div>
            <input type="text" placeholder="Username" value={user} onChange={(e) => setUser(e.target.value)} />
            <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} />
            <button onClick={() => login({username: user, password: password})}>Login</button>
        </div>
    );
}

export default LoginPage;