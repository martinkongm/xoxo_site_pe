package com.xoxo.backend.backendspringboot.services.impl;

import com.xoxo.backend.backendspringboot.models.dao.UsuarioDao;
import com.xoxo.backend.backendspringboot.models.dto.UsuarioCreateDto;
import com.xoxo.backend.backendspringboot.models.dto.UsuarioResponseDto;
import com.xoxo.backend.backendspringboot.models.dto.UsuarioUpdateDto;
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
    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
        return usuarioDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Usuario save(UsuarioCreateDto usuarioCreateDto) {
        Usuario usuario = Usuario.builder()
                .nombreUsuario(usuarioCreateDto.getNombreUsuario())
                .apellidoUsuario(usuarioCreateDto.getApellidoUsuario())
                .correoUsuario(usuarioCreateDto.getCorreoUsuario())
                .fechaRegistro(usuarioCreateDto.getFechaRegistro())
                .contrasenaUsuario(usuarioCreateDto.getContrasenaUsuario())
                .build();
        return usuarioDao.save(usuario);
    }

    @Override
    @Transactional
    public Usuario update(UsuarioUpdateDto usuarioUpdateDto) {
        Usuario usuario = usuarioDao.findById(usuarioUpdateDto.getIdUsuario()).orElseThrow();

        usuario.setNombreUsuario(usuarioUpdateDto.getNombreUsuario());
        usuario.setApellidoUsuario(usuarioUpdateDto.getApellidoUsuario());
        usuario.setCorreoUsuario(usuarioUpdateDto.getCorreoUsuario());
        usuario.setFechaRegistro(usuarioUpdateDto.getFechaRegistro());

        return usuarioDao.save(usuario);
    }

    @Override
    @Transactional
    public void delete(Usuario usuario) {
        usuarioDao.delete(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return usuarioDao.existsById(id);
    }
}
