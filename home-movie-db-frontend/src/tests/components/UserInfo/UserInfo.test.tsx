import { render, screen } from '@testing-library/react';
import { beforeEach, describe, expect, it, vi } from 'vitest';
import UserInfo from '../../../components/UserInfo/UserInfo';
import { Provider } from 'react-redux';
import { store } from '../../../redux/store';
import { UserDto } from '../../../models/User';

describe('UserInfo', () => {
    beforeEach(() => {
        vi.resetAllMocks()
    })

  it('renders user info', () => {
    const user : UserDto = {
      prename: 'John',
      surname: 'Doe',
      id: 1,
      username: 'JohnDoe'
    }

    render(
        <UserInfo user={user}/>
    );

    expect(screen.getByText(`Hallo Doe, John`)).toBeInTheDocument();
  });

  it('renders empty component when user is undefined', () => {
    render(
      <Provider store={store}>
        <UserInfo  user={undefined}/>
      </Provider>
    );

    expect(screen.queryByText('Hallo')).not.toBeInTheDocument();
  });
});