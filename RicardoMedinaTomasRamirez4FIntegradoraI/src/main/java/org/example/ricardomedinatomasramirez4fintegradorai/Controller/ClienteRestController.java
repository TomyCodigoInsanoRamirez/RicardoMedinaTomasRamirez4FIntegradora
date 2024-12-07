package org.example.ricardomedinatomasramirez4fintegradorai.Controller;


import org.example.ricardomedinatomasramirez4fintegradorai.model.Cliente;
import org.example.ricardomedinatomasramirez4fintegradorai.response.Cliente.ClienteResponseRest;
import org.example.ricardomedinatomasramirez4fintegradorai.service.ClienteServiceImpl;
import org.example.ricardomedinatomasramirez4fintegradorai.service.IClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class ClienteRestController  {

    @Autowired
    private IClienteService clienteService;


    @PostMapping("/agregarCliente")
    public ResponseEntity<ClienteResponseRest> crear(@RequestBody Cliente request) {
        ResponseEntity<ClienteResponseRest> response = clienteService.crear(request);
        return response;
    }
}

