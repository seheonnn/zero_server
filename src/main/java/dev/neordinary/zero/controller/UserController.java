package dev.neordinary.zero.controller;

import dev.neordinary.zero.base.BaseResponse;
import dev.neordinary.zero.dto.NoteRequest;
import dev.neordinary.zero.dto.NoteResponse;
import dev.neordinary.zero.dto.UserRequest;
import dev.neordinary.zero.service.NoteService;
import dev.neordinary.zero.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    private final NoteService noteService;

    @PostMapping("")
    public ResponseEntity<BaseResponse> join(@RequestBody UserRequest.UserJoin userJoin) {
        return BaseResponse.toResponseEntityContainsResult(userService.join(userJoin));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<BaseResponse> showUserBeverage(@PathVariable Long userId) {
        return BaseResponse.toResponseEntityContainsResult(userService.showUserBeverage(userId));
    }

    @PostMapping("/{userId}/note")
    public ResponseEntity<BaseResponse> createNote(@PathVariable Long userId, @RequestBody NoteRequest.NoteJoin noteJoin) {
        return BaseResponse.toResponseEntityContainsResult(noteService.createNote(userId, noteJoin));
    }

    @GetMapping("/{userId}/note")
    public NoteResponse.NoteJoinRes getNot(@PathVariable Long userId) {
        return noteService.getNote(userId);
    }

    @GetMapping("/{userId}/purpose")
    public ResponseEntity<BaseResponse> getPurpose(@PathVariable Long userId) {
        return BaseResponse.toResponseEntityContainsResult(userService.getPurpose(userId));
    }

//    @PostMapping("/api/v2/user")
//    public UserResponse.UserJoinRes joinV2(@RequestBody @Valid UserReqRecord.UserJoin userJoinRecord) {
//        return userService.joinV2(userJoinRecord);
//    }
}
