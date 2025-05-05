interface MovieDto {
    id: number;
    title: string;
    overview: string;
    genres: string[];
    thumbnailUrl: string
}

interface MovieMessage {
    id: number;
    title: string;
    overview: string;
    genres: string[];
    thumbnailUrl: string
}

export type { MovieDto, MovieMessage };