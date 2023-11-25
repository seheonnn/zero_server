package dev.neordinary.zero.controller;

import dev.neordinary.zero.dto.*;
import dev.neordinary.zero.service.NoteService;
import dev.neordinary.zero.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final NoteService noteService;

    @PostMapping("/api/v1/user")
    public UserResponse.UserJoinRes join(@RequestBody UserRequest.UserJoin userJoin) {
        return userService.join(userJoin);
    }

    @GetMapping("/api/v1/user/{userId}")
    public UserResponse.UserBeverageRes showUserBeverage(@PathVariable Long userId) {
        return userService.showUserBeverage(userId);
    }

    @PostMapping("/api/v1/user/{userId}/note")
    public NoteResponse.NoteJoinRes createNote(@PathVariable Long userId, @RequestBody NoteRequest.NoteJoin noteJoin) {
        return noteService.createNote(userId, noteJoin);
    }

//    @PostMapping("/api/v2/user")
//    public UserResponse.UserJoinRes joinV2(@RequestBody @Valid UserReqRecord.UserJoin userJoinRecord) {
//        return userService.joinV2(userJoinRecord);
//    }
}
