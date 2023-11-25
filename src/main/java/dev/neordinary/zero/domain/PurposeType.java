package dev.neordinary.zero.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PurposeType {

    HEALTH(1, "HEALTH"),
    DIABETES(2, "DIABETES");

    private final int index;
    private final String role;


    @JsonValue
    public int getIndex() {
        return index;
    }
}
