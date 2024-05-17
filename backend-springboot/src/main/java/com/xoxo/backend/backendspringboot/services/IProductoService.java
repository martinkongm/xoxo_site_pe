package com.xoxo.backend.backendspringboot.services;

import java.util.List;

import com.xoxo.backend.backendspringboot.models.dto.ProductoDto;
import com.xoxo.backend.backendspringboot.models.entities.Producto;

public interface IProductoService {

    List<Producto> listAll();

    Producto save(ProductoDto productoDto);

    Producto findById(Integer id);

    void delete(Producto producto);

    boolean existsById(Integer id);

}
