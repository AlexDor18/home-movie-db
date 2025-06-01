import React from 'react';
import OverviewTable from '../components/OverviewTable/OverviewTable';
import { useGetAllMoviesQuery } from '../redux/api/movieApi';
import { columns } from '../components/OverviewTable/columns';

const HomePage: React.FC = () => {
  const {data} = useGetAllMoviesQuery();

  return (
    <div className='flex flex-col mx-28 '>
      <h3 className='text-xl font-bold flex-block my-6'>Meine Filme</h3>
      {data &&<OverviewTable data={data || []} columns={columns} />}
      {!data && <p>Noch keine Filme hinzugefügt!</p>}
    </div>
  );
};

export default HomePage;

