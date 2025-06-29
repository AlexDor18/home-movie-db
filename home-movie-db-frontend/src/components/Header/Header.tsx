import React from 'react'
import UserInfo from '../UserInfo/UserInfo'
import { useGetUserQuery } from '../../redux/api/userApi';

export const Header: React.FC = () => {

  const { data } = useGetUserQuery();

  return (
    <header className="bg-white border-b-2 border-b-[#ccc] border-b-solid px-8 py-4 shadow-md flex">
      <img alt="Home Movie DB Logo" src="/logo.png" className="w-8 h-8 mr-4" />
      <h1 className="text-xl font-bold">Home Movie DB</h1>
      <UserInfo user={data}/>
    </header>
  )
}
