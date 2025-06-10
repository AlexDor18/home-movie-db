import { configureStore } from "@reduxjs/toolkit";
import { movieApi } from "./api/movieApi";
import { setupListeners } from "@reduxjs/toolkit/query";
import { searchApi } from "./api/searchApi";
import { userApi } from "./api/userApi";
import { authApi } from "./api/authApi";

export const store = configureStore({
    reducer: {
        [movieApi.reducerPath]: movieApi.reducer,
        [searchApi.reducerPath]: searchApi.reducer,
        [userApi.reducerPath]: userApi.reducer,
        [authApi.reducerPath]: authApi.reducer
    },

    middleware: (getDefaultMiddleware) => getDefaultMiddleware().concat(movieApi.middleware).concat(searchApi.middleware).concat(userApi.middleware).concat(authApi.middleware),
});

setupListeners(store.dispatch);