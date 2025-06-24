package com.example.Usuario.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.Usuario.model.Roles;
import com.example.Usuario.model.Usuario;
import com.example.Usuario.repository.UsuarioRepository;

public class UsuarioServiceTest {
    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearUsuario() {
        Usuario usuario = new Usuario(0, Roles.CLIENTE, "", "", "", "");
        Usuario guardarUsuario = new Usuario(1, Roles.CLIENTE, "12345678-9", "Juan", "Pérez", "user@gmail.com");
        when(usuarioRepository.save(usuario)).thenReturn(guardarUsuario);

        Usuario resultado = usuarioService.crearUsuario(usuario);
        assertThat(resultado.getId()).isEqualTo(1);
        assertThat(resultado.getRol()).isEqualTo(Roles.CLIENTE);
        assertThat(resultado.getRut()).isEqualTo("12345678-9");
        assertThat(resultado.getNombre()).isEqualTo("Juan");
        assertThat(resultado.getApellido()).isEqualTo("Pérez");
        assertThat(resultado.getEmail()).isEqualTo("user@gmail.com");
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void testMostrarUsuarios() {
        Usuario usuario1 = new Usuario(1, Roles.CLIENTE, "12345678-9", "Juan", "Pérez", "user1@gmail.com");
        Usuario usuario2 = new Usuario(2, Roles.VENDEDOR, "98765432-1", "Ana", "Gómez", "user2@gmail.com");
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario1, usuario2));

        List<Usuario> usuarios = usuarioService.listarUsuarios();
        assertThat(usuarios).hasSize(2);
        verify(usuarioRepository).findAll();
    }

    @Test
    void testBuscarUsuario() {
        Usuario usuario = new Usuario(1, Roles.CLIENTE, "12345678-9", "Wacoldo", "Soto", "user@gmail.com");
        when(usuarioRepository.findById(1)).thenReturn(java.util.Optional.of(usuario));

        java.util.Optional<Usuario> resultado = usuarioService.buscarUsuario(1);
        assertThat(resultado).isPresent();
        assertThat(resultado.get().getId()).isEqualTo(1);
        assertThat(resultado.get().getRut()).isEqualTo("12345678-9");
        verify(usuarioRepository).findById(1);
    }

    @Test
    void testModificarUsuario() {
        Usuario usuario = new Usuario(1, Roles.CLIENTE, "12345678-9", "Wacoldo", "Soto", "user@gmail.com");
        when(usuarioRepository.findById(1)).thenReturn(java.util.Optional.of(usuario));
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario resultado = usuarioService.modificarUsuario(1, Roles.VENDEDOR);
        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo(1);
        assertThat(resultado.getRol()).isEqualTo(Roles.VENDEDOR);
        verify(usuarioRepository).findById(1);
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void testEliminarUsuario() {
        int id = 1;
        usuarioService.eliminarUsuario(id);
        verify(usuarioRepository).deleteById(id);
    }
}
