package ru.hogwarts.magic_school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hogwarts.magic_school.model.Faculty;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository <Faculty,Long> {
    List<Faculty> findByColor(String color);
    List<Faculty>findFacultyByColorIgnoreCase(String color);
    List<Faculty>findFacultyByNameIgnoreCase(String name);
}
