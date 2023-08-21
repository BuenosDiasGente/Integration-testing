package ru.hogwarts.magic_school.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.magic_school.model.Faculty;
import ru.hogwarts.magic_school.model.Student;
import ru.hogwarts.magic_school.repository.StudentRepository;
import ru.hogwarts.magic_school.service.StudentService;

import java.util.List;

import static ru.hogwarts.magic_school.constant.Constant.*;

@Service

public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);


    public StudentServiceImpl(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }


    @Override
    public Student add(Student student) {
        logger.info(LOGGER_METHOD_ADD);
        return studentRepository.save(student);
    }

    @Override
    public Student get(long id) {
        logger.info(LOGGER_METHOD_GET);
        return studentRepository.findById(id).orElseThrow();
    }

    @Override
    public Student update(Student student) {
        logger.info(LOGGER_METHOD_UPDATE);
        return studentRepository.save(student);
    }

    @Override
    public void remove(long id) {
        logger.info(LOGGER_METHOD_REMOVE);
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> studentsInAge(int age) {
        logger.info("Method studentsInAge was invoked.");
        return studentRepository.findByAge(age);
    }

    @Override
    public List<Student> getStudentByAgeBetween(int ageMin, int ageMax) {
        logger.info("Method getStudentByAgeBetween was invoked.");
        return studentRepository.findByAgeBetween(ageMin, ageMax);
    }

    @Override
    public Faculty getFacultyByStudent(long id) {
        logger.info("Method getFacultyByStudent was invoked.");
        return studentRepository.findById(id).get().getFaculty();
    }

    @Override
    public int getAllCountStudent() {
        logger.info("Method getAllCountStudent was invoked.");
        return studentRepository.getAllStudent();
    }

    @Override
    public int middleAgeByStudents() {
        logger.info("Method middleAgeByStudents was invoked.");
        return studentRepository.middleAgeByStudents();
    }

    @Override
    public List<Student> getTheLastFiveStudents() {
        logger.info("Method getTheLastFiveStudents was invoked.");
        return studentRepository.getTheLastFiveStudents();
    }


}
