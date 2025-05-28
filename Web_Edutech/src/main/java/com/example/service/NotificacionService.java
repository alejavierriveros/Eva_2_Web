package com.example.service;

import com.example.dto.NotificacionDTO;
import com.example.model.Notificacion;
import com.example.model.Usuario;
import com.example.repository.NotificacionRepository;
import com.example.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void enviarNotificacion(NotificacionDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + dto.getUsuarioId()));

        Notificacion notificacion = new Notificacion();
        notificacion.setTipo(dto.getTipo());
        notificacion.setMensaje(dto.getMensaje());
        notificacion.setCanal(dto.getCanal());
        notificacion.setFechaEnvio(LocalDateTime.now());
        notificacion.setUsuario(usuario);

        switch (dto.getCanal().toUpperCase()) {
            case "EMAIL":
                enviarPorEmail(usuario.getEmail(), dto.getMensaje());
                break;
            //* case "SMS":
              //  enviarPorSMS(usuario.getTelefono(), dto.getMensaje());
               // break; //
            case "PUSH":
                enviarPush(usuario, dto.getMensaje());
                break;
            default:
                throw new IllegalArgumentException("Canal no soportado: " + dto.getCanal());
        }

        notificacionRepository.save(notificacion);
    }

    private void enviarPorEmail(String correo, String mensaje) {
        // Aquí iría la integración real con un servicio de envío de correo
        System.out.println("Enviando correo a " + correo + ": " + mensaje);
    }

   // private void enviarPorSMS(String telefono, String mensaje) {
        // Aquí iría la integración real con un servicio de SMS como Twilio
   //     System.out.println("Enviando SMS a " + telefono + ": " + mensaje);
   // }

    private void enviarPush(Usuario usuario, String mensaje) {
        // Aquí iría la lógica para una notificación push, ej: Firebase
        System.out.println("Enviando Push a usuario ID " + usuario.getId() + ": " + mensaje);
    }
}
