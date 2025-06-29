import { ColumnDef } from "@tanstack/react-table";
import { MovieDto } from "../../models/Movie";
import { NavLink } from "react-router";

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
        cell: info => 
            <div className="flex flex-wrap flex-col mb-4">
                {(info.getValue() as string[]).map((genre) => (
                    <span key={genre} className="inline-block bg-[#65C576] text-black text-sm font-medium my-2 px-2.5 py-0.5 rounded">
                        {genre}
                    </span>
                ))}
            </div>
    },
    {
        accessorKey: "overview",
        header: "Beschreibung",
    },
    {
        accessorKey: "id",
        header: "",
        cell: info => (
            <NavLink to={`/movie/${info.getValue()}`} className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
                Details
            </NavLink>
        )
    }
];