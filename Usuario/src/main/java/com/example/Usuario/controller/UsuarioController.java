package com.example.Usuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Usuario.model.Usuario;
import com.example.Usuario.service.UsuarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RequestMapping("/api/usuarios")

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    
    @RequestMapping("/crear")
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        for (Usuario u : usuarioService.listarUsuarios()) {
            if (u.getRut().equals(usuario.getRut())) {
                return null; // Retorna null si el RUT ya existe
            }
        }
        return usuarioService.crearUsuario(usuario);
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/buscar/{id}")
    public Usuario buscarUsuario(@PathVariable int id) {
        return usuarioService.buscarUsuario(id);
    }
    @PutMapping("/modificar/{id}")
    public Usuario modificarUsuario(@PathVariable int id, @RequestBody Usuario usuario) {
        return usuarioService.modificarUsuario(id, usuario);
    }
    @PostMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable int id) {
        return usuarioService.eliminarUsuario(id);
    }
}
