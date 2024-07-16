package com.aluracursos.ForoHub.domain.topicos;

public record DatosListadoTopicos(Long id,
                                  String mensaje,
                                  String nombreCurso,
                                  String titulo) {
    public DatosListadoTopicos (Topicos topicos){
        this(topicos.getId(), topicos.getMensaje(), topicos.getNombreCurso(), topicos.getTitulo());
    }
}
