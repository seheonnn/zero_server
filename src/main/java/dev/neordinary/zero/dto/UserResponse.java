package dev.neordinary.zero.dto;

import dev.neordinary.zero.domain.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class UserResponse {

    @Builder
    @AllArgsConstructor
    @Getter @Setter
    public static class UserJoinRes {
        private Long user_id;
        private String name;
        private Integer height;
        private Integer weight;
        private Integer age;
        private Gender gender;
    }

    @Builder
    @AllArgsConstructor
    @Getter @Setter
    public static class UserBeverageRes {
        private Long user_id;
        private String name;
        private Double sugar;
        private Integer calorie;
    }
}
