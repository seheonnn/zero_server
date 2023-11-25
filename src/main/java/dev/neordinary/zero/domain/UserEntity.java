package dev.neordinary.zero.domain;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer height;
    private Integer weight;
    private Integer age;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Double maxSugar;
    private Double maxCalorie;

//    public static UserEntity createUser(String name, Integer height, Integer weight, Integer age, Gender gender) {
//        return UserEntity.builder()
//                .name(name)
//                .height(height)
//                .weight(weight)
//                .age(age)
//                .gender(gender)
//                .build();
//    }
}
