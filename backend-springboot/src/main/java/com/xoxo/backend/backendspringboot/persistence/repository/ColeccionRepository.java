package com.xoxo.backend.backendspringboot.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.xoxo.backend.backendspringboot.persistence.entity.Coleccion;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ColeccionRepository extends JpaRepository<Coleccion, Long> {
    @Query("SELECT c FROM Coleccion c WHERE c.nombreColeccion = ?1")
    Coleccion getColeccionByName(String nombre);

}
