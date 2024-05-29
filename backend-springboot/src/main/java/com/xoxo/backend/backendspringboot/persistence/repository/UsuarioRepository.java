package com.xoxo.backend.backendspringboot.persistence.repository;

import com.xoxo.backend.backendspringboot.persistence.entity.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Optional<Usuario> findUsuarioByCorreo(String nombre);

}
