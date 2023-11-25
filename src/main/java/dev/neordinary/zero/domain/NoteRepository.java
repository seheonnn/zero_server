package dev.neordinary.zero.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
                    "select t.res " +
                            "from (select SUM(sugar) res, created_at  from note_entity where user_id = :user_id group by created_at) t " +
                            "order by created_at desc limit :day ; ",
            nativeQuery = true
    )
    List<Integer> findTotal(@Param("user_id") Long user_id, @Param("day") Integer day);

    @Query(value = "select * " +
            "from note_entity " +
            "where user_id = :user_id " +
            "and created_at = :today ; ", nativeQuery = true)
    Optional<List<NoteEntity>> findAllToday(@Param("user_id") Long userId, @Param("today") LocalDate today);
}
