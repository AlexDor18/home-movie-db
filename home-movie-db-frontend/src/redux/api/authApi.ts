import { fetchBaseQuery } from "@reduxjs/toolkit/query";
import { createApi } from "@reduxjs/toolkit/query/react";
import { NewUser } from "../../models/Login";

export const authApi = createApi({
    reducerPath: "authApi",
    baseQuery: fetchBaseQuery({
        baseUrl: "/auth",
    }),
    endpoints: (builder) => ({
        signup: builder.mutation<NewUser, NewUser>({
            query: (login) => ({
                url: "/signup",
                method: "POST",
                headers: {
                    "X-XSRF-TOKEN": document.cookie.replace(/(?:(?:^|.*;\s*)XSRF-TOKEN\s*=\s*([^;]*).*$)|^.*$/, '$1')
                },
                body: login,
            }),
        }),
    }),
});

export const { useSignupMutation } = authApi;