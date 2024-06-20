package com.xoxo.backend.backendspringboot.service.interfaces;

import com.xoxo.backend.backendspringboot.persistence.entity.Carrito;

public interface CarritoService {
    Long save(Carrito carrito);
    Carrito add(Long idCarrito, Long idProducto, int cantidad, double totalPrecio);
}
