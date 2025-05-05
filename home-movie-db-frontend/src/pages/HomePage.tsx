import React from 'react';
import OverviewTable from '../components/OverviewTable/OverviewTable';
import { useGetAllMoviesQuery } from '../redux/api/movieApi';

const HomePage: React.FC = () => {
  const {data, error, isLoading} = useGetAllMoviesQuery();

  return (
    <div>
      <OverviewTable movies={data || []} />
    </div>
  );
};

export default HomePage;

