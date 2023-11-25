package dev.neordinary.zero.domain;

public enum Gender {

    MAN("남성"), WOMAN("여성");

    private String displayValue;

    Gender(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
