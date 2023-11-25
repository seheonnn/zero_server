package dev.neordinary.zero.converter;

import dev.neordinary.zero.domain.NoteEntity;
import dev.neordinary.zero.domain.UserEntity;
import dev.neordinary.zero.dto.NoteRequest;
import dev.neordinary.zero.dto.NoteResponse;

public class NoteConverter {

    public static NoteEntity toNote(NoteRequest.NoteJoin noteJoin, UserEntity user) {
        return NoteEntity.builder()
                .name(noteJoin.getProductName())
                .user(user)
                .sugar(noteJoin.getProductSugar())
                .calorie(noteJoin.getProductKcal())
                .capacity(noteJoin.getProductSize())
                .build();
    }

    public static NoteResponse.NoteJoinRes toNoteDto(NoteEntity note) {
        return NoteResponse.NoteJoinRes.builder()
                .user_id(note.getUser().getUser_id())
                .productName(note.getName())
                .productSugar(note.getSugar())
                .productKcal(note.getCalorie())
                .productSize(note.getCapacity())
                .build();
    }
}
