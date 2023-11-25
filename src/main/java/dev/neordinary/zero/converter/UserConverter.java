package dev.neordinary.zero.converter;

import dev.neordinary.zero.domain.UserEntity;
import dev.neordinary.zero.dto.UserRequest;
import dev.neordinary.zero.dto.UserResponse;

public class UserConverter {

    public static UserEntity toUser(UserRequest.UserJoin userJoin) {
        return UserEntity.builder()
                .name(userJoin.getName())
                .height(userJoin.getHeight())
                .weight(userJoin.getWeight())
                .gender(userJoin.getGender())
                .build();
    }

    public static UserResponse.UserJoinRes toUserDto(UserEntity user) {
        return UserResponse.UserJoinRes.builder()
                .user_id(user.getId())
                .name(user.getName())
                .height(user.getHeight())
                .weight(user.getWeight())
                .gender(user.getGender())
                .build();
    }
}
