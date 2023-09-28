package com.alura.api.foro.alura.domain.usuario;

import io.micrometer.observation.ObservationFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Page<Usuario> findByActivoTrue(Pageable paginacion);

    UserDetails findByNombre(String username);

    @Query("""
    select u.activo 
    from Usuario u 
    where u.id=:idUsuario
""")
    Boolean findByActivoById(Long idUsuario);


}
