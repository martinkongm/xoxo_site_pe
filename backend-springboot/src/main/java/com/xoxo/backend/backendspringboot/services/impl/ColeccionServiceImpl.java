package com.xoxo.backend.backendspringboot.services.impl;

import com.xoxo.backend.backendspringboot.models.dao.ColeccionDao;
import com.xoxo.backend.backendspringboot.models.dto.ColeccionDto;
import com.xoxo.backend.backendspringboot.models.entities.Coleccion;
import com.xoxo.backend.backendspringboot.services.IColeccionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ColeccionServiceImpl implements IColeccionService {

    private ColeccionDao coleccionDao;

    public ColeccionServiceImpl(ColeccionDao coleccionDao) {
        this.coleccionDao = coleccionDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Coleccion> listAll() {
        return (List<Coleccion>) coleccionDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Coleccion findById(Long id) {
        return coleccionDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Coleccion save(ColeccionDto coleccionDto) {
        Coleccion coleccion = Coleccion.builder()
                .idColeccion(coleccionDto.getIdColeccion())
                .nombreColeccion(coleccionDto.getNombreColeccion())
                .productosColeccion(coleccionDto.getProductosColeccion())
                .build();
        return coleccionDao.save(coleccion);
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
}
