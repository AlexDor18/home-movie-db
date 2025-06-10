import { useState } from "react";
import { useForm } from "react-hook-form";
import { NewUser } from "../../models/Login";
import { NavLink } from "react-router";
import LoadingSpinner from "../LoadingSpinner/LoadingSpinner";

interface NewUserFormProps {
    onSubmit: (user: NewUser) => void,
    isLoading: boolean,
    error: {
        isError: boolean,
        message: string
    }
}

const NewUserForm = (props: NewUserFormProps) => {
    const [user, setUser] = useState<string>("");
    const [prename, setPrename] = useState<string>("");
    const [surname, setSurname] = useState<string>("");
    const [password, setPassword] = useState<string>("");

    const {register, handleSubmit, formState: {errors}} = useForm();

    return (
        <form className="mx-auto max-w-md p-8 bg-white rounded-lg shadow-lg" onSubmit={handleSubmit(() => props.onSubmit({username: user, prename, surname, password}))}>
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
            {errors.prename && <p className="text-red-500">Vorname ist erforderlich</p>}
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
            {errors.surname && <p className="text-red-500">Nachname ist erforderlich</p>}
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
            {errors.username && <p className="text-red-500">Username ist erforderlich</p>}
            <label className="block text-gray-700 font-bold mb-2">
                Password:{" "}
                <input 
                    {...register("password", {required: true})}
                    type="password" 
                    placeholder="Password" 
                    value={password} 
                    onChange={(e) => setPassword(e.target.value)} 
                    className="bg-white border-solid border-[1px] border-[#ccc] w-full py-1.5 px-2" 
                />
            </label>
            {errors.password && <p className="text-red-500">Password ist erforderlich</p>}
            {props.error.isError && <p className="text-red-500">{props.error.message}</p>}
            <div className="flex flex-row items-center mt-6">
            {!props.isLoading && <input 
                type="submit" 
                className="bg-[#3200ee] px-4 py-1.5 text-white"
                value={"Registrieren"}
            />}
            {props.isLoading && <LoadingSpinner />}
            <NavLink to="/" className="text-[#3200ee] ml-auto">Zurück</NavLink>
            </div>
        </form>
    )
}

export default NewUserForm;