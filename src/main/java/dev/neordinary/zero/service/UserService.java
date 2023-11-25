package dev.neordinary.zero.service;

import dev.neordinary.zero.base.BaseException;
import dev.neordinary.zero.base.BaseResponse;
import dev.neordinary.zero.base.BaseResponseStatus;
import dev.neordinary.zero.converter.UserConverter;
import dev.neordinary.zero.domain.UserEntity;
import dev.neordinary.zero.domain.UserRepository;
import dev.neordinary.zero.dto.UserReqRecord;
import dev.neordinary.zero.dto.UserRequest;
import dev.neordinary.zero.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserResponse.UserJoinRes join(UserRequest.UserJoin userJoin) {
        UserEntity newUser = UserConverter.toUser(userJoin);
        return UserConverter.toUserDto(userRepository.save(newUser));
    }

    @Transactional
    public UserResponse.UserBeverageRes showUserBeverage(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new BaseException(BaseResponseStatus.CANNOT_FIND_USER));
        return UserConverter.toUserBeverageDto(userEntity);
    }

    public int getPurpose(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new BaseException(BaseResponseStatus.CANNOT_FIND_USER));
        return userEntity.getMaxSugar().intValue();
    }

//    public UserResponse.UserJoinRes joinV2(UserReqRecord.UserJoin userJoinRecord) {
//
//        return UserConverter.toUserDto(userRepository.save(userJoinRecord.toUser()));
//    }
}
