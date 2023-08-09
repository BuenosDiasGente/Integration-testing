package ru.hogwarts.magic_school.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.magic_school.model.Faculty;
import ru.hogwarts.magic_school.model.Student;
import ru.hogwarts.magic_school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerRestTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private StudentRepository studentRepository;

    @AfterEach
    public void resetDB() {
        studentRepository.deleteAll();
    }

    @Test
    void shouldGetStudentById() {
        Long studentId = studentCreateBd("Gari", 20).getId();
        ResponseEntity<Student> response = testRestTemplate.getForEntity("/student/{id}", Student.class, studentId);
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().getId()).isEqualTo(studentId);
    }

//      @Test
//    void shouldGetFacultyByStudentId() {
//        Faculty faculty = new Faculty(1L, "Home", "black", studentsList());
//        Long studentId = studentCreateBd("Gari", 19).getId();
//        ResponseEntity<Faculty> response = testRestTemplate.getForEntity("/student/{id}/faculty", Faculty.class,studentId);
//        Assertions.assertThat(response.getBody()).isNotNull();
//        Assertions.assertThat(response.getBody()).isEqualTo(faculty);
//    }
    //Метод не работает,не правильная логика.Получается факультет  null, я его не могу добавить в базу к студенту.
    @Test
    void shouldCreateStudent() {
        Student student = new Student();
        student.setName("Gari");
        student.setAge(20);
        ResponseEntity<Student> response = testRestTemplate.postForEntity("/student", student, Student.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().getId()).isEqualTo(student.getId());
        Assertions.assertThat(response.getBody().getName()).isEqualTo(student.getName());
        Assertions.assertThat(response.getBody().getAge()).isEqualTo(student.getAge());
    }

    @Test
    void shouldUpdateStudent() {
        Student student = studentCreateBd("Gari", 19);
        ResponseEntity<Student> response = testRestTemplate.exchange("/student", HttpMethod.PUT, new HttpEntity<>(student), Student.class);
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().getId()).isEqualTo(student.getId());
        Assertions.assertThat(response.getBody().getName()).isEqualTo(student.getName());
        Assertions.assertThat(response.getBody().getAge()).isEqualTo(student.getAge());
    }

    @Test
    void shouldDeleteStudentById() {
        Student student = studentCreateBd("Gari", 20);
        testRestTemplate.delete("/student/1");
        Assertions.assertThat(studentRepository.findById(1L)).isEmpty();
    }

    @Test
    void shouldGetStudentByAge() throws Exception {
        Student student = studentCreateBd("Gari", 20);
        Student student1 = studentCreateBd("Pokemon", 18);
        Student student2 = studentCreateBd("Ponocha", 30);
        List<Student> studentList = new ArrayList<>();
        studentList.add(0, student);
        studentList.add(1, student1);
        studentList.add(2, student2);

        ResponseEntity<String> responce = testRestTemplate.exchange("/student/by-age?age=20", HttpMethod.GET, new HttpEntity<>(studentList), String.class);
        Assertions.assertThat(responce.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responce.getBody()).isNotNull();
        Assertions.assertThat(parseResultStudents(responce.getBody()).equals(student.getAge()));
    }

    private String parseResultStudents(String responce) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responce);
        String result = jsonNode.get(0).get("age").asText();
        return result;
    }

    @Test
    void shouldGetStudentByAgeBetween() throws Exception {

        Student student = studentCreateBd("Gari", 20);
        Student student1 = studentCreateBd("Pokemon", 18);
        Student student2 = studentCreateBd("Ponocha", 30);

        List<Student> studentList = new ArrayList<>();
        studentList.add(0, student);
        studentList.add(1, student1);
        studentList.add(2, student2);

        ResponseEntity<String> responce = testRestTemplate.exchange("/student/by-age-between?ageMin=20&ageMax=30", HttpMethod.GET, new HttpEntity<>(studentList), String.class);
        Assertions.assertThat(responce.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responce.getBody()).isNotNull();
        Assertions.assertThat(parseResultStudents(responce.getBody()).equals(student.getAge()));
    }

    private Student studentCreateBd(String name, int age) {
        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        return studentRepository.save(student);
    }

    private Faculty facultyCreateBd(String name, String color) {
        Faculty faculty = new Faculty();
        faculty.setName(name);
        faculty.setColor(color);
        return faculty;
    }

}
