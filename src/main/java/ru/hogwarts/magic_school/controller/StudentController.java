package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> get(@PathVariable Long id) {
        Student saveStudent = studentService.get(id);
        if (saveStudent == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(saveStudent);
    }

    @PostMapping()
    public Student add(@RequestBody Student student) {
        return studentService.add(student);
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
    public ResponseEntity <Void> remove(@PathVariable Long id) {
        studentService.remove(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/by-age")
    public List<Student> getStudentByAge(@RequestParam int age) {

        return studentService.getStudentByAge(age);
    }


}
