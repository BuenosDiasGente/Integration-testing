package ru.hogwarts.magic_school.service;

import ru.hogwarts.magic_school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentService {
    Student add(Student student);

    Student get(Long id);

    Student update(Student student);

    void remove(Long id);

    List<Student> studentsInAge(int age);
}
