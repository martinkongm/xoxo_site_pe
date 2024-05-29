package com.xoxo.backend.backendspringboot.service.interfaces;

import com.xoxo.backend.backendspringboot.presentation.dto.coleccion.ColeccionDto;
import com.xoxo.backend.backendspringboot.persistence.entity.Coleccion;

import java.util.List;

public interface ColeccionService {

    List<Coleccion> listAll();

    Coleccion save(ColeccionDto coleccionDto);

    Coleccion findById(Long id);

    void delete(Coleccion coleccion);

    boolean existsById(Long id);
}
