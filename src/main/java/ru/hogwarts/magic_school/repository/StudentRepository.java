package ru.hogwarts.magic_school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.magic_school.model.Student;

import java.util.Collection;


public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByAge(int age);

}
