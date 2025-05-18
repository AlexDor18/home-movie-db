import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { Login } from "../../models/Login";

export const loginApi = createApi({
    reducerPath: "loginApi",
    baseQuery: fetchBaseQuery({
        baseUrl: "/login",
    }),
    endpoints: (build) => ({
        login: build.mutation<void, Login>({
            query: (creds) => ({
                url: "",
                method: "POST",
                body: new URLSearchParams({
                    username: creds.username,
                    password: creds.password
                }),
                headers: {
                    "Content-Type": "application/www-form-urlencoded"
                }
            })
        })
    }),
})

export const { useLoginMutation } = loginApi