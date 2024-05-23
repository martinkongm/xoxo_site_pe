package com.xoxo.backend.backendspringboot.services;

import com.xoxo.backend.backendspringboot.models.dto.ColeccionDto;
import com.xoxo.backend.backendspringboot.models.entities.Coleccion;
import com.xoxo.backend.backendspringboot.models.entities.Producto;

import java.util.List;

public interface IColeccionService {

    List<Coleccion> listAll();

    Coleccion save(ColeccionDto coleccionDto);

    Coleccion findById(Long id);

    void delete(Coleccion coleccion);

    boolean existsById(Long id);
}
