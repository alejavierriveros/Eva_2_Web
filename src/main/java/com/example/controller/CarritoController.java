package com.example.controller;

import com.example.model.Curso;
import com.example.service.CursoService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
@RequestMapping("/api/v2/carrito")
public class CarritoController {
    private final List<Curso> carrito = new ArrayList<>();


    @Autowired
    private CursoService cursoserv;

    @PostMapping("/agregar/{id}")
    public String agregarCurso(@PathVariable int id){
        Curso curso = cursoserv.getCursoId(id);
        if(curso!= null){
            carrito.add(curso);
            return "Curso agregado al carrito " + curso.getNombre();
        }


        return "Curso no fue encontrado";
    }
    
    @GetMapping("path")
    public List<Curso> vercarrito() {
        return carrito;
    }
    
    @DeleteMapping("/eliminar/{id}")
    public String eliminarCurso(@PathVariable int id){
        boolean eliminado = carrito.removeIf(curso -> curso.getId() == id);
        return eliminado ? "Curso eliminado del carrito" : "Curso no estaba en el carrito"; 
    }
    
    @DeleteMapping("/vaciar")
    public String vaciarCarrito() {
        carrito.clear();
        return "Carrito Vaciado";
    }
    
    @GetMapping("/total")
    public int totalCursos () {
        return carrito.size();
    }

}
