package com.example.EdutechEvolutionV1.service;
import com.example.EdutechEvolutionV1.model.Usuario;
import com.example.EdutechEvolutionV1.repository.UsuarioRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repo;

    public Usuario registrar(Usuario u){
        return repo.save(u);
    }

    public Optional<Usuario> autenticar(String email, String password) {
        return repo.findByEmail(email).filter(u -> u.getPassword().equals(password));
    }
}
