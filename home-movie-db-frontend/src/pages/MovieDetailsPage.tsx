import { useParams } from "react-router";
import { useGetAllMoviesQuery, useGetMovieByIdQuery, usePostAddMoveByIdMutation } from "../redux/api/movieApi";
import LoadingSpinner from "../components/LoadingSpinner/LoadingSpinner";

const MovieDetailsPage = () => {

    const { id } = useParams();
    const { data: movies } = useGetAllMoviesQuery();
    const { data, isLoading } = useGetMovieByIdQuery(id ?? "", {
        skip: !id,
        refetchOnMountOrArgChange: true,
    });
    const [trigger] = usePostAddMoveByIdMutation();

    const movieExists = movies?.some(movie => movie.id === data?.id);

    if (isLoading) {
        return <div className="flex-1 flex justify-center items-center my-6 mx-8"><LoadingSpinner /></div>;
    }

    return (
      <div className="flex-1 flex flex-col my-6 mx-28">
            <div className="flex justify-center items-center mb-8">
                <h2 className="text-2xl font-bold flex-block">{data?.title}</h2>
                {movieExists && 
                    <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded ml-auto h-max"
                        onClick={() => trigger(data?.id.toString() ?? "")}>
                        Entfernen
                </button>}
                {!movieExists && 
                    <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded ml-auto h-max"
                        onClick={() => trigger(data?.id.toString() ?? "")}>
                        Hinzufügen
                </button>}
            </div>
            <div className="flex flex-row">
                <img src={data?.thumbnailUrl as string} alt="Thumbnail" className="shadow-lg" />
                <div className="ml-6">
                    <div className="flex flex-wrap mb-4">
                        {data?.genres.map((genre) => (
                            <span key={genre} className="inline-block bg-[#65C576] text-black text-sm font-medium mr-2 px-2.5 py-0.5 rounded">
                                {genre}
                            </span>
                        ))}
                    </div>
                    <p>{data?.overview}</p>
                </div>
            </div>
      </div>
    );
};

export default MovieDetailsPage;