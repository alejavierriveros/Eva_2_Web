package com.example.controller;

import com.example.model.Usuario;
import com.example.service.UsuarioService;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/usuarios")
@CrossOrigin 
public class UsuarioControler {
    @Autowired
    private UsuarioService serv;

    
    @PostMapping("/registrar") 
    public Usuario registrar(@RequestBody Usuario u){
        return serv.registrar(u);
    }

    
    @PostMapping("/login")
    public Map<String, String> login (@RequestBody Usuario u){
        Optional<Usuario> user = serv.autenticar(u.getEmail(), u.getPassword());
        Map<String, String> response = new HashMap<>();
        if (user.isPresent()){
            response.put("result", "OK");
            response.put("nombre", user.get().getNombre());
        } else {
            response.put("result", "ERROR");
        }
        return response;
    }
}
