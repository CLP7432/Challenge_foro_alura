package com.alura.api.foro.alura.domain.respuesta;



import com.alura.api.foro.alura.domain.topico.Topico;
import com.alura.api.foro.alura.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRegistroRespuesta(
        Long id,
        @NotBlank
        String mensaje,
        @NotNull
        Long idTopico,
        @NotNull
        Long idUsuario,
        @NotNull
        Boolean solucion,
        @NotNull
        LocalDateTime fecha) {

}
