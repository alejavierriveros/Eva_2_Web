package com.example.controller;

import com.example.model.Curso;
import com.example.service.CursoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public List<Curso> getCursos() {
        return cursoService.getCursos();
    }

    @PostMapping
    public Curso saveCurso(@RequestBody Curso curso) {
        return cursoService.saveCurso(curso);
    }

    @GetMapping("{id}")
    public Curso buscarCurso(@PathVariable int id) {
        return cursoService.getCursoId(id);
    }

    @PutMapping("{id}")
    public Curso actualizarCurso(@PathVariable int id, @RequestBody Curso curso) {
        curso.setId(id); 
        return cursoService.updateCurso(curso);
    }

    @DeleteMapping("{id}")
    public String eliminarCurso(@PathVariable int id) {
        return cursoService.deleteCurso(id);
    }

    @GetMapping("/total")
    public int totalCursos() {
        return cursoService.totalCursos();
    }

    
}
