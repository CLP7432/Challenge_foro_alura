package com.alura.api.foro.alura.domain.topico;

import com.alura.api.foro.alura.domain.curso.Curso;
import com.alura.api.foro.alura.domain.usuario.Usuario;

public record DatosListadoTopico(Long id,String mensaje, Usuario usuario, Curso curso) {

    public DatosListadoTopico(Topico topico){
        this(topico.getId(),topico.getMensaje(), topico.getAutor(), topico.getCurso());
    }
}
