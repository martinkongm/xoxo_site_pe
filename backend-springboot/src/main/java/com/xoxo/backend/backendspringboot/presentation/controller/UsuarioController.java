package com.xoxo.backend.backendspringboot.presentation.controller;

import com.xoxo.backend.backendspringboot.presentation.dto.usuario.UsuarioCreateDto;
import com.xoxo.backend.backendspringboot.presentation.dto.usuario.UsuarioResponseDto;
import com.xoxo.backend.backendspringboot.presentation.dto.usuario.UsuarioUpdateDto;
import com.xoxo.backend.backendspringboot.persistence.entity.Usuario;
import com.xoxo.backend.backendspringboot.presentation.payload.MensajeResponse;
import com.xoxo.backend.backendspringboot.service.interfaces.UsuarioService;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/usuarios")
    public ResponseEntity<?> showAll() {
        List<Usuario> usuarios = usuarioService.listAll();
        if (usuarios.isEmpty()) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("No existen registros.")
                    .object(null)
                    .build(), HttpStatus.OK);
        }
        List<UsuarioResponseDto> response = usuarios.stream()
                .map(usuario -> UsuarioResponseDto.builder()
                        .idUsuario(usuario.getIdUsuario())
                        .nombreUsuario(usuario.getNombre())
                        .correoUsuario(usuario.getCorreo())
                        .fechaRegistro(usuario.getFechaRegistro())
                        .reviewsUsuario(usuario.getReviewsUsuario())
                        .pedidosUsuario(usuario.getPedidosUsuario())
                        .build())
                .toList();
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Listando registros.")
                .object(usuarios)
                .build(), HttpStatus.OK);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> showById(@PathVariable Long id) {
        Usuario usuario = usuarioService.findById(id);
        if (usuario == null) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("El registro que intentas buscar no existe.")
                    .object(null)
                    .build(), HttpStatus.NOT_FOUND);
        }
        UsuarioResponseDto response = UsuarioResponseDto.builder()
                .idUsuario(usuario.getIdUsuario())
                .nombreUsuario(usuario.getNombre())
                .correoUsuario(usuario.getCorreo())
                .fechaRegistro(usuario.getFechaRegistro())
                .reviewsUsuario(usuario.getReviewsUsuario())
                .pedidosUsuario(usuario.getPedidosUsuario())
                .build();
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Consulta exitosa.")
                .object(response)
                .build(), HttpStatus.OK);
    }

    @PostMapping("/usuario")
    public ResponseEntity<?> create(@RequestBody UsuarioCreateDto usuarioCreateDto) {
        try {
            Usuario usuarioSave = usuarioService.save(usuarioCreateDto);
            UsuarioResponseDto response = UsuarioResponseDto.builder()
                    .idUsuario(usuarioSave.getIdUsuario())
                    .nombreUsuario(usuarioSave.getNombre())
                    .correoUsuario(usuarioSave.getCorreo())
                    .fechaRegistro(usuarioSave.getFechaRegistro())
                    .build();
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado correctamente")
                    .object(response)
                    .build(), HttpStatus.CREATED);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(e.getMessage())
                    .object(null)
                    .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity<?> update(@RequestBody UsuarioUpdateDto usuarioUpdateDto, @PathVariable Long id) {
        try {
            if (!usuarioService.existsById(id)) {
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("El registro que intenta actualizar no se encuentra en la base de datos.")
                        .object(null)
                        .build(), HttpStatus.NOT_FOUND);
            }
            usuarioUpdateDto.setIdUsuario(id);
            Usuario usuarioUpdate = usuarioService.update(usuarioUpdateDto);
            UsuarioResponseDto response = UsuarioResponseDto.builder()
                    .idUsuario(usuarioUpdate.getIdUsuario())
                    .nombreUsuario(usuarioUpdate.getNombre())
                    .correoUsuario(usuarioUpdate.getCorreo())
                    .fechaRegistro(usuarioUpdate.getFechaRegistro())
                    .reviewsUsuario(usuarioUpdate.getReviewsUsuario())
                    .pedidosUsuario(usuarioUpdate.getPedidosUsuario())
                    .build();
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Modificado correctamente")
                    .object(response)
                    .build(), HttpStatus.OK);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(e.getMessage())
                    .object(null)
                    .build(), HttpStatus.NOT_FOUND);
        }
    }

    // ResponseEntity maneja toda la respuesta HTTP (cuerpo, cabecera, códigos de
    // estado)
    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            Usuario usuario = usuarioService.findById(id);
            if (usuario == null) {
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("El registro que intenta eliminar no se encuentra en la base de datos.")
                        .object(null)
                        .build(), HttpStatus.NOT_FOUND);
            }
            usuarioService.delete(usuario);
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Eliminado correctamente")
                    .object(null)
                    .build(), HttpStatus.NO_CONTENT);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(e.getMessage())
                    .object(null)
                    .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
}
