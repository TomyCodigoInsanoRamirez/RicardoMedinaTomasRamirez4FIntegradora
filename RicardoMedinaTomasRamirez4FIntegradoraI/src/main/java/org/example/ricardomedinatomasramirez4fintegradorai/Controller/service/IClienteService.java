package org.example.ricardomedinatomasramirez4fintegradorai.Controller.service;

import org.springframework.http.ResponseEntity;

public interface IClienteService {
    ResponseEntity<PersonaResponseRest> crear(Persona persona);

}
