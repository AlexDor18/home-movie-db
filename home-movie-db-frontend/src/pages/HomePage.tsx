import React from 'react';
import OverviewTable from '../components/OverviewTable/OverviewTable';
import { useGetAllMoviesQuery } from '../redux/api/movieApi';
import { columns } from '../components/OverviewTable/columns';

const HomePage: React.FC = () => {
  const {data} = useGetAllMoviesQuery();

  return (
    <div>
      <OverviewTable data={data || []} columns={columns} />
    </div>
  );
};

export default HomePage;

