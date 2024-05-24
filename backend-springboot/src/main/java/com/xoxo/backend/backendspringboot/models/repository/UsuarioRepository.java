package com.xoxo.backend.backendspringboot.models.repository;

import com.xoxo.backend.backendspringboot.models.entities.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
}
