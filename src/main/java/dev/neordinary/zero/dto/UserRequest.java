package dev.neordinary.zero.dto;

import dev.neordinary.zero.domain.Gender;
import lombok.Getter;
import lombok.Setter;

public class UserRequest {

    @Getter @Setter
    public static class UserJoin {
        private String name;
        private Integer height;
        private Integer weight;
        private Gender gender;
    }
}
