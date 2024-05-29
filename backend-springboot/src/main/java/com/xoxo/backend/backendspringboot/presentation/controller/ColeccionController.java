package com.xoxo.backend.backendspringboot.presentation.controller;

import com.xoxo.backend.backendspringboot.presentation.dto.coleccion.ColeccionDto;
import com.xoxo.backend.backendspringboot.persistence.entity.Coleccion;
import com.xoxo.backend.backendspringboot.presentation.payload.MensajeResponse;
import com.xoxo.backend.backendspringboot.service.interfaces.ColeccionService;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ColeccionController {

    private ColeccionService coleccionService;

    public ColeccionController(ColeccionService coleccionService) {
        this.coleccionService = coleccionService;
    }

    @GetMapping("/colecciones")
    public ResponseEntity<?> showAll() {
        List<Coleccion> getList = coleccionService.listAll();
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
                .object(ColeccionDto.builder()
                        .idColeccion(coleccion.getIdColeccion())
                        .nombreColeccion(coleccion.getNombreColeccion())
                        .productosColeccion(coleccion.getProductosColeccion())
                        .build())
                .build(), HttpStatus.OK);
    }

    // Los recursos que se crean siempre son de tipo POST
    @PostMapping("/coleccion")
    public ResponseEntity<?> create(@RequestBody ColeccionDto coleccionDto) {
        Coleccion coleccionSave;
        try {
            coleccionSave = coleccionService.save(coleccionDto);
            coleccionDto = ColeccionDto.builder()
                    .idColeccion(coleccionSave.getIdColeccion())
                    .nombreColeccion(coleccionSave.getNombreColeccion())
                    .productosColeccion(coleccionSave.getProductosColeccion())
                    .build();
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado correctamente")
                    .object(coleccionDto)
                    .build(), HttpStatus.CREATED);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(e.getMessage())
                    .object(null)
                    .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("/coleccion/{id}")
    public ResponseEntity<?> update(@RequestBody ColeccionDto coleccionDto, @PathVariable Long id) {
        Coleccion coleccionUpdate;
        try {
            if (coleccionService.existsById(id)) {
                coleccionDto.setIdColeccion(id);
                coleccionUpdate = coleccionService.save(coleccionDto);
                coleccionDto = ColeccionDto.builder()
                        .idColeccion(coleccionUpdate.getIdColeccion())
                        .nombreColeccion(coleccionUpdate.getNombreColeccion())
                        .productosColeccion(coleccionUpdate.getProductosColeccion())
                        .build();
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("Modificado correctamente")
                        .object(coleccionDto)
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
            coleccionService.delete(coleccionDelete);
            return new ResponseEntity<>(coleccionDelete, HttpStatus.NO_CONTENT);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(e.getMessage())
                    .object(null)
                    .build(), HttpStatus.METHOD_NOT_ALLOWED); // Es un error de la BD.
        }
    }
}
