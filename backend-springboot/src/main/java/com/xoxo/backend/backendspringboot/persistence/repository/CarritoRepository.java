package com.xoxo.backend.backendspringboot.persistence.repository;

import com.xoxo.backend.backendspringboot.persistence.entity.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {
}
