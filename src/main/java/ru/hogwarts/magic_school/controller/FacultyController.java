package ru.hogwarts.magic_school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.magic_school.model.Faculty;
import ru.hogwarts.magic_school.service.FacultyService;
import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {

        this.facultyService = facultyService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Faculty> get(@PathVariable Long id) {
        Faculty faculty = facultyService.get(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping()
    public Faculty add(@RequestBody Faculty faculty) {
        return facultyService.add(faculty);
    }

    @PutMapping()
    public ResponseEntity<Faculty> update( @RequestBody Faculty faculty) {
        Faculty savedFaculty = facultyService.update(faculty);
        if (savedFaculty == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(savedFaculty);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> remove (@PathVariable Long id) {
        facultyService.remove(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/by-color")
    public List<Faculty> facultiesOfColor(@RequestParam String color) {
        return facultyService.facultiesOfColor(color);
    }

}
