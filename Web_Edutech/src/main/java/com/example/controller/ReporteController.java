package com.example.controller;

import com.example.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


@RestController
@RequestMapping("/api/v2/reportes")
public class ReporteController {

    /**
     * Generar un reporte de ventas.
     * @param fechaInicio Fecha de inicio para el reporte.
     * @param fechaFin Fecha de fin para el reporte.
     * @return Reporte de ventas.
     */
    @GetMapping("/ventas")
    public Map<String, Object> obtenerReporteVentas(@RequestParam String fechaInicio, @RequestParam String fechaFin) {
        return reporteService.generarReporteVentas(fechaInicio, fechaFin);
    }

    /**
     * Análisis de comportamiento de usuarios.
     * @param usuarioId ID del usuario para el análisis.
     * @return Análisis de comportamiento.
     */
    @GetMapping("/comportamiento-usuario/{usuarioId}")
    public Map<String, Object> analizarComportamientoUsuario(@PathVariable Long usuarioId) {
        return reporteService.analizarComportamientoUsuario(usuarioId);
    }
    
    /**
     * Métricas de tráfico.
     * @return Métricas de tráfico.
     */
    @GetMapping("/trafico")
    public Map<String, Object> obtenerMetricasTrafico() {
        return reporteService.obtenerMetricasTrafico();
    }
}
