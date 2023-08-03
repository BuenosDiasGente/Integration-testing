package ru.hogwarts.magic_school.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.magic_school.model.Avatar;
import ru.hogwarts.magic_school.model.Faculty;
import ru.hogwarts.magic_school.model.Student;
import ru.hogwarts.magic_school.repository.AvatarRepository;
import ru.hogwarts.magic_school.repository.StudentRepository;
import ru.hogwarts.magic_school.service.StudentService;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

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
    public Student get(long id) {

        return studentRepository.findById(id).orElseThrow();
    }

    @Override
    public Student update(Student student) {

        return studentRepository.save(student);
    }

    @Override
    public void remove(long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> studentsInAge(int age) {

        return studentRepository.findByAge(age);
    }

    @Override
    public List<Student> getStudentByAgeBetween(int ageMin, int ageMax) {
        return studentRepository.findByAgeBetween(ageMin, ageMax);
    }

    @Override
    public Faculty getFacultyByStudent(long id) {
        return studentRepository.findById(id).get().getFaculty();
    }


}
