package com.alura.api.foro.alura.controller;

import com.alura.api.foro.alura.domain.respuesta.*;
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
@RequestMapping("/respuesta")
public class RespuestaController {

    @Autowired
    private RegistroDeRespuestaService service;

    @Autowired
    private RespuestaRepository respuestaRepository;


    @PostMapping
    @Transactional
    public ResponseEntity registrarRespuesta(@RequestBody @Valid DatosRegistroRespuesta datos,
                                             UriComponentsBuilder uriComponentsBuilder){
        var response = service.registrar(datos);
        System.out.println(datos);

        URI url = uriComponentsBuilder.path("/respuesta/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(url).body(response);
    }


    @GetMapping
    public ResponseEntity<Page<DatosDetalleRespuesta>> listarRespuestas(
            @PageableDefault(size= 5) Pageable paginacion){

        return ResponseEntity.ok(
                respuestaRepository.findByActivoTrue(paginacion).map(DatosDetalleRespuesta::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleRespuesta> retornoDatosRespuesta(@PathVariable Long id){
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        if(!respuestaRepository.existsById(id)){
            throw new ValidacionDeIntegridad("Este id de respuesta no existe");
        }
        var datosRespuesta = new DatosDetalleRespuesta(
                respuesta.getId(), respuesta.getMensaje(),
                respuesta.getTopico().getId(), respuesta.getAutor().getId(),respuesta.getSolucion(),
                respuesta.getFecha_creacion());
        return ResponseEntity.ok(datosRespuesta);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarRespuestas(@PathVariable Long id,
            @RequestBody @Valid DatosActualizarRespuesta datosActualizarRespuesta){
        Respuesta respuesta = respuestaRepository.getReferenceById(datosActualizarRespuesta.id());
        respuesta.actualizarDatos(datosActualizarRespuesta);
        if(!respuestaRepository.existsById(id)){
            throw new ValidacionDeIntegridad("Este id de respuesta no existe");
        }
        return ResponseEntity.ok(new DatosDetalleRespuesta(
                respuesta.getId(), respuesta.getMensaje(),
                respuesta.getTopico().getId(), respuesta.getAutor().getId(),respuesta.getSolucion(),
                respuesta.getFecha_creacion()));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarRespuesta(@PathVariable Long id){
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        if(!respuestaRepository.existsById(id)){
            throw new ValidacionDeIntegridad("Este id de respuesta no existe");
        }
        respuesta.desactivarRespuesta();
        return ResponseEntity.noContent().build();
    }
}
