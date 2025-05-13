import { render, screen } from '@testing-library/react';
import { Provider } from 'react-redux';
import { describe, expect, it } from 'vitest';
import AddMovieButton from '../../../components/AddMovieButton/AddMovieButton';
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

