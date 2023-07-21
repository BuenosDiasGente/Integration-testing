package ru.hogwarts.magic_school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.magic_school.model.Faculty;


import java.util.List;

public interface FacultyRepository extends JpaRepository <Faculty,Long> {
    List <Faculty>findByOrderByColorAsc(String color);
}
