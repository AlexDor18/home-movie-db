import { render, fireEvent, screen } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import NewUserForm from '../../../components/NewUserForm/NewUserForm';
import { describe, expect, it, vi } from 'vitest';
import { MemoryRouter } from 'react-router';

describe('NewUserForm', () => {
  it.skip('should call onSubmit with correct data', async () => {
    const user = userEvent.setup();

    const onSubmit = vi.fn().mockImplementation(() => {
      console.log("fired");
    });

    render(
        <MemoryRouter initialEntries={['/signup']}>
            <NewUserForm onSubmit={onSubmit} />
        </MemoryRouter>
    );

    fireEvent.change(screen.getByPlaceholderText('Vorname'), {
      target: { value: 'test-vorname' },
    });
    fireEvent.change(screen.getByPlaceholderText('Nachname'), {
      target: { value: 'test-nachname' },
    });
    fireEvent.change(screen.getByPlaceholderText('Username'), {
      target: { value: 'test-username' },
    });
    fireEvent.change(screen.getByPlaceholderText('Password'), {
      target: { value: 'test-password' },
    });

    const btn = screen.getByText('Registrieren')

    //console.log(btn)

    await user.click(btn);

    expect(onSubmit).toHaveBeenCalled();
  });
});
