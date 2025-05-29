package com.example.EdutechEvolutionV1.controller;

import com.example.EdutechEvolutionV1.model.Curso;
import com.example.EdutechEvolutionV1.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;


    @GetMapping
    public List<Curso> getCursos() {
        return cursoService.getCursos();
    }

    
    @GetMapping("/{id}")
    public Curso getCursoPorId(@PathVariable int id) {
        return cursoService.getCursoId(id);
    }

    
    @PostMapping
    public Curso crearCurso(@RequestBody Curso curso) {
        return cursoService.saveCurso(curso);
    }

    
    @PutMapping
    public Curso actualizarCurso(@RequestBody Curso curso) {
        return cursoService.updateCurso(curso);
    }

    
    @DeleteMapping("/{id}")
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

    
    @GetMapping("/buscar-fechas")
    public List<Curso> buscarPorRangoFechas(@RequestParam String desde, @RequestParam String hasta) {
        return cursoService.buscarCursosEntreFechas(desde, hasta);
    }

    
    @GetMapping("/buscar-nombre")
    public Curso buscarPorNombre(@RequestParam String nombre) {
        return cursoService.buscarPorNombre(nombre);
    }
}
