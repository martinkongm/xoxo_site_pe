package com.xoxo.backend.backendspringboot.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.xoxo.backend.backendspringboot.persistence.entity.Pedido;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Long>{

}
