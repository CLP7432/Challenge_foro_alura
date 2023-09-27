package com.alura.api.foro.alura.domain.topico;



import com.alura.api.foro.alura.domain.curso.Curso;
import com.alura.api.foro.alura.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record DatosRegistroTopico(
        Long id,
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        Long idUsuario,
        @NotNull
        Long idCurso,
        @NotNull
        LocalDateTime fecha) {


}
