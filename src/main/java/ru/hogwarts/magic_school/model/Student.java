package ru.hogwarts.magic_school.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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

}
