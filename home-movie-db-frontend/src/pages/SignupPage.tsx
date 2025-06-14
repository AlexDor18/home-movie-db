import { useEffect, useState } from "react";
import NewUserForm from "../components/NewUserForm/NewUserForm";
import { NewUser } from "../models/Login";
import { useSignupMutation } from "../redux/api/authApi";
import { useNavigate } from "react-router";

const SignupPage = () => {
    const [trigger, result] = useSignupMutation();
    const [errorMessage, setErrorMessage] = useState<string>("");

    const navigate = useNavigate();

    const error = result.isError

    useEffect(() => {
        console.log(result.isError);
        if(result.isError){
            setErrorMessage("Fehler beim Registrieren. Bitte nutzen Sie einen anderen Benutzernamen.");
        }      
    }, [result.isError])

    useEffect(() => {
        console.log(result.isSuccess, result.isUninitialized);
        if(result.isSuccess && !result.isUninitialized){
            navigate("/");
        }
    })
    
    return (
        <div className="flex-1 flex justify-center flex-col items-center my-6">
            <h2 className="text-3xl font-bold mb-4 flex-block my-8">Registrieren</h2>
            <NewUserForm onSubmit={(user: NewUser) => trigger(user)}  error={{isError: error, message: errorMessage}} isLoading={result.isLoading}/>
        </div>
    );
};

export default SignupPage;