package com.xoxo.backend.backendspringboot.service.interfaces;

import com.xoxo.backend.backendspringboot.presentation.dto.coleccion.ColeccionCreateDto;
import com.xoxo.backend.backendspringboot.presentation.dto.coleccion.ColeccionResponseDto;
import com.xoxo.backend.backendspringboot.persistence.entity.Coleccion;
import com.xoxo.backend.backendspringboot.presentation.dto.coleccion.ColeccionUpdateDto;

import java.util.List;

public interface ColeccionService {
    List<Coleccion> listAll();
    Coleccion save(ColeccionCreateDto coleccionCreateDto);
    Coleccion update(ColeccionUpdateDto coleccionUpdateDto);
    Coleccion findById(Long id);
    void delete(Coleccion coleccion);
    boolean existsById(Long id);
    Coleccion getColeccionByName(String nombre);
}
