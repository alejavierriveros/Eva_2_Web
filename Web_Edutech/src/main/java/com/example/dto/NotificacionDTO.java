package com.example.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificacionDTO {
    private Long usuarioId;
    private String tipo;
    private String mensaje;
    private String canal;
}
