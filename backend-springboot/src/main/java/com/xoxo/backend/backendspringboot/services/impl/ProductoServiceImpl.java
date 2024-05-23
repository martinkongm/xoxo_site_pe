package com.xoxo.backend.backendspringboot.services.impl;

import java.util.List;

import com.xoxo.backend.backendspringboot.models.dao.ColeccionDao;
import com.xoxo.backend.backendspringboot.models.dto.ProductoCreateDto;
import com.xoxo.backend.backendspringboot.models.dto.ProductoUpdateDto;
import com.xoxo.backend.backendspringboot.models.entities.Coleccion;
import org.springframework.stereotype.Service;

import com.xoxo.backend.backendspringboot.models.dao.ProductoDao;
import com.xoxo.backend.backendspringboot.models.entities.Producto;
import com.xoxo.backend.backendspringboot.services.IProductoService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServiceImpl implements IProductoService{

    private ProductoDao productoDao;
    private ColeccionDao coleccionDao;

    public ProductoServiceImpl(ProductoDao productoDao, ColeccionDao coleccionDao) {
        this.productoDao = productoDao;
        this.coleccionDao = coleccionDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> listAll() {
        return (List<Producto>) productoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Producto findById(Long id) {
        return productoDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Producto save(ProductoCreateDto productoCreateDto) {
        Coleccion coleccion = coleccionDao.findById(productoCreateDto.getIdColeccion()).orElseThrow();
        Producto producto = Producto.builder()
                .nombreProducto(productoCreateDto.getNombreProducto())
                .precioProducto(productoCreateDto.getPrecioProducto())
                .tamanoProducto(productoCreateDto.getTamanoProducto())
                .beneficiosProducto(productoCreateDto.getBeneficiosProducto())
                .imagenProducto(productoCreateDto.getImagenProducto())
                .coleccion(coleccion)
                .build();
        return productoDao.save(producto);
    }

    @Override
    @Transactional
    public Producto update(ProductoUpdateDto productoUpdateDto) {
        Producto producto = productoDao.findById(productoUpdateDto.getIdProducto()).orElseThrow();

        Coleccion coleccion = coleccionDao.findById(productoUpdateDto.getIdColeccion()).orElseThrow();

        producto.setNombreProducto(productoUpdateDto.getNombreProducto());
        producto.setPrecioProducto(productoUpdateDto.getPrecioProducto());
        producto.setTamanoProducto(productoUpdateDto.getTamanoProducto());
        producto.setBeneficiosProducto(productoUpdateDto.getBeneficiosProducto());
        producto.setImagenProducto(productoUpdateDto.getImagenProducto());
        producto.setColeccion(coleccion);

        return productoDao.save(producto);
    }


    @Override
    @Transactional
    public void delete(Producto producto) {
        productoDao.delete(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return productoDao.existsById(id);
    }
}
