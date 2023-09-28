package com.alura.api.foro.alura.domain.usuario;

import jakarta.validation.constraints.NotNull;

public record DatosListadoUsuario(Long id, String nombre, String email) {

    public DatosListadoUsuario(Usuario usuario){
        this(usuario.getId(),usuario.getNombre(), usuario.getEmail());

    }
}
