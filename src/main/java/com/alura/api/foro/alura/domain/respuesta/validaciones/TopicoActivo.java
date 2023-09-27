package com.alura.api.foro.alura.domain.respuesta.validaciones;

import com.alura.api.foro.alura.domain.respuesta.DatosRegistroRespuesta;
import com.alura.api.foro.alura.domain.topico.TopicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicoActivo  implements ValidadorDeConsultas{

    @Autowired
    private TopicoRepository topicoRepository;

    public void validar(DatosRegistroRespuesta datos){
        if(datos.idTopico() == null){
            return;
        }
        var topicoActivo = topicoRepository.findByActivoById(datos.idTopico());

        if(!topicoActivo){
            throw new ValidationException("No se puede realizar una respuesta con topicos inactivos");
        }

    }
}
