package ru.hogwarts.magic_school.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@EqualsAndHashCode
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Faculty {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String color;



    @OneToMany(mappedBy = "faculty")
    @JsonIgnore
    private List<Student> student;



}
