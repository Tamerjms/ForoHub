package com.aluracursos.ForoHub.domain.usuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRespository extends JpaRepository<Usuario, Long> {

    UserDetails findByLogin(String username);
}
