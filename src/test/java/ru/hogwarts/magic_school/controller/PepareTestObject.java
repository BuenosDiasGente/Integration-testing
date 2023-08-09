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
        List<Student> studentList=new ArrayList<>();
        Student student = new Student();
        student.setId(1L);
        student.setName("Gari");
        student.setAge(20);
        Student student1 = new Student();
        student.setId(2L);
        student.setName("Germiona");
        student.setAge(19);
        studentList.add(student);
        studentList.add(student1);
        return studentList;
    }
}
