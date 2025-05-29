package com.example.controller;

import com.example.dto.ReporteDTO;
import com.example.service.ReporteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/reportes")
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping
    public List<ReporteDTO> obtenerReporte(
            @RequestParam String desde,
            @RequestParam String hasta
    ) {
        return reporteService.reporteVentasEntreFechas(desde, hasta);
    }
}
