import React from 'react'

export const Header: React.FC = () => {

  const one = parseInt('1');
  if(one == 1){
    console.log(one)
  }

  return (
    <header className="bg-white border-b-2 border-b-[#ccc] border-b-solid px-8 py-4 shadow-md">
      <h1 className="text-xl font-bold">Home Movie DB</h1>
    </header>
  )
}
