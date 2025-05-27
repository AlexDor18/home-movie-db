import { useGetUserQuery } from "../../redux/api/userApi";

const UserInfo = () => {
    const { data: user } = useGetUserQuery();

    return (
        <div>
            Hello {user?.username}
        </div>
    );
};

export default UserInfo;