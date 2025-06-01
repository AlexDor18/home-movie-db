import { render, screen } from '@testing-library/react';
import { describe, expect, it } from 'vitest';
import Layout from '../../pages/Layout';
import { Provider } from 'react-redux';
import { store } from '../../redux/store';

describe('Layout', () => {
  it('renders Header, Outlet and Footer components', () => {
    render(
    <Provider store={store}>
    <Layout />
    </Provider>);
    
    expect(screen.getByText(/Home Movie DB/)).toBeInTheDocument();
    expect(screen.getByText(/Copyright/)).toBeInTheDocument();
  });
});
