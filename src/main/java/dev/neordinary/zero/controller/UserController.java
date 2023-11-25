package dev.neordinary.zero.controller;

import dev.neordinary.zero.dto.UserRequest;
import dev.neordinary.zero.dto.UserResponse;
import dev.neordinary.zero.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    
    public UserResponse.UserJoinRes join(@RequestBody UserRequest.UserJoin userJoin) {
        return userService.join(userJoin);
    }
}
