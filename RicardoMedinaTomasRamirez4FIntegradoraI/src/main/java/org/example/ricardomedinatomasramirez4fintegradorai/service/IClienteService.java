package org.example.ricardomedinatomasramirez4fintegradorai.service;

import org.example.ricardomedinatomasramirez4fintegradorai.model.Cliente;
import org.example.ricardomedinatomasramirez4fintegradorai.response.Cliente.ClienteResponseRest;
import org.springframework.http.ResponseEntity;

public interface IClienteService {
    ResponseEntity<ClienteResponseRest> crear(Cliente cliente);
}
