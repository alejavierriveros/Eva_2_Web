package com.example.controller;

import com.example.model.Curso;
import com.example.service.CursoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Curso buscarCurso(@PathVariable int id){
        return cursoService.getCursoId(id);
    }
    @PutMapping("{id}")
    public Curso actualizarCurso(@PathVariable int id, @RequestBody Curso curso) {
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
    @GetMapping("/totalv2")
    public int totalCursosV2() {
        return cursoService.totalCursosV2();
    }
}

    
    