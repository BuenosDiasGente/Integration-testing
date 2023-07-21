package ru.hogwarts.magic_school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.magic_school.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
     List<Student> findByOrderByAgeAsc(int age);

}
