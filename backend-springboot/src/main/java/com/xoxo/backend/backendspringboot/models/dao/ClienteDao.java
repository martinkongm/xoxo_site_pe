package com.xoxo.backend.backendspringboot.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.xoxo.backend.backendspringboot.models.entities.Cliente;

public interface ClienteDao extends CrudRepository<Cliente, Integer>{

}
