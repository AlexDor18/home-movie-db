package org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb;

import java.util.List;

import org.springframework.stereotype.Service;
import org.thro.sqs.homemoviedb.home_movie_db_backend.business.models.MovieDTO;
import org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.interfaces.MovieInformations;
import org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb.mapper.TmdbMapper;
import org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb.models.TmdbMovieMessage;
import org.thro.sqs.homemoviedb.home_movie_db_backend.movieadapter.tmdb.models.TmdbMovieMessageList;

@Service
public class TmdbMovieInformations implements MovieInformations{

    private TmdbHttpClient tmdbHttpClient;
    private TmdbMapper tmdbMapper;

    public TmdbMovieInformations(TmdbHttpClient httpClient, TmdbMapper mapper) {
        tmdbHttpClient = httpClient;
        tmdbMapper = mapper;
    }

    @Override
    public MovieDTO getMovieInformationsById(Long movieId) {
        TmdbMovieMessage result = this.tmdbHttpClient.get("/movie/"+movieId+"?language=de-DE", TmdbMovieMessage.class);
        return this.tmdbMapper.mapToMovieDTO(result);
    }

    @Override
    public List<MovieDTO> getMoviesInformationsById(List<Long> movieIds) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMoviesInformationsById'");
    }

    @Override
    public List<MovieDTO> searchMovieByQuery(String query, boolean adult) {
        TmdbMovieMessageList result = this.tmdbHttpClient.get("/search/movie?language=de-DE&query="+query+"&include_adult="+adult, TmdbMovieMessageList.class);   
        return this.tmdbMapper.mapToMovieDTO(result.getResults());
    }
}
