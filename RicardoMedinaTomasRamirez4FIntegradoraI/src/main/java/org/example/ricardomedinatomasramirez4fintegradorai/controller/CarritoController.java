package org.example.ricardomedinatomasramirez4fintegradorai.controller;

import org.example.ricardomedinatomasramirez4fintegradorai.dao.ICarritoRepository;
import org.example.ricardomedinatomasramirez4fintegradorai.model.CarritoProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carrito")
public class CarritoController {
    @Autowired
    private ICarritoRepository carritoRepository;

    @PostMapping("/agregar")
    public ResponseEntity<CarritoProducto> agregarItemCarro(@RequestBody CarritoProducto itemAdd) {
        CarritoProducto carritoProducto = carritoRepository.save(itemAdd);
        return ResponseEntity.ok(carritoProducto);
    }
}