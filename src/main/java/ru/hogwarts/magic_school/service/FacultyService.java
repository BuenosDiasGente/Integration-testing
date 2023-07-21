package ru.hogwarts.magic_school.service;

import ru.hogwarts.magic_school.model.Faculty;

import java.util.List;

public interface FacultyService {
    Faculty add(Faculty faculty);

    Faculty get(Long id);

    Faculty update(Faculty faculty);

    void remove(Long id);

   List<Faculty> getFacultiesByColor(String color);
}
