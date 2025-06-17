import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { MovieDto, MovieMessage } from "../../models/Movie";
import MovieMapper from "../../services/mapper/MovieMapper";

export const movieApi = createApi({
    reducerPath: "movieApi",
    baseQuery: fetchBaseQuery({
        baseUrl: "/api/movies",
    }),
    tagTypes: ["Movie"],
    endpoints: (build) => ({
        getAllMovies: build.query<MovieDto[], void>({
            query: () => ({
                url: "",
            }),
            providesTags: ["Movie"],
            transformResponse: (response:  MovieMessage[] ) => { return MovieMapper.toMovieDtoList(response);}
        }),
        postAddMoveById: build.mutation<void, string>({
            query: (movieId) => ({
                url: "/"+movieId,
                method: "POST",
                headers: {
                    "X-XSRF-TOKEN": document.cookie.replace(/(?:(?:^|.*;\s*)XSRF-TOKEN\s*=\s*([^;]*).*$)|^.*$/, '$1')
                }
            }),
            invalidatesTags: ["Movie"]
        })
    })
});

export const { useGetAllMoviesQuery, usePostAddMoveByIdMutation } = movieApi;