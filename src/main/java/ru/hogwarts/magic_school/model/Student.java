package ru.hogwarts.magic_school.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
@Entity
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
