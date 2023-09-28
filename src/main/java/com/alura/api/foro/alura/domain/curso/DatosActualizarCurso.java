package com.alura.api.foro.alura.domain.curso;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarCurso(@NotNull Long id, String nombre, String categoria) {
}
