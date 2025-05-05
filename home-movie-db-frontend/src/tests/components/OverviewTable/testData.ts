import { MovieDto } from "../../models/Movie";

export const testData : MovieDto[] = [
    {
        id: 1,
        title: 'The Shawshank Redemption',
        overview:
            'Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.',
        genres: ['Drama', 'Crime'],
        thumbnailUrl: 'https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg',
    },
    {
        id: 2,
        title: 'The Godfather',
        overview:
            'The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.',
        genres: ['Crime', 'Drama'],
        thumbnailUrl: 'https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg',
    },
    {
        id: 3,
        title: 'The Dark Knight',
        overview:
            'When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of his greatest challenges as a symbol of the night.',
        genres: ['Action', 'Crime', 'Drama'],
        thumbnailUrl: 'https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_SX300.jpg',
    },
    {
        id: 4,
        title: 'The Lord of the Rings: The Return of the King',
        overview:
            'Gandalf and Aragorn lead the World of Men against Sauron\'s army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring.',
        genres: ['Adventure', 'Drama', 'Fantasy'],
        thumbnailUrl: 'https://m.media-amazon.com/images/M/MV5BNzA5ZDNlNjQtMmUyOS00YjFmLWI0YmEtNTY4ZGJkYjQ3NTg3XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg',
    },
    {
        id: 5,
        title: 'Inception',
        overview:
            'A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.',
        genres: ['Action', 'Adventure', 'Sci-Fi'],
        thumbnailUrl: 'https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_SX300.jpg',
    },
]