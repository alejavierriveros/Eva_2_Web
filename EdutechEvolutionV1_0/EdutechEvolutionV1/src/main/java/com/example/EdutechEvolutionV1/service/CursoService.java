package com.example.EdutechEvolutionV1.service;

import com.example.EdutechEvolutionV1.model.Curso;
import com.example.EdutechEvolutionV1.repository.CursoRepository;
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
        return cursoRepository.guardar(curso);
    }

    public Curso getCursoId(int id) {
        return cursoRepository.buscarPorId(id);
    }

    public Curso updateCurso(Curso curso) {
        return cursoRepository.actualizar(curso);
    }

    public String deleteCurso(int id) {
        cursoRepository.eliminar(id);
        return "curso eliminado";
    }

    
    public int totalCursos() {
        return cursoRepository.obtenerCursos().size();
    }

    
    public int totalCursosV2() {
        return cursoRepository.totalCursos();
    }

    public List<Curso> buscarCursosEntreFechas(String desde, String hasta) {
        return cursoRepository.buscarPorRangoFechas(desde, hasta);
    }

    public Curso buscarPorNombre(String nombre) {
        return cursoRepository.buscarPorNombre(nombre);
    }
}
