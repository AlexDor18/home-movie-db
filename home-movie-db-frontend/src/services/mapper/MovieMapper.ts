import { MovieDto, MovieMessage } from "../../models/Movie";

class MovieMapper {
    /**
     * Maps a MovieMessage object to a MovieDto object
     * @param movieMessage The object to be mapped
     * @returns The mapped MovieDto object
     */
    static toMovieDto(movieMessage: MovieMessage): MovieDto {
        return {
            id: movieMessage.id,
            title: movieMessage.title,
            overview: movieMessage.overview,
            genres: movieMessage.genres,
            thumbnailUrl: movieMessage.thumbnailUrl
        };
    }

    
    /**
     * Maps a list of MovieMessage objects to a list of MovieDto objects
     * @param movieMessages The list of objects to be mapped
     * @returns The mapped list of MovieDto objects
     */
    static toMovieDtoList(movieMessages: MovieMessage[]): MovieDto[] {
        return movieMessages.map(movieMessage => MovieMapper.toMovieDto(movieMessage));
    }
}

export default MovieMapper;

