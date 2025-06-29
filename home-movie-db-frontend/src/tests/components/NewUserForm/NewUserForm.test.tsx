import { render, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { MemoryRouter } from 'react-router';
import { describe, expect, it, vi } from 'vitest';
import NewUserForm from '../../../components/NewUserForm/NewUserForm';

describe('NewUserForm', () => {
  it('should call onSubmit with correct data', async () => {
    const user = userEvent.setup();

    const onSubmit = vi.fn().mockImplementation(() => {
      console.log("fired");
    });

    render(
        <MemoryRouter initialEntries={['/signup']}>
            <NewUserForm onSubmit={onSubmit} error={{isError: false, message: ""}} isLoading={false}/>
        </MemoryRouter>
    );

    await user.click(screen.getByPlaceholderText('Vorname'));
    await user.keyboard('test-prename');

    await user.click(screen.getByPlaceholderText('Nachname'));
    await user.keyboard('test-nachname');

    await user.click(screen.getByPlaceholderText('Username'));
    await user.keyboard('test-username');

    await user.click(screen.getByPlaceholderText('Password'));
    await user.keyboard('test-password');

    const btn = screen.getByText('Registrieren')

    await user.click(btn);

    expect(onSubmit).toHaveBeenCalled();
  });
});
