package dev.neordinary.zero.dto;

import dev.neordinary.zero.domain.Gender;
import lombok.*;

public class UserResponse {

    @Builder
    @AllArgsConstructor
    @Getter @Setter
    public static class UserJoinRes {
        private String name;
        private Integer height;
        private Integer weight;
        private Gender gender;
    }
}
