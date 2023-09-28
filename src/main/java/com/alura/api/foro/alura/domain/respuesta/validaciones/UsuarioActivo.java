package com.alura.api.foro.alura.domain.respuesta.validaciones;

import com.alura.api.foro.alura.domain.respuesta.DatosRegistroRespuesta;
import com.alura.api.foro.alura.domain.usuario.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("usuarioActivoDeRespuestas")
public class UsuarioActivo implements ValidadorDeConsultas{

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void validar(DatosRegistroRespuesta datos){
        if(datos.idUsuario() == null){
            return;
        }
        var usuarioActivo = usuarioRepository.findByActivoById(datos.idUsuario());

        if(!usuarioActivo){
            throw new ValidationException("No se puede realizar una respuesta con usuarios inactivos");
        }
    }
}
