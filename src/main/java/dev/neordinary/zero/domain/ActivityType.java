package dev.neordinary.zero.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ActivityType {

    NONE(1,1.2, "NONE"),
    LIGHT(2,1.375, "LIGHT"),
    NORMAL(3,1.55, "NORMAL"),
    ACTIVE(4,1.725, "ACTIVE"),
    VERYACTIVE(5,1.9, "VERYACTIVE");
    private final int index;
    private final Double value;
    private final String role;


    @JsonValue
    public int getIndex() {
        return index;
    }

    public Double getActivity() { return value; }
}
