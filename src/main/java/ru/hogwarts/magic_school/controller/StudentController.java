package ru.hogwarts.magic_school.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.magic_school.model.Faculty;
import ru.hogwarts.magic_school.model.Student;
import ru.hogwarts.magic_school.service.StudentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<Student> get(@PathVariable Long id) {
        Student saveStudent = studentService.get(id);
        if (saveStudent == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(saveStudent);
    }

    @GetMapping("/{id}/faculty")
    public ResponseEntity<Faculty> getFacultyByStudent(@PathVariable Long id) {
        Faculty saveFacultyId = studentService.getFacultyByStudent(id);
        if (saveFacultyId == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(saveFacultyId);
    }

    @PostMapping()
    public ResponseEntity<Student> add(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.add(student)) ;
    }

    @PutMapping()
    public ResponseEntity<Student> update(@RequestBody Student student) {
        Student savedStudent = studentService.update(student);
        if (savedStudent == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(savedStudent);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        studentService.remove(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/by-age")
    public List<Student> getStudentByAge(@RequestParam int age) {

        return studentService.studentsInAge(age);
    }

    @GetMapping("/by-age-between")
    public ResponseEntity<List<Student>> getStudentByAgeBetween(@RequestParam int ageMin,
                                                                @RequestParam int ageMax) {
        if (ageMin != 0 && ageMax != 0) {
            return ResponseEntity.ok(studentService.getStudentByAgeBetween(ageMin, ageMax));
        }
        return ResponseEntity.badRequest().build();
    }


}



