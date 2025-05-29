package com.example.service;

import com.example.model.Curso;
import com.example.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    // Obtener todos los cursos
    public List<Curso> getCursos() {
        return cursoRepository.findAll();
    }

    // Guardar un curso (nuevo o actualizar)
    public Curso saveCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    // Obtener curso por ID
    public Curso getCursoId(int id) {
        Optional<Curso> optionalCurso = cursoRepository.findById(id);
        return optionalCurso.orElse(null); // Devuelve null si no existe
    }

    // Actualizar curso (es igual que guardar)
    public Curso updateCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    // Eliminar curso por ID
    public String deleteCurso(int id) {
        cursoRepository.deleteById(id);
        return "Curso Eliminado";
    }

    // Total de cursos
    public int totalCursos() {
        return (int) cursoRepository.count();
    }

    // Buscar cursos entre fechas (formato: "yyyy-MM-dd")
    public List<Curso> buscarCursosEntreFechas(String desde, String hasta) {
        return cursoRepository.findByFechaInicioBetween(desde, hasta);
    }

    // Buscar por nombre
    public Curso buscarPorNombre(String nombre) {
        return cursoRepository.findByNombre(nombre);
    }
}

