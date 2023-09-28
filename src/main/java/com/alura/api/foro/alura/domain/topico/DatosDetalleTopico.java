package com.alura.api.foro.alura.domain.topico;

import com.alura.api.foro.alura.domain.status.StatusTopico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
        Long id,
        String titulo,
        String mensaje,
        Long idUsuario,
        Long idCurso,
        StatusTopico status,
        LocalDateTime fecha
) {


    public DatosDetalleTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getAutor().getId(), topico.getCurso().getId(), topico.getStatus(),topico.getFecha_creacion());
    }
}
