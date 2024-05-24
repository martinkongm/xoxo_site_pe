package com.xoxo.backend.backendspringboot.services.impl;

import java.util.List;

import com.xoxo.backend.backendspringboot.models.repository.ColeccionRepository;
import com.xoxo.backend.backendspringboot.models.dto.producto.ProductoCreateDto;
import com.xoxo.backend.backendspringboot.models.dto.producto.ProductoUpdateDto;
import com.xoxo.backend.backendspringboot.models.entities.Coleccion;
import org.springframework.stereotype.Service;

import com.xoxo.backend.backendspringboot.models.repository.ProductoRepository;
import com.xoxo.backend.backendspringboot.models.entities.Producto;
import com.xoxo.backend.backendspringboot.services.IProductoService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServiceImpl implements IProductoService{

    private ProductoRepository productoRepository;
    private ColeccionRepository coleccionRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository, ColeccionRepository coleccionRepository) {
        this.productoRepository = productoRepository;
        this.coleccionRepository = coleccionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> listAll() {
        return (List<Producto>) productoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Producto findById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Producto save(ProductoCreateDto productoCreateDto) {
        Coleccion coleccion = coleccionRepository.findById(productoCreateDto.getIdColeccion()).orElseThrow();
        Producto producto = Producto.builder()
                .nombreProducto(productoCreateDto.getNombreProducto())
                .precioProducto(productoCreateDto.getPrecioProducto())
                .tamanoProducto(productoCreateDto.getTamanoProducto())
                .beneficiosProducto(productoCreateDto.getBeneficiosProducto())
                .imagenProducto(productoCreateDto.getImagenProducto())
                .stockProducto(productoCreateDto.getStockProducto())
                .coleccion(coleccion)
                .build();
        return productoRepository.save(producto);
    }

    @Override
    @Transactional
    public Producto update(ProductoUpdateDto productoUpdateDto) {
        Producto producto = productoRepository.findById(productoUpdateDto.getIdProducto()).orElseThrow();

        Coleccion coleccion = coleccionRepository.findById(productoUpdateDto.getIdColeccion()).orElseThrow();

        producto.setNombreProducto(productoUpdateDto.getNombreProducto());
        producto.setPrecioProducto(productoUpdateDto.getPrecioProducto());
        producto.setTamanoProducto(productoUpdateDto.getTamanoProducto());
        producto.setBeneficiosProducto(productoUpdateDto.getBeneficiosProducto());
        producto.setImagenProducto(productoUpdateDto.getImagenProducto());
        producto.setStockProducto(productoUpdateDto.getStockProducto());
        producto.setColeccion(coleccion);

        return productoRepository.save(producto);
    }


    @Override
    @Transactional
    public void delete(Producto producto) {
        productoRepository.delete(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return productoRepository.existsById(id);
    }
}
