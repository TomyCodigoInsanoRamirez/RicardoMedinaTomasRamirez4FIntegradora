package org.example.ricardomedinatomasramirez4fintegradorai.service;

import org.example.ricardomedinatomasramirez4fintegradorai.response.CarritoProducto.CarritoProductoResponse;
import org.example.ricardomedinatomasramirez4fintegradorai.response.Producto.ProductoResponse;
import org.springframework.http.ResponseEntity;

public interface ICarritoService {
    //ResponseEntity<CarritoProductoResponse> buscarTodo();
    ResponseEntity<CarritoProductoResponse> eliminar(Long id);
    ResponseEntity<CarritoProductoResponse> deshacerEliminacion();
}

