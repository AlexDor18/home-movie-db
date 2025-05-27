import { render, screen } from '@testing-library/react'
import { MemoryRouter } from 'react-router'
import { describe, expect, it } from 'vitest'
import NotFoundPage from '../../pages/NotFoundPage'

describe('NotFoundPage', () => {
  it.skip('renders 404 page not found', async () => {
    render(
      <MemoryRouter initialEntries={['/not-found']}>
        <NotFoundPage />
      </MemoryRouter>
    )

    expect(screen.getByText('404 Not Found')).toBeInTheDocument()

    const linkElement = await screen.getByText('Nothing found on route /not-found');

    expect(linkElement).toBeInTheDocument()
  })
})
