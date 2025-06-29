import { UserDto, UserMessage } from "../../models/User";

export class UserMapper {
    static toUserDto(userMessage: UserMessage): UserDto {
        return {
            id: userMessage.id,
            prename: userMessage.prename,
            surname: userMessage.surname,
            username: userMessage.username,
        };
    }
}
