import { render, screen } from '@testing-library/react';
import { describe, expect, it } from 'vitest';
import HomePage from '../../pages/HomePage';
import { Provider } from 'react-redux';
import { store } from '../../redux/store';

describe('HomePage', () => {
  it('renders the homepage component', () => {
    render(
    <Provider store={store}>
      <HomePage />
    </Provider>);
    
    expect(screen.getByText(/Title/)).toBeInTheDocument();
    expect(screen.getByText(/Beschreibung/)).toBeInTheDocument();
  });
});
