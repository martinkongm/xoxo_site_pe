package com.xoxo.backend.backendspringboot.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.xoxo.backend.backendspringboot.models.entities.Producto;

public interface ProductoDao extends CrudRepository<Producto, Integer>{

}
