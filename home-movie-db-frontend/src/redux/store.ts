import { configureStore } from "@reduxjs/toolkit";
import { movieApi } from "./api/movieApi";
import { setupListeners } from "@reduxjs/toolkit/query";
import { searchApi } from "./api/searchApi";

export const store = configureStore({
    reducer: {
        [movieApi.reducerPath]: movieApi.reducer,
        [searchApi.reducerPath]: searchApi.reducer
    },

    middleware: (getDefaultMiddleware) => getDefaultMiddleware().concat(movieApi.middleware).concat(searchApi.middleware)
});

setupListeners(store.dispatch);