package com.xoxo.backend.backendspringboot.persistence.repository;

import com.xoxo.backend.backendspringboot.persistence.entity.DetalleCarrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleCarritoRepository extends JpaRepository<DetalleCarrito, Long> {
}
