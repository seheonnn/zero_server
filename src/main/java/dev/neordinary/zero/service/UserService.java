package dev.neordinary.zero.service;

import dev.neordinary.zero.converter.UserConverter;
import dev.neordinary.zero.domain.UserEntity;
import dev.neordinary.zero.domain.UserRepository;
import dev.neordinary.zero.dto.UserRequest;
import dev.neordinary.zero.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserResponse.UserJoinRes join(UserRequest.UserJoin userJoin) {
        UserEntity newUser = UserConverter.toUser(userJoin);
        return UserConverter.toUserDto(userRepository.save(newUser));
    }
}
