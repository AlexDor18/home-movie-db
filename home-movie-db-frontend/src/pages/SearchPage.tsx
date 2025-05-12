import OverviewTable from "../components/OverviewTable/OverviewTable";
import { selectMovieColumns } from "../components/OverviewTable/selectMovieColumns";
import SearchBar from "../components/SearchBar/SearchBar";
import { useLazyGetSearchMoviesQuery } from "../redux/api/searchApi";

const SearchPage = () => {

    const [trigger, result] = useLazyGetSearchMoviesQuery();

    const onSubmit = (data  : {searchInput: string}) => {
        trigger(data.searchInput);
    }

    return (
        <div>
            <SearchBar onSubmit={onSubmit} />
            <OverviewTable data={result.data ||[]} columns={selectMovieColumns} />
        </div>
    )
}

export default SearchPage;