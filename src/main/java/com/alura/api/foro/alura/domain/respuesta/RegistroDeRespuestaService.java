package com.alura.api.foro.alura.domain.respuesta;


import com.alura.api.foro.alura.domain.curso.CursoRepository;
import com.alura.api.foro.alura.domain.respuesta.validaciones.ValidadorDeConsultas;
import com.alura.api.foro.alura.domain.status.StatusTopico;
import com.alura.api.foro.alura.domain.topico.DatosRegistroTopico;
import com.alura.api.foro.alura.domain.topico.Topico;
import com.alura.api.foro.alura.domain.topico.TopicoRepository;
import com.alura.api.foro.alura.domain.usuario.UsuarioRepository;
import com.alura.api.foro.alura.infra.errores.ValidacionDeIntegridad;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegistroDeRespuestaService {

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    List<ValidadorDeConsultas> validadores;

    public DatosDetalleRespuesta registrar(DatosRegistroRespuesta datos){

        if(!topicoRepository.findById(datos.idTopico()).isPresent()){
            throw new ValidacionDeIntegridad("este id para el topico no fue encontrado");
        }
        if(datos.idUsuario() != null && !respuestaRepository.existsById(datos.idUsuario())){
            throw new ValidacionDeIntegridad("este id de usuario no fue encontrado");
        }

        validadores.forEach(v -> v.validar(datos));

        var topico = topicoRepository.findById(datos.idTopico()).get();
        var usuario = usuarioRepository.findById(datos.idUsuario()).get();

        var respuesta = new Respuesta(datos.mensaje(), topico, usuario, datos.solucion(), datos.fecha());

        Topico topico1 = respuesta.getTopico();

        topico1.setStatus(StatusTopico.SOLUCIONADO);
        topicoRepository.save(topico1);
        respuestaRepository.save(respuesta);

        return new DatosDetalleRespuesta(respuesta);
    }

}
