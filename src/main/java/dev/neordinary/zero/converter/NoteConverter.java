package dev.neordinary.zero.converter;

import dev.neordinary.zero.domain.NoteEntity;
import dev.neordinary.zero.domain.UserEntity;
import dev.neordinary.zero.dto.NoteRequest;
import dev.neordinary.zero.dto.NoteResponse;

public class NoteConverter {

    public static NoteEntity toNote(NoteRequest.NoteJoin noteJoin, UserEntity user) {
        return NoteEntity.builder()
                .name(noteJoin.getName())
                .user(user)
                .sugar(noteJoin.getSugar())
                .calorie(noteJoin.getCalorie())
                .capacity(noteJoin.getCapacity())
                .build();
    }

    public static NoteResponse.NoteJoinRes toNoteDto(NoteEntity note) {
        return NoteResponse.NoteJoinRes.builder()
                .user_id(note.getUser().getUser_id())
                .name(note.getName())
                .sugar(note.getSugar())
                .calorie(note.getCalorie())
                .capacity(note.getCapacity())
                .build();
    }
}
