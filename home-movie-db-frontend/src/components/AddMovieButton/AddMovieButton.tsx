import { usePostAddMoveByIdMutation } from "../../redux/api/movieApi";

interface AddMovieButtonProps {
    id: string
}

const AddMovieButton = (props: AddMovieButtonProps) => {

    const [addMovie] = usePostAddMoveByIdMutation();

    return (
        <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded" onClick={() => addMovie(props.id)}>
            Add Movie
        </button>
    );
}

export default AddMovieButton;