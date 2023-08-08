package ru.hogwarts.magic_school.controller;

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
    void shouldGetFacultyById() throws Exception {
        Faculty faculty = facultyObject();
        facultyRepository.save(faculty);
        ResponseEntity<Faculty> response = testRestTemplate.getForEntity("/faculty/1", Faculty.class);
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().getId()).isEqualTo(faculty.getId());

    }


    @Test
    void shouldGetListStudentByFacultyId() throws Exception {
        Faculty faculty = facultyObject();
        facultyRepository.save(faculty);
        Student student = studentObject();
        List<Student> studentList = studentsList();
        ResponseEntity<Faculty> response = testRestTemplate.exchange("/faculty/1/student", HttpMethod.GET, new HttpEntity<>(studentList), Faculty.class);
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().getStudent()).isEqualTo(faculty.getStudent());

    }

    @Test
    void shouldCreateFaculty() throws Exception {
        Faculty faculty = facultyObject();
        ResponseEntity<Faculty> response = testRestTemplate.postForEntity("/faculty", faculty, Faculty.class);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().getId()).isEqualTo(faculty.getId());
        Assertions.assertThat(response.getBody().getName()).isEqualTo(faculty.getName());
        Assertions.assertThat(response.getBody().getColor()).isEqualTo(faculty.getColor());

    }

    @Test
    void shouldUpdateFaculty() throws Exception {

        Faculty faculty = facultyObject();
        ResponseEntity<Faculty> response = testRestTemplate.exchange("/faculty", HttpMethod.PUT, new HttpEntity<>(faculty), Faculty.class);
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().getId()).isEqualTo(faculty.getId());
        Assertions.assertThat(response.getBody().getName()).isEqualTo(faculty.getName());
        Assertions.assertThat(response.getBody().getColor()).isEqualTo(faculty.getColor());

    }

    @Test
    void shouldDeleteFacultyById() throws Exception {
        Faculty faculty = facultyObject();
        facultyRepository.save(faculty);
        testRestTemplate.delete("/faculty/1");
        Assertions.assertThat(facultyRepository.findById(1L)).isEmpty();

    }
//    @Test
//    void shouldGetFacultyByColor() throws Exception {
//
//
//        List<Faculty> facultyList =new ArrayList<>();
//
//
//        when(facultyService.facultiesFindByColor("black")).thenReturn(facultyList);
//
//
//    }
//
//
//
//    @Test
//    void shouldGetFacultyOfColor() throws Exception {
//
//
//
//        List<Faculty> facultyList =new ArrayList<>();
//
//
//
//        when(facultyService.facultiesFindByColor("black")).thenReturn(facultyList);
//
//
//    }
//    @Test
//    void shouldGetFacultyOfName() throws Exception {
//
//
//        List<Faculty> facultyList =new ArrayList<>();
//
//
//
//        when(facultyService.facultiesFindByName("Rive")).thenReturn(facultyList);
//
//
//    }


}
