package ru.hogwarts.magic_school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.hogwarts.magic_school.model.Faculty;
import ru.hogwarts.magic_school.model.Student;
import ru.hogwarts.magic_school.service.FacultyService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FacultyController.class)
public class FacultyControllerWebMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private FacultyService facultyService;

    @Test
    void shouldGetFacultyById() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("Pokemon");
        faculty.setColor("black");

        when(facultyService.get(1L)).thenReturn(faculty);

        ResultActions resultActions = mockMvc.perform(get("/faculty/1")
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
    void shouldReturnNotFoundWhenGetFacultyById() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/faculty/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );
        resultActions
                .andExpect(status().isNotFound())
                .andDo(print());

    }

    @Test
    void shouldGetListStudentByFacultyId() throws Exception {
        Faculty faculty=new Faculty();
        faculty.setId(2L);
        faculty.setName("Gari");
        faculty.setColor("black");

        Student student1 = new Student();
        student1.setId(2l);
        student1.setName("Gari");
        student1.setAge(25);
        student1.setFaculty(faculty);


        when(facultyService.getStudentByFaculty(2L)).thenReturn(Arrays.asList(student1));

        ResultActions resultActions = mockMvc.perform(get("/faculty/2/students")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );
        resultActions
                .andExpect(status().isOk())
             .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(Arrays.asList(student1))));


    }

    @Test
    void shouldCreateFaculty() throws Exception {

        Faculty faculty=new Faculty();
        faculty.setId(2L);
        faculty.setName("Gari");
        faculty.setColor("black");

        when(facultyService.add(faculty)).thenReturn(faculty);

        ResultActions resultActions = mockMvc.perform(
                post("/faculty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(faculty))
                        .accept(MediaType.APPLICATION_JSON));

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(faculty.getName()))
                .andExpect(jsonPath("$.color").value(faculty.getColor()))
                .andDo(print());

    }
    @Test
    void shouldUpdateFaculty() throws Exception {

        Faculty faculty=new Faculty();
        faculty.setId(2L);
        faculty.setName("Gari");
        faculty.setColor("black");

        when(facultyService.update(faculty)).thenReturn(faculty);

        ResultActions resultActions = mockMvc.perform(
                put("/faculty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(faculty))
                        .accept(MediaType.APPLICATION_JSON));

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(faculty.getId()))
                .andExpect(jsonPath("$.name").value(faculty.getName()))
                .andExpect(jsonPath("$.color").value(faculty.getColor()))
                .andDo(print());

    }
    @Test
    void shouldDeleteFacultyById() throws Exception {

        Faculty faculty=new Faculty();
        faculty.setId(2L);
        faculty.setName("Gari");
        faculty.setColor("black");

        ResultActions resultActions = mockMvc.perform(delete("/faculty/2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );
        resultActions
                .andExpect(status().isOk())
                .andDo(print());

    }
    @Test
    void shouldGetFacultyByColor() throws Exception {
        Faculty faculty=new Faculty();
        faculty.setId(1l);
        faculty.setName("Home");
        faculty.setColor("black");

        Faculty faculty1=new Faculty();
        faculty1.setId(2l);
        faculty1.setName("Rive");
        faculty1.setColor("black");

        List<Faculty> facultyList =new ArrayList<>();
        facultyList.add(faculty);
        facultyList.add(faculty1);

        when(facultyService.facultiesFindByColor("black")).thenReturn(facultyList);

        ResultActions resultActions = mockMvc.perform(get("/faculty/by-color")
                .param("color", "black")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(facultyList)));

    }



    @Test
    void shouldGetFacultyOfColor() throws Exception {
        Faculty faculty=new Faculty();
        faculty.setId(1l);
        faculty.setName("Home");
        faculty.setColor("black");

        Faculty faculty1=new Faculty();
        faculty1.setId(2l);
        faculty1.setName("Rive");
        faculty1.setColor("black");

        List<Faculty> facultyList =new ArrayList<>();
        facultyList.add(faculty);
        facultyList.add(faculty1);


        when(facultyService.facultiesFindByColor("black")).thenReturn(facultyList);

        ResultActions resultActions = mockMvc.perform(get("/faculty/by-color-or-name")
                .param("color", "black")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(facultyList)));

    }
    @Test
    void shouldGetFacultyOfName() throws Exception {
        Faculty faculty=new Faculty();
        faculty.setId(1l);
        faculty.setName("Home");
        faculty.setColor("black");

        Faculty faculty1=new Faculty();
        faculty1.setId(2l);
        faculty1.setName("Rive");
        faculty1.setColor("black");

        List<Faculty> facultyList =new ArrayList<>();
        facultyList.add(faculty);
        facultyList.add(faculty1);


        when(facultyService.facultiesFindByName("Rive")).thenReturn(facultyList);

        ResultActions resultActions = mockMvc.perform(get("/faculty/by-color-or-name")
                .param("name", "Rive")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(facultyList)));

    }



}
