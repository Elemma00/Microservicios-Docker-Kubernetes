package com.emma.springcloud.mscv.usuarios.msvc_usuarios.repositories;

import org.springframework.data.repository.CrudRepository;

import com.emma.springcloud.mscv.usuarios.msvc_usuarios.models.entities.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Boolean existsByEmail(String email);
}
