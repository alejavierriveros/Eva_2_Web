package com.example;

import com.example.model.Curso;
import com.example.service.CursoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebEdutechApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebEdutechApplication.class, args);
    }

    @Bean
    public CommandLineRunner testCursoService(CursoService cursoService) {
        return args -> {
            Curso curso = new Curso();
            curso.setNombre("Spring Boot");
            curso.setDescripcion("Curso de introducción a Spring Boot");
            curso.setCupos(30);
            curso.setFechaInicio("2025-06-01");
            curso.setSeccion("A");
            curso.setPrecio(150);

            cursoService.saveCurso(curso);

            System.out.println("Curso insertado correctamente:");
            System.out.println(cursoService.getCursos());
        };
    }
}
