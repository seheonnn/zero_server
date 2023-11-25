package dev.neordinary.zero.service;

import dev.neordinary.zero.base.BaseException;
import dev.neordinary.zero.base.BaseResponseStatus;
import dev.neordinary.zero.converter.NoteConverter;
import dev.neordinary.zero.domain.NoteEntity;
import dev.neordinary.zero.domain.NoteRepository;
import dev.neordinary.zero.domain.UserEntity;
import dev.neordinary.zero.domain.UserRepository;
import dev.neordinary.zero.dto.NoteRequest;
import dev.neordinary.zero.dto.NoteResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public NoteResponse.NoteJoinRes createNote(Long userId, NoteRequest.NoteJoin noteJoin) {
        UserEntity currentUser = userRepository.findById(userId).orElseThrow(() -> new BaseException(BaseResponseStatus.CANNOT_FIND_USER));
        NoteEntity newNote = NoteConverter.toNote(noteJoin, currentUser);
        return NoteConverter.toNoteDto(noteRepository.save(newNote));
    }

    public Object getNote(Long userId) {
        UserEntity currentUser = userRepository.findById(userId).orElseThrow(() -> new BaseException(BaseResponseStatus.CANNOT_FIND_USER));
        LocalDate today = LocalDate.now();
        List<Object[]> results = noteRepository.findKcalAndSugarByDay(userId, today.toString());

        // QueryDSL 필요성
        String[] split = Arrays.toString(results.get(0)).replace("[", "").replace("]", "").split(", ");
        String totalKcal = split[0];
        String totalSugar = split[1];

        NoteResponse.GetNoteRes NoteRes = NoteConverter.toGetNoteDto(currentUser, totalKcal, totalSugar);

        List<Object[]> totalDatas = noteRepository.findTotal(currentUser.getUser_id(), today.minusDays(7).toString(), today.toString());

        List<Object> completedDateList = new ArrayList<>();
        totalDatas.stream().map(totalData -> {
            if ((Double) totalData[0] <= currentUser.getMaxSugar())
                completedDateList.add(totalData[1]);
            return NoteRes;
        }).collect(Collectors.toList());
        NoteRes.setCompletedDate(completedDateList);
        return NoteRes;
    }
}
