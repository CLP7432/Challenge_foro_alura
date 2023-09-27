package com.alura.api.foro.alura.domain.curso;

public record DatosListadoCursos(Long id, String nombre, String categoria) {

    public DatosListadoCursos(Curso curso){
        this(curso.getId(),curso.getNombre(), curso.getCategoria());
    }
}
