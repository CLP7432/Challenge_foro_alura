package com.alura.api.foro.alura.domain.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosDetalleRespuesta(
        Long id,
        String mensaje,
        Long idTopico,
        Long idUsuario,
        Boolean solucion,
        LocalDateTime fecha
) {
    public DatosDetalleRespuesta(Respuesta respuesta) {
        this(respuesta.getId(), respuesta.getMensaje(), respuesta.getTopico().getId(), respuesta.getAutor().getId(), respuesta.getSolucion(),respuesta.getFecha_creacion());
    }
}
