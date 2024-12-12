package org.example.ricardomedinatomasramirez4fintegradorai.dao;

import org.example.ricardomedinatomasramirez4fintegradorai.model.CarritoProducto;
import org.example.ricardomedinatomasramirez4fintegradorai.response.CarritoProducto.CarritoProductoResponse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;

public interface ICarritoRepository extends CrudRepository<CarritoProducto, Long> {
    //ResponseEntity<CarritoProductoResponse> eliminar();
}
