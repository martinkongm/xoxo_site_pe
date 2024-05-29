package com.xoxo.backend.backendspringboot.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.xoxo.backend.backendspringboot.persistence.entity.Coleccion;
import org.springframework.stereotype.Repository;

@Repository
public interface ColeccionRepository extends CrudRepository<Coleccion, Long>{

}
