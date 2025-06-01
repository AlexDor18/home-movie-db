import React from 'react'
import UserInfo from '../UserInfo/UserInfo'

export const Header: React.FC = () => {

  return (
    <header className="bg-white border-b-2 border-b-[#ccc] border-b-solid px-8 py-4 shadow-md flex">
      <h1 className="text-xl font-bold">Home Movie DB</h1>
      <UserInfo />
    </header>
  )
}
