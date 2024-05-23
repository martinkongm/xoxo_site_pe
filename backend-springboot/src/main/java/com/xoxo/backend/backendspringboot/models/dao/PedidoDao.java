package com.xoxo.backend.backendspringboot.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.xoxo.backend.backendspringboot.models.entities.Pedido;

public interface PedidoDao extends CrudRepository<Pedido, Long>{

}
