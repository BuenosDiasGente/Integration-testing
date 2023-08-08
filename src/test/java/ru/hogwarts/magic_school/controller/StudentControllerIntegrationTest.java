//package ru.hogwarts.magic_school.controller;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.web.servlet.ResultActions;
//import ru.hogwarts.magic_school.model.Faculty;
//import ru.hogwarts.magic_school.model.Student;
//import ru.hogwarts.magic_school.repository.StudentRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class StudentControllerIntegrationTest {
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private StudentRepository studentRepository;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @AfterEach
//    public void resetDB() {
//        studentRepository.deleteAll();
//    }
//
//    @Test
//    void shouldGetStudentById() throws Exception {
//        Student student = new Student();
//        student.setId(1L);
//        student.setName("Pokemon");
//        student.setAge(19);
//        studentRepository.save(student);
//
//        ResponseEntity<Student> response = restTemplate.getForEntity("/student/{id}", Student.class, student.getId());
//
//        Assertions.assertThat(response.getBody()).isNotNull();
//        Assertions.assertThat(response.getBody().getId()).isEqualTo(student.getId());
//    }
//
//    @Test
//    void shouldReturnNotFoundWhenGetStudentById() throws Exception {
//        ResultActions resultActions = mockMvc.perform(get("/student/1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//        );
//        resultActions
//                .andExpect(status().isNotFound())
//                .andDo(print());
//
//    }
//
//    @Test
//    void shouldGetFacultyByStudentId() throws Exception {
//        Faculty faculty = new Faculty();
//        faculty.setId(1L);
//        faculty.setName("Gari");
//        faculty.setColor("black");
//
//        when(studentService.getFacultyByStudent(1)).thenReturn(faculty);
//
//        ResultActions resultActions = mockMvc.perform(get("/student/1/faculty")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//        );
//        resultActions
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(faculty.getId()))
//                .andExpect(jsonPath("$.name").value(faculty.getName()))
//                .andExpect(jsonPath("$.color").value(faculty.getColor()))
//                .andDo(print());
//
//    }
//
//
//    @Test
//    void shouldCreateStudent() throws Exception {
//
//        Student student = new Student();
//        student.setId(1L);
//        student.setName("Pokemon");
//        student.setAge(19);
//
//        ResponseEntity<Student> response = restTemplate.postForEntity("/student", student, Student.class);
//
//        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        Assertions.assertThat(response.getBody()).isNotNull();
//        Assertions.assertThat(response.getBody().getId()).isNotNull();
//        Assertions.assertThat(response.getBody().getName()).isEqualTo(student.getName());
//        Assertions.assertThat(response.getBody().getAge()).isEqualTo(student.getAge());
//
//    }
//
//    @Test
//    void shouldUpdateStudent() throws Exception {
//        Student student = new Student();
//        student.setId(1L);
//        student.setName("Pokemon");
//        student.setAge(19);
//        studentRepository.save(student);
//
//        ResponseEntity<Student> response = restTemplate.put("/student", student, Student.class);
//
//
//
//    }
//
//    @Test
//    void shouldReturnBadRequestWhenUpdateStudent() throws Exception {
//        ResultActions resultActions = mockMvc.perform(
//                put("/student")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON));
//
//        resultActions
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    void shouldDeleteStudentById() throws Exception {
//        Student student = new Student();
//        student.setId(1L);
//        student.setName("Pokemon");
//        student.setAge(19);
//
//        ResultActions resultActions = mockMvc.perform(delete("/student/1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//        );
//        resultActions
//                .andExpect(status().isOk())
//                .andDo(print());
//    }
//
//    @Test
//    void shouldGetStudentByAge() throws Exception {
//        List<Student> studentList = new ArrayList<>() {
//            Student student1 = new Student(1l, "Pokemon", 19, null);
//            Student student2 = new Student(2l, "Pinokio", 25, null);
//            Student student3 = new Student(3l, "Gari", 23, null);
//            Student student4 = new Student(4l, "Ilon", 22, null);
//
//        };
//        int age = 25;
//
//        when(studentService.studentsInAge(age)).thenReturn(studentList);
//
//        ResultActions resultActions = mockMvc.perform(get("/student/by-age/" + age)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//        );
//
//        resultActions
//                .andExpect(status().isOk())
//                //.andExpect(MockMvcResultMatchers.content().json(convertObjectToJsonString(studentList)))
//                .andExpect(content().json(objectMapper.writeValueAsString(studentList.get(1))))
//                .andDo(print());
//
//    }
//
//    @Test
//    void shouldGetStudentByAgeBetween() throws Exception {
//        // List<Student> studentList = new ArrayList<>() {
//        Student student1 = new Student(1l, "Pokemon", 19, null);
//        Student student2 = new Student(2l, "Pinokio", 25, null);
//        Student student3 = new Student(3l, "Gari", 23, null);
//        Student student4 = new Student(4l, "Ilon", 18, null);
//
//        // };
//        when(studentService.getStudentByAgeBetween(22, 25)).thenReturn(List.of(student2, student3));
//
//        ResultActions resultActions = mockMvc.perform(get("/student/by-age-between/22,26")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON)
//                //   .content(objectMapper.writeValueAsString(List.of(student2,student3)))
//        );
//        resultActions
//                .andExpect(status().isOk())
//                // .andExpect(MockMvcResultMatchers.content().json(convertObjectToJsonString()))
//                .andExpect(content().json(objectMapper.writeValueAsString(List.of(student2, student3))))
//                .andDo(print());
//
//
//    }
//
//}
//
//
