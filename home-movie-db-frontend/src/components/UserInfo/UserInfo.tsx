import { UserDto } from "../../models/User";

interface UserInfoProps {
    user: UserDto | undefined
}

const UserInfo = (props: UserInfoProps) => {
    const user = props.user;

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