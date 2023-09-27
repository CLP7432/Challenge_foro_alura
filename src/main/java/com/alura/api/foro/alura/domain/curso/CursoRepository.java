package com.alura.api.foro.alura.domain.curso;

import io.micrometer.observation.ObservationFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    Page<Curso> findByActivoTrue(Pageable paginacion);

    @Query("""
    select c.activo
    from Curso c
    where c.id=:idCurso
""")
    boolean findByActivoById(Long idCurso);
}
