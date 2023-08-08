package ru.hogwarts.magic_school.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true, value = {"error"})
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
