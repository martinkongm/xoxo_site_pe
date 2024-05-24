package com.xoxo.backend.backendspringboot.services.impl;

import com.xoxo.backend.backendspringboot.models.repository.UsuarioRepository;
import com.xoxo.backend.backendspringboot.models.dto.usuario.UsuarioCreateDto;
import com.xoxo.backend.backendspringboot.models.dto.usuario.UsuarioUpdateDto;
import com.xoxo.backend.backendspringboot.models.entities.Usuario;
import com.xoxo.backend.backendspringboot.services.IUsuarioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listAll() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
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
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public Usuario update(UsuarioUpdateDto usuarioUpdateDto) {
        Usuario usuario = usuarioRepository.findById(usuarioUpdateDto.getIdUsuario()).orElseThrow();

        usuario.setNombreUsuario(usuarioUpdateDto.getNombreUsuario());
        usuario.setApellidoUsuario(usuarioUpdateDto.getApellidoUsuario());
        usuario.setCorreoUsuario(usuarioUpdateDto.getCorreoUsuario());
        usuario.setFechaRegistro(usuarioUpdateDto.getFechaRegistro());

        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public void delete(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return usuarioRepository.existsById(id);
    }
}
