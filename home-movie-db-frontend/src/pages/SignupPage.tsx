import NewUserForm from "../components/NewUserForm/NewUserForm";
import { NewUser } from "../models/Login";
import { useSignupMutation } from "../redux/api/authApi";

const SignupPage = () => {
    const [trigger] = useSignupMutation();

    return (
        <div className="flex-1 flex justify-center flex-col items-center my-6">
            <h2 className="text-3xl font-bold mb-4 flex-block my-8">Registrieren</h2>
            <NewUserForm onSubmit={(user: NewUser) => trigger(user)} />
        </div>
    );
};

export default SignupPage;