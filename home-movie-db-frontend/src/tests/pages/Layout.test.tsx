import { render, screen } from '@testing-library/react';
import { describe, expect, it } from 'vitest';
import Layout from '../../pages/Layout';

describe('Layout', () => {
  it('renders Header, Outlet and Footer components', () => {
    render(<Layout />);
    
    expect(screen.getByText(/Home Movie DB/)).toBeInTheDocument();
    expect(screen.getByText(/Copyright/)).toBeInTheDocument();
  });
});
