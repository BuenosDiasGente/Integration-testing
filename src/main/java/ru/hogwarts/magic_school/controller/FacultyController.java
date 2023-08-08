package ru.hogwarts.magic_school.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.magic_school.model.Faculty;
import ru.hogwarts.magic_school.model.Student;
import ru.hogwarts.magic_school.service.FacultyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    @GetMapping("/{id}")
    public ResponseEntity<Faculty> get(@PathVariable Long id) {
        Faculty faculty = facultyService.get(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<List<Student>> getStudentByFaculty(@PathVariable Long id) {
        List<Student> studentSaveIdFaculty = facultyService.getStudentByFaculty(id);
        if (studentSaveIdFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentSaveIdFaculty);
    }

    @PostMapping()
    public ResponseEntity<Faculty> add(@RequestBody Faculty faculty) {

        return ResponseEntity.ok(facultyService.add(faculty));
    }

    @PutMapping()
    public ResponseEntity<Faculty> update(@RequestBody Faculty faculty) {
        Faculty savedFaculty = facultyService.update(faculty);
        if (savedFaculty == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(savedFaculty);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        facultyService.remove(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/by-color")
    public List<Faculty> facultiesOfColor(@RequestParam String color) {

        return facultyService.facultiesOfColor(color);
    }

    @GetMapping("/by-color-or-name")
    public ResponseEntity<List<Faculty>> facultiesOfColor(@RequestParam(required = false) String color,
                                                          @RequestParam(required = false) String name) {
        if (color != null && !color.isBlank()) {

            return ResponseEntity.ok(facultyService.facultiesFindByColor(color));
        }
        if (name != null && !name.isBlank()) {
            return ResponseEntity.ok(facultyService.facultiesFindByName(name));

        }

        return ResponseEntity.badRequest().build();
    }
}
