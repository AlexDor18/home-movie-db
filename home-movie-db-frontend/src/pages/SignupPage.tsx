import { useState } from "react";
import { useForm } from "react-hook-form";
import { NavLink } from "react-router";
import { useSignupMutation } from "../redux/api/authApi";

const SignupPage = () => {
    const [user, setUser] = useState<string>("");
    const [prename, setPrename] = useState<string>("");
    const [surname, setSurname] = useState<string>("");
    const [password, setPassword] = useState<string>("");

    const [trigger] = useSignupMutation();

    const {register, handleSubmit} = useForm();

    const onSubmit = () => {
        trigger({username: user, prename, surname, password});
    };

    return (
        <div className="flex-1 flex justify-center flex-col items-center my-6">
        <h2 className="text-3xl font-bold mb-4 flex-block my-8">Registrieren</h2>
        <form className="mx-auto max-w-md p-8 bg-white rounded-lg shadow-lg" onSubmit={handleSubmit(() => onSubmit())}>
            <label className="block text-gray-700 font-bold mb-2">
                Vorname:{" "}
                <input 
                    {...register("prename", {required: true})}
                    type="text" 
                    placeholder="Vorname" 
                    value={prename} 
                    onChange={(e) => setPrename(e.target.value)} 
                    className="bg-white border-solid border-[1px] border-[#ccc] w-full py-1.5 px-2" 
                />
            </label>
            <label className="block text-gray-700 font-bold mb-2">
                Nachname:{" "}
                <input 
                    {...register("surname", {required: true})}
                    type="text" 
                    placeholder="Nachname" 
                    value={surname} 
                    onChange={(e) => setSurname(e.target.value)} 
                    className="bg-white border-solid border-[1px] border-[#ccc] w-full py-1.5 px-2" 
                />
            </label>
            <label className="block text-gray-700 font-bold mb-2">
                Username:{" "}
                <input 
                    {...register("username", {required: true})}
                    type="text" 
                    placeholder="Username" 
                    value={user} 
                    onChange={(e) => setUser(e.target.value)} 
                    className="bg-white border-solid border-[1px] border-[#ccc] w-full py-1.5 px-2" 
                />
            </label>
            <label className="block text-gray-700 font-bold mb-2">
                Password:{" "}
                <input 
                    {...register("password", { required: true })}
                    type="password" 
                    placeholder="Password" 
                    value={password} 
                    onChange={(e) => setPassword(e.target.value)} 
                    className="bg-white border-solid border-[1px] border-[#ccc] w-full py-1.5 px-2" 
                />
            </label>
            <div>
            <button 
                type="submit" 
                className="bg-[#3200ee] px-4 py-1.5 text-white"
            >
                Registrieren
            </button>
            <NavLink to="/login" className="text-[#3200ee] ml-4">Zurück</NavLink>
            </div>
        </form>
        </div>
    );
};

export default SignupPage;