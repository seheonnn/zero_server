package dev.neordinary.zero.dto;

import dev.neordinary.zero.domain.ActivityType;
import dev.neordinary.zero.domain.Gender;
import dev.neordinary.zero.domain.PurposeType;
import lombok.Getter;
import lombok.Setter;

import static dev.neordinary.zero.domain.Gender.MAN;

public class UserRequest {

    @Getter @Setter
    public static class UserJoin {
        private String name;
        private Integer height;
        private Integer weight;
        private Integer age;
        private Gender gender;
        private PurposeType purposeType;
        private ActivityType activityType;

        public Double calculateBMR() {
            Double bmr = (10 * weight) + (6.25 * height) - (5 * age);
            return gender.equals(MAN) ? (bmr + 5) * activityType.getActivity() : (bmr - 161) * activityType.getActivity();
        }
    }
}
