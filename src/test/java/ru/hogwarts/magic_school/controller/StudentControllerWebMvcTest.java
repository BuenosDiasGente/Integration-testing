package ru.hogwarts.magic_school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import ru.hogwarts.magic_school.model.Faculty;
import ru.hogwarts.magic_school.model.Student;
import ru.hogwarts.magic_school.service.StudentService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
public class StudentControllerWebMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private StudentService studentService;

    @Test
    void shouldGetStudentById() throws Exception {

        Student student = new Student();
        student.setId(1L);
        student.setName("Gari");
        student.setAge(20);
        when(studentService.get(1)).thenReturn(student);

        ResultActions resultActions = mockMvc.perform(get("/student/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(student.getId()))
                .andExpect(jsonPath("$.name").value(student.getName()))
                .andExpect(jsonPath("$.age").value(student.getAge()))
                .andDo(print());

    }

    @Test
    void shouldReturnNotFoundWhenGetStudentById() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/student/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );
        resultActions
                .andExpect(status().isNotFound())
                .andDo(print());

    }

    @Test
    void shouldGetFacultyByStudentId() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("Home");
        faculty.setColor("black");

        when(studentService.getFacultyByStudent(1)).thenReturn(faculty);

        ResultActions resultActions = mockMvc.perform(get("/student/1/faculty")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(faculty.getId()))
                .andExpect(jsonPath("$.name").value(faculty.getName()))
                .andExpect(jsonPath("$.color").value(faculty.getColor()))
                .andDo(print());

    }


    @Test
    void shouldCreateStudent() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("Gari");
        student.setAge(20);

        when(studentService.add(student)).thenReturn(student);

        ResultActions resultActions = mockMvc.perform(
                post("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student))
                        .accept(MediaType.APPLICATION_JSON));

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(student.getName()))
                .andExpect(jsonPath("$.age").value(student.getAge()))
                .andDo(print());

    }

    @Test
    void shouldUpdateStudent() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("Gari");
        student.setAge(20);

        when(studentService.update(student)).thenReturn(student);

        ResultActions resultActions = mockMvc.perform(
                put("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student))
                        .accept(MediaType.APPLICATION_JSON));

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(student.getName()))
                .andExpect(jsonPath("$.age").value(student.getAge()))
                .andDo(print());

    }

    @Test
    void shouldReturnBadRequestWhenUpdateStudent() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                put("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        resultActions
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldDeleteStudentById() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("Gari");
        student.setAge(20);

        ResultActions resultActions = mockMvc.perform(delete("/student/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );
        resultActions
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void shouldGetStudentByAge() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("Gari");
        student.setAge(20);
        Student student1 = new Student();
        student.setId(12L);
        student.setName("Ilona");
        student.setAge(25);
        List<Student> studentList =new ArrayList<>();
        studentList.add(student);
        studentList.add(student1);


        when(studentService.studentsInAge(25)).thenReturn(studentList);

        ResultActions resultActions = mockMvc.perform(get("/student/by-age")
                .param("age", "25")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        resultActions
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(studentList)))
                .andDo(print());
    }

    @Test
    void shouldGetStudentByAgeBetween() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("Gari");
        student.setAge(20);
        Student student1 = new Student();
        student.setId(12L);
        student.setName("Ilona");
        student.setAge(25);
        List<Student> studentList =new ArrayList<>();
        studentList.add(student);
        studentList.add(student1);
        when(studentService.getStudentByAgeBetween(22, 25)).thenReturn(studentList);

        ResultActions resultActions = mockMvc.perform(get("/student/by-age-between")
                        .param("ageMin", "22")
                        .param("ageMax", "25")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(studentList)))
                .andDo(print());

    }
}

