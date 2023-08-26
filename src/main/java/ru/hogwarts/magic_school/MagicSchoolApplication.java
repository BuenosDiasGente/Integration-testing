package ru.hogwarts.magic_school;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.Stream;

import static ru.hogwarts.magic_school.service.impl.StudentServiceImpl.*;

@SpringBootApplication
@OpenAPIDefinition
public class MagicSchoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(MagicSchoolApplication.class, args);
        measureTimeParallel();
        measureTimeNotParallel();

    }

    private static void measureTimeParallel() {
        long start = System.currentTimeMillis();
        Stream.iterate(1, a -> a + 1)
                .parallel()
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b);
        System.out.println(System.currentTimeMillis() - start);
    }

    private static void measureTimeNotParallel() {
        long start = System.currentTimeMillis();
        Stream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .reduce(0, (a, b) -> a + b);
        System.out.println(System.currentTimeMillis() - start);
    }
}
