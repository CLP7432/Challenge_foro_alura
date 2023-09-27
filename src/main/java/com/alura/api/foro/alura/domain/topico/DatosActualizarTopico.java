package com.alura.api.foro.alura.domain.topico;

import com.alura.api.foro.alura.domain.curso.Curso;
import com.alura.api.foro.alura.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


public record DatosActualizarTopico(
        @NotNull Long id,  String titulo,  String mensaje) {
    public DatosActualizarTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje());
    }
}

