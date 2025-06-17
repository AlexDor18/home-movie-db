import { render, screen } from '@testing-library/react';
import { describe, expect, it, vi } from 'vitest';
import AddMovieButton from '../../../components/AddMovieButton/AddMovieButton';

describe('AddMovieButton', () => {
  it('renders the AddMovieButton component', () => {
    vi.mock('../../../redux/api/movieApi', () => ({
      useGetAllMoviesQuery: vi.fn().mockImplementation(() => ({
          data: [{title: "The Shawshank Redemption", overview: "Text", id: "1"}]
      })),
      usePostAddMoveByIdMutation: vi.fn().mockImplementation(() => {
        return [vi.fn(), {isLoading: false}];
      })
    }))

    render(<AddMovieButton id='2'/>);
    expect(screen.getByText(/Add Movie/i)).toBeInTheDocument();
  });

  it('renders the checkmark when movies is already added', () => {
    vi.mock('../../../redux/api/movieApi', () => ({
      useGetAllMoviesQuery: vi.fn().mockImplementation(() => ({
          data: [{title: "The Shawshank Redemption", overview: "Text", id: "1"}]
      })),
      usePostAddMoveByIdMutation: vi.fn().mockImplementation(() => {
        return [vi.fn(), {isLoading: false}];
      })
    }))
    
    render(
      <AddMovieButton id='1'/>
    );
    expect(screen.getByTestId('checkmark')).toBeInTheDocument();
  })
});

