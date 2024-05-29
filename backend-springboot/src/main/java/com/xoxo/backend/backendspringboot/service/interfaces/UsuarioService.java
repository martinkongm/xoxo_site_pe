package com.xoxo.backend.backendspringboot.service.interfaces;

import com.xoxo.backend.backendspringboot.presentation.dto.usuario.UsuarioCreateDto;
import com.xoxo.backend.backendspringboot.presentation.dto.usuario.UsuarioUpdateDto;
import com.xoxo.backend.backendspringboot.persistence.entity.Usuario;

import java.util.List;

public interface UsuarioService {

    List<Usuario> listAll();
    Usuario findById(Long id);
    Usuario save(UsuarioCreateDto usuarioCreateDto);
    Usuario update(UsuarioUpdateDto usuarioUpdateDto);
    void delete(Usuario usuario);
    boolean existsById(Long id);

}
