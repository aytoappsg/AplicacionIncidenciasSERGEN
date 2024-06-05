package com.islacristina.aplicaciongestionincidencias.services;

import com.islacristina.aplicaciongestionincidencias.exceptions.InvalidCredentialsException;
import com.islacristina.aplicaciongestionincidencias.exceptions.UserNotFoundException;
import com.islacristina.aplicaciongestionincidencias.model.Usuario;
import com.islacristina.aplicaciongestionincidencias.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario login(String username, String password) throws UserNotFoundException, InvalidCredentialsException {
        Usuario usuario = usuarioRepository.findByName(username);
        if (usuario == null) {
            throw new UserNotFoundException("User not found");
        }

        if (!usuario.getContrasena().equals(password)) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        return usuario;
    }

    public Usuario findByName(String username) {
        return usuarioRepository.findByName(username);
    }

    public Usuario updateUser(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deleteUser(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

    public List<Usuario> getAllUsers() {
        return usuarioRepository.findAll();
    }

    public Usuario saveUser(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}