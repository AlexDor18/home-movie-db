import { describe, expect, it } from 'vitest';
import { MovieMessage, MovieDto } from '../../models/Movie';
import MovieMapper from '../../services/mapper/MovieMapper';

describe('MovieMapper', () => {
  describe('toMovieDto', () => {
    it('should map MovieMessage to MovieDto', () => {
      const movieMessage: MovieMessage = {
        id: 1,
        title: 'Inception',
        overview: 'A mind-bending thriller',
        genres: ['Action', 'Sci-Fi'],
        thumbnailUrl: 'inception.jpg',
      };

      const expected: MovieDto = {
        id: 1,
        title: 'Inception',
        overview: 'A mind-bending thriller',
        genres: ['Action', 'Sci-Fi'],
        thumbnailUrl: 'inception.jpg',
      };

      const result = MovieMapper.toMovieDto(movieMessage);
      expect(result).toEqual(expected);
    });
  });

  describe('toMovieDtoList', () => {
    it('should map list of MovieMessage to list of MovieDto', () => {
      const movieMessages: MovieMessage[] = [
        {
          id: 1,
          title: 'Inception',
          overview: 'A mind-bending thriller',
          genres: ['Action', 'Sci-Fi'],
          thumbnailUrl: 'inception.jpg',
        },
        {
          id: 2,
          title: 'Interstellar',
          overview: 'A journey through space',
          genres: ['Adventure', 'Drama'],
          thumbnailUrl: 'interstellar.jpg',
        },
      ];

      const expected: MovieDto[] = [
        {
          id: 1,
          title: 'Inception',
          overview: 'A mind-bending thriller',
          genres: ['Action', 'Sci-Fi'],
          thumbnailUrl: 'inception.jpg',
        },
        {
          id: 2,
          title: 'Interstellar',
          overview: 'A journey through space',
          genres: ['Adventure', 'Drama'],
          thumbnailUrl: 'interstellar.jpg',
        },
      ];

      const result = MovieMapper.toMovieDtoList(movieMessages);
      expect(result).toEqual(expected);
    });
  });
});

