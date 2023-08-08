package ru.hogwarts.magic_school.controller;

import ru.hogwarts.magic_school.model.Faculty;
import ru.hogwarts.magic_school.model.Student;

import java.util.ArrayList;
import java.util.List;

public class PepareTestObject {
    public static Student studentObject(){
        Student student = new Student();
        student.setId(1L);
        student.setName("Gari");
        student.setAge(20);
        return student;
    }
    public static Faculty facultyObject(){
        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("Home");
        faculty.setColor("black");
        return faculty;
    }

    public static List<Student> studentsList(){
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
        return studentList;
    }

    public static List<Faculty>facultysList(){
        Faculty faculty=new Faculty();
        faculty.setId(1L);
        faculty.setName("Home");
        faculty.setColor("black");

        Faculty faculty1=new Faculty();
        faculty1.setId(2L);
        faculty1.setName("Rive");
        faculty1.setColor("black");

        List<Faculty> facultyList =new ArrayList<>();
        facultyList.add(faculty);
        facultyList.add(faculty1);
        return facultyList;
    }
}
