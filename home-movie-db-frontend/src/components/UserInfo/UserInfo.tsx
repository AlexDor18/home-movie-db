import { useGetUserQuery } from "../../redux/api/userApi";

const UserInfo = () => {
    const { data: user } = useGetUserQuery();

    if(user === undefined){
        return <></>;
    }

    return (
        <div className="ml-auto">
            Hallo {user?.surname}, {user?.prename}
        </div>
    );
};

export default UserInfo;