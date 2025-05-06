import { useForm } from "react-hook-form";

const SearchBar = () => {
    const {register} = useForm();
    
    return (
        <form className="my-6 mx-8 w-auto flex">
            <input type="text" {...register("searchInput")} className="bg-white border-solid border-[1px] border-[#ccc] w-full py-1.5 px-2" placeholder="Search..." aria-label="Search" aria-describedby="button-addon2" />
            <input className="bg-[#3200ee] px-4 py-1.5 text-white" value={"Search"} type="submit" />
        </form>
    )
}

export default SearchBar;