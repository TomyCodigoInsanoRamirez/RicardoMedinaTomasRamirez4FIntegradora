package org.example.ricardomedinatomasramirez4fintegradorai.service;

import org.example.ricardomedinatomasramirez4fintegradorai.dao.IClienteRepository;
import org.example.ricardomedinatomasramirez4fintegradorai.model.Cliente;
import org.example.ricardomedinatomasramirez4fintegradorai.response.Cliente.ClienteResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteServiceImpl implements IClienteService {
    private static final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);

    @Autowired
    private IClienteRepository clienteDao;

    @Override
    public ResponseEntity<ClienteResponseRest> crear(Cliente persona) {
        log.info("Inicio de la creación de un cliente");
        log.debug("Datos recibidos: {}", persona);

        ClienteResponseRest response = new ClienteResponseRest();
        List<Cliente> listaCliente = new ArrayList<>();

        try {
            Cliente clienteGuardado = clienteDao.save(persona);
            if (clienteGuardado != null) {
                listaCliente.add(clienteGuardado);
                response.getClienteResponse().setClientes(listaCliente);
                response.setMetada("Respuesta OK", "00", "CREACIÓN EXITOSA");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                log.warn("No se pudo agregar el cliente");
                response.setMetada("Error", "-1", "Cliente no creado");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            log.error("Error al crear el cliente", e);
            response.setMetada("Error", "-1", "Error al crear el cliente");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
