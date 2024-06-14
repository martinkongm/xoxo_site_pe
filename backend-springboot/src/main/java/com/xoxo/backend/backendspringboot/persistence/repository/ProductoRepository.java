package com.xoxo.backend.backendspringboot.persistence.repository;

import com.xoxo.backend.backendspringboot.persistence.entity.Coleccion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.xoxo.backend.backendspringboot.persistence.entity.Producto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long>{

    @Query("SELECT p FROM Producto p WHERE p.coleccion = ?1")
    List<Producto> findProductosByColeccion(Coleccion coleccion);

}
