package com.example.EdutechEvolutionV1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int cupos;
    private String nombre;
    private String descripcion;

    private String fechaInicio;

    private String seccion;
    private int precio;
}
