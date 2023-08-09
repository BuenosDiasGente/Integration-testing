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
import ru.hogwarts.magic_school.repository.FacultyRepository;

import java.util.ArrayList;
import java.util.List;

import static ru.hogwarts.magic_school.controller.PepareTestObject.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerRestTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private FacultyRepository facultyRepository;

    @AfterEach
    public void resetDB() {
        facultyRepository.deleteAll();
    }

    @Test
    void shouldGetFacultyById() {
        Faculty faculty = facultyObject();
        facultyRepository.save(faculty);
        ResponseEntity<Faculty> response = testRestTemplate.getForEntity("/faculty/1", Faculty.class);
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().getId()).isEqualTo(faculty.getId());
    }

    @Test
    void shouldGetListStudentByFacultyId() {
        Faculty faculty = facultyObject();
        facultyRepository.save(faculty);
        Student student = studentObject();
        List<Student> studentList = studentsList();
        ResponseEntity<Faculty> response = testRestTemplate.exchange("/faculty/1/student", HttpMethod.GET, new HttpEntity<>(studentList), Faculty.class);
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().getStudent()).isEqualTo(faculty.getStudent());
    }

    @Test
    void shouldCreateFaculty() {
        Faculty faculty = facultyObject();
        ResponseEntity<Faculty> response = testRestTemplate.postForEntity("/faculty", faculty, Faculty.class);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().getId()).isEqualTo(faculty.getId());
        Assertions.assertThat(response.getBody().getName()).isEqualTo(faculty.getName());
        Assertions.assertThat(response.getBody().getColor()).isEqualTo(faculty.getColor());
    }

    @Test
    void shouldUpdateFaculty() {
        Faculty faculty = facultyObject();
        facultyRepository.save(faculty);
        ResponseEntity<Faculty> response = testRestTemplate.exchange("/faculty", HttpMethod.PUT, new HttpEntity<>(faculty), Faculty.class);
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().getId()).isEqualTo(faculty.getId());
        Assertions.assertThat(response.getBody().getName()).isEqualTo(faculty.getName());
        Assertions.assertThat(response.getBody().getColor()).isEqualTo(faculty.getColor());
    }

    @Test
    void shouldDeleteFacultyById() {
        Faculty faculty = facultyObject();
        facultyRepository.save(faculty);
        testRestTemplate.delete("/faculty/1");
        Assertions.assertThat(facultyRepository.findById(1L)).isEmpty();
    }

    @Test
    void shouldGetFacultyByColor() throws Exception {
        Faculty faculty = facultyCreateBd("Home", "black");
        Faculty faculty1 = facultyCreateBd("Multi", "red");
        Faculty faculty2 = facultyCreateBd("Garden", "pink");
        List<Faculty> facultyList = new ArrayList<>();
        facultyList.add(0, faculty);
        facultyList.add(1, faculty1);
        facultyList.add(2, faculty2);

        ResponseEntity<String> responce = testRestTemplate.exchange("/faculty/by-color?color=black", HttpMethod.GET, new HttpEntity<>(facultyList), String.class);
        Assertions.assertThat(responce.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responce.getBody()).isNotNull();
        Assertions.assertThat(parseResultFaculty(responce.getBody()).equals(faculty.getColor()));
    }


    @Test
    void shouldGetFacultyOfColor() throws Exception {
        Faculty faculty = facultyCreateBd("Home", "black");
        Faculty faculty1 = facultyCreateBd("Multi", "red");
        Faculty faculty2 = facultyCreateBd("Garden", "pink");
        List<Faculty> facultyList = new ArrayList<>();
        facultyList.add(0, faculty);
        facultyList.add(1, faculty1);
        facultyList.add(2, faculty2);

        ResponseEntity<String> responce = testRestTemplate.exchange("/faculty/by-color-or-name?color=black", HttpMethod.GET, new HttpEntity<>(facultyList), String.class);
        Assertions.assertThat(responce.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responce.getBody()).isNotNull();
        Assertions.assertThat(parseResultFaculty(responce.getBody()).equals(faculty.getColor()));
    }

    @Test
    void shouldGetFacultyOfName()throws Exception {
        Faculty faculty = facultyCreateBd("Home", "black");
        Faculty faculty1 = facultyCreateBd("Multi", "red");
        Faculty faculty2 = facultyCreateBd("Garden", "pink");
        List<Faculty> facultyList = new ArrayList<>();
        facultyList.add(0, faculty);
        facultyList.add(1, faculty1);
        facultyList.add(2, faculty2);
        ResponseEntity<String> responce = testRestTemplate.exchange("/faculty/by-color-or-name?name=Home", HttpMethod.GET, new HttpEntity<>(facultyList), String.class);
        Assertions.assertThat(responce.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responce.getBody()).isNotNull();
        Assertions.assertThat(parseResultFacultyValueName(responce.getBody()).equals(faculty.getName()));
    }

    private String parseResultFaculty(String responce) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responce);
        String result = jsonNode.get(0).get("color").asText();
        return result;
    }

    private String parseResultFacultyValueName(String responce) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responce);
        String result = jsonNode.get(0).get("name").asText();
        return result;
    }
    private Faculty facultyCreateBd(String name, String color) {
        Faculty faculty = new Faculty();
        faculty.setName(name);
        faculty.setColor(color);
        return facultyRepository.save(faculty);
    }
}
