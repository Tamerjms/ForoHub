package com.aluracursos.ForoHub.domain.topicos;

import java.time.LocalDateTime;

public record DatosRegistroTopicos(String idUsuario,
                                   String mensaje,
                                   String nombreCurso,
                                   String titulo) {
}
