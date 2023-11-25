package dev.neordinary.zero.dto;

import lombok.Getter;
import lombok.Setter;

public class NoteRequest {

    @Getter @Setter
    public static class NoteJoin {
        private String name;
        private Double sugar;
        private Integer calorie;
        private Integer capacity;
    }
}
