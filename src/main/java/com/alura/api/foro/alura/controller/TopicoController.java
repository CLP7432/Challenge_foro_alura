package com.alura.api.foro.alura.controller;



import com.alura.api.foro.alura.domain.topico.*;
import com.alura.api.foro.alura.infra.errores.ValidacionDeIntegridad;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Controller
@ResponseBody
@RequestMapping("/topico")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private RegistroDeTopicoService service;


    @PostMapping
    @Transactional
    public ResponseEntity registrarTopico(@RequestBody @Valid DatosRegistroTopico datos,
                                          UriComponentsBuilder uriComponentsBuilder){
       var response = service.registrar(datos);
        System.out.println(datos);
        URI url = uriComponentsBuilder.path("/topico/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(url).body(response);
    }
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarTopico(@PathVariable Long id,
                                           @RequestBody @Valid DatosActualizarTopico datos){
        Topico topico = topicoRepository.getReferenceById(datos.id());
        if(!topicoRepository.existsById(id)){
            throw new ValidacionDeIntegridad("Este id del topico no existe");
        }
        topico.actualizarDatos(datos);
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<Page<DatosDetalleTopico>> listadoTopicos(
            @PageableDefault(size = 5) Pageable paginacion){

        return ResponseEntity.ok(
                topicoRepository.findByActivoTrue(paginacion).map(DatosDetalleTopico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> retornaDatosTopico(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        if(!topicoRepository.existsById(id)){
            throw new ValidacionDeIntegridad("Este id del topico no existe");
        }
        var datosTopico =  new DatosDetalleTopico(
                topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getAutor().getId(),topico.getCurso().getId(),topico.getStatus(), topico.getFecha_creacion());
        return ResponseEntity.ok(datosTopico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        if(!topicoRepository.existsById(id)){
            throw new ValidacionDeIntegridad("Este id del topico no existe");
        }
        topico.desactivarTopico();
        return ResponseEntity.noContent().build();
    }
}
