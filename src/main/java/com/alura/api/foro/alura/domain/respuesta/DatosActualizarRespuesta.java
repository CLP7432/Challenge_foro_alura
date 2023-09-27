package com.alura.api.foro.alura.domain.respuesta;

import com.alura.api.foro.alura.domain.status.StatusTopico;
import com.alura.api.foro.alura.domain.topico.Topico;
import com.alura.api.foro.alura.domain.usuario.Usuario;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarRespuesta(Long id, String mensaje) {
}



