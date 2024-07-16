package com.aluracursos.ForoHub.domain.topicos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class Topicos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="id_usuario")
    private String idUsuario;

    private String mensaje;

    private String nombreCurso;

    private String titulo;

    private LocalDateTime fecha;
    //private Boolean activo;

    public Topicos(DatosRegistroTopicos datosRegistroTopicos) {
        this.idUsuario = datosRegistroTopicos.idUsuario();
        this.mensaje = datosRegistroTopicos.mensaje();
        this.titulo = datosRegistroTopicos.titulo();
        this.nombreCurso = datosRegistroTopicos.nombreCurso();
        this.fecha = LocalDateTime.now();
    }

    public void actualizarDatos(DatosActualizarTopicos datosActualizarTopicos) {
        if(datosActualizarTopicos.idUsuario() != null){
            this.idUsuario = datosActualizarTopicos.idUsuario();
        }
        if(datosActualizarTopicos.mensaje() != null){
            this.mensaje = datosActualizarTopicos.mensaje();
        }
        if(datosActualizarTopicos.titulo() != null){
            this.titulo = datosActualizarTopicos.titulo();
        }
        if(datosActualizarTopicos.nombreCurso() != null){
            this.nombreCurso = datosActualizarTopicos.nombreCurso();
        }
    }

//    public void desactivarTopico() {
//        this.activo = false;
//    }
}
