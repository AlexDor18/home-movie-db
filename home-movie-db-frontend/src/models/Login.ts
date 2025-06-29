interface Login {
    username: string;
    password: string;
}

interface NewUser {
    prename: string;
    surname: string;
    username: string;
    password: string;
}

export type { Login, NewUser }