package com.xoxo.backend.backendspringboot.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.xoxo.backend.backendspringboot.models.entities.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Long>{

}
