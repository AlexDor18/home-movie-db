import { render, screen } from '@testing-library/react'
import { Header } from '../../../components/Header/Header'
import { describe, expect, it } from 'vitest'

describe('Header', () => {
  it.skip('renders component', () => {
    render(<Header />)

    expect(screen.getByText('Home Movie DB')).toBeInTheDocument()
  })
})
