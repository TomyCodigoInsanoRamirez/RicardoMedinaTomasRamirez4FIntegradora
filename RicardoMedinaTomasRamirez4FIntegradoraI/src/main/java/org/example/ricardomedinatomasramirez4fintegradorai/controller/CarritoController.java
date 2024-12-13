package org.example.ricardomedinatomasramirez4fintegradorai.controller;

import org.example.ricardomedinatomasramirez4fintegradorai.dao.ICarritoRepository;
import org.example.ricardomedinatomasramirez4fintegradorai.dao.ICarritoRepositoryCustom;
import org.example.ricardomedinatomasramirez4fintegradorai.model.CarritoProducto;
import org.example.ricardomedinatomasramirez4fintegradorai.response.CarritoProducto.CarritoProductoResponse;
import org.example.ricardomedinatomasramirez4fintegradorai.service.ICarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/carrito")
public class CarritoController {
    @Autowired
    private ICarritoRepository carritoRepository;
    @Autowired
    private ICarritoRepositoryCustom carritoRepositoryCustom;
    private final ICarritoService carritoService;

    @Autowired
    public CarritoController(ICarritoService carritoService) {
        this.carritoService = carritoService;
    }


    @PostMapping("/agregar")
    public ResponseEntity<CarritoProducto> agregarItemCarro(@RequestBody CarritoProducto itemAdd) {
        CarritoProducto carritoProducto = carritoRepository.save(itemAdd);
        return ResponseEntity.ok(carritoProducto);
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<List> getItemCliente(@PathVariable Long id) {
        List carritoProducto = carritoRepositoryCustom.buscarPorId(id.toString());
        return ResponseEntity.ok(carritoProducto);
    }



    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<CarritoProductoResponse> eliminar(@PathVariable Long id) {
        ResponseEntity<CarritoProductoResponse> response = carritoService.eliminar(id);
        return response;
    }

}