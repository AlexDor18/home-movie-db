import { render, screen } from '@testing-library/react';
import { describe, expect, it } from 'vitest';
import HomePage from '../../pages/HomePage';

describe('HomePage', () => {
  it('renders the homepage component', () => {
    render(<HomePage />);
    
    expect(screen.getByText(/Title/)).toBeInTheDocument();
    expect(screen.getByText(/Beschreibung/)).toBeInTheDocument();
  });
});
