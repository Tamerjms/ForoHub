package com.aluracursos.ForoHub.domain.topicos;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopicos(@NotNull
                                     Long id,
                                     String idUsuario,
                                     String mensaje,
                                     String nombreCurso,
                                     String titulo
                                     ) {
}
