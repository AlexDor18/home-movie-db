import { render, screen } from "@testing-library/react";
import { describe, expect, it, vi } from "vitest";
import SearchBar from "../../../components/SearchBar/SearchBar";

describe('SearchPage', () => {

    it('renders the SearchPage component', () => {
        const submit = vi.fn();
      
        render(<SearchBar onSubmit={submit} />);
      
        expect(screen.getByPlaceholderText('Search...')).toBeInTheDocument();
        expect(screen.getByRole('button', { name: 'Search' })).toBeInTheDocument();
    });
});