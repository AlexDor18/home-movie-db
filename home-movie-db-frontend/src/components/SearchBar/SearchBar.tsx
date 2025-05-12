import { SubmitHandler, useForm } from "react-hook-form";

interface FormInput {
    searchInput: string;
}

interface SearchBarProps {
    onSubmit: SubmitHandler<FormInput>;
}

const SearchBar = (props: SearchBarProps) => {
    const {register, handleSubmit} = useForm<FormInput>();

    return (
        <form className="my-6 mx-8 w-auto flex" onSubmit={handleSubmit(props.onSubmit)}>
            <input type="text" {...register("searchInput")} className="bg-white border-solid border-[1px] border-[#ccc] w-full py-1.5 px-2" placeholder="Search..." aria-label="Search" aria-describedby="button-addon2" />
            <input className="bg-[#3200ee] px-4 py-1.5 text-white" value={"Search"} type="submit" />
        </form>
    )
}

export default SearchBar;