package ru.hogwarts.magic_school.service.impl;


import org.springframework.stereotype.Service;
import ru.hogwarts.magic_school.model.Faculty;
import ru.hogwarts.magic_school.repository.FacultyRepository;
import ru.hogwarts.magic_school.service.FacultyService;

import java.util.Collection;



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
        return facultyRepository.findById(id).get();
    }

    @Override
    public Faculty update( Faculty faculty) {
     return facultyRepository.save(faculty);
    }

    @Override
    public void remove(Long id) {
        facultyRepository.deleteById(id);

    }

    @Override
    public Collection<Faculty> facultiesOfColor(String color) {
        return facultyRepository.findByColor(color);
    }


}
