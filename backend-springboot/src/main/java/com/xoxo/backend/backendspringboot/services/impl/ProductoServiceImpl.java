package com.xoxo.backend.backendspringboot.services.impl;

import java.util.List;

import com.xoxo.backend.backendspringboot.models.entities.Cliente;
import org.springframework.stereotype.Service;

import com.xoxo.backend.backendspringboot.models.dao.ProductoDao;
import com.xoxo.backend.backendspringboot.models.dto.ProductoDto;
import com.xoxo.backend.backendspringboot.models.entities.Producto;
import com.xoxo.backend.backendspringboot.services.IProductoService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServiceImpl implements IProductoService{

    private ProductoDao productoDao;

    public ProductoServiceImpl(ProductoDao productoDao) {
        this.productoDao = productoDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> listAll() {
        return (List<Producto>) productoDao.findAll();
    }

    @Override
    @Transactional
    public Producto save(ProductoDto productoDto) {
        Producto producto = Producto.builder()
                .idProducto(productoDto.getIdProducto())
                .nombreProducto(productoDto.getNombreProducto())
                .precioProducto(productoDto.getPrecioProducto())
                .coleccion(productoDto.getColeccion())
                .build();
        return productoDao.save(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public Producto findById(Integer id) {
        return productoDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Producto producto) {
        productoDao.delete(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Integer id) {
        return productoDao.existsById(id);
    }

}
