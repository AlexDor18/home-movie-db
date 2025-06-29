import React, { useEffect } from 'react';
import OverviewTable from '../components/OverviewTable/OverviewTable';
import { useGetAllMoviesQuery } from '../redux/api/movieApi';
import { columns } from '../components/OverviewTable/columns';
import { NavLink } from 'react-router';
import { MovieDto } from '../models/Movie';

const HomePage: React.FC = () => {
  const [movies, setMovies] = React.useState<MovieDto[]>([]);

  const {data} = useGetAllMoviesQuery();

  useEffect(() => {
    if (data) {
      setMovies(data);
    }
  }, [data]);

  return (
    <div className='flex flex-col mx-28'>
      <div className='flex items-center my-8'>
        <h3 className='text-xl font-bold flex-block'>Meine Filme</h3>
        <NavLink to="/search" className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded ml-auto h-max">Suche</NavLink>
      </div>
      {movies.length > 0 && <OverviewTable data={data || []} columns={columns} />}
      {movies.length === 0 && <p>Noch keine Filme hinzugefügt!</p>}
    </div>
  );
};

export default HomePage;

