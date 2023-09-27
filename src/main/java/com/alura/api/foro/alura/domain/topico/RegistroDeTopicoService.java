package com.alura.api.foro.alura.domain.topico;


import com.alura.api.foro.alura.domain.curso.Curso;
import com.alura.api.foro.alura.domain.curso.CursoRepository;
import com.alura.api.foro.alura.domain.topico.validaciones.ValidadorDeConsultas;
import com.alura.api.foro.alura.domain.usuario.Usuario;
import com.alura.api.foro.alura.domain.usuario.UsuarioRepository;
import com.alura.api.foro.alura.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroDeTopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    List<ValidadorDeConsultas> validadores;

    public DatosDetalleTopico registrar(DatosRegistroTopico datos){

        if(!usuarioRepository.findById(datos.idUsuario()).isPresent()){
            throw new ValidacionDeIntegridad("este id para el usuario no fue encontrado");
        }
        if(datos.idCurso() != null && !cursoRepository.existsById(datos.idCurso())){
            throw new ValidacionDeIntegridad("este id de curso no fue encontrado");
        }
        validadores.forEach(v -> v.validar(datos));
        var usuario = usuarioRepository.findById(datos.idUsuario()).get();
        var curso = cursoRepository.findById(datos.idCurso()).get();
        var topico = new Topico(null, datos.titulo(), datos.mensaje(), usuario, curso, datos.fecha());
        topicoRepository.save(topico);
        return new DatosDetalleTopico(topico);
    }



}
