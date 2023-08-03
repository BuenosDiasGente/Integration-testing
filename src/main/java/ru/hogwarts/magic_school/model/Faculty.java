package ru.hogwarts.magic_school.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.util.List;

@Data
@EqualsAndHashCode
@ToString
@Entity
public class Faculty {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String color;



    @OneToMany(mappedBy = "faculty")
    @JsonIgnore
    private List<Student> student;


    public Faculty() {

    }
    public Faculty(Long id, String name, String color, List<Student> student) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.student = student;
    }
}
