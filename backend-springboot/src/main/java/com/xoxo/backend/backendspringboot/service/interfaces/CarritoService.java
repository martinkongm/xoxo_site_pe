package com.xoxo.backend.backendspringboot.service.interfaces;

import com.xoxo.backend.backendspringboot.persistence.entity.Carrito;
import com.xoxo.backend.backendspringboot.persistence.entity.DetalleCarrito;
import com.xoxo.backend.backendspringboot.persistence.entity.Usuario;
import com.xoxo.backend.backendspringboot.presentation.dto.carrito.CarritoCreateDto;

import java.util.List;

public interface CarritoService {
    //List<Carrito> listAll();
    //Carrito findById(Long id);
    Long save(Carrito carrito);
    Carrito add(Long idCarrito, Long idProducto, int cantidad, double totalPrecio);
    //Carrito update(CarritoCreateDto carritoDto);
    //void delete(Carrito carrito);
    //boolean existsById(Long id);
}
