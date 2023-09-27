package com.alura.api.foro.alura.domain.topico;


import com.alura.api.foro.alura.domain.curso.Curso;
import com.alura.api.foro.alura.domain.respuesta.Respuesta;
import com.alura.api.foro.alura.domain.status.StatusTopico;
import com.alura.api.foro.alura.domain.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String mensaje;
	private LocalDateTime fecha_creacion = LocalDateTime.now();
	private Boolean activo;

	@Enumerated(EnumType.STRING)
	private StatusTopico status = StatusTopico.NO_RESPONDIDO;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "autor_id")
	private Usuario autor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "curso_id")
	private Curso curso;

	@JsonIgnore
	@OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private  List<Respuesta> respuestas = new ArrayList<>();

	public Topico(Long id, String titulo, String mensaje, Usuario usuario, Curso curso, LocalDateTime fecha) {
		this.activo = true;
		this.id = id;
		this.titulo = titulo;
		this.mensaje = mensaje;
		this.autor = usuario;
		this.curso = curso;
		this.fecha_creacion = fecha;
	}
    public void actualizarDatos(DatosActualizarTopico datosActualizarTopico) {
		if(datosActualizarTopico.titulo() != null){
			this.titulo = datosActualizarTopico.titulo();
		}
		if(datosActualizarTopico.mensaje() != null){
			this.mensaje = datosActualizarTopico.mensaje();
		}
    }

	public void desactivarTopico() {
		this.activo = false;
	}

	public void setStatus(StatusTopico status){
		this.status = status;
	}

}
