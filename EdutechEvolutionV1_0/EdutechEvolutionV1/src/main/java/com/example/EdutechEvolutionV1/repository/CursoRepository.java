package com.example.EdutechEvolutionV1.repository;

import com.example.EdutechEvolutionV1.model.Curso;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CursoRepository {


    private List<Curso> listaCursos = new ArrayList<>();

    public CursoRepository() {
       
        listaCursos.add(new Curso(1, 30, "Java Básico", "Curso introductorio a Java", "2024-06-01", "A", 50000));
        listaCursos.add(new Curso(2, 25, "Spring Boot", "Aplicaciones Web con Spring Boot", "2024-07-15", "B", 75000));
        listaCursos.add(new Curso(3, 20, "SQL y Bases de Datos", "Curso completo de SQL", "2024-08-10", "C", 60000));
    }

    public List<Curso> obtenerCursos() {
        return listaCursos;
    }

    public Curso buscarPorId(int id) {
        for (Curso curso : listaCursos) {
            if (curso.getId() == id) {
                return curso;
            }
        }
        return null;
    }

    public Curso guardar(Curso c) {
        long nuevoId = 1;
        for (Curso curso : listaCursos) {
            if (curso.getId() >= nuevoId) {
                nuevoId = curso.getId() + 1;
            }
        }

        Curso nuevoCurso = new Curso();
        nuevoCurso.setId((int) nuevoId); 
        nuevoCurso.setNombre(c.getNombre());
        nuevoCurso.setDescripcion(c.getDescripcion());
        nuevoCurso.setFechaInicio(c.getFechaInicio());
        nuevoCurso.setCupos(c.getCupos());
        nuevoCurso.setSeccion(c.getSeccion());
        nuevoCurso.setPrecio(c.getPrecio());

        listaCursos.add(nuevoCurso);
        return nuevoCurso;
    }

    public Curso actualizar(Curso c) {
        int id = 0;
        int posicion = -1;

        for (int i = 0; i < listaCursos.size(); i++) {
            if (listaCursos.get(i).getId() == c.getId()) {
                id = c.getId();
                posicion = i;
                break;
            }
        }

        if (posicion != -1) {
            Curso actualizado = new Curso();
            actualizado.setId(id);
            actualizado.setNombre(c.getNombre());
            actualizado.setDescripcion(c.getDescripcion());
            actualizado.setFechaInicio(c.getFechaInicio());
            actualizado.setCupos(c.getCupos());
            actualizado.setSeccion(c.getSeccion());
            actualizado.setPrecio(c.getPrecio());

            listaCursos.set(posicion, actualizado);
            return actualizado;
        }

        return null;
    }

    public void eliminar(int id) {
        listaCursos.removeIf(c -> c.getId() == id);
    }

    public int totalCursos() {
        return listaCursos.size();
    }

    public List<Curso> buscarPorRangoFechas(String desde, String hasta) {
        List<Curso> resultado = new ArrayList<>();
        for (Curso c : listaCursos) {
            if (c.getFechaInicio().compareTo(desde) >= 0 && c.getFechaInicio().compareTo(hasta) <= 0) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    public Curso buscarPorNombre(String nombre) {
        for (Curso c : listaCursos) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                return c;
            }
        }
        return null;
    }
}
