package service;

import ru.hogwarts.school.model.Student;

import java.util.List;

public interface StudentService {
    Student add(Student student);
    Student get(Long id);
    Student update(Student student);
    void remove(Long id);
    List<Student> getStudentByAge(int age);
}
