package org.example.ricardomedinatomasramirez4fintegradorai.service;

import org.example.ricardomedinatomasramirez4fintegradorai.response.CarritoProducto.CarritoProductoResponse;
import org.springframework.http.ResponseEntity;

public interface ICarritoService {
    ResponseEntity<CarritoProductoResponse> eliminar(Long id, Long id);
    ResponseEntity<CarritoProductoResponse> deshacerEliminacion(Long id);
}
