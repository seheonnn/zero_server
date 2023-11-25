package dev.neordinary.zero.controller;

import dev.neordinary.zero.dto.UserReqRecord;
import dev.neordinary.zero.dto.UserRequest;
import dev.neordinary.zero.dto.UserResponse;
import dev.neordinary.zero.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/api/v1/user")
    public UserResponse.UserJoinRes join(@RequestBody UserRequest.UserJoin userJoin) {
        return userService.join(userJoin);
    }

    @GetMapping("/api/v1/user/{userId}")
    public UserResponse.UserBeverageRes showUserBeverage(@PathVariable Long userId) {
        return userService.showUserBeverage(userId);
    }

//    @PostMapping("/api/v2/user")
//    public UserResponse.UserJoinRes joinV2(@RequestBody @Valid UserReqRecord.UserJoin userJoinRecord) {
//        return userService.joinV2(userJoinRecord);
//    }
}
