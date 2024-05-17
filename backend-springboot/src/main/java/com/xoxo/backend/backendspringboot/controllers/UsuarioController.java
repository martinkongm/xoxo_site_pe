package com.xoxo.backend.backendspringboot.controllers;

import com.xoxo.backend.backendspringboot.models.dto.ClienteDto;
import com.xoxo.backend.backendspringboot.models.dto.UsuarioDto;
import com.xoxo.backend.backendspringboot.models.entities.Cliente;
import com.xoxo.backend.backendspringboot.models.entities.Usuario;
import com.xoxo.backend.backendspringboot.models.payload.MensajeResponse;
import com.xoxo.backend.backendspringboot.services.IClienteService;
import com.xoxo.backend.backendspringboot.services.IUsuarioService;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UsuarioController {

    private IUsuarioService usuarioService;

    public UsuarioController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/usuarios")
    public ResponseEntity<?> showAll() {
        List<Usuario> getList = usuarioService.listAll();
        if (getList == null) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("No existen registros.")
                    .object(null)
                    .build(), HttpStatus.OK);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Listando registros.")
                .object(getList)
                .build(), HttpStatus.OK);
    }

    // Los recursos que se crean siempre son de tipo POST
    @PostMapping("/usuario")
    public ResponseEntity<?> create(@RequestBody UsuarioDto usuarioDto) {
        Usuario usuarioSave;
        try {
            usuarioSave = usuarioService.save(usuarioDto);
            usuarioDto = UsuarioDto.builder()
                    .idUsuario(usuarioSave.getIdUsuario())
                    .nombre(usuarioSave.getNombre())
                    .apellido(usuarioSave.getApellido())
                    .correo(usuarioSave.getCorreo())
                    .fechaRegistro(usuarioSave.getFechaRegistro())
                    .build();

            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado correctamente")
                    .object(usuarioDto)
                    .build(), HttpStatus.CREATED);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(e.getMessage())
                    .object(null)
                    .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity<?> update(@RequestBody UsuarioDto usuarioDto, @PathVariable Integer id) {
        Usuario usuarioUpdate = null;
        try {
            if (usuarioService.existsById(id)) {
                usuarioDto.setIdUsuario(id);
                usuarioUpdate = usuarioService.save(usuarioDto);
                usuarioDto = UsuarioDto.builder()
                        .idUsuario(usuarioUpdate.getIdUsuario())
                        .nombre(usuarioUpdate.getNombre())
                        .apellido(usuarioUpdate.getApellido())
                        .correo(usuarioUpdate.getCorreo())
                        .fechaRegistro(usuarioUpdate.getFechaRegistro())
                        .build();
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("Modificado correctamente")
                        .object(usuarioDto)
                        .build(), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("El registro que intenta actualizar no se encuentra en la base de datos.")
                        .object(null)
                        .build(), HttpStatus.NOT_FOUND);
            }

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
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            Usuario usuarioDelete = usuarioService.findById(id);
            usuarioService.delete(usuarioDelete);
            return new ResponseEntity<>(usuarioDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(e.getMessage())
                    .object(null)
                    .build(), HttpStatus.METHOD_NOT_ALLOWED); // Es un error de la BD.
        }
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id) {
        Usuario usuario = usuarioService.findById(id);
        if (usuario == null) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("El registro que intentas buscar no existe.")
                    .object(null)
                    .build(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Consulta exitosa.")
                .object(UsuarioDto.builder()
                        .idUsuario(usuario.getIdUsuario())
                        .nombre(usuario.getNombre())
                        .apellido(usuario.getApellido())
                        .correo(usuario.getCorreo())
                        .fechaRegistro(usuario.getFechaRegistro())
                        .build())
                .build(), HttpStatus.OK);
    }
}
