package dev.neordinary.zero.controller;

import dev.neordinary.zero.dto.NoteResponse;
import dev.neordinary.zero.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class NoteController {

    private final NoteService noteService;


}
