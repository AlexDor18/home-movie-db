import { ColumnDef } from "@tanstack/react-table";
import { MovieDto } from "../../models/Movie";

export const columns : ColumnDef<MovieDto>[] = [
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
];