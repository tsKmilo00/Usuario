package com.example.Usuario.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.Usuario.model.Usuario;

@Repository
public class UsuarioRepository {
    private List<Usuario> Usuario = new ArrayList<>();

    public Usuario create(Usuario newUsuario) {
        Usuario.add(newUsuario);
        System.out.println(newUsuario.toString());
        return newUsuario;
    }

   public List<Usuario> realAll() {
    return Usuario;
   } 
   public Usuario read(int id) {
    for (Usuario usuario : Usuario) {
        if (usuario.getId() == id) {
            return usuario;
        }
    }
    return null;
   }
   public Usuario readxNombre(String nombre) {
    for (Usuario usuario : Usuario) {
        if (usuario.getNombre().equalsIgnoreCase(nombre)) {
            return usuario;
        }
    }
    return null;
   }
    public Usuario update(int id, Usuario usuario) {
        Usuario modif = this.read(id);
        if (modif != null) {
        modif.setRol(usuario.getRol());
        modif.setRut(usuario.getRut());
        modif.setNombre(usuario.getNombre());
        modif.setApellido(usuario.getApellido());
        modif.setEmail(usuario.getEmail());
        return modif;
        }
    
     return null;
    }
    public String delete(int id) {
        if(Usuario.removeIf(kill -> kill.getId() == id)) {
            return "Usuario eliminado correctamente";
        } else {
            return "Usuario no encontrado";
        }

    }
}
