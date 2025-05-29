package com.example.controller;

import com.example.dto.NotificacionDTO;
import com.example.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @PostMapping
    public ResponseEntity<String> enviarNotificacion(@RequestBody NotificacionDTO dto) {
        notificacionService.enviarNotificacion(dto);
        return ResponseEntity.ok("Notificación enviada exitosamente");
    }
}
