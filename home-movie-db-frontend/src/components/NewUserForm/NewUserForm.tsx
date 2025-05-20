import { useState } from "react";
import { useForm } from "react-hook-form";
import { NewUser } from "../../models/Login";
import { NavLink } from "react-router";

interface NewUserFormProps {
    onSubmit: (user: NewUser) => void
}

const NewUserForm = (props: NewUserFormProps) => {
    const [user, setUser] = useState<string>("");
    const [prename, setPrename] = useState<string>("");
    const [surname, setSurname] = useState<string>("");
    const [password, setPassword] = useState<string>("");

    const {register, handleSubmit} = useForm();

    return (
        <form className="mx-auto max-w-md p-8 bg-white rounded-lg shadow-lg" onSubmit={handleSubmit(() => props.onSubmit({username: user, prename, surname, password}))}>
            <label className="block text-gray-700 font-bold mb-2">
                Vorname:{" "}
                <input 
                    {...register("prename")}
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
                    {...register("surname")}
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
                    {...register("username")}
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
                    {...register("password")}
                    type="password" 
                    placeholder="Password" 
                    value={password} 
                    onChange={(e) => setPassword(e.target.value)} 
                    className="bg-white border-solid border-[1px] border-[#ccc] w-full py-1.5 px-2" 
                />
            </label>
            <div>
            <input 
                type="submit" 
                className="bg-[#3200ee] px-4 py-1.5 text-white"
                value={"Registrieren"}
            />
            <NavLink to="/login" className="text-[#3200ee] ml-4">Zurück</NavLink>
            </div>
        </form>
    )
}

export default NewUserForm;