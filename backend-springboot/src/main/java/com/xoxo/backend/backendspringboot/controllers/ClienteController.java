package com.xoxo.backend.backendspringboot.controllers;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xoxo.backend.backendspringboot.models.dto.ClienteDto;
import com.xoxo.backend.backendspringboot.models.entities.Cliente;
import com.xoxo.backend.backendspringboot.models.payload.MensajeResponse;
import com.xoxo.backend.backendspringboot.services.IClienteService;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {

    private IClienteService clienteService;

    public ClienteController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/clientes")
    public ResponseEntity<?> showAll() {
        List<Cliente> getList = clienteService.listAll();
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
    @PostMapping("/cliente")
    public ResponseEntity<?> create(@RequestBody ClienteDto clienteDto) {
        Cliente clienteSave = null;
        try {
            clienteSave = clienteService.save(clienteDto);
            clienteDto = ClienteDto.builder()
                    .idCliente(clienteSave.getIdCliente())
                    .nombre(clienteSave.getNombre())
                    .apellido(clienteSave.getApellido())
                    .correo(clienteSave.getCorreo())
                    .fechaRegistro(clienteSave.getFechaRegistro())
                    .build();

            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado correctamente")
                    .object(clienteDto)
                    .build(), HttpStatus.CREATED);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(e.getMessage())
                    .object(null)
                    .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("/cliente/{id}")
    public ResponseEntity<?> update(@RequestBody ClienteDto clienteDto, @PathVariable Integer id) {
        Cliente clienteUpdate = null;
        try {
            if (clienteService.existsById(id)) {
                clienteDto.setIdCliente(id);
                clienteUpdate = clienteService.save(clienteDto);
                clienteDto = ClienteDto.builder()
                        .idCliente(clienteUpdate.getIdCliente())
                        .nombre(clienteUpdate.getNombre())
                        .apellido(clienteUpdate.getApellido())
                        .correo(clienteUpdate.getCorreo())
                        .fechaRegistro(clienteUpdate.getFechaRegistro())
                        .build();
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("Modificado correctamente")
                        .object(clienteDto)
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
    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            Cliente clienteDelete = clienteService.findById(id);
            clienteService.delete(clienteDelete);
            return new ResponseEntity<>(clienteDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(e.getMessage())
                    .object(null)
                    .build(), HttpStatus.METHOD_NOT_ALLOWED); // Es un error de la BD.
        }
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id) {
        Cliente cliente = clienteService.findById(id);
        if (cliente == null) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("El registro que intentas buscar no existe.")
                    .object(null)
                    .build(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Consulta exitosa.")
                .object(ClienteDto.builder()
                        .idCliente(cliente.getIdCliente())
                        .nombre(cliente.getNombre())
                        .apellido(cliente.getApellido())
                        .correo(cliente.getCorreo())
                        .fechaRegistro(cliente.getFechaRegistro())
                        .build())
                .build(), HttpStatus.OK);

    }

}
