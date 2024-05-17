package com.xoxo.backend.backendspringboot.services;

import com.xoxo.backend.backendspringboot.models.dto.ProductoDto;
import com.xoxo.backend.backendspringboot.models.dto.UsuarioDto;
import com.xoxo.backend.backendspringboot.models.entities.Producto;
import com.xoxo.backend.backendspringboot.models.entities.Usuario;

import java.util.List;

public interface IUsuarioService {

    List<Usuario> listAll();

    Usuario save(UsuarioDto usuarioDto);

    Usuario findById(Integer id);

    void delete(Usuario usuario);

    boolean existsById(Integer id);

}
