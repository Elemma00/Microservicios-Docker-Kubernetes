package com.emma.springcloud.msvc.cursos.msvc_cursos.services;

import java.util.List;
import java.util.Optional;

import com.emma.springcloud.msvc.cursos.msvc_cursos.models.entities.Curso;

public interface CursoService {

    List<Curso> listar();
    Optional<Curso> porId(Long id);
    Curso guardar(Curso curso);
    void eliminar(Long id);
}
