package com.example.repository;

import com.example.dto.ReporteDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;
import com.example.model.Venta;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReporteRepository extends CrudRepository<Venta, Long> {

    @Query("SELECT new com.example.dto.ReporteDTO(" +
           "c.nombre, CAST(v.fecha AS string), COUNT(v), SUM(v.precio)) " +
           "FROM Venta v JOIN v.curso c " +
           "WHERE v.fecha BETWEEN :desde AND :hasta " +
           "GROUP BY c.nombre, v.fecha")
    List<ReporteDTO> findVentasPorRango(
            @Param("desde") LocalDate desde,
            @Param("hasta") LocalDate hasta
    );
}
