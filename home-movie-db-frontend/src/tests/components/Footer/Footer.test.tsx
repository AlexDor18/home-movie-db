import { render, screen } from '@testing-library/react';
import { Footer } from '../../../components/Footer/Footer';
import { describe, expect, it } from 'vitest';

describe('Footer', () => {
  it('renders footer text', () => {
    render(<Footer />);
    
    expect(screen.getByText(/Copyright/)).toBeInTheDocument();
    expect(screen.getByText(new Date().getFullYear(), { exact: false })).toBeInTheDocument();
  });
});
