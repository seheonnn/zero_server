package dev.neordinary.zero.dto;

import dev.neordinary.zero.converter.UserConverter;
import dev.neordinary.zero.domain.Gender;
import dev.neordinary.zero.domain.UserEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class UserReqRecord {
    public static record UserJoin(
            @NotBlank(message = "이름은 필수값입니다.")
            String name,
            @NotNull(message = "키는 필수값입니다.")
            Integer height,
            @NotNull(message = "몸무게 필수값입니다.")
            Integer weight,
            Gender gender) {
        public UserEntity toUser() {
            return UserEntity.createUser(name, height, weight, gender);
        }
    }
}
