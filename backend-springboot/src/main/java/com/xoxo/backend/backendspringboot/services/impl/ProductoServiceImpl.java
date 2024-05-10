package com.xoxo.backend.backendspringboot.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xoxo.backend.backendspringboot.models.dao.ProductoDao;
import com.xoxo.backend.backendspringboot.models.dto.ProductoDto;
import com.xoxo.backend.backendspringboot.models.entities.Producto;
import com.xoxo.backend.backendspringboot.services.IProductoService;

@Service
public class ProductoServiceImpl implements IProductoService{

    private ProductoDao productoDao;

    public ProductoServiceImpl(ProductoDao productoDao) {
        this.productoDao = productoDao;
    }

    @Override
    public List<ProductoDto> listAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listAll'");
    }

    @Override
    public Producto save(ProductoDto cliente) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public ProductoDto findById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public void delete(ProductoDto cliente) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public boolean existsById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existsById'");
    }

}
