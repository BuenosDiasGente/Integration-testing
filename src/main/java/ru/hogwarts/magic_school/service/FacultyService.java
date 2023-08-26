package ru.hogwarts.magic_school.service;

import ru.hogwarts.magic_school.model.Faculty;
import ru.hogwarts.magic_school.model.Student;

import java.util.List;

public interface FacultyService {
    Faculty add(Faculty faculty);

    Faculty get(Long id);

    Faculty update(Faculty faculty);

    void remove(Long id);

    List<Faculty> facultiesOfColor(String color);
    List<Faculty> facultiesFindByColor(String color);
    List<Faculty> facultiesFindByName(String name);
    List<Student> getStudentByFaculty(Long id);

    String getFacultiesLongestName();
}
