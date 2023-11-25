package dev.neordinary.zero.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class NoteResponse {

    @Builder
    @AllArgsConstructor
    @Getter @Setter
    public static class NoteJoinRes {
        private Long user_id;
        private Double sugar;
        private Integer calorie;
        private Integer capacity;
    }
}
