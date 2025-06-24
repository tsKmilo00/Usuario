package com.example.Usuario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Usuario.model.Roles;
import com.example.Usuario.model.Usuario;
import com.example.Usuario.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario crearUsuario(Usuario usuario) {
        usuario.setId(0); 
        return usuarioRepository.save(usuario);
    }
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll(); 
    }
    public Optional<Usuario> buscarUsuario(int id) {
        return usuarioRepository.findById(id);
    }
    public Usuario modificarUsuario(int id, Roles rol) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setRol(rol);
            return usuarioRepository.save(usuario);
        }
        return null; 
    }
    public void eliminarUsuario(int id) {
        usuarioRepository.deleteById(id);
    }
}
