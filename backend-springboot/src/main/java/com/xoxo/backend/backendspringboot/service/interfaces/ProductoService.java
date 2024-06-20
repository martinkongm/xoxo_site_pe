package com.xoxo.backend.backendspringboot.service.interfaces;

import java.util.List;

import com.xoxo.backend.backendspringboot.persistence.entity.Coleccion;
import com.xoxo.backend.backendspringboot.presentation.dto.producto.ProductoCreateDto;
import com.xoxo.backend.backendspringboot.presentation.dto.producto.ProductoUpdateDto;
import com.xoxo.backend.backendspringboot.persistence.entity.Producto;

public interface ProductoService {
    List<Producto> listAll();
    Producto findById(Long id);
    Producto save(ProductoCreateDto productoCreateDto);
    Producto update(ProductoUpdateDto productoUpdateDto);
    void delete(Producto producto);
    boolean existsById(Long id);
    List<Producto> findProductosByColeccion(Coleccion coleccion);
    List<Producto> buscarProductos(String nombre);
}
