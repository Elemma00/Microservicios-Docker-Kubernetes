package com.emma.springcloud.msvc.cursos.msvc_cursos.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emma.springcloud.msvc.cursos.msvc_cursos.clients.UsuarioClientRest;
import com.emma.springcloud.msvc.cursos.msvc_cursos.models.Usuario;
import com.emma.springcloud.msvc.cursos.msvc_cursos.models.entities.Curso;
import com.emma.springcloud.msvc.cursos.msvc_cursos.models.entities.CursoUsuario;
import com.emma.springcloud.msvc.cursos.msvc_cursos.repositories.CursoRepository;


@Service
public class CursoServiceImpl implements CursoService{

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioClientRest client;

    @Override
    @Transactional(readOnly = true)
    public List<Curso> listar() {
        return (List<Curso>) cursoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> porId(Long id) {
        return cursoRepository.findById(id);
    }

    @Override
    @Transactional
    public Curso guardar(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        cursoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Usuario> asignarUsuario(Usuario usuario, Long idCurso) {
        Optional<Curso> o = cursoRepository.findById(idCurso);
        if(o.isPresent()){
            
            Usuario usuarioMsvc = client.detalle(usuario.getId());
            Curso curso = o.get();
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usuarioMsvc.getId());
            curso.addCursoUsuario(cursoUsuario);
            cursoRepository.save(curso);
            return Optional.of(usuarioMsvc);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Usuario> crearUsuario(Usuario usuario, Long idCurso) {
        Optional<Curso> o = cursoRepository.findById(idCurso);
        if(o.isPresent()){
            Usuario usuarioNuevoMsvc = client.crear(usuario);

            Curso curso = o.orElseThrow();
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usuarioNuevoMsvc.getId());
            curso.addCursoUsuario(cursoUsuario);

            cursoRepository.save(curso);
            return Optional.of(usuarioNuevoMsvc);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Usuario> eliminarUsuario(Usuario usuario, Long idCurso) {
        Optional<Curso> o = cursoRepository.findById(idCurso);
        if(o.isPresent()){
            Usuario usuarioMsvc = client.detalle(usuario.getId());

            Curso curso = o.orElseThrow();
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usuarioMsvc.getId());

            curso.removeCursoUsuario(cursoUsuario);
            cursoRepository.save(curso);
            return Optional.of(usuarioMsvc);
        }
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> porIdConUsuarios(Long id) {
        Optional<Curso> o = cursoRepository.findById(id);
        if(o.isPresent()){
            Curso curso = o.get();
            if(!curso.getCursoUsuarios().isEmpty()){
                List<Long> ids = curso.getCursoUsuarios()
                        .stream()
                        .map(cu -> cu.getUsuarioId())
                        .toList();
                List<Usuario> usuarios = client.obtenerAlumnosPorCurso(ids);    
                curso.setUsuarios(usuarios);  
            }
            return Optional.of(curso);  
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public void eliminarCursoUsuarioPorId(Long id) {
       cursoRepository.eliminarCursoUsuarioPorId(id);
    }

}
