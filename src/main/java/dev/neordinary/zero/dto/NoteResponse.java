package dev.neordinary.zero.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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

    @Builder
    @AllArgsConstructor
    @Getter @Setter
    public static class GetNoteRes {
        private Long user_id;
        private String username;
        private Object totalKcal;
        private Object totalSugar;
        private Boolean[] completedDate;
    }
}
