package com.example.repository;

import com.example.model.Curso;

import java.util.ArrayList;
import java.util.List;

public class CursoRepository {
    
        
    private List<Curso> listaCursos = new ArrayList<>();
    
    
    public List<Curso> obtenerCursos() {
        return listaCursos;
    }

    
    public Curso buscarPorId(int id) {
        for (Curso curso : listaCursos) {
            if (curso.getId()==id) {
                return curso;
            }
        }
        return null;
    }

    
    public Curso buscarPorNombre(String nombre) {
        for (Curso curso : listaCursos) {
            if(curso.getNombre().equals(nombre)) {
                return curso;
            }
        }
        return null;
    }

    
    public Curso guardad(Curso cur) {
        long nuevoId = 1;
        for (Curso c : listaCursos) {
            if (c.getId() >= nuevoId) {
                nuevoId = c.getId() + 1;
            }
        }

        Curso curso = new Curso();
        curso.setId((int) nuevoId);
        curso.setCupos(cur.getCupos());
        curso.setNombre(cur.getNombre());
        curso.setDescripcion(cur.getDescripcion());
        curso.setFechaInicio(cur.getFechaInicio());
        curso.setSeccion(cur.getSeccion());
        curso.setPrecio(0);


        listaCursos.add(curso);

        return curso;
    }

    public Curso actualizar(Curso cur) {
        int id = 0;
        int idPosicion = 0;

        for(int i = 0; i < listaCursos.size(); i++) {
            if (listaCursos.get(i).getId()== cur.getId()) {
                id = cur.getId();
                idPosicion = i;
            }
        }

        Curso curso1 = new Curso();
        curso1.setId(id);
        curso1.setCupos(cur.getCupos());
        curso1.setNombre(cur.getNombre());
        curso1.setDescripcion(cur.getDescripcion());
        curso1.setFechaInicio(cur.getFechaInicio());
        curso1.setSeccion(cur.getSeccion());
        curso1.setPrecio(0);

        listaCursos.set(idPosicion, curso1);
        return curso1;
    }

    public void eliminarCurso(int id) {
        Curso curso = buscarPorId(id);
        if (curso != null) {
            listaCursos.remove(curso);
        }
    }

    public int totalCursos() {
        return listaCursos.size();
    }
}


