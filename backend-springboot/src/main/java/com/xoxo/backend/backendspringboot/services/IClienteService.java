package com.xoxo.backend.backendspringboot.services;


import java.util.List;

import com.xoxo.backend.backendspringboot.models.dto.ClienteDto;
import com.xoxo.backend.backendspringboot.models.entities.Cliente;

public interface IClienteService {

    List<Cliente> listAll();

    Cliente save(ClienteDto cliente);

    Cliente findById(Integer id);

    void delete(Cliente cliente);

    boolean existsById(Integer id);
}
