import React from 'react';
import OverviewTable from '../components/OverviewTable/OverviewTable';
import { useGetAllMoviesQuery } from '../redux/api/movieApi';
import { columns } from '../components/OverviewTable/columns';
import { NavLink } from 'react-router';

const HomePage: React.FC = () => {
  const {data} = useGetAllMoviesQuery();

  return (
    <div className='flex flex-col mx-28'>
      <div className='flex items-center my-8'>
        <h3 className='text-xl font-bold flex-block'>Meine Filme</h3>
        <NavLink to="/search" className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded ml-auto h-max">Suche</NavLink>
      </div>
      {data &&<OverviewTable data={data || []} columns={columns} />}
      {!data && <p>Noch keine Filme hinzugefügt!</p>}
    </div>
  );
};

export default HomePage;

