import {
  ColumnDef,
  flexRender,
  getCoreRowModel,
  getFilteredRowModel,
  getPaginationRowModel,
  getSortedRowModel,
  useReactTable,
} from '@tanstack/react-table'

interface OverviewTableProps<T> {
  data: T[]
  columns: ColumnDef<T>[]
}

function OverviewTable<T> (props: Readonly<OverviewTableProps<T>>) {
  const table = useReactTable({
    data: props.data,
    columns: props.columns,
    getCoreRowModel: getCoreRowModel(),
    getFilteredRowModel: getFilteredRowModel(),
    getSortedRowModel: getSortedRowModel(),
    getPaginationRowModel: getPaginationRowModel(),
  })

  return (
    <table className='mx-auto mb-6 border-[#BBBBBB] border-solid border-[1px] max-w-[1650px]'>
      <thead>
        {table.getHeaderGroups().map(headerGroup => (
          <tr key={headerGroup.id}>
            {headerGroup.headers.map(header => (
              <th key={header.id} className='bg-[#65C576] border-r-black border-r-[1px] border-solid last:border-r-0 py-2'>
                {header.isPlaceholder
                  ? null
                  : flexRender(
                      header.column.columnDef.header,
                      header.getContext()
                    )}
              </th>
            ))}
          </tr>
        ))}
      </thead>
      <tbody>
        {table.getRowModel().rows.map(row => (
          <tr key={row.id} className='odd:bg-white even:bg-[#65C576]/16'>
            {row.getVisibleCells().map(cell => (
              <td key={cell.id} className='text-center px-3 py-6 border-r-[#BBBBBB] border-r-[1px] border-solid last:border-r-0 min-w-32'>
                {flexRender(cell.column.columnDef.cell, cell.getContext())}
              </td>
            ))}
          </tr>
        ))}
      </tbody>
    </table>
  )
}

export default OverviewTable
