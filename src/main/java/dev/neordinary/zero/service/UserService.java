package dev.neordinary.zero.service;

import dev.neordinary.zero.base.BaseException;
import dev.neordinary.zero.base.BaseResponseStatus;
import dev.neordinary.zero.converter.NoteConverter;
import dev.neordinary.zero.converter.UserConverter;
import dev.neordinary.zero.domain.NoteEntity;
import dev.neordinary.zero.domain.NoteRepository;
import dev.neordinary.zero.domain.UserEntity;
import dev.neordinary.zero.domain.UserRepository;
import dev.neordinary.zero.dto.NoteResponse;
import dev.neordinary.zero.dto.UserRequest;
import dev.neordinary.zero.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static dev.neordinary.zero.base.BaseResponseStatus.CANNOT_FIND_NOTE;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final NoteRepository noteRepository;

    public UserResponse.UserJoinRes join(UserRequest.UserJoin userJoin) {
        UserEntity newUser = UserConverter.toUser(userJoin);
        return UserConverter.toUserDto(userRepository.save(newUser));
    }

    @Transactional
    public List<NoteResponse.NoteJoinRes> showUserBeverage(Long userId) {
        List<NoteEntity> noteEntities = noteRepository.findAllToday(userId, LocalDate.now()).orElseThrow(() -> new BaseException(CANNOT_FIND_NOTE));
        List<NoteResponse.NoteJoinRes> noteJoinResList = new ArrayList<>();

        noteEntities.stream().map(noteEntity -> {
            noteJoinResList.add(NoteConverter.toNoteDto(noteEntity));
            return noteEntity;
        }).collect(Collectors.toList());
        return noteJoinResList;
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
