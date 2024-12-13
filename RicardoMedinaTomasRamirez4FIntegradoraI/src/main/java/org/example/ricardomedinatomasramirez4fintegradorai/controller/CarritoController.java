package org.example.ricardomedinatomasramirez4fintegradorai.controller;

import org.example.ricardomedinatomasramirez4fintegradorai.dao.ICarritoProductoRepositoryCustom;
import org.example.ricardomedinatomasramirez4fintegradorai.dao.ICarritoRepository;
import org.example.ricardomedinatomasramirez4fintegradorai.model.CarritoProducto;
import org.example.ricardomedinatomasramirez4fintegradorai.model.Cliente;
import org.example.ricardomedinatomasramirez4fintegradorai.model.Producto;
import org.example.ricardomedinatomasramirez4fintegradorai.response.CarritoProducto.CarritoProductoResponse;
import org.example.ricardomedinatomasramirez4fintegradorai.service.ICarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Stack;

@RestController
@RequestMapping("/carrito")
public class CarritoController {
    Stack<CarritoProducto> elementosEliminados = new Stack<>();
    @Autowired
    private ICarritoRepository carritoRepository;
    private ICarritoProductoRepositoryCustom carritoRepositoryCustom;
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

    @Transactional
    @GetMapping("/carrito/{id}")
    public ResponseEntity<CarritoProducto> getItemCliente(@PathVariable Long id) {


        //System.out.println("Id Cliente "+carritoRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()).getBody().getCliente().getId());
        //System.out.println("Nombre cliente:"+carritoRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()).getBody().getCliente().getPrimerNombre());
        //System.out.println("Id producto: "+carritoRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()).getBody().getProducto().getId());
        //System.out.println("Nomrbe producto"+carritoRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()).getBody().getProducto().getNombre());

        return carritoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/eliminar/{id}")
    public ResponseEntity<CarritoProductoResponse> eliminar(@PathVariable Long idProducto, Long idCliente) {

        CarritoProducto elementoEliminado = new CarritoProducto();
        //elementoEliminado.setId(carritoRepository.findByIdProductoAndIdCliente(idProducto,idCliente).get(0).getId());
        elementoEliminado.setCantidad(carritoRepository.findByIdProductoAndIdCliente(idProducto,idCliente).get(0).getCantidad());
        elementoEliminado.setCliente(carritoRepository.findByIdProductoAndIdCliente(idProducto,idCliente).get(0).getCliente());
        elementoEliminado.setProducto(carritoRepository.findByIdProductoAndIdCliente(idProducto,idCliente).get(0).getProducto());
        elementosEliminados.push(elementoEliminado);

        return carritoService.eliminar(carritoRepository.findByIdProductoAndIdCliente(idProducto,idCliente).get(0).getId());
    }

    @PostMapping("/deshacer")
    public ResponseEntity<CarritoProducto> deshacer() {

        CarritoProducto itemReAdd = new CarritoProducto();

        CarritoProducto carritoProducto = carritoRepository.save(elementosEliminados.pop());
        return ResponseEntity.ok(carritoProducto);
    }
}
