package org.example.ricardomedinatomasramirez4fintegradorai.dao;

import org.example.ricardomedinatomasramirez4fintegradorai.model.CarritoProducto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICarritoRepositoryCustom extends CrudRepository<CarritoProducto, Long> {

    @Query("SELECT c FROM CarritoProducto c WHERE c.cliente.id = :id")
    List<CarritoProducto> buscarPorId(@Param("id") String id);


}
