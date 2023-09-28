package com.alura.api.foro.alura.domain.topico.validaciones;

import com.alura.api.foro.alura.domain.topico.DatosActualizarTopico;
import com.alura.api.foro.alura.domain.topico.DatosRegistroTopico;
import com.alura.api.foro.alura.domain.usuario.Usuario;
import com.alura.api.foro.alura.domain.usuario.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("UsuarioActivoDeTopico")
public class UsuarioActivo  implements ValidadorDeConsultas{

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void validar(DatosRegistroTopico datos){
        if(datos.idUsuario() == null){
            return;
        }
        var usuarioActivo = usuarioRepository.findByActivoById(datos.idUsuario());

        if(!usuarioActivo){
            throw new ValidationException("No se puede crear un topico con usuarios inactivos");
        }

    }




}
