package com.example.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.dto.ReporteDTO; 

public class ExcelGenerator {

    public ByteArrayInputStream generarExcel(List<ReporteDTO> data) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Ventas");
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Curso");
            header.createCell(1).setCellValue("Fecha");
            header.createCell(2).setCellValue("Cantidad");
            header.createCell(3).setCellValue("Total");

            int rowIdx = 1;
            for (ReporteDTO d : data) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(d.getNombreCurso());
                row.createCell(1).setCellValue(d.getFechaVenta().toString()); // Asegúrate de convertir la fecha a String
                row.createCell(2).setCellValue(d.getCantidad());
                row.createCell(3).setCellValue(d.getTotal());
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("Error al generar el reporte Excel", e);
        }
    }
}
