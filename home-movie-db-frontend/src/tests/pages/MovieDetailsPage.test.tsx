import { describe, it, expect, vi, beforeEach } from "vitest";
import { render, screen, fireEvent } from "@testing-library/react";
import { MemoryRouter, Route, Routes } from "react-router";
import MovieDetailsPage from "../../pages/MovieDetailsPage";

// Dynamische Mock-Funktionen, die im Test überschrieben werden können
let mockUseGetAllMoviesQuery = vi.fn();
let mockUseGetMovieByIdQuery = vi.fn();
let mockUsePostAddMoveByIdMutation = vi.fn();
let mockUseDeleteMovieByIdMutation = vi.fn();

vi.mock("../../redux/api/movieApi", () => ({
    useGetAllMoviesQuery: (...args: any[]) => mockUseGetAllMoviesQuery(...args),
    useGetMovieByIdQuery: (...args: any[]) => mockUseGetMovieByIdQuery(...args),
    usePostAddMoveByIdMutation: (...args: any[]) => mockUsePostAddMoveByIdMutation(...args),
    useDeleteMovieByIdMutation: (...args: any[]) => mockUseDeleteMovieByIdMutation(...args),
}));
vi.mock("../../components/LoadingSpinner/LoadingSpinner", () => ({
    default: () => <div data-testid="spinner" />,
}));

const renderWithRouter = (id: string) =>
    render(
        <MemoryRouter initialEntries={[`/movie/${id}`]}>
            <Routes>
                <Route path="/movie/:id" element={<MovieDetailsPage />} />
            </Routes>
        </MemoryRouter>
    );

describe("MovieDetailsPage", () => {
    const movie = {
        id: "1",
        title: "Test Movie",
        thumbnailUrl: "test.jpg",
        genres: ["Action", "Comedy"],
        overview: "A test movie.",
    };

    beforeEach(() => {
        vi.clearAllMocks();
        mockUseGetAllMoviesQuery = vi.fn();
        mockUseGetMovieByIdQuery = vi.fn();
        mockUsePostAddMoveByIdMutation = vi.fn().mockReturnValue([vi.fn()]);
        mockUseDeleteMovieByIdMutation = vi.fn().mockReturnValue([vi.fn()]);
    });

    it("shows loading spinner when loading", () => {
        mockUseGetMovieByIdQuery.mockReturnValue({ isLoading: true });
        mockUseGetAllMoviesQuery.mockReturnValue({ data: [] });
        renderWithRouter("1");
        expect(screen.getByTestId("spinner")).toBeInTheDocument();
    });

    it("renders movie details and 'Entfernen' button if movie exists", () => {
        mockUseGetMovieByIdQuery.mockReturnValue({ data: movie, isLoading: false });
        mockUseGetAllMoviesQuery.mockReturnValue({ data: [movie] });
        mockUseDeleteMovieByIdMutation.mockReturnValue([vi.fn()]);
        renderWithRouter("1");
        expect(screen.getByText(movie.title)).toBeInTheDocument();
        expect(screen.getByText("Entfernen")).toBeInTheDocument();
        expect(screen.getByText("Action")).toBeInTheDocument();
        expect(screen.getByText("Comedy")).toBeInTheDocument();
        expect(screen.getByText(movie.overview)).toBeInTheDocument();
        expect(screen.getByAltText("Thumbnail")).toHaveAttribute("src", movie.thumbnailUrl);
    });

    it("renders 'Hinzufügen' button if movie does not exist", () => {
        mockUseGetMovieByIdQuery.mockReturnValue({ data: movie, isLoading: false });
        mockUseGetAllMoviesQuery.mockReturnValue({ data: [] });
        mockUsePostAddMoveByIdMutation.mockReturnValue([vi.fn()]);
        renderWithRouter("1");
        expect(screen.getByText("Hinzufügen")).toBeInTheDocument();
    });

    it("calls triggerDelete when 'Entfernen' is clicked", () => {
        const triggerDelete = vi.fn();
        mockUseGetMovieByIdQuery.mockReturnValue({ data: movie, isLoading: false });
        mockUseGetAllMoviesQuery.mockReturnValue({ data: [movie] });
        mockUseDeleteMovieByIdMutation.mockReturnValue([triggerDelete]);
        renderWithRouter("1");
        fireEvent.click(screen.getByText("Entfernen"));
        expect(triggerDelete).toHaveBeenCalledWith(movie.id);
    });

    it("calls triggerAdd when 'Hinzufügen' is clicked", () => {
        const triggerAdd = vi.fn();
        mockUseGetMovieByIdQuery.mockReturnValue({ data: movie, isLoading: false });
        mockUseGetAllMoviesQuery.mockReturnValue({ data: [] });
        mockUsePostAddMoveByIdMutation.mockReturnValue([triggerAdd]);
        renderWithRouter("1");
        fireEvent.click(screen.getByText("Hinzufügen"));
        expect(triggerAdd).toHaveBeenCalledWith(movie.id);
    });

    it("renders back link", () => {
        mockUseGetMovieByIdQuery.mockReturnValue({ data: movie, isLoading: false });
        mockUseGetAllMoviesQuery.mockReturnValue({ data: [movie] });
        renderWithRouter("1");
        expect(screen.getByText(/Zurück zur Startseite/)).toBeInTheDocument();
    });
});