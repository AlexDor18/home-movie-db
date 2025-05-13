import { render, screen, fireEvent } from '@testing-library/react';
import { describe, expect, it, vi } from 'vitest';
import AddMovieButton from '../../../components/AddMovieButton/AddMovieButton';
import { Provider } from 'react-redux';
import { store } from '../../../redux/store';

describe('AddMovieButton', () => {
  it('renders the AddMovieButton component', () => {
    render(
        <Provider store={store}>
            <AddMovieButton id='1'/>
        </Provider>);
    expect(screen.getByText(/Add Movie/i)).toBeInTheDocument();
  });
});

