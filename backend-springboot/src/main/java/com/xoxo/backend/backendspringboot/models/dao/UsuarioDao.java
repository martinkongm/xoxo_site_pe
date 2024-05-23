package com.xoxo.backend.backendspringboot.models.dao;

import com.xoxo.backend.backendspringboot.models.entities.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioDao extends CrudRepository<Usuario, Long> {
}
