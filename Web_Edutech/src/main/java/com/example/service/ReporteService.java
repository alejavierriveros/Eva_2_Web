package com.example.service;

import com.example.dto.ReporteDTO;
import com.example.repository.ReporteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReporteService {

    private final ReporteRepository reporteRepository;

    public ReporteService(ReporteRepository reporteRepository) {
        this.reporteRepository = reporteRepository;
    }

    public List<ReporteDTO> reporteVentasEntreFechas(String desde, String hasta) {
        LocalDate fechaDesde = LocalDate.parse(desde);
        LocalDate fechaHasta = LocalDate.parse(hasta);
        return reporteRepository.findVentasPorRango(fechaDesde, fechaHasta);
    }
}
