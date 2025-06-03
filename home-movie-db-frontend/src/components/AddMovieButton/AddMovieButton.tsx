import { use } from "react";
import { useGetAllMoviesQuery, usePostAddMoveByIdMutation } from "../../redux/api/movieApi";

interface AddMovieButtonProps {
    id: string
}

const AddMovieButton = (props: AddMovieButtonProps) => {

    const [addMovie] = usePostAddMoveByIdMutation();
    const {data} = useGetAllMoviesQuery();

    const foundMovie = data?.find((movie) => movie.id.toString() === props.id);

    return (<>{
            !foundMovie &&<button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded" onClick={() => addMovie(props.id)}>
                Add Movie
            </button>}
            {foundMovie && <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="size-6 text-green-500">
                <path strokeLinecap="round" strokeLinejoin="round" d="M9 12.75 11.25 15 15 9.75M21 12a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z" />
            </svg>
            }
        </>
    );
}

export default AddMovieButton;