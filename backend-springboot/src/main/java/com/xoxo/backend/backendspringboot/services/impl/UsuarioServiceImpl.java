package com.xoxo.backend.backendspringboot.services.impl;

import com.xoxo.backend.backendspringboot.models.dao.UsuarioDao;
import com.xoxo.backend.backendspringboot.models.dto.ProductoDto;
import com.xoxo.backend.backendspringboot.models.dto.UsuarioDto;
import com.xoxo.backend.backendspringboot.models.entities.Producto;
import com.xoxo.backend.backendspringboot.models.entities.Usuario;
import com.xoxo.backend.backendspringboot.services.IUsuarioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private UsuarioDao usuarioDao;

    public UsuarioServiceImpl(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listAll() {
        return (List<Usuario>) usuarioDao.findAll();
    }

    @Override
    @Transactional
    public Usuario save(UsuarioDto usuarioDto) {
        Usuario usuario = Usuario.builder()
                .idUsuario(usuarioDto.getIdUsuario())
                .nombre(usuarioDto.getNombre())
                .apellido(usuarioDto.getApellido())
                .correo(usuarioDto.getCorreo())
                .build();
        return usuarioDao.save(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findById(Integer id) {
        return usuarioDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Usuario usuario) {
        usuarioDao.delete(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Integer id) {
        return usuarioDao.existsById(id);
    }
}
