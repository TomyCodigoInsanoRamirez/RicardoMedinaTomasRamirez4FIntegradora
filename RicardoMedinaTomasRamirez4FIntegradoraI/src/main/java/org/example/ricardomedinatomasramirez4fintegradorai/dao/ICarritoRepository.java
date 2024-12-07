package org.example.ricardomedinatomasramirez4fintegradorai.dao;

import org.example.ricardomedinatomasramirez4fintegradorai.model.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ICarritoRepository extends CrudRepository<Cliente, Long> {
}
