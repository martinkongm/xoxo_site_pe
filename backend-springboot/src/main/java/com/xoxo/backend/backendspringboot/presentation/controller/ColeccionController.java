package com.xoxo.backend.backendspringboot.presentation.controller;

import com.xoxo.backend.backendspringboot.persistence.entity.Producto;
import com.xoxo.backend.backendspringboot.presentation.dto.coleccion.ColeccionCreateDto;
import com.xoxo.backend.backendspringboot.presentation.dto.coleccion.ColeccionResponseDto;
import com.xoxo.backend.backendspringboot.persistence.entity.Coleccion;
import com.xoxo.backend.backendspringboot.presentation.dto.coleccion.ColeccionUpdateDto;
import com.xoxo.backend.backendspringboot.presentation.payload.MensajeResponse;
import com.xoxo.backend.backendspringboot.service.interfaces.ColeccionService;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class ColeccionController {

    private final ColeccionService coleccionService;

    public ColeccionController(ColeccionService coleccionService) {
        this.coleccionService = coleccionService;
    }

    @GetMapping("/colecciones")
    public ResponseEntity<?> showAll() {
        List<Coleccion> lista = coleccionService.listAll();
        if (lista == null) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("No existen registros.")
                    .object(null)
                    .build(), HttpStatus.OK);
        }
        List<ColeccionResponseDto> response = lista.stream()
                .map(c -> new ColeccionResponseDto(c.getIdColeccion(), c.getNombreColeccion(), c.getProductosColeccion().stream().map(Producto::getNombreProducto).toList())).toList();
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Listando registros.")
                .object(response)
                .build(), HttpStatus.OK);
    }

    @GetMapping("/coleccion/{id}")
    public ResponseEntity<?> showById(@PathVariable Long id) {
        Coleccion coleccion = coleccionService.findById(id);
        if (coleccion == null) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("El registro que intentas buscar no existe.")
                    .object(null)
                    .build(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("Consulta exitosa.")
                .object(ColeccionResponseDto.builder()
                        .idColeccion(coleccion.getIdColeccion())
                        .nombreColeccion(coleccion.getNombreColeccion())
                        .productosColeccion(coleccion.getProductosColeccion().stream().map(Producto::getNombreProducto).toList())
                        .build())
                .build(), HttpStatus.OK);
    }

    // Los recursos que se crean siempre son de tipo POST
    @PostMapping("/coleccion")
    public ResponseEntity<?> create(@RequestBody ColeccionCreateDto coleccionCreateDto) {
        Coleccion coleccionSave;
        try {
            coleccionSave = coleccionService.save(coleccionCreateDto);
            ColeccionResponseDto response = ColeccionResponseDto.builder()
                    .idColeccion(coleccionSave.getIdColeccion())
                    .nombreColeccion(coleccionSave.getNombreColeccion())
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

    @PutMapping("/coleccion/{id}")
    public ResponseEntity<?> update(@RequestBody ColeccionUpdateDto coleccionUpdateDto, @PathVariable Long id) {
        try {
            Coleccion coleccionUpdate = new Coleccion();
            if (coleccionService.existsById(id)) {
                coleccionUpdate.setIdColeccion(id);
                coleccionUpdate = coleccionService.update(coleccionUpdateDto);
                ColeccionResponseDto response = ColeccionResponseDto.builder()
                        .idColeccion(coleccionUpdate.getIdColeccion())
                        .nombreColeccion(coleccionUpdate.getNombreColeccion())
                        .productosColeccion(coleccionUpdate.getProductosColeccion().stream().map(Producto::getNombreProducto).toList())
                        .build();
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("Modificado correctamente")
                        .object(response)
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
    @DeleteMapping("/coleccion/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            Coleccion coleccionDelete = coleccionService.findById(id);
            ColeccionResponseDto response = ColeccionResponseDto.builder()
                    .idColeccion(coleccionDelete.getIdColeccion())
                    .nombreColeccion(coleccionDelete.getNombreColeccion())
                    .productosColeccion(coleccionDelete.getProductosColeccion().stream().map(Producto::getNombreProducto).toList())
                    .build();
            coleccionService.delete(coleccionDelete);
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(e.getMessage())
                    .object(null)
                    .build(), HttpStatus.METHOD_NOT_ALLOWED); // Es un error de la BD.
        }
    }
}
