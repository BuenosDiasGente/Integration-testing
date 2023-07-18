package service.impl;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }

    @Override
    public Student add(Student student) {

        return studentRepository.save(student);
    }

    @Override
    public Student get(Long id) {

        return studentRepository.findById(id).get();
    }

    @Override
    public Student update(Student student) {

        return studentRepository.save(student);
    }


    @Override
    public void remove(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getStudentByAge(int age) {
       return null;
      // return studentRepository.findAll(Sort.by(Sort.Direction.ASC,age));
    }
}
