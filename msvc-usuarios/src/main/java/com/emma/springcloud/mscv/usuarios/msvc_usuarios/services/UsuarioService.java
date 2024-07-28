package com.emma.springcloud.mscv.usuarios.msvc_usuarios.services;

import java.util.List;
import java.util.Optional;

import com.emma.springcloud.mscv.usuarios.msvc_usuarios.models.entities.Usuario;

public interface UsuarioService {

    List<Usuario> listar();
    Optional<Usuario> porId(Long id);
    Usuario guardar(Usuario usuario);
    void eliminar(Long id);
    List<Usuario> listarPorIds(Iterable<Long> ids);

    Boolean existeEmail(String email);

}
