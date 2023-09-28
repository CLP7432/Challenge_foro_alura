package com.alura.api.foro.alura.domain.topico.validaciones;

import com.alura.api.foro.alura.domain.curso.Curso;
import com.alura.api.foro.alura.domain.curso.CursoRepository;
import com.alura.api.foro.alura.domain.topico.DatosActualizarTopico;
import com.alura.api.foro.alura.domain.topico.DatosRegistroTopico;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CursoActivo  implements ValidadorDeConsultas{

    @Autowired
    private CursoRepository cursoRepository;

    public void validar(DatosRegistroTopico datos) {
        if (datos.idCurso() == null) {
            return;
        }
        var cursoActivo = cursoRepository.findByActivoById(datos.idCurso());

        if (!cursoActivo) {
            throw new ValidationException("No se puede crear un topico con cursos inactivos");
        }
    }



}
