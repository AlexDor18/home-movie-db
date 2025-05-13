import { ColumnDef } from "@tanstack/react-table";
import { MovieDto } from "../../models/Movie";
import AddMovieButton from "../AddMovieButton/AddMovieButton";

export const selectMovieColumns : ColumnDef<MovieDto>[] = [
    {
        accessorKey: "thumbnailUrl",
        header: "",
        cell: info => <img src={info.getValue() as string} alt="Thumbnail" className="" />
    },
    {
        accessorKey: "title",
        header: "Title",
    },
    {
        accessorKey: "genres",
        header: "Genres",
    },
    {
        accessorKey: "overview",
        header: "Beschreibung",
    },
    {
        header: "",
        id: "add-movie",
        cell: info => <AddMovieButton id={info.row.original.id.toString()}/>
    }
];