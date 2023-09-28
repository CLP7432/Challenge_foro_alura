package com.alura.api.foro.alura.controller;



import com.alura.api.foro.alura.domain.curso.*;
import com.alura.api.foro.alura.infra.errores.ValidacionDeIntegridad;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaCurso> registrarCurso(@RequestBody @Valid DatosRegistroCurso datosRegistroCurso,
                                         UriComponentsBuilder uriComponentsBuilder){
      Curso curso = cursoRepository.save(new Curso(datosRegistroCurso));
      DatosRespuestaCurso datosRespuestaCurso = new DatosRespuestaCurso(
              curso.getId(), curso.getNombre(), curso.getCategoria());
        URI url = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
      return ResponseEntity.created(url).body(datosRespuestaCurso);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoCursos>> listadoCursos(@PageableDefault(size = 2) Pageable paginacion){
        return ResponseEntity.ok(cursoRepository.findByActivoTrue(paginacion).map(DatosListadoCursos::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaCurso> retornaDatosCurso(@PathVariable Long id){
        Curso curso = cursoRepository.getReferenceById(id);
        if(!cursoRepository.existsById(id)){
            throw new ValidacionDeIntegridad("Este id del curso no existe");
        }
        var datosCurso = new DatosRespuestaCurso(
                curso.getId(), curso.getNombre(), curso.getCategoria());
        return ResponseEntity.ok(datosCurso);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarCurso(@RequestBody @Valid DatosActualizarCurso datosActualizarCurso){
        Curso curso = cursoRepository.getReferenceById(datosActualizarCurso.id());
        if(!cursoRepository.existsById(datosActualizarCurso.id())){
            throw new ValidacionDeIntegridad("Este id del curso no existe");
        }
        curso.actualizarDatos(datosActualizarCurso);
        return ResponseEntity.ok(new DatosRespuestaCurso(
                curso.getId(), curso.getNombre(), curso.getCategoria()
        ));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarCurso(@PathVariable Long id){
        Curso curso = cursoRepository.getReferenceById(id);
        if(!cursoRepository.existsById(id)){
            throw new ValidacionDeIntegridad("Este id del curso no existe");
        }
        curso.desactivarCurso();
        return  ResponseEntity.noContent().build();
    }
}
