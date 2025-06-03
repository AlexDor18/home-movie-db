import { NavLink } from "react-router";
import OverviewTable from "../components/OverviewTable/OverviewTable";
import { selectMovieColumns } from "../components/OverviewTable/selectMovieColumns";
import SearchBar from "../components/SearchBar/SearchBar";
import { useLazyGetSearchMoviesQuery } from "../redux/api/searchApi";

const SearchPage = () => {

    const [trigger, result,] = useLazyGetSearchMoviesQuery();

    const onSubmit = (data  : {searchInput: string}) => {
        trigger(data.searchInput);
    }

    return (
        <div className="flex flex-col mx-8">
            <NavLink to="/home" className="my-3 text-blue-800 underline">{"<-"} Zurück zur Startseite</NavLink>
            <SearchBar onSubmit={onSubmit} />
            {result.data && <OverviewTable data={result.data ||[]} columns={selectMovieColumns} />}
            {result.isLoading && <p>Suche wird ausgeführt...</p>}
            {!result.data && <p>Geben Sie für die Suche einen Begriff in die Suchleiste ein</p>}
        </div>
    )
}

export default SearchPage;