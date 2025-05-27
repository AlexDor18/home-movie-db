import { render, screen } from '@testing-library/react';
import { describe, expect, it, vi } from 'vitest';
import HomePage from '../../pages/HomePage';

describe('HomePage', () => {
  it.skip('renders the homepage component', () => {

    vi.mock("../../redux/api/movieApi", () => ({
      useGetAllMoviesQuery: vi.fn().mockImplementation(() => ({
          data: [{title: "The Shawshank Redemption", overview: "Text"}]
      }))
    }))

    render(<HomePage />);
    
    expect(screen.getByText(/Title/)).toBeInTheDocument();
    expect(screen.getByText(/Beschreibung/)).toBeInTheDocument();
  });
});
