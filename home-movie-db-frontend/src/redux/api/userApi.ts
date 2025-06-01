import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import { UserDto, UserMessage } from "../../models/User";
import { UserMapper } from "../../services/mapper/UserMapper";

export const userApi = createApi({
    reducerPath: "userApi",
    baseQuery: fetchBaseQuery({
        baseUrl: "/api/user",
    }),
    endpoints: (builder) => ({
        getUser: builder.query<UserDto, void>({
            query: () => ({
                url: "",
                method: "GET",
            }),

            transformResponse: (response: UserMessage) => {
                return UserMapper.toUserDto(response);
            }
        })
    }),
});

export const { useGetUserQuery } = userApi;