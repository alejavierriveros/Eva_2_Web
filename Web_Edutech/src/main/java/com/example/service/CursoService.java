package com.example.service;

import com.example.model.Curso;
import com.example.repository.CursoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CursoService {
     @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> getCursos() {
        return cursoRepository.obtenerCursos();
    }

    public Curso saveCurso(Curso curso) {
        return cursoRepository.guardad(curso);
    }

    public Curso getCursoId(int id) {
        return cursoRepository.buscarPorId(id);
    }

    public Curso updateCurso(Curso curso) {
        return cursoRepository.actualizar(curso);
    }

    public String deleteCurso(int id) {
        cursoRepository.eliminarCurso(id);
        return "Curso Eliminado";
    }

    public int totalCursos() {
        return cursoRepository.obtenerCursos().size();
    }

    public int totalCursosV2() {
        return cursoRepository.totalCursos();
    }
}
