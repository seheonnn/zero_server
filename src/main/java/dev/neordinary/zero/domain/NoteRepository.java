package dev.neordinary.zero.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface NoteRepository extends JpaRepository<NoteEntity, Long> {

    @Query(
            value =
                    "select SUM(calorie), SUM(sugar) " +
                            "from note_entity " +
                            "where user_id = :user_id " +
                            "and created_at like :today% ; ",
            nativeQuery = true
    )
    List<Object[]> findKcalAndSugarByDay(@Param("user_id") Long user_id, @Param("today") String today);

    @Query(
            value =
                    "select *  " +
                            "from (select SUM(sugar), created_at  from note_entity where user_id = :user_id group by created_at) t " +
                            "where t.created_at between :start and :end ; ",
            nativeQuery = true
    )
    List<Object[]> findTotal(@Param("user_id") Long user_id, @Param("start") String start, @Param("end") String end);
}
