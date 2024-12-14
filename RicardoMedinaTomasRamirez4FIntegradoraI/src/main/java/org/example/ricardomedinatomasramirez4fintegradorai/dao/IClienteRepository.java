package org.example.ricardomedinatomasramirez4fintegradorai.dao;

import org.example.ricardomedinatomasramirez4fintegradorai.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface IClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findTopByOrderByIdDesc(); // Método para obtener el último cliente
}

