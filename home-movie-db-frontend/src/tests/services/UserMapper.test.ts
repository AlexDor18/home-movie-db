import { describe, expect, it } from "vitest";
import { UserDto, UserMessage } from "../../models/User";
import { UserMapper } from "../../services/mapper/UserMapper";

describe('UserMapper', () => {
    it('should map UserMessage to UserDto', () => {
      // Arrange
      const userMessage: UserMessage = {
        id: 1,
        prename: 'John',
        surname: 'Doe',
        username: 'johndoe',
      };

      const expected: UserDto = {
        id: 1,
        prename: 'John',
        surname: 'Doe',
        username: 'johndoe',
      };

      // Act
      const result = UserMapper.toUserDto(userMessage);

      // Assert
      expect(result).toEqual(expected);
    });
  });