package com.emma.springcloud.msvc.cursos.msvc_cursos.services;

import java.util.List;
import java.util.Optional;

import com.emma.springcloud.msvc.cursos.msvc_cursos.models.Usuario;
import com.emma.springcloud.msvc.cursos.msvc_cursos.models.entities.Curso;

public interface CursoService {

    List<Curso> listar();
    Optional<Curso> porId(Long id);
    Optional<Curso> porIdConUsuarios(Long id);
    Curso guardar(Curso curso);
    void eliminar(Long id);

    void eliminarCursoUsuarioPorId(Long id);

    Optional<Usuario> asignarUsuario(Usuario usuario, Long idCurso);
    Optional<Usuario> crearUsuario(Usuario usuario, Long idCurso);
    Optional<Usuario> eliminarUsuario(Usuario usuario, Long idCurso);
}
