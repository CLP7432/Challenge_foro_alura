package com.alura.api.foro.alura.domain.respuesta;


import com.alura.api.foro.alura.domain.topico.Topico;
import com.alura.api.foro.alura.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String mensaje;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "topico_id")
	private Topico topico;

	private LocalDateTime fecha_creacion = LocalDateTime.now();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "autor_id")
	private Usuario autor;
	private Boolean solucion = false;
	private Boolean activo;

	public Respuesta(String mensaje, Topico topico, Usuario usuario, Boolean solucion, LocalDateTime fecha) {
		this.activo = true;
		this.mensaje = mensaje;
		this.topico = topico;
		this.autor = usuario;
		this.solucion = solucion;
		this.fecha_creacion = fecha;
	}

    public void actualizarDatos(DatosActualizarRespuesta datosActualizarRespuesta) {
		if(datosActualizarRespuesta.mensaje() != null){
			this.mensaje = datosActualizarRespuesta.mensaje();
		}
    }

	public void desactivarRespuesta() {
		this.activo = false;
	}
}
