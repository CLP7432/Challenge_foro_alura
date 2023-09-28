package com.alura.api.foro.alura.domain.topico;

import com.alura.api.foro.alura.domain.curso.Curso;
import com.alura.api.foro.alura.domain.usuario.Usuario;

public record DatosRespuestaTopico(Long id, String titulo, String mensaje, Usuario usuario, Curso curso) {
}
