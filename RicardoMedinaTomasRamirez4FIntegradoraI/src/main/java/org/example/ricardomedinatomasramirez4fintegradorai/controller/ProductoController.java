package org.example.ricardomedinatomasramirez4fintegradorai.controller;

import org.example.ricardomedinatomasramirez4fintegradorai.dao.IClienteRepository;
import org.example.ricardomedinatomasramirez4fintegradorai.dao.IProductoRepository;
import org.example.ricardomedinatomasramirez4fintegradorai.model.Cliente;
import org.example.ricardomedinatomasramirez4fintegradorai.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private IProductoRepository productoRepository;

    @PostMapping("/agregarProducto")
    public ResponseEntity<Producto> agregarProducto(@RequestBody Producto prodcuto) {
        Producto nuevoProducto = productoRepository.save(prodcuto);
        return ResponseEntity.ok(nuevoProducto);
    }
}