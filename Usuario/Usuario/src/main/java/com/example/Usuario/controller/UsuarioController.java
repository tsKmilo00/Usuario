package com.example.Usuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Usuario.model.Roles;
import com.example.Usuario.model.Usuario;
import com.example.Usuario.service.UsuarioService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RequestMapping("/api/usuarios")

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    
    @PostMapping("/crear")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Usuario nuevUsuario = usuarioService.crearUsuario(usuario);
        return ResponseEntity.ok(nuevUsuario);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable int id) {
        return usuarioService.buscarUsuario(id)
                .map(usuario -> ResponseEntity.ok(usuario))
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/modificar/{id}")
    public ResponseEntity<Usuario> modificarUsuario(@PathVariable int id, @RequestParam Roles roles) {
        Usuario usuarioModificado = usuarioService.modificarUsuario(id, roles);
        if (usuarioModificado != null) {
            return ResponseEntity.ok(usuarioModificado);
        } else {
            return ResponseEntity.notFound().build();
        }
        
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable int id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

}
