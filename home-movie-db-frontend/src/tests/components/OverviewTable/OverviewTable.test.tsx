import { render, screen } from '@testing-library/react';
import { describe, expect, it } from 'vitest';
import OverviewTable from '../../../components/OverviewTable/OverviewTable';
import { testData } from './testData';
import { columns } from '../../../components/OverviewTable/columns';

describe('OverviewTable', () => {
  it('renders the OverviewTable component', () => {
    const movies = testData;
    
    render(<OverviewTable columns={columns} data={movies}/>);
    
    expect(screen.getByText(/Title/)).toBeInTheDocument();
    expect(screen.getByText(/Beschreibung/)).toBeInTheDocument();
  });

  it('renders data inserted in the table', () => {
    const movies = testData;    

    render(<OverviewTable columns={columns} data={movies}/>);

    expect(screen.getByText(/The Shawshank Redemption/)).toBeInTheDocument();
  });
});
