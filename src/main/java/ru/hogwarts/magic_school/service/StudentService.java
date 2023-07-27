package ru.hogwarts.magic_school.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.magic_school.model.Avatar;
import ru.hogwarts.magic_school.model.Faculty;
import ru.hogwarts.magic_school.model.Student;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public interface StudentService {
    Student add(Student student);

    Student get(long id);

    Student update(Student student);

    void remove(long id);

    List<Student> studentsInAge(int age);
    List<Student>  getStudentByAgeBetween(int ageMin,int ageMax);
    Faculty getFacultyByStudent(long id);




}
