package ru.hogwarts.magic_school.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.magic_school.model.Faculty;
import ru.hogwarts.magic_school.model.Student;
import ru.hogwarts.magic_school.repository.FacultyRepository;
import ru.hogwarts.magic_school.service.FacultyService;

import java.util.List;

import static ru.hogwarts.magic_school.constant.Constant.*;


@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;
    private final Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);


    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    @Override
    public Faculty add(Faculty faculty) {
        logger.info(LOGGER_METHOD_ADD);
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty get(Long id) {
        logger.info(LOGGER_METHOD_GET);
        return facultyRepository.findById(id).orElse(null);
    }

    @Override
    public Faculty update(Faculty faculty) {
        logger.info(LOGGER_METHOD_UPDATE);
        return facultyRepository.save(faculty);
    }

    @Override
    public void remove(Long id) {
        logger.info(LOGGER_METHOD_REMOVE);
        facultyRepository.deleteById(id);
    }

    @Override
    public List<Faculty> facultiesOfColor(String color) {
        logger.info("Method facultiesOfColor was invoked.");
        return facultyRepository.findByColor(color);
    }

    @Override
    public List<Faculty> facultiesFindByColor(String color) {
        logger.info("Method facultiesFindByColor was invoked.");
        return facultyRepository.findFacultyByColorIgnoreCase(color);
    }

    @Override
    public List<Faculty> facultiesFindByName(String name) {
        logger.info("Method facultiesFindByName was invoked.");
        return facultyRepository.findFacultyByNameIgnoreCase(name);
    }

    @Override
    public List<Student> getStudentByFaculty(Long id) {
        logger.info("Method getStudentByFaculty was invoked.");
        return facultyRepository.findById(id).get().getStudent();
    }

}
