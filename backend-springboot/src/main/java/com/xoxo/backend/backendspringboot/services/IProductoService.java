package com.xoxo.backend.backendspringboot.services;

import java.util.List;

import com.xoxo.backend.backendspringboot.models.dto.producto.ProductoCreateDto;
import com.xoxo.backend.backendspringboot.models.dto.producto.ProductoUpdateDto;
import com.xoxo.backend.backendspringboot.models.entities.Producto;

public interface IProductoService {

    List<Producto> listAll();

    Producto findById(Long id);

    Producto save(ProductoCreateDto productoCreateDto);

    Producto update(ProductoUpdateDto productoUpdateDto);

    void delete(Producto producto);

    boolean existsById(Long id);

}
