import { render, screen } from '@testing-library/react';
import { describe, expect, it } from 'vitest';
import OverviewTable from '../../../components/OverviewTable/OverviewTable';

describe('OverviewTable', () => {
  it('renders the OverviewTable component', () => {
    render(<OverviewTable />);
    
    expect(screen.getByText(/Title/)).toBeInTheDocument();
    expect(screen.getByText(/Beschreibung/)).toBeInTheDocument();
  });

    it('renders data inserted in the table', () => {
        render(<OverviewTable />);

        expect(screen.getByText(/The Shawshank Redemption/)).toBeInTheDocument();
    });
});
