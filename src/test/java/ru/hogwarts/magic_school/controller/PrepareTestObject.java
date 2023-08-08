package ru.hogwarts.magic_school.controller;

import ru.hogwarts.magic_school.model.Faculty;
import ru.hogwarts.magic_school.model.Student;

import java.util.ArrayList;
import java.util.List;

public class PrepareTestObject {
    public static Student prepareStudent() {
        return new Student(1L, "Pokemon", 19, null);
    }

    public static Faculty prepareFaculty(){
        return new Faculty(1L,"Gari","black",null);
    }

    public static List<Student> prepareStudentsList(){
        return new ArrayList<>() {
            Student student1 = new Student(1l, "Pokemon", 19, null);
            Student student2 = new Student(2l, "Pinokio", 25, null);
            Student student3 = new Student(3l, "Gari", 23, null);
            Student student4 = new Student(4l, "Ilon", 22, null);

        };
    }
}
