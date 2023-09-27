package com.alura.api.foro.alura.domain.respuesta;

import com.alura.api.foro.alura.domain.topico.Topico;
import com.alura.api.foro.alura.domain.usuario.Usuario;

public record DatosRespuestaRespuestas(Long id, String mensaje, Topico topico, Usuario usuario, Boolean solucion) {
}
