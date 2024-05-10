package com.xoxo.backend.backendspringboot.services;

import java.util.List;

import com.xoxo.backend.backendspringboot.models.dto.ProductoDto;
import com.xoxo.backend.backendspringboot.models.entities.Producto;

public interface IProductoService {

    List<ProductoDto> listAll();

    Producto save(ProductoDto cliente);

    ProductoDto findById(Integer id);

    void delete(ProductoDto cliente);

    boolean existsById(Integer id);

}
