package com.xoxo.backend.backendspringboot.services;

import com.xoxo.backend.backendspringboot.models.dto.UsuarioCreateDto;
import com.xoxo.backend.backendspringboot.models.dto.UsuarioResponseDto;
import com.xoxo.backend.backendspringboot.models.dto.UsuarioUpdateDto;
import com.xoxo.backend.backendspringboot.models.entities.Usuario;

import java.util.List;

public interface IUsuarioService {

    List<Usuario> listAll();
    Usuario findById(Long id);
    Usuario save(UsuarioCreateDto usuarioCreateDto);
    Usuario update(UsuarioUpdateDto usuarioUpdateDto);
    void delete(Usuario usuario);
    boolean existsById(Long id);

}
