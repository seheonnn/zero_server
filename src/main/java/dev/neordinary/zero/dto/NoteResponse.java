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
        private String productName;
        private Double productSugar;
        private Integer productKcal;
        private Integer productSize;
    }
}
