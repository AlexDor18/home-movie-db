interface UserDto{
    id: number,
    prename: string,
    surname: string,
    username: string
}

interface UserMessage {
    id: number,
    prename: string,
    surname: string,
    username: string
}

export type { UserDto, UserMessage };