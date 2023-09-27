package com.alura.api.foro.alura.controller;


import com.alura.api.foro.alura.domain.curso.Curso;
import com.alura.api.foro.alura.domain.usuario.*;
import com.alura.api.foro.alura.infra.errores.ValidacionDeIntegridad;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaUsuario> registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario,
                                             UriComponentsBuilder uriComponentsBuilder){
        Usuario usuario = usuarioRepository.save(new Usuario(datosRegistroUsuario));
        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(
                usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getClave());

        URI url = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaUsuario);

    }
    @GetMapping
    public ResponseEntity<Page<DatosListadoUsuario>> listadoUsuarios(Pageable paginacion){
        return ResponseEntity.ok(usuarioRepository.findByActivoTrue(paginacion).map(DatosListadoUsuario::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaUsuario> retornaDatosUsuario(@PathVariable Long id){
        Usuario usuario = usuarioRepository.getReferenceById(id);
        if(!usuarioRepository.existsById(id)){
            throw new ValidacionDeIntegridad("Este id del usuario no existe");
        }
        var datosUsuario = new DatosRespuestaUsuario(
                usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getClave());
        return ResponseEntity.ok(datosUsuario);
    }


    @PutMapping
    @Transactional
    public ResponseEntity actualizarUsuario(@RequestBody @Valid DatosActualizarUsuario datosActualizarUsuario){
        Usuario usuario = usuarioRepository.getReferenceById(datosActualizarUsuario.id());
        if(!usuarioRepository.existsById(datosActualizarUsuario.id())){
            throw new ValidacionDeIntegridad("Este id del usuario no existe");
        }
        usuario.actualizarDatos(datosActualizarUsuario);
        return ResponseEntity.ok(new DatosRespuestaUsuario(
                usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getClave()
        ));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable Long id){
        Usuario usuario = usuarioRepository.getReferenceById(id);
        if(!usuarioRepository.existsById(id)){
            throw new ValidacionDeIntegridad("Este id del usuario no existe");
        }
        usuario.desactivarUsuario();
        return ResponseEntity.noContent().build();
    }
}
