package com.example.repository;

import com.example.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {

    // Buscar curso por nombre exacto
    Curso findByNombre(String nombre);

    // Buscar cursos por rango de fechas
    List<Curso> findByFechaInicioBetween(String desde, String hasta);

    // Buscar curso por ID (ya incluido por JpaRepository: findById)

    // Eliminar por ID (ya incluido por JpaRepository: deleteById)

    // Contar total de cursos (ya incluido por JpaRepository: count)

}
