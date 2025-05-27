import { render, screen } from '@testing-library/react';
import { describe, expect, it } from 'vitest';
import App from '../App';
import { Provider } from 'react-redux';
import { store } from '../redux/store';

describe('App', () => {
    it.skip('renders the App component', () => {
        render(
        <Provider store={store}>
        <App />
        </Provider>);
        
        expect(screen.getByText(/Willkommen/)).toBeInTheDocument();
    });
});
