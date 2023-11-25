package dev.neordinary.zero.converter;

import dev.neordinary.zero.domain.Gender;
import dev.neordinary.zero.domain.UserEntity;
import dev.neordinary.zero.dto.UserRequest;
import dev.neordinary.zero.dto.UserResponse;

import static dev.neordinary.zero.domain.Gender.*;

public class UserConverter {

    public static UserEntity toUser(UserRequest.UserJoin userJoin) {
        return UserEntity.builder()
                .name(userJoin.getName())
                .height(userJoin.getHeight())
                .weight(userJoin.getWeight())
                .age(userJoin.getAge())
                .gender(userJoin.getGender())
                .maxSugar(userJoin.calculateBMR() * 0.1 / 3.867)
                .maxCalorie(userJoin.calculateBMR())
                .build();
    }

    public static UserResponse.UserJoinRes toUserDto(UserEntity user) {
        return UserResponse.UserJoinRes.builder()
                .user_id(user.getUser_id())
                .name(user.getName())
                .height(user.getHeight())
                .weight(user.getWeight())
                .age(user.getAge())
                .gender(user.getGender())
                .build();
    }

    public static UserResponse.UserBeverageRes toUserBeverageDto(UserEntity user) {
        return UserResponse.UserBeverageRes.builder()
                .user_id(user.getUser_id())
                .name(user.getName())
                .build();
    }
}
