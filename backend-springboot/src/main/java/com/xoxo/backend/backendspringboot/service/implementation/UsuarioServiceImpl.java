package com.xoxo.backend.backendspringboot.service.implementation;

import com.xoxo.backend.backendspringboot.persistence.repository.UsuarioRepository;
import com.xoxo.backend.backendspringboot.presentation.dto.usuario.UsuarioCreateDto;
import com.xoxo.backend.backendspringboot.presentation.dto.usuario.UsuarioUpdateDto;
import com.xoxo.backend.backendspringboot.persistence.entity.Usuario;
import com.xoxo.backend.backendspringboot.service.interfaces.UsuarioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

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
                .nombre(usuarioCreateDto.getNombreUsuario())
                .correo(usuarioCreateDto.getCorreoUsuario())
                .fechaRegistro(usuarioCreateDto.getFechaRegistro())
                .contrasenaUsuario(usuarioCreateDto.getContrasenaUsuario())
                .build();
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public Usuario update(UsuarioUpdateDto usuarioUpdateDto) {
        Usuario usuario = usuarioRepository.findById(usuarioUpdateDto.getIdUsuario()).orElseThrow();

        usuario.setNombre(usuarioUpdateDto.getNombreUsuario());
        usuario.setApellido(usuarioUpdateDto.getApellidoUsuario());
        usuario.setCorreo(usuarioUpdateDto.getCorreoUsuario());
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
