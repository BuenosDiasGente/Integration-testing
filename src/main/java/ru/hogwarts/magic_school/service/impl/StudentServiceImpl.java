package ru.hogwarts.magic_school.service.impl;

import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;
import ru.hogwarts.magic_school.model.Student;
import ru.hogwarts.magic_school.repository.StudentRepository;
import ru.hogwarts.magic_school.service.StudentService;


import java.util.Collection;
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
    public List<Student> studentsInAge(int age) {
        return studentRepository.findByAge(age);
    }
}
