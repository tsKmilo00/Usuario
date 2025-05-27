package com.example.Usuario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Usuario.model.Usuario;
import com.example.Usuario.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.create(usuario);
    }
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.realAll();
    }
    public Usuario buscarUsuario(int id) {
        return usuarioRepository.read(id);
    }
    public Usuario modificarUsuario(int id, Usuario usuario) {
        return usuarioRepository.update(id, usuario);
    }
    public String eliminarUsuario(int id) {
        return usuarioRepository.delete(id);
    }
}
