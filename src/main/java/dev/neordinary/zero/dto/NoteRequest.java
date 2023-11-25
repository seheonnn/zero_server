package dev.neordinary.zero.dto;

import lombok.Getter;
import lombok.Setter;

public class NoteRequest {

    @Getter @Setter
    public static class NoteJoin {
        private String productName;
        private Double productSugar;
        private Integer productKcal;
        private Integer productSize;
    }
}
