import React from 'react';
import OverviewTable from '../components/OverviewTable/OverviewTable';
import { testData } from '../components/OverviewTable/testData';
import { useGetAllMoviesQuery } from '../redux/api/movieApi';

const HomePage: React.FC = () => {
  const datatest = testData; 

  const {data, error, isLoading} = useGetAllMoviesQuery();

  return (
    <div>
      <OverviewTable movies={data || []} />
    </div>
  );
};

export default HomePage;

