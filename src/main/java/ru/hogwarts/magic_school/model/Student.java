package ru.hogwarts.magic_school.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int age;

    @ManyToOne()
    @JoinColumn(name="faculty_id")
    private Faculty faculty;

}
