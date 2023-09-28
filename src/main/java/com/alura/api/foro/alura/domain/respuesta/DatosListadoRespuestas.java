package com.alura.api.foro.alura.domain.respuesta;

import com.alura.api.foro.alura.domain.topico.Topico;
import com.alura.api.foro.alura.domain.usuario.Usuario;

public record DatosListadoRespuestas(Long id, String mensaje, Topico topico, Usuario usuario, Boolean solucion) {

    public DatosListadoRespuestas(Respuesta respuesta){
        this(respuesta.getId(),respuesta.getMensaje(), respuesta.getTopico(), respuesta.getAutor(), respuesta.getSolucion());
    }
}
