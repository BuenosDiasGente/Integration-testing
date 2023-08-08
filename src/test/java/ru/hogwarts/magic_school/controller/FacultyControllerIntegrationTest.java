package ru.hogwarts.magic_school.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.magic_school.model.Faculty;
import ru.hogwarts.magic_school.repository.FacultyRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @AfterEach
    public void resetDB() {
        facultyRepository.deleteAll();
    }

    @Test
    void shouldGetFacultyById() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(1l);
        faculty.setName("Gari");
        faculty.setColor("black");
        facultyRepository.save(faculty);

        ResponseEntity<Faculty> response = restTemplate.getForEntity("/faculty/{id}", Faculty.class, faculty.getId());

        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().getId()).isEqualTo(faculty.getId());

    }

    @Test
    void shouldGetListStudentByFacultyId() throws Exception{

    }

    @Test
    void shouldCreateFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("Gari");
        faculty.setColor("black");


        ResponseEntity<Faculty> response = restTemplate.postForEntity("/faculty", faculty, Faculty.class);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isNotNull();
        Assertions.assertThat(response.getBody().getId()).isNotNull();
        Assertions.assertThat(response.getBody().getName()).isEqualTo(faculty.getName());
        Assertions.assertThat(response.getBody().getColor()).isEqualTo(faculty.getColor());

    }

    @Test
    void shouldUpdateFaculty() throws Exception{

    }

    @Test
    void shouldDeleteFacultyById() throws Exception{

    }
    @Test
    void shouldGetFacultyByColor() throws Exception{

    }
    @Test
    void shouldGetFacultyOfColor() throws Exception{

    }



}


