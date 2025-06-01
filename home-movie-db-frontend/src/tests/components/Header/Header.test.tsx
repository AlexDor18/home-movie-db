import { render, screen } from '@testing-library/react'
import { Header } from '../../../components/Header/Header'
import { describe, expect, it, vi } from 'vitest'
import { Provider } from 'react-redux'
import { store } from '../../../redux/store'

describe('Header', () => {

  vi.mock('../../redux/api/userApi', () => ({
    useGetUserQuery: vi.fn().mockImplementation(() => ({
      data:  {
        prename: 'John',
        surname: 'Doe',
        id: 1,
        username: 'JohnDoe'
      } 
    }))
  }))

  it('renders component', () => {

    render(
    <Provider store={store}>
      <Header />
    </Provider>)

    expect(screen.getByText('Home Movie DB')).toBeInTheDocument()
  })
})
