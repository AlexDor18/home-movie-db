import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { MovieDto, MovieMessage } from "../../models/Movie";
import MovieMapper from "../../services/mapper/MovieMapper";

export const searchApi = createApi({
    reducerPath: "searchApi",
    baseQuery: fetchBaseQuery({
        baseUrl: "/api/search",
    }),
    endpoints: (build) => ({
        getSearchMovies: build.query<MovieDto[], string>({
            query: (searchInput) => ({
                url: "/movies?query="+searchInput+"&adult=true",
            }),

            transformResponse: (response:  MovieMessage[] ) => { return MovieMapper.toMovieDtoList(response);}
        })
    })
})

export const { useLazyGetSearchMoviesQuery } = searchApi;