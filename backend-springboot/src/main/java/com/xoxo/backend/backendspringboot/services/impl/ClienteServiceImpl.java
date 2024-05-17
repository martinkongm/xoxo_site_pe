package com.xoxo.backend.backendspringboot.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xoxo.backend.backendspringboot.models.dao.ClienteDao;
import com.xoxo.backend.backendspringboot.models.dto.ClienteDto;
import com.xoxo.backend.backendspringboot.models.entities.Cliente;
import com.xoxo.backend.backendspringboot.services.IClienteService;

@Service
public class ClienteServiceImpl implements IClienteService{

    private final ClienteDao clienteDao;

    public ClienteServiceImpl(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    @Transactional
    @Override
    public Cliente save(ClienteDto clienteDto) {
        Cliente cliente = Cliente.builder()
                                .idCliente(clienteDto.getIdCliente())
                                .nombre(clienteDto.getNombre())
                                .apellido(clienteDto.getApellido())
                                .correo(clienteDto.getCorreo())
                                .fechaRegistro(clienteDto.getFechaRegistro())
                                .build();
        return clienteDao.save(cliente);
    }

    @Transactional(readOnly = true)
    @Override
    public Cliente findById(Integer id) {
        return clienteDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Cliente cliente) {
        clienteDao.delete(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Integer id) {
        return clienteDao.existsById(id);    
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> listAll() {
        return (List<Cliente>) clienteDao.findAll();
    }
}
