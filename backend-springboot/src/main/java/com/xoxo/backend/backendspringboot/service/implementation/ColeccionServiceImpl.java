package com.xoxo.backend.backendspringboot.service.implementation;

import com.xoxo.backend.backendspringboot.persistence.repository.ColeccionRepository;
import com.xoxo.backend.backendspringboot.presentation.dto.coleccion.ColeccionCreateDto;
import com.xoxo.backend.backendspringboot.presentation.dto.coleccion.ColeccionResponseDto;
import com.xoxo.backend.backendspringboot.persistence.entity.Coleccion;
import com.xoxo.backend.backendspringboot.presentation.dto.coleccion.ColeccionUpdateDto;
import com.xoxo.backend.backendspringboot.service.interfaces.ColeccionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ColeccionServiceImpl implements ColeccionService {

    private final ColeccionRepository coleccionDao;

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
                .productosColeccion(coleccionCreateDto.getProductosColeccion())
                .build();
        return coleccionDao.save(coleccion);
    }

    @Override
    public Coleccion update(ColeccionUpdateDto coleccionUpdateDto) {
        Coleccion coleccion = coleccionDao.findById(coleccionUpdateDto.getIdColeccion()).orElseThrow();
        coleccion.setNombreColeccion(coleccionUpdateDto.getNombreColeccion());
        coleccion.setProductosColeccion(coleccionUpdateDto.getProductosColeccion());
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
