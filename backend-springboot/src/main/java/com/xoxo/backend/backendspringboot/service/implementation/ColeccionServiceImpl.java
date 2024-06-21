package com.xoxo.backend.backendspringboot.service.implementation;

import com.xoxo.backend.backendspringboot.persistence.entity.Producto;
import com.xoxo.backend.backendspringboot.persistence.repository.ColeccionRepository;
import com.xoxo.backend.backendspringboot.persistence.repository.ProductoRepository;
import com.xoxo.backend.backendspringboot.presentation.dto.coleccion.ColeccionCreateDto;
import com.xoxo.backend.backendspringboot.persistence.entity.Coleccion;
import com.xoxo.backend.backendspringboot.presentation.dto.coleccion.ColeccionUpdateDto;
import com.xoxo.backend.backendspringboot.service.interfaces.ColeccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ColeccionServiceImpl implements ColeccionService {

    private final ColeccionRepository coleccionDao;
    @Autowired
    private ProductoRepository productoRepository;

    public ColeccionServiceImpl(ColeccionRepository coleccionRepository) {
        this.coleccionDao = coleccionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Coleccion> listAll() {
        return coleccionDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Coleccion findById(Long id) {
        return coleccionDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Coleccion save(ColeccionCreateDto coleccionCreateDto) {
        Coleccion coleccion = Coleccion.builder()
                .nombreColeccion(coleccionCreateDto.getNombreColeccion())
                .productosColeccion(null)
                .build();
        return coleccionDao.save(coleccion);
    }

    @Override
    @Transactional
    public Coleccion update(ColeccionUpdateDto coleccionUpdateDto) {
        Coleccion coleccion = coleccionDao.findById(coleccionUpdateDto.getIdColeccion()).orElseThrow();
        /*
        List<Producto> productos = new ArrayList<>();
        for (Producto p : coleccionUpdateDto.getProductosColeccion()) {
            Producto addProduct = productoRepository.findById(p.getIdProducto()).orElseThrow();
            productos.add(addProduct);
        }*/
        coleccion.setNombreColeccion(coleccionUpdateDto.getNombreColeccion());
        //coleccion.setProductosColeccion(productos);
        return coleccion;
    }

    @Override
    @Transactional
    public void delete(Coleccion coleccion) {
        coleccionDao.delete(coleccion);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return coleccionDao.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Coleccion getColeccionByName(String nombre) {
        return coleccionDao.getColeccionByName(nombre);
    }
}
