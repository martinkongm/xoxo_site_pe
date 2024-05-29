package com.xoxo.backend.backendspringboot.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.xoxo.backend.backendspringboot.persistence.entity.Producto;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long>{

}
