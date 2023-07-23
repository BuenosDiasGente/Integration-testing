package ru.hogwarts.magic_school.service.impl;


import org.springframework.stereotype.Service;
import ru.hogwarts.magic_school.model.Faculty;
import ru.hogwarts.magic_school.model.Student;
import ru.hogwarts.magic_school.repository.FacultyRepository;
import ru.hogwarts.magic_school.service.FacultyService;

import java.util.List;


@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    @Override
    public Faculty add(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Faculty get(Long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    @Override
    public Faculty update(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public void remove(Long id) {
        facultyRepository.deleteById(id);

    }

    @Override
    public List<Faculty> facultiesOfColor(String color) {
        return facultyRepository.findByColor(color);
    }
    @Override
    public List<Faculty> facultiesFindByColor(String color){
        return facultyRepository.findFacultyByColorIgnoreCase(color);
    }
    @Override
    public List<Faculty> facultiesFindByName(String name){
        return facultyRepository.findFacultyByNameIgnoreCase(name);

    }
    @Override
   public List<Student> getStudentByFaculty(Long id){
        return facultyRepository.findById(id).get().getStudent();
    }

}
