package ru.hogwarts.magic_school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.magic_school.model.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByAge(int age);
    List<Student> findByAgeBetween(int ageMin,int ageMax);

    @Query(nativeQuery = true,value="SELECT COUNT* FROM student")
    int getAllStudent();

    @Query(nativeQuery = true,value = "SELECT AVG(age)FROM student")
    int middleAgeByStudents();

    @Query(nativeQuery = true,value = "SELECT * FROM student ORDER BY CreateDate DESC LIMIT 5 ")
    List<Student> getTheLastFiveStudents();

}
